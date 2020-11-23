package seedu.tr4cker.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.tr4cker.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.tr4cker.commons.core.index.Index;

public class UserPrefsTest {

    @Test
    public void testEquals() {
        UserPrefs userPref = new UserPrefs();
        assertEquals(userPref, userPref);

        Index index = Index.fromOneBased(1);
        assertNotEquals(userPref, index);
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        UserPrefs userPref = new UserPrefs();
        assertThrows(NullPointerException.class, () -> userPref.setGuiSettings(null));
    }

    @Test
    public void setTr4ckerFilePath_nullPath_throwsNullPointerException() {
        UserPrefs userPrefs = new UserPrefs();
        assertThrows(NullPointerException.class, () -> userPrefs.setTr4ckerFilePath(null));
    }

}
