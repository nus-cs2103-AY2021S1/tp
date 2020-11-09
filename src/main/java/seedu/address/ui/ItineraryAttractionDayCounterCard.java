package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.itinerary.ItineraryAttraction;

/**
 * An UI component that displays information of a {@code ItineraryAttractionDayCounter}.
 */
public class ItineraryAttractionDayCounterCard extends UiPart<Region> {

    private static final String FXML = "ItineraryAttractionDayCounterCard.fxml";

    public final ItineraryAttraction itineraryAttractionDayCounter;

    @FXML
    private HBox cardPane;
    @FXML
    private Label day;

    /**
     * Creates a {@code ItineraryAttractionDayCounterCard} with the given {@code ItineraryAttractionDayCounter}.
     */
    public ItineraryAttractionDayCounterCard(ItineraryAttraction itineraryAttractionDayCounter) {
        super(FXML);
        this.itineraryAttractionDayCounter = itineraryAttractionDayCounter;
        day.setText(itineraryAttractionDayCounter.toString());
    }


    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ItineraryAttractionDayCounterCard)) {
            return false;
        }

        // state check
        ItineraryAttractionDayCounterCard card = (ItineraryAttractionDayCounterCard) other;
        return day.getText().equals(card.day.getText())
                && itineraryAttractionDayCounter.equals(card.itineraryAttractionDayCounter);
    }
}
