package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;

public class UserPrefsTest {

    private final UserPrefs testUserPrefs = new UserPrefs();
    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        UserPrefs userPref = new UserPrefs();
        assertThrows(NullPointerException.class, () -> userPref.setGuiSettings(null));
    }

    @Test
    public void setGradPadFilePath_nullPath_throwsNullPointerException() {
        UserPrefs userPrefs = new UserPrefs();
        assertThrows(NullPointerException.class, () -> userPrefs.setGradPadFilePath(null));
    }

    @Test
    public void equalsMethod_sameObjectTest() {
        assertTrue(testUserPrefs.equals(testUserPrefs));
    }

    @Test
    public void equalsMethod_differentObjectTest() {
        GradPad testGradPad = new GradPad();
        assertFalse(testUserPrefs.equals(testGradPad));
    }

    @Test
    public void hashCode_validTest() {
        GuiSettings testGuiSettings = new GuiSettings();
        Path testPath = Paths.get("data" , "gradpad.json");
        int expected = Objects.hash(testGuiSettings, testPath);
        int actual = testUserPrefs.hashCode();
        assertEquals(expected, actual);
    }
}
