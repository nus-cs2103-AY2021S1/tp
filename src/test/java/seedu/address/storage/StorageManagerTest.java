package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.address.testutil.TypicalPerson.getTypicalMyFitnessBuddy;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.MyFitnessBuddy;
import seedu.address.model.ReadOnlyMyFitnessBuddy;
import seedu.address.model.UserPrefs;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonMyFitnessBuddyStorage addressBookStorage = new JsonMyFitnessBuddyStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new StorageManager(addressBookStorage, userPrefsStorage);
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
    public void getUserPrefsFilePath() {
        Path actual = storageManager.getUserPrefsFilePath();
        Path expected = testFolder.resolve("prefs");
        assertEquals(actual, expected);
    }

    @Test
    public void myFitnessBuddyReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonMyFitnessBuddyStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonMyFitnessBuddyStorageTest} class.
         */
        MyFitnessBuddy original = getTypicalMyFitnessBuddy();
        storageManager.saveFitnessBuddy(original);
        ReadOnlyMyFitnessBuddy retrieved = storageManager.readFitnessBuddy().get();
        assertEquals(original, new MyFitnessBuddy(retrieved));
    }

    @Test
    public void getMyFitnessBuddyFilePath() {
        assertNotNull(storageManager.getMyFitnessBuddyFilePath());
    }

}
