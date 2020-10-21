package seedu.expense.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.expense.testutil.TypicalExpenses.getTypicalExpenseBook;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.expense.commons.core.GuiSettings;
import seedu.expense.model.ExpenseBook;
import seedu.expense.model.ReadOnlyExpenseBook;
import seedu.expense.model.UserPrefs;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonExpenseBookStorage expenseBookStorage = new JsonExpenseBookStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        JsonAliasMapStorage aliasMapStorage = new JsonAliasMapStorage(getTempFilePath("als"));
        storageManager = new StorageManager(expenseBookStorage, userPrefsStorage, aliasMapStorage);
    }

    private Path getTempFilePath(String fileName) {
        return testFolder.resolve(fileName);
    }

    @Test
    public void prefsReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonUserPrefsStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonUserPrefsStorageTest} class.
         */
        UserPrefs original = new UserPrefs();
        original.setGuiSettings(new GuiSettings(300, 600, 4, 6));
        storageManager.saveUserPrefs(original);
        UserPrefs retrieved = storageManager.readUserPrefs().get();
        assertEquals(original, retrieved);
    }

    @Test
    public void expenseBookReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonExpenseBookStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonExpenseBookStorageTest} class.
         */
        ExpenseBook original = getTypicalExpenseBook();
        storageManager.saveExpenseBook(original);
        ReadOnlyExpenseBook retrieved = storageManager.readExpenseBook().get();
        assertEquals(original, new ExpenseBook(retrieved));
    }

    @Test
    public void getExpenseBookFilePath() {
        assertNotNull(storageManager.getExpenseBookFilePath());
    }

}
