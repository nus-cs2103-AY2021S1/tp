package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalTime;
import java.util.Objects;

public class Duration {

    public static final String MESSAGE_CONSTRAINTS_FORMAT =
            "Duration has to be of the following format: HHmm-HHmm e.g. 1600-1800";

    public static final String MESSAGE_CONSTRAINTS_ORDER =
            "Start time has to be before end time";

    public static final String VALIDATION_REGEX = "/^([0-1][0-9]|2[0-3])[0-5][0-9]-([0-1][0-9]|2[0-3])[0-5][0-9]$/";

    private final LocalTime startTime;

    private final LocalTime endTime;

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

    public boolean isSameDuration(Duration otherDuration) {
        if (otherDuration == this) {
            return true;
        }

        return otherDuration != null
                && otherDuration.getStartTime().equals(startTime)
                && otherDuration.getEndTime().equals(endTime);
    }

    public boolean hasOverlapDuration(Duration otherDuration) {
        requireNonNull(otherDuration);
        LocalTime otherStartTime = otherDuration.getStartTime();
        LocalTime otherEndTime = otherDuration.getEndTime();

        boolean isStartTimeOverlap = startTime.isAfter(otherStartTime) && startTime.isBefore(otherEndTime);
        boolean isEndTimeOverlap = endTime.isAfter(otherStartTime) && endTime.isBefore(otherEndTime);
        boolean isCompletelyOverlap = startTime.isBefore(otherStartTime) && endTime.isAfter(otherEndTime);

        return isStartTimeOverlap || isEndTimeOverlap || isCompletelyOverlap;
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(startTime, endTime);
    }

    @Override
    public String toString() {
        return startTime.toString() + " to " + endTime.toString();
    }
}
