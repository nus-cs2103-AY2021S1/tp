package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import seedu.address.model.itinerary.Itinerary;

public class ItineraryListCard2Field extends ItineraryListCard {

    private static final String FXML = "ItineraryListCard2Field.fxml";

    @FXML
    private Label field1;
    @FXML
    private Label field2;

    /**
     * Creates a {@code ItineraryCode} with the given {@code Itinerary} and index to display.
     */
    public ItineraryListCard2Field(Itinerary itinerary, int displayedIndex) {
        super(itinerary, displayedIndex, FXML);

        int filledFields = 0;

        if (!itinerary.getLocations().isEmpty()) {
            field1.setText("\uD83C\uDF0E " + itinerary.getLocations());
            filledFields++;
        }

        if (!itinerary.getBudget().value.isEmpty()) {
            if (filledFields == 0) {
                field1.setText("\uD83D\uDCB5 " + itinerary.getBudget().toString());
            } else {
                field2.setText("\uD83D\uDCB5 " + itinerary.getBudget().toString());
            }
            filledFields++;
        }

        if (!itinerary.getDescription().value.isEmpty()) {
            if (filledFields == 0) {
                field1.setText(itinerary.getDescription().value);
            } else {
                field2.setText(itinerary.getDescription().value);
            }
        }

        field1.setWrapText(true);
        field2.setWrapText(true);
    }

}

