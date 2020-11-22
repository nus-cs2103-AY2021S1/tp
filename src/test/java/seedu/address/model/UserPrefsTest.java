package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TestUtil.basicEqualsTests;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;

public class UserPrefsTest {

    private final UserPrefs defaultUserPrefs = new UserPrefs();
    private final GuiSettings differentGuiSettings = new GuiSettings(1.0, 1.0, 20, 0);
    private final UserPrefs userPrefsDifferentGuiSettings = new UserPrefs();
    private final Path differentFilePath = Path.of("different/file/path");
    private final UserPrefs userPrefsDifferentFilePath = new UserPrefs();

    @BeforeEach
    public void setUp() {
        userPrefsDifferentGuiSettings.setGuiSettings(differentGuiSettings);
        userPrefsDifferentFilePath.setTbmManagerFilePath(differentFilePath);
    }

    @Test
    public void constructor() {
        // Null user prefs passed in -> throws NullPointerException
        assertThrows(NullPointerException.class, () -> new UserPrefs(null));

        UserPrefs newUserPrefs = new UserPrefs(userPrefsDifferentFilePath);
        assertEquals(newUserPrefs.getTbmManagerFilePath(), differentFilePath);

        newUserPrefs = new UserPrefs(userPrefsDifferentGuiSettings);
        assertEquals(newUserPrefs.getGuiSettings(), differentGuiSettings);
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> defaultUserPrefs.setGuiSettings(null));
    }

    @Test
    public void setTbmManagerFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> defaultUserPrefs.setTbmManagerFilePath(null));
    }

    @Test
    public void equals() {
        // basic equals tests
        basicEqualsTests(defaultUserPrefs);

        // same gui settings and file path -> returns true
        assertTrue(defaultUserPrefs.equals(new UserPrefs()));

        // different gui settings -> returns false
        assertFalse(defaultUserPrefs.equals(userPrefsDifferentGuiSettings));

        // different file paths -> returns false
        assertFalse(defaultUserPrefs.equals(userPrefsDifferentFilePath));
    }

    @Test
    public void hashCode_test() {
        // same object -> returns same hashcode
        assertEquals(defaultUserPrefs.hashCode(), defaultUserPrefs.hashCode());

        // same gui settings and file path -> returns same hashcode
        assertEquals(defaultUserPrefs.hashCode(), new UserPrefs().hashCode());

        // different gui settings -> returns different hashcode
        assertNotEquals(defaultUserPrefs.hashCode(), userPrefsDifferentGuiSettings.hashCode());

        // different file paths -> returns different hashcode
        assertNotEquals(defaultUserPrefs.hashCode(), userPrefsDifferentFilePath.hashCode());
    }

    @Test
    public void toString_test() {
        assertEquals(defaultUserPrefs.toString(),
                "Gui Settings : " + defaultUserPrefs.getGuiSettings().toString()
                    + "\nLocal data file location : " + defaultUserPrefs.getTbmManagerFilePath().toString());
    }

}
