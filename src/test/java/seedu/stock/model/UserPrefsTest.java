package seedu.stock.model;

import static seedu.stock.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class UserPrefsTest {
    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        UserPrefs userPref = new UserPrefs();
        assertThrows(NullPointerException.class, () -> userPref.setGuiSettings(null));
    }

    @Test
    public void setStockBookFilePath_nullPath_throwsNullPointerException() {
        UserPrefs userPrefs = new UserPrefs();
        assertThrows(NullPointerException.class, () -> userPrefs.setStockBookFilePath(null));
    }
}
