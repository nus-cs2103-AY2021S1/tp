package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;

/**
 * A ui for the current view display that is displayed at the header of the application.
 */
public class ViewDisplay extends UiPart<Region> {
    private static final String FXML = "ViewDisplay.fxml";

    @FXML
    private TextArea viewDisplay;

    /**
     * Constructor for ViewDisplay.
     */
    public ViewDisplay() {
        super(FXML);
        viewDisplay.setText("MODULES");
        viewDisplay.setDisable(true);
        viewDisplay.getStyleClass().remove(0, 1);
    }

    public void setCurrentView(String view) {
        ObservableList<String> viewDisplayStyle = viewDisplay.getStyleClass();
        viewDisplayStyle.clear();

        switch (view) {
        case "TUTORIAL GROUPS":
            viewDisplayStyle.add("view-display-tg");
            break;

        case "STUDENTS":
            viewDisplayStyle.add("view-display-student");
            break;

        default:
            viewDisplayStyle.add("view-display-module");
            break;
        }

        viewDisplay.setText(view);
    }
}
