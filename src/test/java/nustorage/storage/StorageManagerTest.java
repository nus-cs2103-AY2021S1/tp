package nustorage.storage;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import nustorage.commons.core.GuiSettings;
import nustorage.model.UserPrefs;


public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;


    @BeforeEach
    public void setUp() {
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));

        JsonFinanceAccountStorage financeAccountStorage = new JsonFinanceAccountStorage(getTempFilePath("finance"));
        JsonInventoryStorage inventoryStorage = new JsonInventoryStorage(getTempFilePath("inventory"));

        storageManager = new StorageManager(financeAccountStorage, inventoryStorage, userPrefsStorage);
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
}
