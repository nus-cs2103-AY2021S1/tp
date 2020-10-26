package com.eva.commons.core;

import static com.eva.commons.core.PanelState.STAFF_LIST;

import java.awt.Point;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Serializable class that contains the GUI settings.
 * Guarantees: immutable.
 */
public class GuiSettings implements Serializable {

    private static final double DEFAULT_HEIGHT = 560;
    private static final double DEFAULT_WIDTH = 800;
    private static final PanelState DEFAULT_PANEL = STAFF_LIST;

    private final double windowWidth;
    private final double windowHeight;
    private final Point windowCoordinates;
    private PanelState panelState;

    /**
     * Constructs a {@code GuiSettings} with the default height, width, position and panel.
     */
    public GuiSettings() {
        windowWidth = DEFAULT_WIDTH;
        windowHeight = DEFAULT_HEIGHT;
        windowCoordinates = null; // null represent no coordinates
        panelState = DEFAULT_PANEL;
    }

    /**
     * Constructs a {@code GuiSettings} with the specified height, width, position.
     */
    public GuiSettings(double windowWidth, double windowHeight, int xPosition, int yPosition) {
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        windowCoordinates = new Point(xPosition, yPosition);
        this.panelState = DEFAULT_PANEL;
    }

    /**
     * Constructs a {@code GuiSettings} with the specified height, width, position and panel.
     */
    public GuiSettings(double windowWidth, double windowHeight, int xPosition, int yPosition, PanelState panelState) {
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        windowCoordinates = new Point(xPosition, yPosition);
        this.panelState = panelState;
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

    public PanelState getPanelState() {
        return panelState;
    }

    public void setPanelState(PanelState panelState) {
        this.panelState = panelState;
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
                && panelState.equals(o.panelState);
    }

    @Override
    public int hashCode() {
        return Objects.hash(windowWidth, windowHeight, windowCoordinates, panelState);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Width : " + windowWidth + "\n");
        sb.append("Height : " + windowHeight + "\n");
        sb.append("Position : " + windowCoordinates);
        sb.append("Panel State : " + panelState.toString());
        return sb.toString();
    }
}
