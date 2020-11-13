package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_DAYS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalDays.DAY1;
import static seedu.address.testutil.TypicalDays.DAY2;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
//import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.day.Day;
//import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.testutil.MyFitnessBuddyBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new MyFitnessBuddy(), new MyFitnessBuddy(modelManager.getMyFitnessBuddy()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setMyFitnessBuddyFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setMyFitnessBuddyFilePath(Paths.get("new/address/book/file/path"));
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
    public void setMyFitnessBuddyFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setMyFitnessBuddyFilePath(null));
    }

    @Test
    public void setMyFitnessBuddyFilePath_validPath_setsMyFitnessBuddyFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setMyFitnessBuddyFilePath(path);
        assertEquals(path, modelManager.getMyFitnessBuddyFilePath());
    }

    @Test
    public void hasDay_nullDay_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasDay((Day) null));
    }

    @Test
    public void hasDay_nullDate_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasDay((LocalDate) null));
    }


    @Test
    public void hasDay_dayNotInDayList_returnsFalse() {
        assertFalse(modelManager.hasDay(DAY1));
    }

    //    @Test
    public void hasDay_dayInMyFitnessBuddy_returnsTrue() {
        modelManager.addDay(DAY1);
        assertTrue(modelManager.hasDay(DAY1));
    }

    @Test
    public void getFilteredDayList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredDayList().remove(0));
    }
    //    @Test
    public void equals() {
        MyFitnessBuddy myFitnessBuddy = new MyFitnessBuddyBuilder().withDay(DAY1).withDay(DAY2).build();
        MyFitnessBuddy differentFitnessBuddy = new MyFitnessBuddy();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(myFitnessBuddy, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(myFitnessBuddy, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different myFitnessBuddy -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentFitnessBuddy, userPrefs)));

        // different filteredList -> returns false
        //        String[] keywords = DAY1.getDate().value.split("\\s+");
        //        modelManager.updateFilteredDayList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        //        assertFalse(modelManager.equals(new ModelManager(myFitnessBuddy, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredDayList(PREDICATE_SHOW_ALL_DAYS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setMyFitnessBuddyFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(myFitnessBuddy, differentUserPrefs)));
    }
}
