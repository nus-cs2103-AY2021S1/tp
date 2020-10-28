package seedu.pivot.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.pivot.testutil.TypicalCases.getTypicalPivot;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.pivot.commons.core.GuiSettings;
import seedu.pivot.model.Pivot;
import seedu.pivot.model.ReadOnlyPivot;
import seedu.pivot.model.UserPrefs;
import seedu.pivot.storage.testutil.ReferenceStorageStub;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() throws IOException {
        JsonPivotStorage pivotStorage = new JsonPivotStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        ReferenceStorage referenceStorage = new ReferenceStorageStub(getTempFilePath("./testDir"));
        storageManager = new StorageManager(pivotStorage, userPrefsStorage, referenceStorage);
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
    public void pivotReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonPivotStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonPivotStorageTest} class.
         */
        Pivot original = getTypicalPivot();
        storageManager.savePivot(original);
        ReadOnlyPivot retrieved = storageManager.readPivot().get();
        assertEquals(original, new Pivot(retrieved));
    }

    @Test
    public void getPivotFilePath() {
        assertNotNull(storageManager.getPivotFilePath());
    }


}
