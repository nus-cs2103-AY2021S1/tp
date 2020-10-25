package seedu.resireg.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.resireg.testutil.TypicalStudents.getTypicalResiReg;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.resireg.commons.core.GuiSettings;
import seedu.resireg.model.ReadOnlyResiReg;
import seedu.resireg.model.ResiReg;
import seedu.resireg.model.UserPrefs;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonResiRegStorage resiRegStorage = new JsonResiRegStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new StorageManager(resiRegStorage, userPrefsStorage);
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
    public void resiRegReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonResiRegStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonResiRegStorageTest} class.
         */
        ResiReg original = getTypicalResiReg();
        storageManager.saveResiReg(original);
        ReadOnlyResiReg retrieved = storageManager.readResiReg().get();
        assertEquals(original, new ResiReg(retrieved));
    }

    @Test
    public void getResiRegFilePath() {
        assertNotNull(storageManager.getResiRegFilePath());
    }

}
