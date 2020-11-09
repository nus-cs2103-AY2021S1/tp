package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import seedu.address.model.itinerary.Itinerary;

public class ItineraryListCard1Field extends ItineraryListCard {

    private static final String FXML = "ItineraryListCard1Field.fxml";

    @FXML
    private Label field1;


    /**
     * Creates a {@code ItineraryCode} with the given {@code Itinerary} and index to display.
     */
    public ItineraryListCard1Field(Itinerary itinerary, int displayedIndex) {
        super(itinerary, displayedIndex, FXML);

        if (!itinerary.getLocations().isEmpty()) {
            field1.setText("\uD83C\uDF0E " + itinerary.getLocations());
        } else if (!itinerary.getBudget().value.isEmpty()) {
            field1.setText("\uD83D\uDCB5 " + itinerary.getBudget().toString());
        } else if (!itinerary.getDescription().value.isEmpty()) {
            field1.setText(itinerary.getDescription().value);
        }
        field1.setWrapText(true);
    }
}

