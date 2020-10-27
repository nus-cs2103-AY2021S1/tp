package com.eva.logic.commands;

import static com.eva.model.Model.PREDICATE_SHOW_ALL_APPLICANTS;
import static com.eva.model.Model.PREDICATE_SHOW_ALL_STAFFS;
import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.eva.commons.core.Messages;
import com.eva.commons.core.index.Index;
import com.eva.logic.commands.exceptions.CommandException;
import com.eva.model.Model;
import com.eva.model.comment.Comment;
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

    public static final String COMMAND_WORD = "addcomment";

    public static final String MISSING_PERSONTYPE_MESSAGE = "Need to specify applicant or staff \n"
            + "e.g: 'addcomment s- ...' or 'addcomment a- ...'";

    public static final String MESSAGE_ADDCOMMENT_USAGE = "Format for this command: \n"
            + COMMAND_WORD + " INDEX <s-/a-> ti/TITLE d/DATE desc/DESCRIPTION";

    private String personType;

    /**
     * Creates an addcommentcommand object
     * @param index
     * @param commentPersonDescriptor
     * @param personType
     */
    public AddCommentCommand(Index index, CommentCommand.CommentPersonDescriptor commentPersonDescriptor,
                             String personType) {
        super(index, commentPersonDescriptor);
        this.personType = personType;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        //for now is staff because we only working with staff for now
        List<? extends Person> lastShownList;
        if (this.personType.equals("applicant")) {
            lastShownList = model.getFilteredApplicantList();
        } else {
            lastShownList = model.getFilteredStaffList();
        }

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        //for now staff
        Person personToEdit = lastShownList.get(index.getZeroBased());
        Person editedPerson = createAddEditedPerson(personToEdit, commentPersonDescriptor);

        if (!personToEdit.isSamePerson(editedPerson) && model.hasPerson(editedPerson)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        if (editedPerson instanceof Staff) {
            model.setStaff((Staff) personToEdit, (Staff) editedPerson);
            model.updateFilteredStaffList(PREDICATE_SHOW_ALL_STAFFS);
        } else {
            model.setApplicant((Applicant) personToEdit, (Applicant) editedPerson);
            model.updateFilteredApplicantList(PREDICATE_SHOW_ALL_APPLICANTS);
        }
        return new CommandResult(String.format(MESSAGE_ADD_COMMENT_SUCCESS, editedPerson));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Person createAddEditedPerson(Person personToEdit,
                                                CommentCommand.CommentPersonDescriptor commentPersonDescriptor) {
        assert personToEdit != null;

        Name updatedName = commentPersonDescriptor.getName().orElse(personToEdit.getName());
        Phone updatedPhone = commentPersonDescriptor.getPhone().orElse(personToEdit.getPhone());
        Email updatedEmail = commentPersonDescriptor.getEmail().orElse(personToEdit.getEmail());
        Address updatedAddress = commentPersonDescriptor.getAddress().orElse(personToEdit.getAddress());
        Set<Tag> updatedTags = commentPersonDescriptor.getTags().orElse(personToEdit.getTags());
        Set<Comment> updatedCommentsCommands = commentPersonDescriptor.getComments();
        Set<Comment> updatedComments = personToEdit.getComments();
        for (Comment comment: updatedCommentsCommands) {
            updatedComments.add(new Comment(comment.getDate(),
                    comment.getDescription(), comment.getTitle().getTitleDescription()));
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


}
