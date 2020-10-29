package com.eva.logic.commands;

import static com.eva.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static java.util.Objects.requireNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.eva.commons.core.Messages;
import com.eva.commons.core.index.Index;
import com.eva.commons.util.CollectionUtil;
import com.eva.logic.commands.exceptions.CommandException;
import com.eva.model.Model;
import com.eva.model.comment.Comment;
import com.eva.model.person.Address;
import com.eva.model.person.Email;
import com.eva.model.person.Name;
import com.eva.model.person.Person;
import com.eva.model.person.Phone;
import com.eva.model.person.applicant.ApplicationStatus;
import com.eva.model.person.applicant.InterviewDate;
import com.eva.model.person.staff.leave.Leave;
import com.eva.model.tag.Tag;

public class CommentCommand extends Command {

    public static final String COMMAND_WORD = "comment";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds or delete comment to person"
            + "To add comment: key in 'addComment INDEX <s-/a-> ti/TITLE d/DATE desc/DESCRIPTION'\n"
            + "To delete, key in 'deleteComment INDEX <s-/a-> ti/TITLE_TO_DELETE' \n"
            + "To edit, key in editComment INDEX <s-/a-> ti/TITLE d/DATE desc/NEW_DESCRIPTION";
    public static final String MESSAGE_ADD_COMMENT_SUCCESS = "Commented on Person: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the eva database.";
    public static final String MESSAGE_DELETE_COMMENT_SUCCESS = "Deleted comment on Person: %1$s";
    public static final String MESSAGE_NO_SUCH_COMMENT = "No such comment with this title and date.";



    public final Index index;
    public final CommentCommand.CommentPersonDescriptor commentPersonDescriptor;

    /**
     * Creates CommentCommand object
     * @param index index of person being commented on
     * @param commentPersonDescriptor details of person once comments are done
     */
    public CommentCommand(Index index,
                          CommentCommand.CommentPersonDescriptor commentPersonDescriptor) {
        requireNonNull(index);
        requireNonNull(commentPersonDescriptor);

        this.index = index;
        this.commentPersonDescriptor = new CommentCommand.CommentPersonDescriptor(commentPersonDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Person editedPerson = createEditedPerson(personToEdit, commentPersonDescriptor);

        if (!personToEdit.isSamePerson(editedPerson) && model.hasPerson(editedPerson)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_ADD_COMMENT_SUCCESS, editedPerson));
    }

    /**
     * Creates and returns a {@code Person} with
     * the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Person createEditedPerson(Person personToEdit,
                                             CommentCommand.CommentPersonDescriptor commentPersonDescriptor)
            throws CommandException {
        assert personToEdit != null;

        throw new CommandException("Shouldn't come here");
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class CommentPersonDescriptor {
        private Name name;
        private Phone phone;
        private Email email;
        private Address address;
        private ApplicationStatus applicationStatus;
        private InterviewDate interviewDate;
        private Set<Tag> tags;
        private Set<Comment> comments;
        private Set<Leave> leaves;


        public CommentPersonDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public CommentPersonDescriptor(CommentCommand.CommentPersonDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setAddress(toCopy.address);
            setTags(toCopy.tags);
            setApplicationStatus(toCopy.applicationStatus);
            setInterviewDate(toCopy.interviewDate);
            setComments(toCopy.comments);
            setLeaves(toCopy.leaves);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, address, tags, comments);
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

        public Optional<ApplicationStatus> getApplicationStatus() {
            return Optional.ofNullable(applicationStatus);
        }

        public void setApplicationStatus(ApplicationStatus applicationStatus) {
            this.applicationStatus = applicationStatus;
        }

        public Optional<InterviewDate> getInterviewDate() {
            return Optional.ofNullable(interviewDate);
        }

        public void setInterviewDate(InterviewDate interviewDate) {
            this.interviewDate = interviewDate;
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

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof CommentCommand.CommentPersonDescriptor)) {
                return false;
            }

            // state check
            CommentCommand.CommentPersonDescriptor e = (CommentCommand.CommentPersonDescriptor) other;

            return getName().equals(e.getName())
                    && getPhone().equals(e.getPhone())
                    && getEmail().equals(e.getEmail())
                    && getAddress().equals(e.getAddress())
                    && getTags().equals(e.getTags());
        }
    }
}
