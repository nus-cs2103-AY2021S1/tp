package seedu.address.ui.cards;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.accommodation.Accommodation;
import seedu.address.ui.UiPart;

/**
 * A UI component that displays information of an {@code Accommodation}.
 */
public class AccommodationCard extends UiPart<Region> {

    private static final String FXML = "cards/AccommodationListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     */

    public final Accommodation accommodation;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label startToEndDates;
    @FXML
    private Label cost;
    @FXML
    private Label address;

    /**
     * Creates an {@code AccommodationCard} with the given {@code Accommodation} and index to display.
     */
    public AccommodationCard(Accommodation accommodation, int displayedIndex) {
        super(FXML);
        this.accommodation = accommodation;
        id.setText(displayedIndex + ". ");
        name.setText(accommodation.getName().toString());
        startToEndDates.setText(accommodation.dateString());
        cost.setText("Cost: $" + accommodation.getCost().toString());
        address.setText("Location: " + accommodation.getLocation().toString());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AccommodationCard)) {
            return false;
        }

        // state check
        AccommodationCard card = (AccommodationCard) other;
        return id.getText().equals(card.id.getText())
                && accommodation.equals(card.accommodation);
    }
}
