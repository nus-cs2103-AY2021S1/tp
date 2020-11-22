package seedu.forgetfulnus.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.forgetfulnus.testutil.TypicalFlashCards.getTypicalGlossary;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.forgetfulnus.commons.core.GuiSettings;
import seedu.forgetfulnus.model.Glossary;
import seedu.forgetfulnus.model.ReadOnlyGlossary;
import seedu.forgetfulnus.model.UserPrefs;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonGlossaryStorage glossaryStorage = new JsonGlossaryStorage(getTempFilePath("ab"));
        JsonScoreStorage scoreStorage = new JsonScoreStorage(getTempFilePath("sc"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new StorageManager(glossaryStorage, scoreStorage, userPrefsStorage);
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
    public void glossaryReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonGlossaryStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonGlossaryStorageTest} class.
         */
        Glossary original = getTypicalGlossary();
        storageManager.saveGlossary(original);
        ReadOnlyGlossary retrieved = storageManager.readGlossary().get();
        assertEquals(original, new Glossary(retrieved));
    }

    @Test
    public void getGlossaryFilePath() {
        assertNotNull(storageManager.getGlossaryFilePath());
    }

}
