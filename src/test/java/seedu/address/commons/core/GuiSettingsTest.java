package seedu.address.commons.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TestUtil.basicEqualsTests;

import java.awt.Point;

import org.junit.jupiter.api.Test;

public class GuiSettingsTest {

    private final GuiSettings defaultGuiSettings = new GuiSettings();

    @Test
    public void constructor() {
        double windowWidth = 200.0;
        double windowHeight = 100.0;
        int xPosition = 50;
        int yPosition = 10;
        Point windowCoordinates = new Point(xPosition, yPosition);
        GuiSettings guiSettings = new GuiSettings(windowWidth, windowHeight, xPosition, yPosition);
        assertEquals(guiSettings.getWindowWidth(), windowWidth);
        assertEquals(guiSettings.getWindowHeight(), windowHeight);
        assertEquals(guiSettings.getWindowCoordinates(), windowCoordinates);
    }

    @Test
    public void toString_defaultObject_stringReturned() {
        String defaultGuiSettingsAsString = "Width : 740.0\n"
                + "Height : 600.0\n"
                + "Position : null";
        assertEquals(defaultGuiSettings.toString(), defaultGuiSettingsAsString);
    }

    @Test
    public void equals() {
        // basic equals tests
        basicEqualsTests(defaultGuiSettings);

        // same values -> returns true
        assertTrue(defaultGuiSettings.equals(new GuiSettings()));
        GuiSettings guiSettings = new GuiSettings(1.0, 1.0, 5, 1);
        assertTrue(guiSettings.equals(guiSettings));

        // different values -> returns false
        assertFalse(defaultGuiSettings.equals(guiSettings));
    }

    @Test
    public void hashCode_test() {
        assertEquals(defaultGuiSettings.hashCode(), defaultGuiSettings.hashCode());

        assertEquals(defaultGuiSettings.hashCode(), new GuiSettings().hashCode());

        GuiSettings guiSettings = new GuiSettings(1.0, 1.0, 5, 1);
        assertNotEquals(defaultGuiSettings.hashCode(), guiSettings.hashCode());
    }
}
