package seedu.bookmark.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.bookmark.model.Model.PREDICATE_SHOW_ALL_BOOKS;
import static seedu.bookmark.testutil.Assert.assertThrows;
import static seedu.bookmark.testutil.TypicalBooks.HARRY_POTTER;
import static seedu.bookmark.testutil.TypicalBooks.TO_KILL_A_MOCKINGBIRD;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.bookmark.commons.core.GuiSettings;
import seedu.bookmark.model.book.predicates.NameContainsKeywordsPredicate;
import seedu.bookmark.model.exceptions.RedoException;
import seedu.bookmark.model.exceptions.UndoException;
import seedu.bookmark.testutil.LibraryBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new Library(), new Library(modelManager.getLibrary()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setBookmarkFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setBookmarkFilePath(Paths.get("new/address/book/file/path"));
        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        modelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, modelManager.getGuiSettings());
    }

    @Test
    public void setAddressBookFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setBookmarkFilePath(null));
    }

    @Test
    public void setAddressBookFilePath_validPath_setsAddressBookFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setBookmarkFilePath(path);
        assertEquals(path, modelManager.getBookmarkFilePath());
    }

    @Test
    public void hasBook_nullBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasBook(null));
    }

    @Test
    public void hasBook_bookNotInAddressBook_returnsFalse() {
        assertFalse(modelManager.hasBook(HARRY_POTTER));
    }

    @Test
    public void hasBook_bookInAddressBook_returnsTrue() {
        modelManager.addBook(HARRY_POTTER);
        assertTrue(modelManager.hasBook(HARRY_POTTER));
    }

    @Test
    public void getFilteredBookList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredBookList().remove(0));
    }

    @Test
    public void undo_nothingToUndo_throwsUndoException() {
        Library library = new LibraryBuilder().withBook(HARRY_POTTER).build();
        UserPrefs userPrefs = new UserPrefs();
        modelManager = new ModelManager(library, userPrefs);
        assertThrows(UndoException.class, modelManager::undo);
    }

    @Test
    public void undo_validUndo_success() {
        Library library = new LibraryBuilder().withBook(HARRY_POTTER).build();
        UserPrefs userPrefs = new UserPrefs();
        modelManager = new ModelManager(library, userPrefs);
        modelManager.deleteBook(HARRY_POTTER);
        modelManager.save();
        modelManager.undo();
        assertTrue(modelManager.hasBook(HARRY_POTTER));
    }

    @Test
    public void redo_nothingToRedo_throwsRedoException() {
        Library library = new LibraryBuilder().withBook(HARRY_POTTER).build();
        UserPrefs userPrefs = new UserPrefs();
        modelManager = new ModelManager(library, userPrefs);
        assertThrows(RedoException.class, modelManager::redo);
    }

    @Test
    public void undoThenRedo_validUndoRedo_success() {
        Library library = new LibraryBuilder().withBook(HARRY_POTTER).build();
        UserPrefs userPrefs = new UserPrefs();
        modelManager = new ModelManager(library, userPrefs);
        modelManager.deleteBook(HARRY_POTTER);
        modelManager.save();
        modelManager.undo();
        modelManager.redo();
        assertFalse(modelManager.hasBook(HARRY_POTTER));
    }

    @Test
    public void equals() {
        Library library = new LibraryBuilder().withBook(HARRY_POTTER).withBook(TO_KILL_A_MOCKINGBIRD).build();
        Library differentLibrary = new Library();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(library, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(library, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different addressBook -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentLibrary, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = HARRY_POTTER.getName().fullName.split("\\s+");
        modelManager.updateFilteredBookList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(library, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredBookList(PREDICATE_SHOW_ALL_BOOKS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setBookmarkFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(library, differentUserPrefs)));
    }
}
