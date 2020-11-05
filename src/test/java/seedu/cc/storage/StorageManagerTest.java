package seedu.cc.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.cc.testutil.TypicalEntries.getTypicalCommonCents;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.cc.commons.core.GuiSettings;
import seedu.cc.model.CommonCents;
import seedu.cc.model.ReadOnlyCommonCents;
import seedu.cc.model.UserPrefs;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonCommonCentsStorage commonCentsStorage = new JsonCommonCentsStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new StorageManager(commonCentsStorage, userPrefsStorage);
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
    public void commonCentsReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonCommonCentsStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonCommonCentsStorageTest} class.
         */
        CommonCents original = getTypicalCommonCents();
        storageManager.saveCommonCents(original);
        ReadOnlyCommonCents retrieved = storageManager.readCommonCents().get();
        assertEquals(original, new CommonCents(retrieved));
    }

    @Test
    public void getCommonCentsFilePath() {
        assertNotNull(storageManager.getCommonCentsFilePath());
    }

}
