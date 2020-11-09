package seedu.zookeep.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.zookeep.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.Optional;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.zookeep.commons.core.GuiSettings;
import seedu.zookeep.logic.commands.exceptions.CommandException;
import seedu.zookeep.model.HistoryStack;
import seedu.zookeep.model.Model;
import seedu.zookeep.model.ReadOnlyUserPrefs;
import seedu.zookeep.model.ReadOnlyZooKeepBook;
import seedu.zookeep.model.ZooKeepBook;
import seedu.zookeep.model.animal.Animal;
import seedu.zookeep.model.animal.AnimalComparator;
import seedu.zookeep.model.animal.Id;
import seedu.zookeep.testutil.AnimalBuilder;

public class UndoCommandTest {

    @Test
    public void execute_undoSuccessful() throws Exception {
        HistoryStack historyStack = HistoryStack.getHistoryStack();
        historyStack.clearHistory();
        ZooKeepBook bookA = new ZooKeepBook();
        bookA.addAnimal(new AnimalBuilder().withName("Bob").build());

        ZooKeepBook bookB = new ZooKeepBook();
        bookB.addAnimal(new AnimalBuilder().withName("Bob").build());
        bookB.addAnimal(new AnimalBuilder().withName("Tom").withId("1234").build());

        historyStack.addToHistory(bookA);
        historyStack.addToHistory(bookB);

        ModelStub modelStub = new ModelStub();

        CommandResult commandResult = new UndoCommand().execute(modelStub);

        assertEquals(UndoCommand.MESSAGE_SUCCESS, commandResult.getFeedbackToUser());
        assertEquals(historyStack.getHistorySize(), 1);
    }

    @Test
    public void execute_noUndoAvailable_throwsCommandException() {
        UndoCommand undoCommand = new UndoCommand();
        ModelStub modelStub = new ModelStub();

        assertThrows(CommandException.class, UndoCommand.MESSAGE_NO_UNDO, () -> undoCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        HistoryStack historyStack = HistoryStack.getHistoryStack();
        historyStack.clearHistory();
        ZooKeepBook book = new ZooKeepBook();
        book.addAnimal(new AnimalBuilder().withName("Bob").build());
        historyStack.addToHistory(book);

        UndoCommand undoCommand = new UndoCommand();

        // same object -> returns true
        assertTrue(undoCommand.equals(undoCommand));

        // same values -> returns true
        UndoCommand undoCommandCopy = new UndoCommand();
        assertTrue(undoCommand.equals(undoCommandCopy));

        // different types -> returns false
        assertFalse(undoCommand.equals(1));

        // null -> returns false
        assertFalse(undoCommand.equals(null));

    }

    private class ModelStub implements Model {

        private ZooKeepBook zooKeepBook = new ZooKeepBook();

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
        public void setZooKeepBook(ReadOnlyZooKeepBook zooKeepBook) {
            this.zooKeepBook.resetData(zooKeepBook);
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

}
