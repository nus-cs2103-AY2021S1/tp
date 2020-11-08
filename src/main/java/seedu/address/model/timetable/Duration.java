package seedu.address.model.timetable;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Duration {

    public static final String MESSAGE_CONSTRAINTS_FORMAT =
            "Duration has to be of the following format: HHmm-HHmm e.g. 1600-1800";

    public static final String MESSAGE_CONSTRAINTS_ORDER =
            "Start time has to be before end time";

    public static final String VALIDATION_REGEX = "^(([0-1][0-9])|2[0-3])[0-5][0-9]-(([0-1][0-9])|2[0-3])[0-5][0-9]$";

    private final LocalTime startTime;

    private final LocalTime endTime;

    /**
     * Constructs a Duration object with a start and end time.
     * @param startTime The start time.
     * @param endTime The end time.
     */
    public Duration(LocalTime startTime, LocalTime endTime) {
        requireAllNonNull(startTime, endTime);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public static boolean isValidDuration(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public static boolean isValidDuration(LocalTime startTime, LocalTime endTime) {
        return startTime.isBefore(endTime);
    }

    /**
     * Returns true if this duration is the same as the other duration.
     * @param otherDuration The other duration to be checked.
     * @return true if this duration is the same as the other duration.
     */
    public boolean isSameDuration(Duration otherDuration) {
        if (otherDuration == this) {
            return true;
        }

        return otherDuration != null
                && otherDuration.getStartTime().equals(startTime)
                && otherDuration.getEndTime().equals(endTime);
    }

    /**
     * Returns true if this duration has overlapping duration as the other duration.
     * @param otherDuration The other duration to be checked.
     * @return true if this duration has overlapping duration as the other duration.
     */
    public boolean hasOverlapDuration(Duration otherDuration) {
        requireNonNull(otherDuration);
        LocalTime otherStartTime = otherDuration.getStartTime();
        LocalTime otherEndTime = otherDuration.getEndTime();

        boolean hasSameStartTime = startTime.equals(otherStartTime);
        boolean hasSameEndTime = endTime.equals(otherEndTime);
        boolean isStartTimeOverlap = startTime.isAfter(otherStartTime) && startTime.isBefore(otherEndTime);
        boolean isEndTimeOverlap = endTime.isAfter(otherStartTime) && endTime.isBefore(otherEndTime);
        boolean isCompletelyOverlap = startTime.isBefore(otherStartTime) && endTime.isAfter(otherEndTime);

        return hasSameStartTime || hasSameEndTime || isStartTimeOverlap || isEndTimeOverlap || isCompletelyOverlap;
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(startTime, endTime);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Duration)) {
            return false;
        }

        Duration otherDuration = (Duration) other;
        return otherDuration.getStartTime().equals(startTime)
                && otherDuration.getEndTime().equals(endTime);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmm");
        return startTime.format(formatter) + "-" + endTime.format(formatter);
    }
}
