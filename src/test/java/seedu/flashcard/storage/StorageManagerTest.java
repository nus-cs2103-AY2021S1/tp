package seedu.flashcard.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.flashcard.testutil.TypicalFlashcards.getTypicalFlashcardDeck;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.flashcard.commons.core.GuiSettings;
import seedu.flashcard.model.FlashcardDeck;
import seedu.flashcard.model.ReadOnlyFlashcardDeck;
import seedu.flashcard.model.UserPrefs;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonFlashcardDeckStorage flashcardDeckStorage = new JsonFlashcardDeckStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new StorageManager(flashcardDeckStorage, userPrefsStorage);
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
    public void flashcardDeckReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonFlashcardDeckStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonFlashcardDeckStorageTest} class.
         */
        FlashcardDeck original = getTypicalFlashcardDeck();
        storageManager.saveFlashcardDeck(original);
        ReadOnlyFlashcardDeck retrieved = storageManager.readFlashcardDeck().get();
        assertEquals(original, new FlashcardDeck(retrieved));
    }

    @Test
    public void getFlashcardDeckFilePath() {
        assertNotNull(storageManager.getFlashcardDeckFilePath());
    }

}
