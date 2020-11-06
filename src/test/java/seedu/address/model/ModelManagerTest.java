package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ASSIGNMENT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAssignments.CS1231S_HW;
import static seedu.address.testutil.TypicalAssignments.CS2103T_TUT;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.assignment.NameContainsKeywordsPredicate;
import seedu.address.testutil.ProductiveNusBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new ProductiveNus(), new ProductiveNus(modelManager.getProductiveNus()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setProductiveNusFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setProductiveNusFilePath(Paths.get("new/address/book/file/path"));
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
    public void setProductiveNusFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setProductiveNusFilePath(null));
    }

    @Test
    public void setProductiveNusFilePath_validPath_setsProductiveNusFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setProductiveNusFilePath(path);
        assertEquals(path, modelManager.getProductiveNusFilePath());
    }

    @Test
    public void hasAssignment_nullAssignment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasAssignment(null));
    }

    @Test
    public void hasAssignment_assignmentNotInProductiveNus_returnsFalse() {
        assertFalse(modelManager.hasAssignment(CS1231S_HW));
    }

    @Test
    public void hasAssignment_assignmentInProductiveNus_returnsTrue() {
        modelManager.addAssignment(CS1231S_HW);
        assertTrue(modelManager.hasAssignment(CS1231S_HW));
    }

    @Test
    public void getFilteredAssignmentList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredAssignmentList().remove(0));
    }

    @Test
    public void getRemindedAssignmentsList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(
                UnsupportedOperationException.class, () -> modelManager.getRemindedAssignmentsList().remove(0));
    }

    @Test
    public void equals() {
        ProductiveNus productiveNus = new ProductiveNusBuilder()
                .withAssignment(CS1231S_HW).withAssignment(CS2103T_TUT).build();
        ProductiveNus differentProductiveNus = new ProductiveNus();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(productiveNus, userPrefs, null);
        ModelManager modelManagerCopy = new ModelManager(productiveNus, userPrefs, null);

        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different productiveNus -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentProductiveNus, userPrefs, null)));

        // different filteredList -> returns false
        String[] keywords = CS1231S_HW.getName().fullName.split("\\s+");
        modelManager.updateFilteredAssignmentList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(differentProductiveNus, userPrefs, null)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredAssignmentList(PREDICATE_SHOW_ALL_ASSIGNMENT);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setProductiveNusFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(productiveNus, differentUserPrefs, null)));
    }
}
