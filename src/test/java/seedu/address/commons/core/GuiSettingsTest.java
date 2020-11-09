package seedu.address.commons.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static seedu.address.commons.core.GuiSettings.DEFAULT_HEIGHT;
import static seedu.address.commons.core.GuiSettings.DEFAULT_WIDTH;

import java.awt.Point;
import java.util.Objects;

import org.junit.jupiter.api.Test;

class GuiSettingsTest {

    private final GuiSettings defaultGui = new GuiSettings();
    @Test
    public void getWindowWidth_validTest() {
        double actual = defaultGui.getWindowWidth();
        assertEquals(DEFAULT_WIDTH, actual);
    }

    @Test
    public void getWindowHeight_validTest() {
        double actual = defaultGui.getWindowHeight();
        assertEquals(DEFAULT_HEIGHT, actual);
    }

    @Test
    public void getWindowCoordinates_validTest() {
        Point actual = defaultGui.getWindowCoordinates();
        assertNull(actual);
    }

    @Test
    public void equals_validTest() {
        Config testConfig = new Config();
        assertFalse(defaultGui.equals(testConfig));
    }

    @Test
    public void hashCode_validTest() {
        int expected = Objects.hash(defaultGui.getWindowWidth(),
                defaultGui.getWindowHeight(), defaultGui.getWindowCoordinates());
        int actual = defaultGui.hashCode();
        assertEquals(expected, actual);
    }
}
