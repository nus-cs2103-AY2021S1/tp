package seedu.address.model.itinerary;

/**
 * Wrapper to hold the time and perform time related functions.
 */
public class ItineraryTime {
    public static final String MESSAGE_CONSTRAINTS = "Time should be of the format HHMM and from 0000 to 2359.";
    public static final String VALIDATION_REGEX = "([01][0-9]|2[0-3])[0-5][0-9]";

    private final int time;

    public ItineraryTime(String time) {
        this.time = Integer.parseInt(time);
    }

    /**
     * Returns if a given string is an valid time.
     */
    public static boolean isValidItineraryTime(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if the time is earlier than time given.
     */
    public boolean isEarlierThan(ItineraryTime time) {
        return this.time < time.time;
    }

    /**
     * Returns ture if the time is later than the time given.
     */
    public boolean isLaterThan(ItineraryTime time) {
        return this.time > time.time;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof ItineraryTime)) {
            return false;
        }

        ItineraryTime otherTime = (ItineraryTime) other;
        return time == otherTime.time; // state check
    }

    @Override
    public String toString() {
        return String.format("%04d", time);
    }

    @Override
    public int hashCode() {
        return time * 103;
    }
}
