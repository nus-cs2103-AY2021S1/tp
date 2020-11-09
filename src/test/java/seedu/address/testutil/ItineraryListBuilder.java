package seedu.address.testutil;

import seedu.address.model.ItineraryList;
import seedu.address.model.itinerary.Itinerary;

/**
 * A utility class to help with building ItineraryList objects.
 * Example usage: <br>
 *     {@code ItineraryList il = new ItineraryListBuilder().withItinerary(SG_ZOOS_TOUR, PARIS_TRIP).build();}
 */
public class ItineraryListBuilder {

    private ItineraryList itineraryList;

    public ItineraryListBuilder() {
        itineraryList = new ItineraryList();
    }

    public ItineraryListBuilder(ItineraryList itineraryList) {
        this.itineraryList = itineraryList;
    }

    /**
     * Adds a new {@code Itinerary} to the {@code ItineraryList} that we are building.
     */
    public ItineraryListBuilder withItinerary(Itinerary itinerary) {
        itineraryList.addItinerary(itinerary);
        return this;
    }

    public ItineraryList build() {
        return itineraryList;
    }
}

