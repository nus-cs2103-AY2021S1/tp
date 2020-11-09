package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.booking.Booking;

/**
 * An UI component that displays information of a {@code Booking}.
 */
public class BookingCard extends UiPart<Region> {

    private static final String FXML = "BookingCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on PersonBook level 4</a>
     */

    public final Booking booking;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label personId;
    @FXML
    private Label roomId;
    @FXML
    private Label period;
    @FXML
    private FlowPane archivedTag;

    /**
     * Creates a {@code BookingCard} with the given {@code Booking} and index to display.
     */
    public BookingCard(Booking booking, int displayedIndex) {
        super(FXML);
        this.booking = booking;
        id.setText(String.format("Booking ID: %s", booking.getId().toString()));
        personId.setText(String.format("Person ID: %s", booking.getPersonId().toString()));
        roomId.setText(String.format("Room ID: %s", booking.getRoomId().toString()));
        period.setText(String.format("Period: from %s to %s",
                booking.getStartDate().toString(), booking.getEndDate().toString()));
        if (!booking.isActive()) {
            archivedTag.getChildren().add(new Label("ARCHIVED"));
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof BookingCard)) {
            return false;
        }

        // state check
        BookingCard card = (BookingCard) other;
        return id.getText().equals(card.id.getText())
                && booking.equals(card.booking);
    }
}
