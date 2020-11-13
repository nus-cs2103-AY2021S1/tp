package seedu.address.model.calorie;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents the time of the activity for a Input/Output
 */
public class Time {
    public static final String MESSAGE_CONSTRAINTS =
            "Time should be in the format of HHMM and it should not be blank";

    public static final String VALIDATION_REGEX = "^[0-9]{4}$";

    public final String time;

    /**
     * Constructs a {@code Time}.
     *
     * @param time A valid Time.
     */
    public Time(String time) {
        requireNonNull(time);
        checkArgument(isValidTime(time), MESSAGE_CONSTRAINTS);
        this.time = time;
    }

    /**
     * Returns if a given string is a valid Time.
     */
    public static boolean isValidTime(String test) {
        if (test.matches(VALIDATION_REGEX)) {
            int hour = Integer.parseInt(test.substring(0, 2));
            int min = Integer.parseInt(test.substring(2, 4));
            return hour <= 23 && min <= 59;
        } else {
            return false;
        }
    }

    /**
     * Returns true if the current time is after the input time
     */
    public boolean isAfter(Time inputTime) {
        return Integer.parseInt(this.time) >= Integer.parseInt(inputTime.toString());
    }

    @Override
    public String toString() {
        return time;
    }

    /**
     * Returns true if both Times have the same time string.
     * This defines a stronger notion of equality between two times.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Time)) {
            return false;
        }

        Time otherTime = (Time) other;
        return otherTime.time.equals(this.time);
    }
}
