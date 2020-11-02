package com.eva.logic.commands;

import static com.eva.commons.core.PanelState.APPLICANT_LIST;
import static com.eva.commons.core.PanelState.APPLICANT_PROFILE;
import static com.eva.commons.core.PanelState.STAFF_LIST;
import static com.eva.commons.core.PanelState.STAFF_PROFILE;
import static com.eva.model.Model.PREDICATE_SHOW_ALL_APPLICANTS;
import static com.eva.model.Model.PREDICATE_SHOW_ALL_STAFFS;
import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.eva.commons.core.Messages;
import com.eva.commons.core.PanelState;
import com.eva.commons.core.index.Index;
import com.eva.logic.commands.exceptions.CommandException;
import com.eva.model.Model;
import com.eva.model.comment.Comment;
import com.eva.model.current.view.CurrentViewApplicant;
import com.eva.model.current.view.CurrentViewStaff;
import com.eva.model.person.Address;
import com.eva.model.person.Email;
import com.eva.model.person.Name;
import com.eva.model.person.Person;
import com.eva.model.person.Phone;
import com.eva.model.person.applicant.Applicant;
import com.eva.model.person.applicant.ApplicationStatus;
import com.eva.model.person.applicant.InterviewDate;
import com.eva.model.person.staff.Staff;
import com.eva.model.person.staff.leave.Leave;
import com.eva.model.tag.Tag;

public class AddCommentCommand extends CommentCommand {

    public static final String COMMAND_WORD = "addc";

    public static final String MESSAGE_ADDCOMMENT_USAGE = "Format for this command: \n"
            + COMMAND_WORD + " INDEX c/ ti/TITLE d/DATE desc/DESCRIPTION";
    public static final String MESSAGE_DUPLICATE_COMMENT = "Duplicate comment titles not allowed";


    /**
     * Creates an addcommentcommand object
     * @param index
     * @param commentPersonDescriptor
     */
    public AddCommentCommand(Index index, CommentCommand.CommentPersonDescriptor commentPersonDescriptor) {
        super(index, commentPersonDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        PanelState panelState = model.getPanelState();
        List<? extends Person> lastShownList;
        if (panelState.equals(APPLICANT_LIST) || panelState.equals(APPLICANT_PROFILE)) {
            lastShownList = model.getFilteredApplicantList();
        } else if (panelState.equals(STAFF_LIST) || panelState.equals(STAFF_PROFILE)) {
            lastShownList = model.getFilteredStaffList();
        } else {
            throw new CommandException("Program spoil");
        }

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        //for now staff
        Person personToEdit = lastShownList.get(index.getZeroBased());
        try {
            Person editedPerson = createAddEditedPerson(personToEdit, commentPersonDescriptor);
            if (!personToEdit.isSamePerson(editedPerson) && model.hasPerson(editedPerson)) {
                throw new CommandException(MESSAGE_DUPLICATE_PERSON);
            }
            switch (panelState) {
            case STAFF_LIST:
                model.setStaff((Staff) personToEdit, (Staff) editedPerson);
                model.updateFilteredStaffList(PREDICATE_SHOW_ALL_STAFFS);
                break;
            case APPLICANT_LIST:
                model.setApplicant((Applicant) personToEdit, (Applicant) editedPerson);
                model.updateFilteredApplicantList(PREDICATE_SHOW_ALL_APPLICANTS);
                break;
            case STAFF_PROFILE:
                model.setStaff((Staff) personToEdit, (Staff) editedPerson);
                model.updateFilteredStaffList(PREDICATE_SHOW_ALL_STAFFS);
                Staff staffToView = (Staff) lastShownList.get(index.getZeroBased());
                model.setCurrentViewStaff(new CurrentViewStaff(staffToView, index));
                break;
            case APPLICANT_PROFILE:
                model.setApplicant((Applicant) personToEdit, (Applicant) editedPerson);
                model.updateFilteredApplicantList(PREDICATE_SHOW_ALL_APPLICANTS);
                Applicant applicantToView = (Applicant) lastShownList.get(index.getZeroBased());
                model.setCurrentViewApplicant(new CurrentViewApplicant(applicantToView));
                break;
            default:
                throw new CommandException("Unknown Panel");
            }
            return new CommandResult(String.format(MESSAGE_ADD_COMMENT_SUCCESS, editedPerson),
                    false, false, true);
        } catch (CommandException e) {
            throw new CommandException(e.getMessage());
        }
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Person createAddEditedPerson(Person personToEdit,
                                                CommentCommand.CommentPersonDescriptor commentPersonDescriptor)
            throws CommandException {
        assert personToEdit != null;

        Name updatedName = commentPersonDescriptor.getName().orElse(personToEdit.getName());
        Phone updatedPhone = commentPersonDescriptor.getPhone().orElse(personToEdit.getPhone());
        Email updatedEmail = commentPersonDescriptor.getEmail().orElse(personToEdit.getEmail());
        Address updatedAddress = commentPersonDescriptor.getAddress().orElse(personToEdit.getAddress());
        Set<Tag> updatedTags = commentPersonDescriptor.getTags().orElse(personToEdit.getTags());
        Set<Comment> updatedCommentsCommands = commentPersonDescriptor.getComments();
        Set<Comment> updatedComments = personToEdit.getComments();
        try {
            for (Comment comment : updatedCommentsCommands) {
                checkDuplicateComment(comment, updatedComments);
                updatedComments.add(new Comment(comment.getDate(),
                        comment.getDescription(), comment.getTitle().getTitleDescription()));
            }
        } catch (CommandException e) {
            throw new CommandException(e.getMessage());
        }

        if (personToEdit instanceof Staff) {
            Set<Leave> updatedLeaves = ((Staff) personToEdit).getLeaves();
            return new Staff(updatedName, updatedPhone, updatedEmail,
                    updatedAddress, updatedTags, updatedLeaves, updatedComments);
        } else if (personToEdit instanceof Applicant) {
            ApplicationStatus applicationStatus = ((Applicant) personToEdit).getApplicationStatus();
            Optional<InterviewDate> interviewDate = ((Applicant) personToEdit).getInterviewDate();
            return new Applicant(updatedName, updatedPhone, updatedEmail, updatedAddress,
                    updatedTags, updatedComments, interviewDate, applicationStatus);
        }
        return new Person(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedTags, updatedComments);
    }

    private static void checkDuplicateComment(Comment comment, Set<Comment> updatedComments) throws CommandException {
        for (Comment presentComment: updatedComments) {
            if (comment.getTitle().getTitleDescription().equals(presentComment.getTitle().getTitleDescription())) {
                throw new CommandException(MESSAGE_DUPLICATE_COMMENT);
            }
        }
    }
}
