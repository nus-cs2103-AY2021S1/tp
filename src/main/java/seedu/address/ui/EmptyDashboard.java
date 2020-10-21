package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;

/**
 * An UI component that displays message of a empty dashboard.
 */
public class EmptyDashboard extends UiPart<Region> {
    private static final String FXML = "EmptyDashboard.fxml";

    @FXML
    private Label message;

    /**
     * Creates a {@code ProjectDashboardCode} with the given {@code Project} and index to display.
     */
    public EmptyDashboard(String msg) {
        super(FXML);
        message.setText(msg);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EmptyDashboard)) {
            return false;
        }

        // state check
        EmptyDashboard card = (EmptyDashboard) other;
        return message.getText().equals(card.message.getText());
    }

}
