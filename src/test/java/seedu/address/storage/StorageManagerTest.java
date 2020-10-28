package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.address.testutil.TypicalModules.getTypicalModuleList;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.ModuleList;
import seedu.address.model.ReadOnlyModuleList;
import seedu.address.model.UserPrefs;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonModuleListStorage moduleListStorage = new JsonModuleListStorage(getTempFilePath("ab"));
        JsonContactListStorage contactListStorage = new JsonContactListStorage(getTempFilePath("cd"));
        JsonTodoListStorage todoListStorage = new JsonTodoListStorage(getTempFilePath("ef"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new StorageManager(moduleListStorage, contactListStorage, todoListStorage, userPrefsStorage);
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
    public void moduleListReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonModuleListStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonModuleListStorageTest} class.
         */
        ModuleList original = getTypicalModuleList();
        storageManager.saveModuleList(original);
        ReadOnlyModuleList retrieved = storageManager.readModuleList().get();
        assertEquals(original, new ModuleList(retrieved));
    }
    @Test
    public void getModuleListFilePath() {
        assertNotNull(storageManager.getModuleListFilePath());
    }
}
