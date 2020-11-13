package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.deliverable.deliverable.Deliverable;

/**
 * An UI component that displays information of a {@code Deliverable}.
 */
public class CalendarDeliverableCard extends UiPart<Region> {

    private static final String FXML = "CalendarListDeliverableCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Deliverable deliverable;

    @FXML
    private HBox cardPane;
    @FXML
    private Label title;
    @FXML
    private Label deadline;
    @FXML
    private Label milestone;
    @FXML
    private Label isCompleted;

    /**
     * Creates a {@code DeliverableCode} with the given {@code Deliverable} and index to display.
     */
    public CalendarDeliverableCard(Deliverable deliverable) {
        super(FXML);
        this.deliverable = deliverable;
        title.setText("Deliverable: " + deliverable.getTitle().value);
        deadline.setText(deliverable.getDeadline().toString());
        if (deliverable.getIsComplete()) {
            isCompleted.setText("completed");
            isCompleted.setStyle("-fx-background-color: #32cd32");
        } else {
            isCompleted.setText("on-going");
            isCompleted.setStyle("-fx-background-color: #ffa500");
        }
        milestone.setText(deliverable.getMilestone().toString());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CalendarDeliverableCard)) {
            return false;
        }

        // state check
        CalendarDeliverableCard card = (CalendarDeliverableCard) other;
        return deliverable.equals(card.deliverable);
    }
}
