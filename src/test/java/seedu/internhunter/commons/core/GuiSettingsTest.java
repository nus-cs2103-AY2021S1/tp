package seedu.internhunter.commons.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Point;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GuiSettingsTest {

    private GuiSettings defaultGuiSettings;
    private GuiSettings userGuiSettings;

    @BeforeEach
    public void setUp() {
        defaultGuiSettings = new GuiSettings();
        userGuiSettings = new GuiSettings(700, 900, 200, 300); // this are fake values;
    }

    @Test
    public void getWindowWidth_defaultValues_equals() {
        assertEquals(defaultGuiSettings.getWindowWidth(), 932);
    }

    @Test
    public void getWindowWidth_userValues_equals() {
        assertEquals(userGuiSettings.getWindowWidth(), 700);
    }

    @Test
    public void getWindowHeight_defaultValues_equals() {
        assertEquals(defaultGuiSettings.getWindowHeight(), 743);
    }

    @Test
    public void getWindowHeight_userValues_equals() {
        assertEquals(userGuiSettings.getWindowHeight(), 900);
    }

    @Test
    public void getWindowCoordinates_defaultValues_equalsNull() {
        assertNull(defaultGuiSettings.getWindowCoordinates());
    }

    @Test
    public void getWindowCoordinates_userValues_equals() {
        assertEquals(userGuiSettings.getWindowCoordinates(), new Point(200, 300));
    }

    @Test
    public void hashCode_equals() {
        // same object -> same hash code
        assertEquals(defaultGuiSettings.hashCode(), defaultGuiSettings.hashCode());
        assertEquals(userGuiSettings.hashCode(), userGuiSettings.hashCode());

        // same values -> same hash code
        assertEquals(defaultGuiSettings.hashCode(), new GuiSettings().hashCode());
        assertEquals(userGuiSettings.hashCode(), new GuiSettings(700, 900, 200, 300).hashCode());
    }

    @Test
    public void equals() {
        // same object -> return true
        assertTrue(defaultGuiSettings.equals(defaultGuiSettings));
        assertTrue(userGuiSettings.equals(userGuiSettings));

        // same values -> return true
        assertTrue(defaultGuiSettings.equals(new GuiSettings()));
        assertTrue(userGuiSettings.equals(new GuiSettings(700, 900, 200, 300)));

        // null -> false
        assertFalse(defaultGuiSettings.equals(null));
        assertFalse(userGuiSettings.equals(null));

        // different type -> false
        assertFalse(defaultGuiSettings.equals(0.5f));
        assertFalse(userGuiSettings.equals(0.5f));

        // different window width -> false
        GuiSettings guiSettingsDifferentWindowWith = new GuiSettings(1234, 1234, 12, 34);
        assertFalse(userGuiSettings.equals(guiSettingsDifferentWindowWith));

        // different window coordinates -> false
        GuiSettings guiSettingsDifferentWindowCoordinate = new GuiSettings(700, 900, 20, 30);
        assertFalse(userGuiSettings.equals(guiSettingsDifferentWindowCoordinate));
    }
}
