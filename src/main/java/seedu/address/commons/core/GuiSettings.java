package seedu.address.commons.core;

import seedu.address.ui.Theme;
import seedu.address.ui.ThemeSet;

import java.awt.Point;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Serializable class that contains the GUI settings.
 * Guarantees: immutable.
 */
public class GuiSettings implements Serializable {

    private static final double DEFAULT_HEIGHT = 600;
    private static final double DEFAULT_WIDTH = 740;
    private static final Theme DEFAULT_THEME = ThemeSet.DARK_THEME;

    private final double windowWidth;
    private final double windowHeight;
    private final Point windowCoordinates;

    private final Theme uiTheme;

    /**
     * Constructs a {@code GuiSettings} with the default height, width, position and theme.
     */
    public GuiSettings() {
        windowWidth = DEFAULT_WIDTH;
        windowHeight = DEFAULT_HEIGHT;
        windowCoordinates = null; // null represent no coordinates
        uiTheme = DEFAULT_THEME;
    }

    /**
     * Constructs a {@code GuiSettings} with the specified height, width, position and theme.
     */
    public GuiSettings(double windowWidth, double windowHeight, int xPosition, int yPosition, Theme uiTheme) {
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        windowCoordinates = new Point(xPosition, yPosition);
        this.uiTheme = uiTheme;
    }

    public double getWindowWidth() {
        return windowWidth;
    }

    public double getWindowHeight() {
        return windowHeight;
    }

    public Point getWindowCoordinates() {
        return windowCoordinates != null ? new Point(windowCoordinates) : null;
    }

    public Theme getUiTheme() {
        return uiTheme;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof GuiSettings)) { //this handles null as well.
            return false;
        }

        GuiSettings o = (GuiSettings) other;

        return windowWidth == o.windowWidth
                && windowHeight == o.windowHeight
                && Objects.equals(windowCoordinates, o.windowCoordinates)
                && Objects.equals(uiTheme, o.uiTheme);
    }

    @Override
    public int hashCode() {
        return Objects.hash(windowWidth, windowHeight, windowCoordinates);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Width : " + windowWidth + "\n");
        sb.append("Height : " + windowHeight + "\n");
        sb.append("Position : " + windowCoordinates + "\n");
        sb.append("Theme: " + uiTheme);
        return sb.toString();
    }
}
