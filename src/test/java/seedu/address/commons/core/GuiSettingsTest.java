package seedu.address.commons.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

public class GuiSettingsTest {

    private GuiSettings defaultGui = new GuiSettings();
    private GuiSettings testingGui = new GuiSettings(1000, 700, 0, 0);
    private GuiSettings testingGuiCopy = new GuiSettings(testingGui.getWindowWidth(),
                                                testingGui.getWindowHeight(),
                                                testingGui.getWindowCoordinates().x,
                                                testingGui.getWindowCoordinates().y);
    private GuiSettings differentGui = new GuiSettings(200, testingGui.getWindowHeight(),
        testingGui.getWindowCoordinates().x, testingGui.getWindowCoordinates().y);

    @Test
    public void equals() {
        // same object -> returns true
        assertEquals(testingGui, testingGui);
        assertEquals(defaultGui, defaultGui);

        // same values -> returns true
        assertEquals(testingGui, testingGuiCopy);

        // different types -> returns false
        assertNotEquals(null, testingGui);
        assertNotEquals(1, testingGui);

        // different values -> returns false
        assertNotEquals(testingGui, differentGui);
    }

    @Test
    public void hashCodeTest() {
        // same object -> same hashcode
        assertEquals(testingGui.hashCode(), testingGui.hashCode());

        // same value -> same hashcode
        assertEquals(testingGui.hashCode(), testingGuiCopy.hashCode());

        // different value -> different hashcode
        assertNotEquals(testingGui.hashCode(), differentGui.hashCode());
    }

    @Test
    public void toStringTest() {
        assertEquals(testingGuiCopy.toString(), testingGui.toString());
    }
}
