package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;

/**
 * A ui for the current view display that is displayed at the header of the application.
 */
public class ViewDisplay extends UiPart<Region> {
    private static final String FXML = "viewDisplay.fxml";

    @FXML
    private TextArea viewDisplay;

    /**
     * Constructor for ViewDisplay.
     */
    public ViewDisplay() {
        super(FXML);
        viewDisplay.setText("MODULES");
        viewDisplay.setDisable(true);
    }

    public void setCurrentView(String view) {
        viewDisplay.setText(view);
    }
}
