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

public class DeleteCommentCommand extends CommentCommand {

    private static final String NO_TITLE_MESSAGE = "No such title. To delete comment, "
            + "type: " + DeleteCommand.COMMAND_WORD + " INDEX c- t:<TITLE>";

    private String personType;

    /**
     * Creates delete comment command object
     * @param index
     * @param commentPersonDescriptor
     * @param personType
     */
    public DeleteCommentCommand(Index index, CommentCommand.CommentPersonDescriptor commentPersonDescriptor,
                                String personType) {
        super(index, commentPersonDescriptor);
        this.personType = personType;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<? extends Person> lastShownList;
        if (this.personType.equals("applicant")) {
            lastShownList = model.getFilteredApplicantList();
        } else {
            lastShownList = model.getFilteredStaffList();
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

            if (editedPerson instanceof Staff) {
                model.setStaff((Staff) personToEdit, (Staff) editedPerson);
                model.updateFilteredStaffList(PREDICATE_SHOW_ALL_STAFFS);
            } else {
                model.setApplicant((Applicant) personToEdit, (Applicant) editedPerson);
                model.updateFilteredApplicantList(PREDICATE_SHOW_ALL_APPLICANTS);
            }
            return new CommandResult(String.format(MESSAGE_DELETE_COMMENT_SUCCESS, editedPerson));
        } catch (CommandException e) {
            throw new CommandException(NO_TITLE_MESSAGE);
        }
    }

    /**
     * Creates and returns a {@code Person} with
     * the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Person createDeleteEditedPerson(Person personToEdit,
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
            return new Applicant(updatedName, updatedPhone, updatedEmail, updatedAddress,
                    updatedTags, updatedComments, interviewDate, applicationStatus);
        }
        return new Person(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedTags, updatedComments);
    }

    public String getPersonType() {
        return this.personType;
    }

}
