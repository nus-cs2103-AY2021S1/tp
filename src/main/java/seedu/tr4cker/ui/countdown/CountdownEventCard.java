package seedu.tr4cker.ui.countdown;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.tr4cker.model.countdown.Event;
import seedu.tr4cker.ui.UiPart;

/**
 * An UI component that displays information of a {@code Event}.
 */
public class CountdownEventCard extends UiPart<Region> {

    private static final String FXML = "CountdownEventListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on Tr4cker level 4</a>
     */

    public final Event event;

    @FXML
    private HBox cardPane;

    @FXML
    private Label eventName;

    @FXML
    private Label id;

    @FXML
    private Label eventDate;

    @FXML
    private Label daysRemaining;

    /**
     * Creates a {@code CountdownEventCard} with the given {@code Event} and index to display.
     */
    public CountdownEventCard(Event event, int displayedIndex) {
        super(FXML);
        this.event = event;
        id.setText(displayedIndex + ". ");
        eventName.setText(event.getEventName().eventName);
        eventDate.setText(event.getEventDate().toString());
        int daysLeft = event.getDaysRemaining();
        if (daysLeft < 0) {
            daysRemaining.setText("Over!");
        } else {
            daysRemaining.setText(daysLeft + " days!");
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof seedu.tr4cker.ui.countdown.CountdownEventCard)) {
            return false;
        }

        // state check
        seedu.tr4cker.ui.countdown.CountdownEventCard card = (seedu.tr4cker.ui.countdown.CountdownEventCard) other;
        return id.getText().equals(card.id.getText())
                && event.equals(card.event);
    }
}
