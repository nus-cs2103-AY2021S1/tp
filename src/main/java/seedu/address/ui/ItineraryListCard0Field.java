package seedu.address.ui;

import seedu.address.model.itinerary.Itinerary;

public class ItineraryListCard0Field extends ItineraryListCard {

    private static final String FXML = "ItineraryListCard0Field.fxml";

    /**
     * Creates a {@code ItineraryCode} with the given {@code Itinerary} and index to display.
     */
    public ItineraryListCard0Field(Itinerary itinerary, int displayedIndex) {
        super(itinerary, displayedIndex, FXML);
    }

}

