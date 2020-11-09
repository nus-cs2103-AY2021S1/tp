package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.itinerary.Itinerary;

/**
 * Unmodifiable view of a itinerary list
 */
public interface ReadOnlyItineraryList {

    /**
     * Returns an unmodifiable view of the itinerary list.
     * This list will not contain any duplicate itineraries.
     */
    ObservableList<Itinerary> getItineraryList();

    Itinerary getCurrentItinerary();
}

