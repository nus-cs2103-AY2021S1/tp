package com.eva.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;

/**
 * A ui for the status bar that is displayed at the footer of the application.
 */
public class CurrentPanelHeader extends UiPart<Region> {

    private static final String FXML = "CurrentPanelHeader.fxml";

    @FXML
    private Label panelHeader;

    /**
     * Creates a {@code CurrentPanelHeader} with the given {@code Path}.
     */
    public CurrentPanelHeader(String currentPanelName) {
        super(FXML);
        panelHeader.setText(currentPanelName);
    }

}
