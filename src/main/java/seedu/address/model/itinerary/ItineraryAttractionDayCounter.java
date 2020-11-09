package seedu.address.model.itinerary;

import java.util.Objects;

/**
 * Child class of an itinerary attraction.
 * Designed to hold the day number in ItineraryAttractionList.
 */
public class ItineraryAttractionDayCounter extends ItineraryAttraction {
    private final int day;

    /**
     * Constructs a Itinerary attraction day counter.
     *
     * @param itineraryAttraction Original itinerary attraction.
     * @param day Day of visit.
     */
    public ItineraryAttractionDayCounter(ItineraryAttraction itineraryAttraction, int day) {
        super(itineraryAttraction.getAttraction(), itineraryAttraction.getStartTime(),
                itineraryAttraction.getEndTime());
        this.day = day;
    }

    public int getDay() {
        return day;
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

        if (!(other instanceof ItineraryAttractionDayCounter)) {
            return false;
        }

        ItineraryAttractionDayCounter otherItineraryAttraction = (ItineraryAttractionDayCounter) other;
        // calls equals of Attraction and checks the timing.
        return super.equals(otherItineraryAttraction)
                && isSameTiming(otherItineraryAttraction)
                && day == otherItineraryAttraction.getDay();
    }

    @Override
    public int hashCode() {
        return Objects.hash(day);
    }

    @Override
    public String toString() {
        return "Day " + day;
    }
}
