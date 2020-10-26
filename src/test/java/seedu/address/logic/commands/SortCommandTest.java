package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.parser.SortCommandParser;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.ReadOnlyZooKeepBook;
import seedu.address.model.animal.Animal;
import seedu.address.model.animal.AnimalComparator;
import seedu.address.model.animal.Id;
import seedu.address.testutil.AnimalBuilder;

class SortCommandTest {

    @Test
    public void execute_sortAnimalNameSuccessful() {
        Animal ahmeng = new AnimalBuilder().withName("Ahmeng").build();
        Animal buttercup = new AnimalBuilder().withName("Buttercup").build();
        Animal coco = new AnimalBuilder().withName("Coco").build();
        ModelStubSortingAnimals modelStub = new ModelStubSortingAnimals(
                new ArrayList<>(Arrays.asList(buttercup, ahmeng, coco)));

        AnimalComparator animalNameComparator = AnimalComparator.createAnimalNameComparator();
        CommandResult commandResult = new SortCommand(animalNameComparator).execute(modelStub);

        assertEquals(String.format(SortCommand.MESSAGE_SUCCESS + SortCommandParser.NAME_CATEGORY),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(ahmeng, buttercup, coco), modelStub.animals);
    }

    @Test
    public void execute_sortAnimalIdSuccessful() {
        Animal ahmeng = new AnimalBuilder().withId("121").build();
        Animal buttercup = new AnimalBuilder().withId("122").build();
        Animal coco = new AnimalBuilder().withId("123").build();
        ModelStubSortingAnimals modelStub = new ModelStubSortingAnimals(
                new ArrayList<>(Arrays.asList(buttercup, ahmeng, coco)));

        AnimalComparator animalIdComparator = AnimalComparator.createAnimalIdComparator();
        CommandResult commandResult = new SortCommand(animalIdComparator).execute(modelStub);

        assertEquals(String.format(SortCommand.MESSAGE_SUCCESS + SortCommandParser.ID_CATEGORY),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(ahmeng, buttercup, coco), modelStub.animals);
    }

    @Test
    public void execute_sortAnimalFeedTimeSuccessful() {
        Animal ahmeng = new AnimalBuilder().withFeedTimes("1200").build();
        Animal buttercup = new AnimalBuilder().withFeedTimes("1230").build();
        Animal coco = new AnimalBuilder().withFeedTimes("1300").build();
        ModelStubSortingAnimals modelStub = new ModelStubSortingAnimals(
                new ArrayList<>(Arrays.asList(buttercup, ahmeng, coco)));

        AnimalComparator animalFeedTimeComparator = AnimalComparator.createAnimalFeedTimeComparator();
        CommandResult commandResult = new SortCommand(animalFeedTimeComparator).execute(modelStub);

        assertEquals(String.format(SortCommand.MESSAGE_SUCCESS + SortCommandParser.FEEDTIME_CATEGORY),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(ahmeng, buttercup, coco), modelStub.animals);
    }

    @Test
    public void execute_sortAnimalNoFeedTimeSuccessful() {
        Animal ahmeng = new AnimalBuilder().withFeedTimes().build();
        Animal buttercup = new AnimalBuilder().withFeedTimes().build();
        Animal coco = new AnimalBuilder().withFeedTimes("1300").build();
        Animal nemo = new AnimalBuilder().withFeedTimes().build();
        ModelStubSortingAnimals modelStub = new ModelStubSortingAnimals(
                new ArrayList<>(Arrays.asList(buttercup, coco, ahmeng, nemo)));

        AnimalComparator animalFeedTimeComparator = AnimalComparator.createAnimalFeedTimeComparator();
        CommandResult commandResult = new SortCommand(animalFeedTimeComparator).execute(modelStub);

        assertEquals(String.format(SortCommand.MESSAGE_SUCCESS + SortCommandParser.FEEDTIME_CATEGORY),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(coco, buttercup, ahmeng, nemo), modelStub.animals);
    }

    @Test
    public void equals() {
        SortCommand sortByNameCommand = new SortCommand(AnimalComparator.createAnimalNameComparator());
        SortCommand sortByIdCommand = new SortCommand(AnimalComparator.createAnimalIdComparator());

        // same object -> returns true
        assertTrue(sortByNameCommand.equals(sortByNameCommand));

        // same command keyword -> returns true
        SortCommand sortByNameCommandCopy = new SortCommand(AnimalComparator.createAnimalNameComparator());
        assertTrue(sortByNameCommand.equals(sortByNameCommandCopy));

        // different types -> return false
        assertFalse(sortByNameCommand.equals(1));

        // null -> returns false
        assertFalse(sortByNameCommand.equals(null));

        // different command keyword -> return false
        assertFalse(sortByNameCommand.equals(sortByIdCommand));
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
     * A Model stub that always sorts a list of animals
     */
    private class ModelStubSortingAnimals extends ModelStub {
        private final List<Animal> animals;

        ModelStubSortingAnimals(List<Animal> animals) {
            requireNonNull(animals);
            this.animals = animals;
        }

        @Override
        public void sortAnimals(AnimalComparator animalComparator) {
            animals.sort(animalComparator.getAnimalComparator());
        }
    }
}
