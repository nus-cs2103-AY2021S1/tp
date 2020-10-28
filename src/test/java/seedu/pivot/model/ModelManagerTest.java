package seedu.pivot.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.pivot.model.Model.PREDICATE_SHOW_ALL_CASES;
import static seedu.pivot.testutil.Assert.assertThrows;
import static seedu.pivot.testutil.TypicalCases.ALICE_PAULINE_ASSAULT;
import static seedu.pivot.testutil.TypicalCases.BENSON_MEIER_ROBBERY;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.pivot.commons.core.GuiSettings;
import seedu.pivot.model.investigationcase.DetailsContainsKeywordsPredicate;
import seedu.pivot.testutil.PivotBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new Pivot(), new Pivot(modelManager.getPivot()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setPivotFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setPivotFilePath(Paths.get("new/address/book/file/path"));
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
    public void setPivotFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setPivotFilePath(null));
    }

    @Test
    public void setPivotFilePath_validPath_setsPivotFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setPivotFilePath(path);
        assertEquals(path, modelManager.getPivotFilePath());
    }

    @Test
    public void hasCase_nullCase_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasCase(null));
    }

    @Test
    public void hasCase_caseNotInPivot_returnsFalse() {
        assertFalse(modelManager.hasCase(ALICE_PAULINE_ASSAULT));
    }

    @Test
    public void hasCase_caseInPivot_returnsTrue() {
        modelManager.addCase(ALICE_PAULINE_ASSAULT);
        assertTrue(modelManager.hasCase(ALICE_PAULINE_ASSAULT));
    }

    @Test
    public void getFilteredCaseList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredCaseList().remove(0));
    }

    @Test
    public void equals() {
        Pivot pivot = new PivotBuilder().withCase(ALICE_PAULINE_ASSAULT).withCase(BENSON_MEIER_ROBBERY).build();
        Pivot differentPivot = new Pivot();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(pivot, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(pivot, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different Pivot -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentPivot, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = ALICE_PAULINE_ASSAULT.getTitle().getAlphaNum().split("\\s+");
        modelManager.updateFilteredCaseList(new DetailsContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(pivot, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredCaseList(PREDICATE_SHOW_ALL_CASES);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setPivotFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(pivot, differentUserPrefs)));
    }
}
