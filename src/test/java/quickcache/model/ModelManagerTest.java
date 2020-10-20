package quickcache.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static quickcache.model.Model.PREDICATE_SHOW_ALL_FLASHCARDS;
import static quickcache.testutil.Assert.assertThrows;
import static quickcache.testutil.TypicalFlashcards.RANDOM1;
import static quickcache.testutil.TypicalFlashcards.RANDOM3;
import static quickcache.testutil.TypicalFlashcards.RANDOM_1_TAG;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;

import org.junit.jupiter.api.Test;

import quickcache.commons.core.GuiSettings;
import quickcache.model.flashcard.FlashcardContainsTagPredicate;
import quickcache.testutil.QuickCacheBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new QuickCache(), new QuickCache(modelManager.getQuickCache()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setQuickCacheFilePath(Paths.get("quick/cache/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setQuickCacheFilePath(Paths.get("new/quick/cache/file/path"));
        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        modelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, modelManager.getGuiSettings());
    }

    @Test
    public void setQuickCacheFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setQuickCacheFilePath(null));
    }

    @Test
    public void setQuickCacheFilePath_validPath_setsQuickCacheFilePath() {
        Path path = Paths.get("quick/cache/file/path");
        modelManager.setQuickCacheFilePath(path);
        assertEquals(path, modelManager.getQuickCacheFilePath());
    }

    @Test
    public void hasFlashcard_nullFlashcard_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasFlashcard(null));
    }

    @Test
    public void hasFlashcard_flashcardNotInQuickCache_returnsFalse() {
        assertFalse(modelManager.hasFlashcard(RANDOM1));
    }

    @Test
    public void hasFlashcard_flashcardInQuickCache_returnsTrue() {
        modelManager.addFlashcard(RANDOM1);
        assertTrue(modelManager.hasFlashcard(RANDOM1));
    }

    @Test
    public void getFilteredFlashcardList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () ->
                modelManager.getFilteredFlashcardList().remove(0));
    }

    @Test
    public void equals() {
        QuickCache quickCache = new QuickCacheBuilder().withFlashcard(RANDOM1).withFlashcard(RANDOM3).build();
        QuickCache differentQuickCache = new QuickCache();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(quickCache, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(quickCache, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different quickCache -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentQuickCache, userPrefs)));

        // different filteredList -> returns false
        modelManager.updateFilteredFlashcardList(new FlashcardContainsTagPredicate(
                new HashSet<>(Arrays.asList(RANDOM_1_TAG))));
        assertFalse(modelManager.equals(new ModelManager(quickCache, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredFlashcardList(PREDICATE_SHOW_ALL_FLASHCARDS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setQuickCacheFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(quickCache, differentUserPrefs)));
    }
}
