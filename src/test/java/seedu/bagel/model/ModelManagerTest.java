package seedu.bagel.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.bagel.model.Model.PREDICATE_SHOW_ALL_FLASHCARDS;
import static seedu.bagel.testutil.Assert.assertThrows;
import static seedu.bagel.testutil.TypicalFlashcards.ALICE;
import static seedu.bagel.testutil.TypicalFlashcards.BENSON;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.bagel.commons.core.GuiSettings;
import seedu.bagel.model.flashcard.TitleContainsKeywordsPredicate;
import seedu.bagel.testutil.BagelBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new Bagel(), new Bagel(modelManager.getBagel()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setBagelFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setBagelFilePath(Paths.get("new/address/book/file/path"));
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
    public void setBagelFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setBagelFilePath(null));
    }

    @Test
    public void setBagelFilePath_validPath_setsBagelFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setBagelFilePath(path);
        assertEquals(path, modelManager.getBagelFilePath());
    }

    @Test
    public void hasFlashcard_nullFlashcard_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasFlashcard(null));
    }

    @Test
    public void hasFlashcard_flashcardNotInBagel_returnsFalse() {
        assertFalse(modelManager.hasFlashcard(ALICE));
    }

    @Test
    public void hasFlashcard_flashcardInBagel_returnsTrue() {
        modelManager.addFlashcard(ALICE);
        assertTrue(modelManager.hasFlashcard(ALICE));
    }

    @Test
    public void getFilteredFlashcardList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredFlashcardList().remove(0));
    }

    @Test
    public void equals() {
        Bagel bagel = new BagelBuilder().withFlashcard(ALICE).withFlashcard(BENSON).build();
        Bagel differentBagel = new Bagel();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(bagel, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(bagel, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different addressBook -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentBagel, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = ALICE.getTitle().fullTitle.split("\\s+");
        modelManager.updateFilteredFlashcardList(new TitleContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(bagel, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredFlashcardList(PREDICATE_SHOW_ALL_FLASHCARDS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setBagelFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(bagel, differentUserPrefs)));
    }
}
