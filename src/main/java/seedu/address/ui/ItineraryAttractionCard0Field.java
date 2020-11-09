package seedu.address.ui;

import seedu.address.model.itinerary.ItineraryAttraction;

public class ItineraryAttractionCard0Field extends ItineraryAttractionCard {

    private static final String FXML = "ItineraryAttractionListCard0Field.fxml";

    /**
     * Creates a {@code AttractionCode} with the given {@code Attraction} and index to display.
     */
    public ItineraryAttractionCard0Field(ItineraryAttraction itineraryAttraction) {
        super(itineraryAttraction, FXML);
    }

}
