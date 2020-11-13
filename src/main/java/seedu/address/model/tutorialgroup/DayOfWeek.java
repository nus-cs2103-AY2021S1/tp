package seedu.address.model.tutorialgroup;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Arrays;
import java.util.List;

/**
 * Represents a Tutorial Group's day of the week in Trackr.
 * Guarantees: immutable; is valid as declared in {@link #isValidDayOfWeek(String)}
 */
public class DayOfWeek {

    public static final String MESSAGE_CONSTRAINTS =
            "DAY_OF_WEEK can only be MON/TUE/WED/THU/FRI/SAT/SUN";


    private final String day;

    /**
     * Constructs a {@code DayOfWeek}.
     *
     * @param day The day of the week.
     */
    public DayOfWeek(String day) {
        requireNonNull(day);
        checkArgument(isValidDayOfWeek(day), MESSAGE_CONSTRAINTS);
        this.day = day;
    }

    /**
     * Returns true if a given string is a valid ID.
     */
    public static boolean isValidDayOfWeek(String day) throws NullPointerException {
        requireNonNull(day);
        List<String> daysOfWeek = Arrays.asList("MON", "TUE", "WED", "THU", "FRI");
        return daysOfWeek.contains(day);
    }


    @Override
    public String toString() {
        return day;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DayOfWeek // instanceof handles nulls
                && day.equals(((DayOfWeek) other).day)); // state check
    }

    @Override
    public int hashCode() {
        return day.hashCode();
    }

}
