package seedu.flashcard.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.flashcard.model.Model.PREDICATE_SHOW_ALL_FLASHCARDS;
import static seedu.flashcard.testutil.Assert.assertThrows;
import static seedu.flashcard.testutil.TypicalFlashcards.FLASHCARD_1;
import static seedu.flashcard.testutil.TypicalFlashcards.FLASHCARD_2;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.flashcard.commons.core.GuiSettings;
import seedu.flashcard.model.flashcard.QuestionContainsKeywordsPredicate;
import seedu.flashcard.testutil.FlashcardDeckBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new FlashcardDeck(), new FlashcardDeck(modelManager.getFlashcardDeck()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setFlashcardDeckFilePath(Paths.get("flashcard/deck/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setFlashcardDeckFilePath(Paths.get("new/flashcard/deck/file/path"));
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
    public void setFlashcardDeckFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setFlashcardDeckFilePath(null));
    }

    @Test
    public void setFlashcardDeckFilePath_validPath_setsFlashcardDeckFilePath() {
        Path path = Paths.get("flashcard/deck/file/path");
        modelManager.setFlashcardDeckFilePath(path);
        assertEquals(path, modelManager.getFlashcardDeckFilePath());
    }

    @Test
    public void hasFlashcard_nullFlashcard_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasFlashcard(null));
    }

    @Test
    public void hasFlashcard_flashcardNotInFlashcardDeck_returnsFalse() {
        assertFalse(modelManager.hasFlashcard(FLASHCARD_1));
    }

    @Test
    public void hasFlashcard_flashcardInFlashcardDeck_returnsTrue() {
        modelManager.addFlashcard(FLASHCARD_1);
        assertTrue(modelManager.hasFlashcard(FLASHCARD_1));
    }

    @Test
    public void getFilteredFlashcardList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () ->
            modelManager.getFilteredFlashcardList().remove(0));
    }

    @Test
    public void equals() {
        FlashcardDeck flashcardDeck = new FlashcardDeckBuilder().withFlashcard(FLASHCARD_1)
                .withFlashcard(FLASHCARD_2).build();
        FlashcardDeck differentFlashcardDeck = new FlashcardDeck();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(flashcardDeck, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(flashcardDeck, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different flashcardDeck -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentFlashcardDeck, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = FLASHCARD_1.getQuestion().toString().split("\\s+");
        modelManager.updateFilteredFlashcardList(new QuestionContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(differentFlashcardDeck, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredFlashcardList(PREDICATE_SHOW_ALL_FLASHCARDS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setFlashcardDeckFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(flashcardDeck, differentUserPrefs)));
    }
}
