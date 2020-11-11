package seedu.bookmark.model.history;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.bookmark.testutil.Assert.assertThrows;

import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.bookmark.model.Library;
import seedu.bookmark.model.UserPrefs;
import seedu.bookmark.model.book.Book;
import seedu.bookmark.model.exceptions.RedoException;
import seedu.bookmark.model.exceptions.UndoException;
import seedu.bookmark.testutil.TypicalBooks;

/**
 * Integration tests with {@code State}
 */
public class HistoryManagerTest {

    private static final Library DEFAULT_LIBRARY = TypicalBooks.getTypicalLibrary();
    private static final Book TO_ADD = TypicalBooks.HAMLET;
    private static final UserPrefs DEFAULT_USER_PREFS = new UserPrefs();
    private static final Predicate<Book> DEFAULT_PREDICATE = (b) -> true;
    private static final State DEFAULT_STATE = State.createState(DEFAULT_LIBRARY,
            DEFAULT_USER_PREFS, DEFAULT_PREDICATE);


    @Test
    public void equalsTest() {
        Library editedLibrary = new Library(DEFAULT_LIBRARY);
        editedLibrary.addBook(TO_ADD);
        State newState = State.createState(editedLibrary, DEFAULT_USER_PREFS, DEFAULT_PREDICATE);


        HistoryManager historyA = new HistoryManager(DEFAULT_STATE);
        HistoryManager historyB = new HistoryManager(DEFAULT_STATE);
        HistoryManager historyC = new HistoryManager(newState);

        // same object
        assertEquals(historyA, historyA);

        // same current state, same undo and redo deque
        assertEquals(historyA, historyB);

        // different current state
        assertNotEquals(historyA, historyC);

        // same current state, different undo deque
        historyA = historyA.addNewState(newState);
        assertNotEquals(historyA, historyC);

        // same current state, different redo deque
        historyA = historyA.undo();
        assertNotEquals(historyA, historyB);
    }

    @Test
    public void changeThenUndo_validActions_success() {
        Library editedLibrary = new Library(DEFAULT_LIBRARY);
        editedLibrary.addBook(TO_ADD);
        State newState = State.createState(editedLibrary, DEFAULT_USER_PREFS, DEFAULT_PREDICATE);

        HistoryManager historyA = new HistoryManager(DEFAULT_STATE);
        historyA = historyA.addNewState(newState);
        historyA = historyA.undo();

        assertEquals(historyA.getCurrentState(), DEFAULT_STATE);
    }

    @Test
    public void changeThenUndoThenRedo_validActions_success() {
        Library editedLibrary = new Library(DEFAULT_LIBRARY);
        editedLibrary.addBook(TO_ADD);
        State newState = State.createState(editedLibrary, DEFAULT_USER_PREFS, DEFAULT_PREDICATE);

        HistoryManager historyA = new HistoryManager(DEFAULT_STATE);
        historyA = historyA.addNewState(newState);
        historyA = historyA.undo();
        historyA = historyA.redo();

        assertEquals(historyA.getCurrentState(), newState);
    }

    @Test
    public void undo_noUndoAvailable_throwsException() {
        HistoryManager historyA = new HistoryManager(DEFAULT_STATE);
        assertThrows(UndoException.class, historyA::undo);
    }

    @Test
    public void redo_noRedoAvailable_throwsException() {
        HistoryManager historyA = new HistoryManager(DEFAULT_STATE);
        assertThrows(RedoException.class, historyA::redo);
    }
}
