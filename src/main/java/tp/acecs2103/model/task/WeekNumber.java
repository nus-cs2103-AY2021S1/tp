package tp.acecs2103.model.task;

import static java.util.Objects.requireNonNull;
import static tp.acecs2103.commons.util.AppUtil.checkArgument;



/**
 * Represents which week a Task falls under in the task manager.
 * Guarantees: immutable; is valid as declaredi n {@link #isValidWeekNumber(String)}
 * TODO: Check parser and all for parsing of string and integers
 */
public class WeekNumber {

    public static final String MESSAGE_CONSTRAINTS =
            "Week number can only be integer in range [1,13].";

    public final String value;

    /**
     * Constructs a {@code WeekNumber}.
     *
     * @param weekNumber A valid week number.
     */
    public WeekNumber(String weekNumber) {
        requireNonNull(weekNumber);
        checkArgument(isValidWeekNumber(weekNumber), MESSAGE_CONSTRAINTS);
        value = weekNumber;
    }

    /**
     * Check if week number is valid.
     *
     * @param test WeekNumber in String form.
     * @return boolean true if within 1 and 13, false otherwise.
     */
    public static boolean isValidWeekNumber(String test) {
        int weekNumberInteger = Integer.parseInt(test);
        return weekNumberInteger >= 1 && weekNumberInteger <= 13;
    }

    /**
     * Get the int value from the WeekNumber String.
     *
     * @return int value of String.
     */
    public int getWeekValueInt() {
        return Integer.parseInt(value);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null || !(other instanceof WeekNumber)) {
            return false;
        } else {
            return this.value.equals(other.toString());
        }
    }
}
