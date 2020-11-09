package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_BOOKS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalBooks.BOOK1;
import static seedu.address.testutil.TypicalBooks.BOOK2;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.book.NameContainsKeywordsPredicate;
import seedu.address.testutil.LibraryBuilder;
import seedu.address.ui.Mode;

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
        userPrefs.setLibraryFilePath(Paths.get("language/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setLibraryFilePath(Paths.get("new/language/book/file/path"));
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
    public void setLibraryFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setLibraryFilePath(null));
    }

    @Test
    public void setLibraryFilePath_validPath_setsLibraryFilePath() {
        Path path = Paths.get("language/book/file/path");
        modelManager.setLibraryFilePath(path);
        assertEquals(path, modelManager.getLibraryFilePath());
    }

    @Test
    public void hasBook_nullBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasBook(null));
    }

    @Test
    public void hasBook_bookNotInLibrary_returnsFalse() {
        assertFalse(modelManager.hasBook(BOOK1));
    }

    @Test
    public void hasBook_bookInLibrary_returnsTrue() {
        modelManager.addBook(BOOK1);
        assertTrue(modelManager.hasBook(BOOK1));
    }

    @Test
    public void getFilteredBookList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredBookList().remove(0));
    }

    @Test
    public void equals() {
        Library intellibrary = new LibraryBuilder().withBook(BOOK1).withBook(BOOK2).build();
        Library differentIntellibrary = new Library();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(intellibrary, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(intellibrary, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different libraries -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentIntellibrary, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = BOOK1.getName().fullName.split("\\s+");
        modelManager.updateFilteredBookList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)), Mode.NORMAL);
        assertFalse(modelManager.equals(new ModelManager(intellibrary, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredBookList(PREDICATE_SHOW_ALL_BOOKS, Mode.NORMAL);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setLibraryFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(intellibrary, differentUserPrefs)));
    }
}
