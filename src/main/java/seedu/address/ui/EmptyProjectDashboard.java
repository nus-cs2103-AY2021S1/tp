package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;

/**
 * An UI component that displays message of a empty dashboard.
 */
public class EmptyProjectDashboard extends UiPart<Region> {
    private static final String FXML = "EmptyProjectDashboard.fxml";
    private static final String EMPTY_DASHBOARD_MSG = "No project to be shown here.";

    @FXML
    private Label message;

    /**
     * Creates a {@code ProjectDashboardCode} with the given {@code Project} and index to display.
     */
    public EmptyProjectDashboard() {
        super(FXML);
        message.setText(EMPTY_DASHBOARD_MSG);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EmptyProjectDashboard)) {
            return false;
        }

        // state check
        EmptyProjectDashboard card = (EmptyProjectDashboard) other;
        return message.getText().equals(card.message.getText());
    }
}
