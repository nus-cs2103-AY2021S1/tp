package trackitnus.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static trackitnus.testutil.typical.TypicalTrackIter.getTypicalTrackIter;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import trackitnus.commons.core.GuiSettings;
import trackitnus.model.ReadOnlyTrackIter;
import trackitnus.model.TrackIter;
import trackitnus.model.UserPrefs;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonTrackIterStorage trackIterStorage = new JsonTrackIterStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new StorageManager(trackIterStorage, userPrefsStorage);
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
    public void trackIterReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonTrackIterStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonTrackIterStorageTest} class.
         */
        TrackIter original = getTypicalTrackIter();
        storageManager.saveTrackIter(original);
        ReadOnlyTrackIter retrieved = storageManager.readTrackIter().get();
        assertEquals(original, new TrackIter(retrieved));
    }

    @Test
    public void getTrackIterFilePath() {
        assertNotNull(storageManager.getTrackIterFilePath());
    }

}
