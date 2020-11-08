package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.UserPrefs;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonVendorManagerStorage vendorManagerStorage = new JsonVendorManagerStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        JsonPresetManagerStorage orderManagerStorage =
                new JsonPresetManagerStorage(getTempFilePath("presets"));
        JsonProfileManagerStorage profileManagerStorage =
                new JsonProfileManagerStorage(getTempFilePath("profile"));
        storageManager = new StorageManager(
                vendorManagerStorage,
                userPrefsStorage,
                orderManagerStorage,
                profileManagerStorage
        );
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

    //TODO: pass
    //    @Test
    //    public void vendorManagerReadSave() throws Exception {
    //        /*
    //         * Note: This is an integration test that verifies the StorageManager is properly wired to the
    //         * {@link JsonVendorManagerStorage} class.
    //         * More extensive testing of UserPref saving/reading is done in {@link JsonVendorManagerStorageTest}
    //         class.
    //         */
    //        VendorManager original = getTypicalVendorManager();
    //        storageManager.saveVendorManager(original);
    //        ReadOnlyVendorManager retrieved = storageManager.readVendorManager().get();
    //        assertEquals(original, new VendorManager(retrieved));
    //    }

    @Test
    public void getVendorManagerFilePath() {
        assertNotNull(storageManager.getVendorManagerFilePath());
    }

}
