package seedu.address.model.itinerary;

import java.util.Objects;

import seedu.address.model.attraction.Attraction;
import seedu.address.model.commons.Name;

/**
 * Child class of an attraction which additionally stores start time and end time
 * Is-a attraction specific to a particular Itinerary.
 */
public class ItineraryAttraction extends Attraction {
    private final ItineraryTime startTime;
    private final ItineraryTime endTime;

    /**
     * Constructs a Itinerary attraction.
     *
     * @param a         attraction to visit.
     * @param startTime time to visit the attraction.
     * @param endTime   time to leave the attraction.
     */
    public ItineraryAttraction(Attraction a, ItineraryTime startTime, ItineraryTime endTime) {
        super(a.getName(),
                a.getPhone(),
                a.getEmail(),
                a.getAddress(),
                a.getDescription(),
                a.getLocation(),
                a.getOpeningHours(),
                a.getPriceRange(),
                a.getRating(),
                a.getVisited(),
                a.getTags());
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Returns name of the attraction in this itinerary attraction.
     */
    public Name getName() {
        return super.getName();
    }

    /**
     * Returns the attraction in this itinerary attraction.
     */
    public Attraction getAttraction() {
        return this;
    }

    /**
     * Returns the start time of this itinerary attraction.
     */
    public ItineraryTime getStartTime() {
        return startTime;
    }

    /**
     * Returns the end time of this itinerary attraction.
     */
    public ItineraryTime getEndTime() {
        return endTime;
    }

    /**
     * Method for ItineraryAttractionIndexCounter
     * @return null
     */
    public ItineraryAttraction getItineraryAttraction() {
        return null;
    }

    /**
     * Method for ItineraryAttractionIndexCounter
     * @return 0
     */
    public int getIndex() {
        return 0;
    }

    /**
     * Returns number of filled fields in this itinerary attraction.
     * @return integer of number of filled fields
     */
    public int getNumOfFilledFields() {
        return super.getNumOfFilledFields();
    }

    /**
     * Returns true if both ItineraryAttraction has the same start and end time.
     *
     * @param otherItineraryAttraction the ItineraryAttraction to compare with.
     */
    public boolean isSameTiming(ItineraryAttraction otherItineraryAttraction) {
        return this.startTime.equals(otherItineraryAttraction.startTime)
                && this.endTime.equals(otherItineraryAttraction.endTime);
    }


    // todo remove this as this weaker equal method is not useful.

    /**
     * Returns true if both itinerary attractions are of the same name, have the same visiting timing.
     * This defines a weaker notion of equality between two itinerary attractions.
     *
     * @return otherItineraryAttraction the itineraryAttraction to compare with.
     */
    public boolean isSameItineraryAttraction(ItineraryAttraction otherItineraryAttraction) {
        if (otherItineraryAttraction == this) {
            return true;
        }

        return isSameAttraction(otherItineraryAttraction.getAttraction())
                && isSameTiming(otherItineraryAttraction);
    }

    /**
     * Returns false if timing does not clash with this itinerary attraction.
     */
    public boolean isTimingClash(ItineraryAttraction itineraryAttraction) {
        return (this.getStartTime().isEarlierThan(itineraryAttraction.getEndTime()))
                && (itineraryAttraction.getStartTime().isEarlierThan(this.getEndTime()));
    }

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

        if (!(other instanceof ItineraryAttraction)) {
            return false;
        }

        ItineraryAttraction otherItineraryAttraction = (ItineraryAttraction) other;
        // calls equals of Attraction and checks the timing.
        return super.equals(otherItineraryAttraction)
                && isSameTiming(otherItineraryAttraction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this, startTime, endTime);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(super.toString())
                .append(" Start time: ")
                .append(startTime)
                .append(" End time: ")
                .append(endTime);

        return builder.toString();
    }
}
