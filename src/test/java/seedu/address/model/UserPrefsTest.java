package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;

public class UserPrefsTest {

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        UserPrefs userPref = new UserPrefs();
        assertThrows(NullPointerException.class, () -> userPref.setGuiSettings(null));
    }

    @Test
    public void setVendorManagerFilePath_nullPath_throwsNullPointerException() {
        UserPrefs userPrefs = new UserPrefs();
        assertThrows(NullPointerException.class, () -> userPrefs.setVendorManagerFilePath(null));
    }

    @Test
    public void equals() {
        UserPrefs userPref = new UserPrefs();
        UserPrefs copyOfuserPref = new UserPrefs();
        UserPrefs userPrefDiffFilePath = new UserPrefs();
        UserPrefs userPrefDiffGuiSettings = new UserPrefs();
        userPrefDiffFilePath.setVendorManagerFilePath(Paths.get("different"));
        // Gui settings has different height and width
        userPrefDiffGuiSettings.setGuiSettings(new GuiSettings(400, 400, 0, 0));

        //same object, returns true
        assertTrue(userPref.equals(userPref));

        //same values, returns true
        assertTrue(userPref.equals(copyOfuserPref));

        //different types, returns false
        assertFalse(userPref.equals(5));

        //null, returns false
        assertFalse(userPref.equals(null));

        //different file path, returns false
        assertFalse(userPref.equals(userPrefDiffFilePath));

        //different gui settings, returns false
        assertFalse(userPref.equals(userPrefDiffGuiSettings));

    }

}
