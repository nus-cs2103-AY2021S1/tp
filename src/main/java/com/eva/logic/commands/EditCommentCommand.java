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

public class EditCommentCommand extends Command {

    public static final String COMMAND_WORD = "editcomment";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Person: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the eva database.";
    public static final String MESSAGE_EDIT_COMMENT_USAGE = "To edit comment, \n"
            + COMMAND_WORD + " INDEX <s-/a-> ti/TITLE_OF_COMMENT d/DATE_OF_COMMENT desc/NEW_DESCRITION";

    private String personType;
    private final Index index;
    private final EditCommand.EditPersonDescriptor editPersonDescriptor;

    /**
     * @param index                of the person in the filtered person list to edit
     * @param editPersonDescriptor details to edit the person with
     */
    public EditCommentCommand(Index index, EditCommand.EditPersonDescriptor editPersonDescriptor,
                       String personType) {
        requireNonNull(index);
        requireNonNull(editPersonDescriptor);

        this.index = index;
        this.editPersonDescriptor = new EditCommand.EditPersonDescriptor(editPersonDescriptor);
        this.personType = personType;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<? extends Person> lastShownList;
        if (this.personType.equals("staff")) {
            lastShownList = model.getFilteredStaffList();
        } else {
            lastShownList = model.getFilteredApplicantList();
        }

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Person editedPerson = createEditedPerson(personToEdit, editPersonDescriptor);

        if (!personToEdit.isSamePerson(editedPerson) && model.hasPerson(editedPerson)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        if (this.personType.equals("staff")) {
            model.setStaff((Staff) personToEdit, (Staff) editedPerson);
            model.updateFilteredStaffList(PREDICATE_SHOW_ALL_STAFFS);
        } else {
            model.setApplicant((Applicant) personToEdit, (Applicant) editedPerson);
            model.updateFilteredApplicantList(PREDICATE_SHOW_ALL_APPLICANTS);
        }
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedPerson));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    public static Person createEditedPerson(Person personToEdit, EditCommand.EditPersonDescriptor editPersonDescriptor)
            throws CommandException {
        assert personToEdit != null;

        Name updatedName = personToEdit.getName();
        Phone updatedPhone = personToEdit.getPhone();
        Email updatedEmail = personToEdit.getEmail();
        Address updatedAddress = personToEdit.getAddress();
        Set<Tag> updatedTags = personToEdit.getTags();
        Set<Comment> editedComments = editPersonDescriptor.getComments();
        Set<Comment> currentComments = personToEdit.getComments();
        Set<Comment> newComments;
        try {
            newComments = updateComments(editedComments, currentComments);
        } catch (CommandException e) {
            throw new CommandException(e.getMessage());
        }
        if (personToEdit instanceof Staff) {
            Set<Leave> updatedLeaves = ((Staff) personToEdit).getLeaves();
            return new Staff(updatedName, updatedPhone, updatedEmail,
                    updatedAddress, updatedTags, updatedLeaves, newComments);
        } else if (personToEdit instanceof Applicant) {
            ApplicationStatus applicationStatus = ((Applicant) personToEdit).getApplicationStatus();
            Optional<InterviewDate> updatedInterviewDate = ((Applicant) personToEdit).getInterviewDate();
            return new Applicant(updatedName, updatedPhone, updatedEmail, updatedAddress,
                    updatedTags, newComments, updatedInterviewDate, applicationStatus);
        } else {
            throw new CommandException("Specify Staff Or Applicant");
        }
    }

    /**
     * Updates current comments with new incoming comment input
     * @param editedComments
     * @param currentComments
     * @return Set of comments
     * @throws CommandException When no comment specified
     */
    public static Set<Comment> updateComments(Set<Comment> editedComments, Set<Comment> currentComments)
            throws CommandException {
        boolean hasChange = false;
        if (editedComments != null && currentComments != null) {
            for (Comment comment: currentComments) {
                for (Comment editedComment : editedComments) {
                    if (editedComment.getTitle().equals(comment.getTitle())
                            && editedComment.getDate().equals(comment.getDate())) {
                        comment.update(editedComment.getDescription());
                        hasChange = true;
                    }
                }
            }
        }
        if (hasChange || editedComments == null) {
            return currentComments;
        } else {
            throw new CommandException(MESSAGE_EDIT_COMMENT_USAGE
                    + "\nMake sure comment with title and date specified exists!");
        }
    }
}
