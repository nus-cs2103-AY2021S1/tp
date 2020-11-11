package seedu.bookmark.model;

import static seedu.bookmark.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class UserPrefsTest {

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        UserPrefs userPref = new UserPrefs();
        assertThrows(NullPointerException.class, () -> userPref.setGuiSettings(null));
    }

    @Test
    public void setAddressBookFilePath_nullPath_throwsNullPointerException() {
        UserPrefs userPrefs = new UserPrefs();
        assertThrows(NullPointerException.class, () -> userPrefs.setBookmarkFilePath(null));
    }

    @Test
    public void setSortingPreferences_nullSortingPreferences_throwsAssertionError() {
        UserPrefs userPrefs = new UserPrefs();
        assertThrows(AssertionError.class, () -> userPrefs.setSortingPreference(null));
    }

}
