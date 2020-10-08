package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Description;
import seedu.address.model.person.Document;
import seedu.address.model.person.Person;
import seedu.address.model.person.Status;
import seedu.address.model.person.Suspect;
import seedu.address.model.person.Title;
import seedu.address.model.person.Victim;
import seedu.address.model.person.Witness;
import seedu.address.model.tag.Tag;

/**
 * Edits the details of an existing person in the address book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the person identified "
            + "by the index number used in the displayed person list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_TITLE + "TITLE] "
            + "[" + PREFIX_STATUS + "STATUS] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 ";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Person: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book.";

    private final Index index;
    private final EditPersonDescriptor editPersonDescriptor;

    /**
     * @param index of the person in the filtered person list to edit
     * @param editPersonDescriptor details to edit the person with
     */
    public EditCommand(Index index, EditPersonDescriptor editPersonDescriptor) {
        requireNonNull(index);
        requireNonNull(editPersonDescriptor);

        this.index = index;
        this.editPersonDescriptor = new EditPersonDescriptor(editPersonDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Person editedPerson = createEditedPerson(personToEdit, editPersonDescriptor);

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
    private static Person createEditedPerson(Person personToEdit, EditPersonDescriptor editPersonDescriptor) {
        assert personToEdit != null;

        Title updatedTitle = editPersonDescriptor.getTitle().orElse(personToEdit.getTitle());
        Description updatedDescription = new Description("");
        Status updatedStatus = editPersonDescriptor.getStatus().orElse(personToEdit.getStatus());
        List<Document> updatedDocuments = editPersonDescriptor.getDocuments().orElse(personToEdit.getDocuments());
        List<Suspect> updatedSuspects = editPersonDescriptor.getSuspects().orElse(personToEdit.getSuspects());
        List<Victim> updatedVictims = editPersonDescriptor.getVictims().orElse(personToEdit.getVictims());
        Set<Tag> updatedTags = editPersonDescriptor.getTags().orElse(personToEdit.getTags());
        List<Witness> updatedWitnesses =
                editPersonDescriptor.getWitnesses().orElse(personToEdit.getWitnesses());
        return new Person(updatedTitle, updatedDescription, updatedStatus, updatedDocuments,
                updatedSuspects, updatedVictims, updatedWitnesses, updatedTags);
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
        private Title title;
        private Description description;
        private Status status;
        private List<Document> documents;
        private List<Suspect> suspects;
        private List<Victim> victims;
        private Set<Tag> tags;
        private ArrayList<Witness> witnesses;

        public EditPersonDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditPersonDescriptor(EditPersonDescriptor toCopy) {
            setTitle(toCopy.title);
            setDescription(toCopy.description);
            setStatus(toCopy.status);
            setDocuments(toCopy.documents);
            setSuspects(toCopy.suspects);
            setVictims(toCopy.victims);
            setWitnesses(toCopy.witnesses);
            setTags(toCopy.tags);

        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(title, description,
                    status, suspects, victims, tags);
        }

        public void setTitle(Title title) {
            this.title = title;
        }

        public Optional<Title> getTitle() {
            return Optional.ofNullable(title);
        }

        public void setDescription(Description description) {
            this.description = description;
        }

        public Optional<Description> getDescription() {
            return Optional.ofNullable(description);
        }

        public void setStatus(Status status) {
            this.status = status;
        }

        public Optional<Status> getStatus() {
            return Optional.ofNullable(status);
        }

        public void setDocuments(List<Document> documents) {
            this.documents = documents;
        }

        public Optional<List<Document>> getDocuments() {
            return (documents != null) ? Optional.of(documents) : Optional.empty();
        }

        /**
         * Sets {@code suspects} to this object's {@code suspects}.
         * A defensive copy of {@code suspects} is used internally.
         */
        public void setSuspects(List<Suspect> suspects) {
            this.suspects = (suspects != null) ? new ArrayList<>() : null;
        }

        /**
         * Returns {@code Optional#empty()} if {@code suspects} is null.
         */
        public Optional<List<Suspect>> getSuspects() {
            return (suspects != null) ? Optional.of(suspects) : Optional.empty();
        }

        /**
         * Sets {@code victims} to this object's {@code victims}.
         * A defensive copy of {@code victims} is used internally.
         */
        public void setVictims(List<Victim> victims) {
            this.victims = (victims != null) ? new ArrayList<>() : null;
        }

        /**
         * Returns {@code Optional#empty()} if {@code victims} is null.
         */
        public Optional<List<Victim>> getVictims() {
            return (victims != null) ? Optional.of(victims) : Optional.empty();
        }


        /**
         * Sets {@code witnesses} to this object's {@code witnesses}.
         * A defensive copy of {@code witnesses} is used internally.
         */
        public void setWitnesses(ArrayList<Witness> witnesses) {
            this.witnesses = (witnesses != null) ? new ArrayList<>(witnesses) : null;
        }

        /**
         * Returns {@code Optional#empty()} if {@code witnesses} is null.
         */
        public Optional<List<Witness>> getWitnesses() {
            return (witnesses != null) ? Optional.of(witnesses) : Optional.empty();
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

            return getTitle().equals(e.getTitle())
                    && getStatus().equals(e.getStatus())
                    && getDocuments().equals(e.getDocuments())
                    && getTags().equals(e.getTags());
        }

    }
}
