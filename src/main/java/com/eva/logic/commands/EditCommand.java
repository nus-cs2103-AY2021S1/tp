package com.eva.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import com.eva.commons.util.CollectionUtil;
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
import com.eva.model.person.applicant.application.Application;
import com.eva.model.person.staff.Staff;
import com.eva.model.person.staff.leave.Leave;
import com.eva.model.person.staff.leave.LeaveTaken;
import com.eva.model.tag.Tag;


/**
 * Edits the details of an existing person in the eva database.
 */
public class EditCommand extends Command {

    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the eva database.";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        throw new CommandException("Invalid Command at Edit");
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    public static Person createEditedPerson(Person personToEdit, EditPersonDescriptor editPersonDescriptor)
            throws CommandException {
        assert personToEdit != null;

        Name updatedName = editPersonDescriptor.getName().orElse(personToEdit.getName());
        Phone updatedPhone = editPersonDescriptor.getPhone().orElse(personToEdit.getPhone());
        Email updatedEmail = editPersonDescriptor.getEmail().orElse(personToEdit.getEmail());
        Address updatedAddress = editPersonDescriptor.getAddress().orElse(personToEdit.getAddress());
        Set<Tag> updatedTags = editPersonDescriptor.getTags().orElse(personToEdit.getTags());
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
            Optional<InterviewDate> updatedInterviewDate = editPersonDescriptor.getInterviewDate();
            if (updatedInterviewDate.isEmpty()) {
                updatedInterviewDate = ((Applicant) personToEdit).getInterviewDate();
            }
            Application application = ((Applicant) personToEdit).getApplication();
            return new Applicant(updatedName, updatedPhone, updatedEmail, updatedAddress,
                    updatedTags, newComments, updatedInterviewDate, applicationStatus, application);
        } else {
            throw new CommandException("Invalid personType");
        }
    }

    /**
     * Updates Comments
     * @param editedComments
     * @param currentComments
     * @return
     */
    public static Set<Comment> updateComments(Set<Comment> editedComments, Set<Comment> currentComments)
            throws CommandException {
        boolean hasChange = false;
        if (editedComments != null) {
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
            throw new CommandException(CommentCommand.MESSAGE_NO_SUCH_COMMENT);
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        // state check
        EditCommand e = (EditCommand) other;
        return false;
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditPersonDescriptor {
        private Name name;
        private Phone phone;
        private Email email;
        private Address address;
        private LeaveTaken leaveTaken;
        private Set<Tag> tags;
        private Set<Comment> comments;
        private Set<Leave> leaves;
        private Optional<InterviewDate> interviewDate;
        private ApplicationStatus applicationStatus;
        private String title;

        public EditPersonDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditPersonDescriptor(EditPersonDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setAddress(toCopy.address);
            setLeaveTaken(toCopy.leaveTaken);
            setTags(toCopy.tags);
            setComments(toCopy.comments);
            setLeaves(toCopy.leaves);
            setInterviewDate(toCopy.interviewDate);
            setApplicationStatus(toCopy.applicationStatus);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, address, leaveTaken, tags, comments,
                    leaves, applicationStatus, interviewDate);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setPhone(Phone phone) {
            this.phone = phone;
        }

        public Optional<Phone> getPhone() {
            return Optional.ofNullable(phone);
        }

        public void setEmail(Email email) {
            this.email = email;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
        }

        public void setLeaveTaken(LeaveTaken leaveTaken) {
            this.leaveTaken = leaveTaken;
        }

        public Optional<LeaveTaken> getLeaveTaken() {
            return Optional.ofNullable(leaveTaken);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        public void setComments(Set<Comment> comments) {
            this.comments = (comments != null) ? new HashSet<>(comments) : null;
        }

        public Set<Comment> getComments() {
            return (comments != null) ? comments : null;
        }


        public void setLeaves(Set<Leave> leaves) {
            this.leaves = (leaves != null) ? new HashSet<>(leaves) : null;
        }

        public Set<Leave> getLeaves() {
            return (leaves != null) ? leaves : new HashSet<>();
        }

        public void setInterviewDate(Optional<InterviewDate> interviewDate) {
            if (interviewDate == null) {
                this.interviewDate = Optional.empty();
            } else {
                this.interviewDate = interviewDate;
            }
        }

        public Optional<InterviewDate> getInterviewDate() {
            return this.interviewDate;
        }

        public void setApplicationStatus(ApplicationStatus applicationStatus) {
            this.applicationStatus = applicationStatus;
        }

        public ApplicationStatus getApplicationStatus() {
            return this.applicationStatus;
        }

        public String getCommentTitle() {
            this.comments.forEach(comment -> {
                    this.title = comment.getTitle().getTitleDescription();
                }
            );
            return this.title;
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditPersonDescriptor)) {
                return false;
            }

            // state check
            EditPersonDescriptor e = (EditPersonDescriptor) other;

            return getName().equals(e.getName())
                    && getPhone().equals(e.getPhone())
                    && getEmail().equals(e.getEmail())
                    && getAddress().equals(e.getAddress())
                    && getTags().equals(e.getTags());
        }
    }
}
