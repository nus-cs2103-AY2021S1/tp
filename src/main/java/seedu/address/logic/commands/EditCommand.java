package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SPECIES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ANIMALS;

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
import seedu.address.model.animal.Animal;
import seedu.address.model.animal.Id;
import seedu.address.model.animal.Name;
import seedu.address.model.animal.Species;
import seedu.address.model.tag.Tag;

/**
 * Edits the details of an existing animal in the address book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the animal identified "
            + "by the index number used in the displayed animal list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_ID + "ID] "
            + "[" + PREFIX_SPECIES + "SPECIES] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_ID + "91234567 ";

    public static final String MESSAGE_EDIT_ANIMAL_SUCCESS = "Edited Animal: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_ANIMAL = "This animal already exists in the address book.";

    private final Index index;
    private final EditAnimalDescriptor editAnimalDescriptor;

    /**
     * @param index of the animal in the filtered animal list to edit
     * @param editAnimalDescriptor details to edit the animal with
     */
    public EditCommand(Index index, EditAnimalDescriptor editAnimalDescriptor) {
        requireNonNull(index);
        requireNonNull(editAnimalDescriptor);

        this.index = index;
        this.editAnimalDescriptor = new EditAnimalDescriptor(editAnimalDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Animal> lastShownList = model.getFilteredAnimalList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ANIMAL_DISPLAYED_ID);
        }

        Animal animalToEdit = lastShownList.get(index.getZeroBased());
        Animal editedAnimal = createEditedAnimal(animalToEdit, editAnimalDescriptor);

        if (!animalToEdit.isSameAnimal(editedAnimal) && model.hasAnimal(editedAnimal)) {
            throw new CommandException(MESSAGE_DUPLICATE_ANIMAL);
        }

        model.setAnimal(animalToEdit, editedAnimal);
        model.updateFilteredAnimalList(PREDICATE_SHOW_ALL_ANIMALS);
        return new CommandResult(String.format(MESSAGE_EDIT_ANIMAL_SUCCESS, editedAnimal));
    }

    /**
     * Creates and returns a {@code Animal} with the details of {@code animalToEdit}
     * edited with {@code editAnimalDescriptor}.
     */
    private static Animal createEditedAnimal(Animal animalToEdit, EditAnimalDescriptor editAnimalDescriptor) {
        assert animalToEdit != null;

        Name updatedName = editAnimalDescriptor.getName().orElse(animalToEdit.getName());
        Id updatedId = editAnimalDescriptor.getId().orElse(animalToEdit.getId());
        Species updatedSpecies = editAnimalDescriptor.getSpecies().orElse(animalToEdit.getSpecies());
        Set<Tag> updatedTags = editAnimalDescriptor.getTags().orElse(animalToEdit.getTags());

        return new Animal(updatedName, updatedId, updatedSpecies, updatedTags);
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
                && editAnimalDescriptor.equals(e.editAnimalDescriptor);
    }

    /**
     * Stores the details to edit the animal with. Each non-empty field value will replace the
     * corresponding field value of the animal.
     */
    public static class EditAnimalDescriptor {
        private Name name;
        private Id id;
        private Species species;
        private Set<Tag> tags;

        public EditAnimalDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditAnimalDescriptor(EditAnimalDescriptor toCopy) {
            setName(toCopy.name);
            setId(toCopy.id);
            setSpecies(toCopy.species);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, id, species, tags);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setId(Id id) {
            this.id = id;
        }

        public Optional<Id> getId() {
            return Optional.ofNullable(id);
        }

        public void setSpecies(Species species) {
            this.species = species;
        }

        public Optional<Species> getSpecies() {
            return Optional.ofNullable(species);
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
            if (!(other instanceof EditAnimalDescriptor)) {
                return false;
            }

            // state check
            EditAnimalDescriptor e = (EditAnimalDescriptor) other;

            return getName().equals(e.getName())
                    && getId().equals(e.getId())
                    && getSpecies().equals(e.getSpecies())
                    && getTags().equals(e.getTags());
        }
    }
}
