package seedu.fma.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.fma.testutil.TypicalLogs.getTypicalLogBook;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.fma.commons.core.GuiSettings;
import seedu.fma.model.LogBook;
import seedu.fma.model.ReadOnlyLogBook;
import seedu.fma.model.UserPrefs;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonLogBookStorage logBookStorage = new JsonLogBookStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new StorageManager(logBookStorage, userPrefsStorage);
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
    public void logBookReadSave() throws Exception {
        /* Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonLogBookStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonLogBookStorageTest} class.
         */
        LogBook original = getTypicalLogBook();
        storageManager.saveLogBook(original);
        ReadOnlyLogBook retrieved = storageManager.readLogBook().get();
        assertEquals(original, new LogBook(retrieved));
    }

    @Test
    public void getLogBookFilePath() {
        assertNotNull(storageManager.getLogBookFilePath());
    }

}
