package seedu.address.ui;

import seedu.address.model.attraction.Attraction;

public class AttractionCard0Field extends AttractionCard {

    private static final String FXML = "AttractionListCard0Field.fxml";

    /**
     * Creates a {@code AttractionCode} with the given {@code Attraction} and index to display.
     */
    public AttractionCard0Field(Attraction attraction, int displayedIndex) {
        super(attraction, displayedIndex, FXML);
    }

}
