package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;

class ExerciseModelManagerTest {
    private ExerciseModelManager modelManager = new ExerciseModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new ExerciseBook(), new ExerciseBook(modelManager.getExerciseBook()));
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
    public void setExerciseBookFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setExerciseBookFilePath(null));
    }

    /* Not available because UserPrefs still take in AddressBook instead of ExerciseBook
    @Test
    public void setExerciseBookFilePath_validPath_setsExerciseBookFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setExerciseBookFilePath(path);
        assertEquals(path, modelManager.getExerciseBookFilePath());
    }
     */

    @Test
    public void hasPerson_nullExercise_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasExercise(null));
    }

    /* Omitted due to the use of Stubs
    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(modelManager.hasExercise(PUSH_UP));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        modelManager.addExercise(PUSH_UP);
        assertTrue(modelManager.hasExercise(PUSH_UP));
    }
     */

    @Test
    public void getFilteredPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredExerciseList().remove(0));
    }

    /* Omitted due to the use of Stubs
    @Test
    public void equals() {
        ExerciseBook exerciseBook = new ExerciseBookBuilder().withExercise(PUSH_UP).withExercise(SIT_UP).build();
        ExerciseBook differentExerciseBook = new ExerciseBook();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ExerciseModelManager(exerciseBook, userPrefs);
        ExerciseModelManager modelManagerCopy = new ExerciseModelManager(exerciseBook, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different addressBook -> returns false
        assertFalse(modelManager.equals(new ExerciseModelManager(differentExerciseBook, userPrefs)));

        /*
        // different filteredList -> returns false
        String[] keywords = ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(addressBook, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setAddressBookFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(addressBook, differentUserPrefs)));
    }
    */

}

