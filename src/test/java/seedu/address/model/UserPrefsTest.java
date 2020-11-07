package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.TagCommand;
import seedu.address.model.tag.TagName;

public class UserPrefsTest {

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        UserPrefs userPref = new UserPrefs();
        assertThrows(NullPointerException.class, () -> userPref.setGuiSettings(null));
    }

    @Test
    public void setAddressBookFilePath_nullPath_throwsNullPointerException() {
        UserPrefs userPrefs = new UserPrefs();
        assertThrows(NullPointerException.class, () -> userPrefs.setAddressBookFilePath(null));
    }

    @Test
    public void equals() {
        UserPrefs firstUserPrefs = new UserPrefs();
        TagName notUserPrefs = new TagName("CS2103");
        UserPrefs secondUserPrefs = new UserPrefs();

        //same object -> return true
        assertTrue(firstUserPrefs.equals(firstUserPrefs));

        //different object -> returns true
        assertFalse(firstUserPrefs.equals(notUserPrefs));

        //same class -> retursn true
        assertTrue(firstUserPrefs.equals(secondUserPrefs));
    }

    @Test
    public void hashcodeCheck_success() {
        UserPrefs userPrefs = new UserPrefs();
        UserPrefs newUserPrefs = new UserPrefs();
        //same object -> returns true
        assertTrue(userPrefs.hashCode() == userPrefs.hashCode());

        //different instances -> returns true
        assertTrue(userPrefs.hashCode() == newUserPrefs.hashCode());
    }

}
