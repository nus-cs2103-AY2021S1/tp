package seedu.bookmark.model.history;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.nio.file.Paths;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.bookmark.model.Library;
import seedu.bookmark.model.UserPrefs;
import seedu.bookmark.model.book.Book;
import seedu.bookmark.testutil.TypicalBooks;

/**
 * Hybrid tests with {@code Library}, and {@code UserPrefs}
 */
public class StateTest {

    private static final Library DEFAULT_LIBRARY = TypicalBooks.getTypicalLibrary();
    private static final Book TO_ADD = TypicalBooks.HAMLET;
    private static final UserPrefs DEFAULT_USER_PREFS = new UserPrefs();
    private static final Predicate<Book> DEFAULT_PREDICATE = (b) -> true;
    private static final State DEFAULT_STATE = State.createState(DEFAULT_LIBRARY,
            DEFAULT_USER_PREFS, DEFAULT_PREDICATE);

    @Test
    public void equalsTest() {
        State state = State.createState(DEFAULT_LIBRARY, DEFAULT_USER_PREFS, DEFAULT_PREDICATE);

        Library editedLibrary = new Library(DEFAULT_LIBRARY);
        editedLibrary.addBook(TO_ADD);

        UserPrefs editedUserPrefs = new UserPrefs();
        editedUserPrefs.setBookmarkFilePath(Paths.get("data"));

        State editedState = State.createState(editedLibrary, DEFAULT_USER_PREFS, DEFAULT_PREDICATE);

        // same object
        assertEquals(DEFAULT_STATE, DEFAULT_STATE);

        // same library, user prefs and predicate
        assertEquals(state, DEFAULT_STATE);

        // diff library
        assertNotEquals(DEFAULT_STATE, editedState);

        // diff user prefs
        editedState = State.createState(DEFAULT_LIBRARY, editedUserPrefs, DEFAULT_PREDICATE);
        assertNotEquals(DEFAULT_STATE, editedState);

        // diff predicate
        editedState = State.createState(DEFAULT_LIBRARY, DEFAULT_USER_PREFS, (b) -> false);
        assertNotEquals(DEFAULT_STATE, editedState);
    }
}
