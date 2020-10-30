package seedu.address.model.tutorialgroup;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;

/**
 * Represents a Person's name in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidTimeOfDay(String)}
 */
public class TimeOfDay {

    public static final String MESSAGE_CONSTRAINTS =
            "Time is supposed to take the form of 'HH:MM'";
    public static final String TIME_CONSTRAINTS =
            "Start time is supposed to be BEFORE End time";


    public final LocalTime time;

    /**
     * Constructs a {@code TimeOfDay}.
     *
     * @param time The time of the week.
     */
    public TimeOfDay(String time) {
        requireNonNull(time);
        checkArgument(isValidTimeOfDay(time), MESSAGE_CONSTRAINTS);
        this.time = LocalTime.parse(time);
    }

    /**
     * Returns true if a given string is a valid timeDate format.
     */
    public static boolean isValidTimeOfDay(String time) {
        try {
            LocalTime.parse(time);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    /**
     * Returns true if a given string is a valid timeDate format.
     */
    public static boolean isValidTimes(String start, String end) {
        int startTimeInteger = Integer.parseInt(start.replace(":", ""));
        int endTimeInteger = Integer.parseInt(end.replace(":", ""));
        return endTimeInteger - startTimeInteger > 0;
    }


    @Override
    public String toString() {
        return time.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TimeOfDay // instanceof handles nulls
                && time.equals(((TimeOfDay) other).time)); // state check
    }

    @Override
    public int hashCode() {
        return time.hashCode();
    }

}
