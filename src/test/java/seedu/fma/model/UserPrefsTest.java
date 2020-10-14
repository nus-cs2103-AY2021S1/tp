package seedu.fma.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.fma.testutil.Assert.assertThrows;
import static seedu.fma.testutil.TypicalUserPrefs.VALID_FILE_PATH;

import java.util.Objects;

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
        assertThrows(NullPointerException.class, () -> userPrefs.setLogBookFilePath(null));
    }

    @Test
    void testEquals() {
        UserPrefs userPrefs = new UserPrefs();
        UserPrefs differentUserPrefs = new UserPrefs();

        // Different type -> return False
        assertFalse(userPrefs.equals(12));

        // Same object -> return True
        assertTrue(userPrefs.equals(userPrefs));

        // Different userPrefs -> returns false
        differentUserPrefs.setLogBookFilePath(VALID_FILE_PATH);
        assertFalse(userPrefs.equals(differentUserPrefs));
    }

    @Test
    void testHashCode() {
        UserPrefs userPrefs = new UserPrefs();
        assertTrue(userPrefs.hashCode() == Objects.hash(userPrefs.getGuiSettings(),
                userPrefs.getLogBookFilePath()));
    }
}
