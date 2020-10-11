package seedu.address.model.calendar;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Calendar's Time in the Calendar book.
 * Guarantees: immutable; is valid as declared in {@link #isValidCalendarTime(String)}
 */
public class CalendarTime {

    public static final String MESSAGE_CONSTRAINTS = "Time should only contain numbers, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String time;

    /**
     * Constructs an {@code CalendarTime}.
     *
     * @param time A valid CalendarTime.
     */
    public CalendarTime (String time) {
        requireNonNull(time);
        checkArgument(isValidCalendarTime(time), MESSAGE_CONSTRAINTS);
        this.time = time;
    }

    /**
     * Returns true if a given string is a valid time.
     */
    public static boolean isValidCalendarTime(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return this.time;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CalendarTime // instanceof handles nulls
                && this.time.equals(((CalendarTime) other).time)); // state check
    }

    @Override
    public int hashCode() {
        return this.time.hashCode();
    }

}
