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
import com.eva.model.person.Address;
import com.eva.model.person.Email;
import com.eva.model.person.Name;
import com.eva.model.person.Person;
import com.eva.model.person.Phone;
import com.eva.model.person.comment.Comment;
import com.eva.model.tag.Tag;

public class CommentCommand extends Command {




    public static final String COMMAND_WORD = "comment";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds comment to person";
    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Person: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book.";

    private final Index index;
    private final CommentCommand.CommentPersonDescriptor commentPersonDescriptor;


    public CommentCommand(Index index, CommentCommand.CommentPersonDescriptor commentPersonDescriptor) {
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
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedPerson));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Person createEditedPerson(Person personToEdit, CommentCommand.CommentPersonDescriptor commentPersonDescriptor) {
        assert personToEdit != null;

        Name updatedName = commentPersonDescriptor.getName().orElse(personToEdit.getName());
        Phone updatedPhone = commentPersonDescriptor.getPhone().orElse(personToEdit.getPhone());
        Email updatedEmail = commentPersonDescriptor.getEmail().orElse(personToEdit.getEmail());
        Address updatedAddress = commentPersonDescriptor.getAddress().orElse(personToEdit.getAddress());
        Set<Tag> updatedTags = commentPersonDescriptor.getTags().orElse(personToEdit.getTags());
        Set<Comment> updatedCommentsCommands = commentPersonDescriptor.getComments();
        Set<Comment> updatedComments = personToEdit.getComments();
        for (Comment comment: updatedCommentsCommands) {
            System.out.println(comment.getInstruction());
            System.out.println(comment.getInstruction().equals("-a"));
            if (comment.getInstruction().equals("-a")) {
                System.out.println("hullo");
                updatedComments.add(new Comment(comment.getDate(), comment.getDescription()));
            } else if (comment.getInstruction().equals("-d")) {
                System.out.println(comment.getIdentity());
                for (Comment commentToDelete: updatedComments) {
                    if (commentToDelete.getIdentity() == comment.getIdentity()) {
                        updatedComments.remove(commentToDelete);
                        break;
                    }
                }
            }
        }

        return new Person(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedTags, updatedComments);
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
        private Set<Tag> tags;
        private Set<Comment> comments;


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
            setComments(toCopy.comments);
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


        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditCommand.EditPersonDescriptor)) {
                return false;
            }

            // state check
            EditCommand.EditPersonDescriptor e = (EditCommand.EditPersonDescriptor) other;

            return getName().equals(e.getName())
                    && getPhone().equals(e.getPhone())
                    && getEmail().equals(e.getEmail())
                    && getAddress().equals(e.getAddress())
                    && getTags().equals(e.getTags());
        }

        public Set<Comment> getComments() {

            return (comments != null) ? comments : null;
        }
    }
}
