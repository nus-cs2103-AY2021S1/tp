package seedu.zookeep.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.zookeep.testutil.TypicalAnimals.getTypicalZooKeepBook;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.zookeep.commons.core.GuiSettings;
import seedu.zookeep.model.ReadOnlyZooKeepBook;
import seedu.zookeep.model.UserPrefs;
import seedu.zookeep.model.ZooKeepBook;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonZooKeepBookStorage zooKeepBookStorage = new JsonZooKeepBookStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new StorageManager(zooKeepBookStorage, userPrefsStorage);
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
    public void zooKeepBookReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonZooKeepBookStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonZooKeepBookStorageTest} class.
         */
        ZooKeepBook original = getTypicalZooKeepBook();
        storageManager.saveZooKeepBook(original);
        ReadOnlyZooKeepBook retrieved = storageManager.readZooKeepBook().get();
        assertEquals(original, new ZooKeepBook(retrieved));
    }

    @Test
    public void getZooKeepBookFilePath() {
        assertNotNull(storageManager.getZooKeepBookFilePath());
    }

}
