package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.itinerary.Itinerary;

public class ItineraryListCard extends UiPart<Region> {

    public final Itinerary itinerary;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label startEndDate;

    /**
     * Creates a {@code ItineraryCode} with the given {@code Itinerary} and index to display.
     */
    public ItineraryListCard(Itinerary itinerary, int displayedIndex, String fxml) {
        super(fxml);
        this.itinerary = itinerary;

        id.setText(displayedIndex + ". ");

        name.setText(itinerary.getName().fullName);
        name.setWrapText(true);

        startEndDate.setText("\uD83D\uDCC6 " + itinerary.getStartDate().value
                + " to "
                + itinerary.getEndDate().value);

    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ItineraryListCard)) {
            return false;
        }

        // state check
        ItineraryListCard card = (ItineraryListCard) other;
        return id.getText().equals(card.id.getText())
                && itinerary.equals(card.itinerary);
    }

}

