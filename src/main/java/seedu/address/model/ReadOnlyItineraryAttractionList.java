package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.itinerary.ItineraryAttraction;

/**
 * Unmodifiable view of an itinerary attraction list
 */
public interface ReadOnlyItineraryAttractionList {

    /**
     * Returns an unmodifiable view of the itinerary attraction list.
     */
    ObservableList<ItineraryAttraction> getItineraryAttractionList();
}

