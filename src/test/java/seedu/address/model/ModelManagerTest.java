package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_MODULES;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_STUDENTS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TUTORIALGROUPS;

import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalModules.CS2103T;
import static seedu.address.testutil.TypicalStudents.ALEX;
import static seedu.address.testutil.TypicalTutorialGroups.T05;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.module.ModuleContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.tutorialgroup.TutorialContainsKeywordsPredicate;
import seedu.address.testutil.TrackrBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new Trackr(), new Trackr(modelManager.getModuleList()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setAddressBookFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setAddressBookFilePath(Paths.get("new/address/book/file/path"));
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
    public void setTrackrFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setTrackrFilePath(null));
    }

    @Test
    public void setTrackrFilePath_validPath_setsAddressBookFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setTrackrFilePath(path);
        assertEquals(path, modelManager.getTrackrFilePath());
    }

    @Test
    public void hasModule_nullModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasModule(null));
    }

    @Test
    public void hasTutorialGroup_nullTutorialGroup_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasTutorialGroup(null));
    }

    @Test
    public void hasStudent_nullStudent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasStudent(null));
    }

    @Test
    public void hasModule_moduleNotInTrackr_returnsFalse() {
        assertFalse(modelManager.hasModule(CS2103T));
    }

    @Test
    public void hasTutorialGroup_tutorialGroupNotInTrackr_returnsFalse() {
        assertFalse(modelManager.hasTutorialGroup(T05));
    }

    @Test
    public void hasStudent_studentNotInTrackr_returnsFalse() {
        assertFalse(modelManager.hasStudent(ALEX));
    }

    @Test
    public void hasModule_moduleInTrackr_returnsTrue() {
        modelManager.addModule(CS2103T);
        assertTrue(modelManager.hasModule(CS2103T));
    }

    @Test
    public void hasTutorialGroup_tutorialGroupInTrackr_returnsTrue() {
        modelManager.addTutorialGroup(T05);
        assertTrue(modelManager.hasTutorialGroup(T05));
    }

    @Test
    public void hasStudent_studentInTrackr_returnsTrue() {
        modelManager.addStudent(ALEX);
        assertTrue(modelManager.hasStudent(ALEX));
    }

    @Test
    public void getFilteredModuleList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredModuleList().remove(0));
    }

    @Test
    public void getFilteredTutorialGroupList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> modelManager.getFilteredTutorialGroupList().remove(0)
        );
    }

    @Test
    public void getFilteredStudentList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredStudentList().remove(0));
    }

    @Test
    public void equals() {
        Trackr trackr = new TrackrBuilder()
                .withModule(CS2103T)
                .withTutorialGroup(T05, CS2103T)
                .withStudent(ALEX, T05, CS2103T)
                .build();
        Trackr differentTrackr = new Trackr();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(trackr, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(trackr, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different addressBook -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentTrackr, userPrefs)));

        // different module filteredList -> returns false

        String[] moduleKeywords = CS2103T.getModuleId().getId().split("\\s+");

        modelManager.updateFilteredModuleList(new ModuleContainsKeywordsPredicate(Arrays.asList(moduleKeywords)));
        assertFalse(modelManager.equals(new ModelManager(trackr, userPrefs)));

        // different tutorial group filteredList -> returns false
        String[] tutorialGroupKeywords = T05.getId().id.split("\\s+");
        modelManager.updateFilteredTutorialGroupList(
                        new TutorialContainsKeywordsPredicate(Arrays.asList(tutorialGroupKeywords))
        );
        assertFalse(modelManager.equals(new ModelManager(trackr, userPrefs)));

        // different student filteredList -> returns false
        String[] studentKeywords = ALEX.getName().fullName.split("\\s+");
        modelManager.updateFilteredStudentList(new NameContainsKeywordsPredicate(Arrays.asList(studentKeywords)));
        assertFalse(modelManager.equals(new ModelManager(trackr, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredModuleList(PREDICATE_SHOW_ALL_MODULES);
        modelManager.updateFilteredTutorialGroupList(PREDICATE_SHOW_ALL_TUTORIALGROUPS);
        modelManager.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setAddressBookFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(trackr, differentUserPrefs)));
    }
}
