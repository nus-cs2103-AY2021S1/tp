package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import seedu.address.model.itinerary.Itinerary;

public class ItineraryListCard3Field extends ItineraryListCard {

    private static final String FXML = "ItineraryListCard3Field.fxml";

    @FXML
    private Label field1;
    @FXML
    private Label field2;
    @FXML
    private Label field3;

    /**
     * Creates a {@code ItineraryCode} with the given {@code Itinerary} and index to display.
     */
    public ItineraryListCard3Field(Itinerary itinerary, int displayedIndex) {
        super(itinerary, displayedIndex, FXML);

        if (!itinerary.getLocations().isEmpty()) {
            field1.setText("\uD83C\uDF0E " + itinerary.getLocations());
        }

        if (!itinerary.getBudget().value.isEmpty()) {
            field2.setText("\uD83D\uDCB5 " + itinerary.getBudget().toString());
        }

        if (!itinerary.getDescription().value.isEmpty()) {
            field3.setText(itinerary.getDescription().value);
        }

        field1.setWrapText(true);
        field2.setWrapText(true);
        field3.setWrapText(true);
    }

}

