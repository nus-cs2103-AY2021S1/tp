package seedu.resireg.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.resireg.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.resireg.testutil.Assert.assertThrows;
import static seedu.resireg.testutil.TypicalCommandWordAliases.ROOMS_R;
import static seedu.resireg.testutil.TypicalCommandWordAliases.STUDENTS_ST;
import static seedu.resireg.testutil.TypicalRooms.ROOM_ONE;
import static seedu.resireg.testutil.TypicalStudents.ALICE;
import static seedu.resireg.testutil.TypicalStudents.BENSON;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.resireg.commons.core.GuiSettings;
import seedu.resireg.model.student.NameContainsKeywordsPredicate;
import seedu.resireg.testutil.ResiRegBuilder;
import seedu.resireg.testutil.UserPrefsBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new ResiReg(), new ResiReg(modelManager.getResiReg()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setResiRegFilePath(Paths.get("resi/reg/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setResiRegFilePath(Paths.get("new/resi/reg/file/path"));
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
    public void setResiRegFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setResiRegFilePath(null));
    }

    @Test
    public void setResiRegFilePath_validPath_setsResiRegFilePath() {
        Path path = Paths.get("resi/reg/file/path");
        modelManager.setResiRegFilePath(path);
        assertEquals(path, modelManager.getResiRegFilePath());
    }

    @Test
    public void hasStudent_nullStudent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasStudent(null));
    }

    @Test
    public void hasStudent_studentNotInResiReg_returnsFalse() {
        assertFalse(modelManager.hasStudent(ALICE));
    }

    @Test
    public void hasStudent_studentInResiReg_returnsTrue() {
        modelManager.addStudent(ALICE);
        assertTrue(modelManager.hasStudent(ALICE));
    }

    @Test
    public void getFilteredStudentList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredStudentList().remove(0));
    }

    // Rooms
    @Test
    public void getFilteredRoomList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredRoomList().remove(0));
    }

    @Test
    public void hasRoom_nullRoom_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasRoom(null));
    }

    @Test
    public void hasRoom_roomNotInResiReg_returnsFalse() {
        assertFalse(modelManager.hasRoom(ROOM_ONE));
    }

    @Test
    public void hasRoom_roomInResiReg_returnsTrue() {
        modelManager.addRoom(ROOM_ONE);
        assertTrue(modelManager.hasRoom(ROOM_ONE));
    }

    // Allocations (to be added)

    // Command word aliases
    @Test
    public void getAliasList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getCommandWordAliases().remove(0));
    }

    @Test
    public void hasCommandWordAlias_nullAlias_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasCommandWordAlias(null));
    }

    @Test
    public void hasCommandWordAlias_aliasNotInUserPrefs_returnsFalse() {
        assertFalse(modelManager.hasCommandWordAlias(ROOMS_R));
    }

    @Test
    public void hasCommandWordAlias_aliasInUserPrefs_returnsTrue() {
        modelManager.addCommandWordAlias(ROOMS_R);
        assertTrue(modelManager.hasCommandWordAlias(ROOMS_R));
    }

    @Test
    public void equals() {
        ResiReg resiReg = new ResiRegBuilder().withStudent(ALICE).withStudent(BENSON).build();
        ResiReg differentResiReg = new ResiReg();
        UserPrefs userPrefs = new UserPrefsBuilder().withCommandWordAlias(ROOMS_R)
            .withCommandWordAlias(STUDENTS_ST).build();
        UserPrefs differentUserPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(resiReg, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(resiReg, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different resiReg -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentResiReg, userPrefs)));

        // different resiReg -> returns false
        assertFalse(modelManager.equals(new ModelManager(resiReg, differentUserPrefs)));

        // different filteredList -> returns false
        String[] keywords = ALICE.getNameAsString().split("\\s+");
        modelManager.updateFilteredStudentList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(resiReg, userPrefs)));

        // different alias list -> returns false
        modelManager.deleteCommandWordAlias(STUDENTS_ST);
        assertFalse(modelManager.equals(new ModelManager(resiReg, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredStudentList(PREDICATE_SHOW_ALL_PERSONS);
        modelManager.addCommandWordAlias(STUDENTS_ST);

        // different userPrefs -> returns false
        differentUserPrefs.setResiRegFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(resiReg, differentUserPrefs)));
    }
}
