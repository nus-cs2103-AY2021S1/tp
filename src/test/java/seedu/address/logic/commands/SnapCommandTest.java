package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.ReadOnlyZooKeepBook;
import seedu.address.model.UserPrefs;
import seedu.address.model.animal.Animal;
import seedu.address.model.animal.AnimalComparator;
import seedu.address.model.animal.Id;
import seedu.address.storage.JsonZooKeepBookStorage;
import seedu.address.testutil.TypicalAnimals;

public class SnapCommandTest {
    private Path makePath(String fileName) {
        return Path.of("src", "test", "data", "SnapCommandTest", fileName + SnapCommand.FILE_FORMAT);
    }

    @Test
    public void constructor_nullConstructor_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SnapCommand(null, null));
    }

    @Test
    public void execute_validFileName_success() throws Exception {
        ModelStub modelStub = new ModelStub();
        String validFileName = "ZooKeepBookTest_19-10-2020";
        Path path = makePath(validFileName);
        CommandResult commandResult = new SnapCommand(path, validFileName).execute(modelStub);

        // assert that command has executed successfully
        assertEquals(String.format(SnapCommand.MESSAGE_SUCCESS, validFileName + SnapCommand.FILE_FORMAT),
                commandResult.getFeedbackToUser());

        // assert that contents of created file is equal
        ReadOnlyZooKeepBook expectedBook = modelStub.getZooKeepBook();
        ReadOnlyZooKeepBook savedBook = new JsonZooKeepBookStorageStub(path).readZooKeepBook().get();
        assertEquals(expectedBook, savedBook);
    }

    @Test
    public void equals() {
        final String fileName = "zookeepbook_19-10-2020";
        Path path = makePath(fileName);
        SnapCommand snapCommand = new SnapCommand(path, fileName);

        // same object -> returns true
        assertTrue(snapCommand.equals(snapCommand));

        // same values -> returns true
        SnapCommand snapCommandCopy = new SnapCommand(path, fileName);
        assertTrue(snapCommand.equals(snapCommandCopy));

        // different file names -> returns false
        SnapCommand snapCommandWithDifferentFileName = new SnapCommand(path, fileName + "x");
        assertFalse(snapCommand.equals(snapCommandWithDifferentFileName));

        // different types -> returns false
        assertFalse(snapCommand.equals(1));

        // null -> returns false
        assertFalse(snapCommand.equals(null));
    }

    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            UserPrefs userPrefs = new UserPrefs();
            userPrefs.setGuiSettings(new GuiSettings(1000, 500, 300, 100));
            userPrefs.setZooKeepBookFilePath(Paths.get("zookeepbook.json"));
            return userPrefs;
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
            return TypicalAnimals.getTypicalZooKeepBook();
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

    private class JsonZooKeepBookStorageStub extends JsonZooKeepBookStorage {

        public JsonZooKeepBookStorageStub(Path filePath) {
            super(filePath);
        }

        @Override
        public Path getZooKeepBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void saveZooKeepBook(ReadOnlyZooKeepBook zooKeepBook) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void saveZooKeepBook(ReadOnlyZooKeepBook zooKeepBook, Path filePath) {
            throw new AssertionError("This method should not be called.");
        }
    }
}
