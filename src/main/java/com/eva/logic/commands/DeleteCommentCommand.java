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
import com.eva.model.person.applicant.application.Application;
import com.eva.model.person.staff.Staff;
import com.eva.model.person.staff.leave.Leave;
import com.eva.model.tag.Tag;

public class DeleteCommentCommand extends CommentCommand {

    public static final String COMMAND_WORD = "delc";
    public static final String MESSAGE_DELETE_COMMENT_SUCCESS_STAFF = "Deleted comment with "
            + "title '%1$s' from Staff: %2$s";
    public static final String MESSAGE_DELETE_COMMENT_SUCCESS_APPLICANT = "Deleted comment with "
            + "title '%1$s' from Applicant: %2$s";

    public static final String MESSAGE_DELETECOMMENT_USAGE = "Format for this command: \n"
            + COMMAND_WORD + " INDEX c/ ti/TITLE";

    private static final String NO_TITLE_MESSAGE = "No such title. To delete comment, "
            + "Format: " + DeleteCommand.COMMAND_WORD + " INDEX c/ t/TITLE";



    /**
     * Creates delete comment command object
     * @param index
     * @param commentPersonDescriptor
     */
    public DeleteCommentCommand(Index index, CommentCommand.CommentPersonDescriptor commentPersonDescriptor) {
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

        if (panelState.equals(APPLICANT_PROFILE)) {
            if (!model.getCurrentViewApplicant().getIndex().equals(index)) {
                throw new CommandException("Please go to applicant with keyed in index"
                        + " or applicant list panel with 'list a-'");
            }
        } else if (panelState.equals(STAFF_PROFILE)) {
            if (!model.getCurrentViewStaff().getIndex().equals(index)) {
                throw new CommandException("Please go to staff profile with keyed in index"
                        + " or staff list panel with 'list s-'");
            }
        }

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        try {
            Person editedPerson = createDeleteEditedPerson(personToEdit, commentPersonDescriptor);
            if (!personToEdit.isSamePerson(editedPerson) && model.hasPerson(editedPerson)) {
                throw new CommandException(MESSAGE_DUPLICATE_PERSON);
            }
            switch (panelState) {
            case STAFF_LIST:
                model.setStaff((Staff) personToEdit, (Staff) editedPerson);
                model.updateFilteredStaffList(PREDICATE_SHOW_ALL_STAFFS);
                return new CommandResult(String.format(MESSAGE_DELETE_COMMENT_SUCCESS_STAFF,
                        commentPersonDescriptor.getCommentTitle() , editedPerson),
                        false, false, true);
            case APPLICANT_LIST:
                model.setApplicant((Applicant) personToEdit, (Applicant) editedPerson);
                model.updateFilteredApplicantList(PREDICATE_SHOW_ALL_APPLICANTS);
                return new CommandResult(String.format(MESSAGE_DELETE_COMMENT_SUCCESS_APPLICANT,
                        commentPersonDescriptor.getCommentTitle() , editedPerson),
                        false, false, true);
            case STAFF_PROFILE:
                model.setStaff((Staff) personToEdit, (Staff) editedPerson);
                model.updateFilteredStaffList(PREDICATE_SHOW_ALL_STAFFS);
                Staff staffToView = (Staff) lastShownList.get(index.getZeroBased());
                model.setCurrentViewStaff(new CurrentViewStaff(staffToView, index));
                return new CommandResult(String.format(MESSAGE_DELETE_COMMENT_SUCCESS_STAFF,
                        commentPersonDescriptor.getCommentTitle(), editedPerson),
                        false, false, true);
            case APPLICANT_PROFILE:
                model.setApplicant((Applicant) personToEdit, (Applicant) editedPerson);
                model.updateFilteredApplicantList(PREDICATE_SHOW_ALL_APPLICANTS);
                Applicant applicantToView = (Applicant) lastShownList.get(index.getZeroBased());
                model.setCurrentViewApplicant(new CurrentViewApplicant(applicantToView, index));
                return new CommandResult(String.format(MESSAGE_DELETE_COMMENT_SUCCESS_APPLICANT,
                        commentPersonDescriptor.getCommentTitle(), editedPerson),
                        false, false, true);
            default:
                throw new CommandException("Unknown Panel");
            }
        } catch (CommandException e) {
            throw new CommandException(NO_TITLE_MESSAGE);
        }
    }

    /**
     * Creates and returns a {@code Person} with
     * the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    public static Person createDeleteEditedPerson(Person personToEdit,
                                                   CommentCommand.CommentPersonDescriptor commentPersonDescriptor)
            throws CommandException {
        assert personToEdit != null;
        boolean hasTitle = false;

        Name updatedName = commentPersonDescriptor.getName().orElse(personToEdit.getName());
        Phone updatedPhone = commentPersonDescriptor.getPhone().orElse(personToEdit.getPhone());
        Email updatedEmail = commentPersonDescriptor.getEmail().orElse(personToEdit.getEmail());
        Address updatedAddress = commentPersonDescriptor.getAddress().orElse(personToEdit.getAddress());
        Set<Tag> updatedTags = commentPersonDescriptor.getTags().orElse(personToEdit.getTags());
        Set<Comment> updatedCommentsCommands = commentPersonDescriptor.getComments();
        Set<Comment> updatedComments = personToEdit.getComments();
        for (Comment comment: updatedCommentsCommands) {
            for (Comment commentToDelete : updatedComments) {
                if (commentToDelete.getTitle().equals(comment.getTitle())) {
                    hasTitle = true;
                    updatedComments.remove(commentToDelete);
                    break;
                }
            }
        }
        if (!hasTitle) {
            throw new CommandException(NO_TITLE_MESSAGE);
        }
        if (personToEdit instanceof Staff) {
            Set<Leave> updatedLeaves = ((Staff) personToEdit).getLeaves();
            return new Staff(updatedName, updatedPhone, updatedEmail,
                    updatedAddress, updatedTags, updatedLeaves, updatedComments);
        } else if (personToEdit instanceof Applicant) {
            ApplicationStatus applicationStatus = ((Applicant) personToEdit).getApplicationStatus();
            Optional<InterviewDate> interviewDate = ((Applicant) personToEdit).getInterviewDate();
            Application application = ((Applicant) personToEdit).getApplication();
            return new Applicant(updatedName, updatedPhone, updatedEmail, updatedAddress,
                    updatedTags, updatedComments, interviewDate, applicationStatus, application);
        } else {
            throw new CommandException("Invalid.");
        }
    }
}
