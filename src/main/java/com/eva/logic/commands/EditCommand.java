package com.eva.logic.commands;

import static com.eva.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static com.eva.logic.parser.CliSyntax.PREFIX_EMAIL;
import static com.eva.logic.parser.CliSyntax.PREFIX_NAME;
import static com.eva.logic.parser.CliSyntax.PREFIX_PHONE;
import static com.eva.logic.parser.CliSyntax.PREFIX_TAG;
import static com.eva.model.Model.PREDICATE_SHOW_ALL_APPLICANTS;
import static com.eva.model.Model.PREDICATE_SHOW_ALL_STAFFS;
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
import com.eva.model.person.applicant.Applicant;
import com.eva.model.person.applicant.ApplicationStatus;
import com.eva.model.person.applicant.InterviewDate;
import com.eva.model.person.staff.Staff;
import com.eva.model.person.staff.leave.Leave;
import com.eva.model.tag.Tag;


/**
 * Edits the details of an existing person in the eva database.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the person identified "
            + "by the index number used in the displayed person list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + ", PERSONTYPE: s- / a-"
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 s- "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Person: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the eva database.";
    public static final String MESSAGE_NO_APPLICANTORSTAFF = "Please key in s- or a- to indicate "
            + "if you want to edit applicant or staff";

    private String personType;
    private final Index index;
    private final EditPersonDescriptor editPersonDescriptor;

    /**
     * @param index of the person in the filtered person list to edit
     * @param editPersonDescriptor details to edit the person with
     */
    public EditCommand(Index index, EditPersonDescriptor editPersonDescriptor,
                       String personType) {
        requireNonNull(index);
        requireNonNull(editPersonDescriptor);

        this.index = index;
        this.editPersonDescriptor = new EditPersonDescriptor(editPersonDescriptor);
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
            return new Applicant(updatedName, updatedPhone, updatedEmail, updatedAddress,
                    updatedTags, newComments, updatedInterviewDate, applicationStatus);
        }
        return new Person(updatedName, updatedPhone,
                updatedEmail, updatedAddress, updatedTags, newComments);
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
        return index.equals(e.index)
                && editPersonDescriptor.equals(e.editPersonDescriptor);
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
        private Set<Tag> tags;
        private Set<Comment> comments;
        private Set<Leave> leaves;
        private Optional<InterviewDate> interviewDate;
        private ApplicationStatus applicationStatus;

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
            return CollectionUtil.isAnyNonNull(name, phone, email, address, tags, comments,
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
