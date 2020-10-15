package seedu.fma.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.fma.model.Model.PREDICATE_SHOW_ALL_LOGS;
import static seedu.fma.testutil.Assert.assertThrows;
import static seedu.fma.testutil.TypicalLogs.LOG_A;
import static seedu.fma.testutil.TypicalLogs.LOG_B;
import static seedu.fma.testutil.TypicalUserPrefs.GUI_SETTINGS_A;
import static seedu.fma.testutil.TypicalUserPrefs.GUI_SETTINGS_B;
import static seedu.fma.testutil.TypicalUserPrefs.INVALID_FILE_PATH;
import static seedu.fma.testutil.TypicalUserPrefs.VALID_FILE_PATH;
import static seedu.fma.testutil.TypicalUserPrefs.getSampleUserPrefs;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.fma.commons.core.GuiSettings;
import seedu.fma.model.util.NameContainsKeywordsPredicate;
import seedu.fma.testutil.LogBookBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new LogBook(), new LogBook(modelManager.getLogBook()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setLogBookFilePath(VALID_FILE_PATH);
        userPrefs.setGuiSettings(GUI_SETTINGS_B);
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setLogBookFilePath(VALID_FILE_PATH);
        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        modelManager.setGuiSettings(GUI_SETTINGS_A);
        assertEquals(GUI_SETTINGS_A, modelManager.getGuiSettings());
    }

    @Test
    public void setLogBookFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setLogBookFilePath(null));
    }

    @Test
    public void setLogBookFilePath_validPath_setsLogBookFilePath() {
        Path path = Paths.get("fma/book/file/path");
        modelManager.setLogBookFilePath(path);
        assertEquals(path, modelManager.getLogBookFilePath());
    }

    @Test
    public void hasLog_nullLog_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasLog(null));
    }

    @Test
    public void hasLog_logNotInLogBook_returnsFalse() {
        assertFalse(modelManager.hasLog(LOG_A));
    }

    @Test
    public void hasLog_logInLogBook_returnsTrue() {
        modelManager.addLog(LOG_A);
        assertTrue(modelManager.hasLog(LOG_A));
    }

    @Test
    public void getFilteredLogList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredLogList().remove(0));
    }

    @Test
    public void equals_validEqualLog_returnTrue() {
        LogBook fmaBook = new LogBookBuilder().withLog(LOG_A).withLog(LOG_B).build();

        // same values -> returns true
        modelManager = new ModelManager(fmaBook, getSampleUserPrefs('A'));
        ModelManager modelManagerCopy = new ModelManager(fmaBook, getSampleUserPrefs('A'));
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));
    }

    @Test
    public void equals_invalidEqualLog_returnFalse() {
        LogBook fmaBook = new LogBookBuilder().withLog(LOG_A).withLog(LOG_B).build();
        LogBook differentLogBook = new LogBook();
        UserPrefs userPrefs = new UserPrefs();
        modelManager = new ModelManager(fmaBook, userPrefs);

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different fmaBook -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentLogBook, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = LOG_A.getExercise().getName().value.split("\\s+");
        modelManager.updateFilteredLogList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(fmaBook, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredLogList(PREDICATE_SHOW_ALL_LOGS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setLogBookFilePath(INVALID_FILE_PATH);
        assertFalse(modelManager.equals(new ModelManager(fmaBook, differentUserPrefs)));
    }
}
