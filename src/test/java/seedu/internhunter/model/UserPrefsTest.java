package seedu.internhunter.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.internhunter.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.internhunter.commons.core.GuiSettings;

public class UserPrefsTest {

    private UserPrefs userPrefs;

    @BeforeEach
    public void setUp() {
        userPrefs = new UserPrefs();
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> userPrefs.setGuiSettings(null));
    }

    @Test
    public void setApplicationItemListFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> userPrefs.setApplicationItemListFilePath(null));
    }

    @Test
    public void setCompanyItemListFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> userPrefs.setCompanyItemListFilePath(null));
    }

    @Test
    public void setProfileItemListFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> userPrefs.setProfileItemListFilePath(null));
    }

    @Test
    public void hashCode_equals() {
        assertEquals(userPrefs.hashCode(), userPrefs.hashCode());
    }

    @Test
    public void equals() {
        // same object -> return true
        assertTrue(userPrefs.equals(userPrefs));

        // null -> return false
        assertFalse(userPrefs.equals(null));

        // different Types -> return false
        assertFalse(userPrefs.equals(0.5f));

        // different Gui Settings
        GuiSettings guiSettings = new GuiSettings(100, 100, 1, 1);
        UserPrefs userPrefsDifferentGuiSettings = new UserPrefs();
        userPrefsDifferentGuiSettings.setGuiSettings(guiSettings);
        assertFalse(userPrefs.equals(userPrefsDifferentGuiSettings));

        Path path = Paths.get("differentPath");
        // different applicationItemListFilePath
        UserPrefs userPrefsDifferentApplicationItemListFilePath = new UserPrefs();
        userPrefsDifferentApplicationItemListFilePath.setApplicationItemListFilePath(path);
        assertFalse(userPrefs.equals(userPrefsDifferentApplicationItemListFilePath));

        // different companyItemListFilePath
        UserPrefs userPrefsDifferentCompanyItemListFilePath = new UserPrefs();
        userPrefsDifferentCompanyItemListFilePath.setCompanyItemListFilePath(path);
        assertFalse(userPrefs.equals(userPrefsDifferentCompanyItemListFilePath));

        // different profileItemListFilePath
        UserPrefs userPrefsDifferentProfileItemListFilePath = new UserPrefs();
        userPrefsDifferentProfileItemListFilePath.setProfileItemListFilePath(path);
        assertFalse(userPrefs.equals(userPrefsDifferentProfileItemListFilePath));
    }

}
