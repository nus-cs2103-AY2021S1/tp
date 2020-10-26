package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.ReadOnlyZooKeepBook;
import seedu.address.model.ZooKeepBook;
import seedu.address.model.animal.Animal;
import seedu.address.model.animal.AnimalComparator;
import seedu.address.model.animal.Id;
import seedu.address.testutil.AnimalBuilder;

public class AddCommandTest {

    @Test
    public void constructor_nullAnimal_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_animalAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingAnimalAdded modelStub = new ModelStubAcceptingAnimalAdded();
        Animal validAnimal = new AnimalBuilder().build();

        CommandResult commandResult = new AddCommand(validAnimal).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validAnimal), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validAnimal), modelStub.animalsAdded);
    }

    @Test
    public void execute_duplicateAnimal_throwsCommandException() {
        Animal validAnimal = new AnimalBuilder().build();
        AddCommand addCommand = new AddCommand(validAnimal);
        ModelStub modelStub = new ModelStubWithAnimal(validAnimal);

        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_ANIMAL, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Animal ahmeng = new AnimalBuilder().withName("Ahmeng").build();
        Animal buttercup = new AnimalBuilder().withName("Buttercup").build();
        AddCommand addAhmengCommand = new AddCommand(ahmeng);
        AddCommand addButtercupCommand = new AddCommand(buttercup);

        // same object -> returns true
        assertTrue(addAhmengCommand.equals(addAhmengCommand));

        // same values -> returns true
        AddCommand addAhmengCommandCopy = new AddCommand(ahmeng);
        assertTrue(addAhmengCommand.equals(addAhmengCommandCopy));

        // different types -> returns false
        assertFalse(addAhmengCommand.equals(1));

        // null -> returns false
        assertFalse(addAhmengCommand.equals(null));

        // different animal -> returns false
        assertFalse(addAhmengCommand.equals(addButtercupCommand));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getZooKeepBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setZooKeepBookFilePath(Path zooKeepBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addAnimal(Animal animal) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setZooKeepBook(ReadOnlyZooKeepBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyZooKeepBook getZooKeepBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasAnimal(Animal animal) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Optional<Animal> getAnimal(Id id) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteAnimal(Animal target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAnimal(Animal target, Animal editedAnimal) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void sortAnimals(AnimalComparator animalComparator) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Animal> getFilteredAnimalList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredAnimalList(Predicate<Animal> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single animal.
     */
    private class ModelStubWithAnimal extends ModelStub {
        private final Animal animal;

        ModelStubWithAnimal(Animal animal) {
            requireNonNull(animal);
            this.animal = animal;
        }

        @Override
        public boolean hasAnimal(Animal animal) {
            requireNonNull(animal);
            return this.animal.isSameAnimal(animal);
        }
    }

    /**
     * A Model stub that always accept the animal being added.
     */
    private class ModelStubAcceptingAnimalAdded extends ModelStub {
        final ArrayList<Animal> animalsAdded = new ArrayList<>();

        @Override
        public boolean hasAnimal(Animal animal) {
            requireNonNull(animal);
            return animalsAdded.stream().anyMatch(animal::isSameAnimal);
        }

        @Override
        public void addAnimal(Animal animal) {
            requireNonNull(animal);
            animalsAdded.add(animal);
        }

        @Override
        public ReadOnlyZooKeepBook getZooKeepBook() {
            return new ZooKeepBook();
        }
    }

}
