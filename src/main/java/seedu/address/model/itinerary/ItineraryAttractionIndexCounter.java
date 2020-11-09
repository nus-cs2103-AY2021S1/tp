package seedu.address.model.itinerary;

import java.util.Objects;

/**
* Child class of an itinerary attraction.
* Designed to hold the index number in ItineraryAttractionList.
*/
public class ItineraryAttractionIndexCounter extends ItineraryAttraction {
    private final ItineraryAttraction itineraryAttraction;
    private final int index;

    /**
     * Constructs a Itinerary attraction index counter.
     *
     * @param itineraryAttraction Original itinerary attraction.
     * @param index Index of itinerary attraction, relative to the list of attractions in that day.
     */
    public ItineraryAttractionIndexCounter(ItineraryAttraction itineraryAttraction, int index) {
        super(itineraryAttraction.getAttraction(), itineraryAttraction.getStartTime(),
                itineraryAttraction.getEndTime());
        this.itineraryAttraction = itineraryAttraction;
        this.index = index;
    }

    @Override
    public ItineraryAttraction getItineraryAttraction() {
        return itineraryAttraction;
    }

    @Override
    public int getIndex() {
        return index;
    }

    // todo determine if this method should stay
    /**
     * Returns true if both itinerary attractions have the same identity and data fields.
     * This defines a stronger notion of equality between two itinerary attractions.
     * Attraction, startTime, endTime, dayVisiting must be the same to return true.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof ItineraryAttractionIndexCounter)) {
            return false;
        }

        ItineraryAttractionIndexCounter otherItineraryAttraction = (ItineraryAttractionIndexCounter) other;
        // calls equals of Attraction and checks the timing.
        return super.equals(otherItineraryAttraction)
                && isSameTiming(otherItineraryAttraction)
                && index == otherItineraryAttraction.getIndex();
    }

    @Override
    public int hashCode() {
        return Objects.hash(itineraryAttraction, index);
    }

    @Override
    public String toString() {
        return Integer.toString(index);
    }
}
