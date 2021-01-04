package seedu.homerce.model.appointment;

import static java.util.Objects.requireNonNull;
import static seedu.homerce.commons.util.AppUtil.checkArgument;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.function.Predicate;

public class TimeOfDay {

    public static final String MESSAGE_CONSTRAINTS = "TimeOfDay entered must be in the format of <HHmm>, where"
            + " HH is the 24-hour clock timing, mm is the minutes of the hour.\n"
            + " Minutes must be in 30 min intervals, i.e. 1200, 1230, etc.";

    private static final DateTimeFormatter FORMAT_INPUT = DateTimeFormatter.ofPattern("HHmm");
    private static final DateTimeFormatter FORMAT_OUTPUT = DateTimeFormatter.ofPattern("h:mm a");
    // Ensure time is in 30 minute intervals.
    private static final Predicate<LocalTime> VALIDATION_PREDICATE = time -> time.getMinute() % 30 == 0;
    protected final LocalTime value;

    /**
     * Represents the date stored for any model object.
     * @param timeString The input time by the user.
     */
    public TimeOfDay(String timeString) {
        requireNonNull(timeString);
        checkArgument(isValidTime(timeString), MESSAGE_CONSTRAINTS);
        this.value = LocalTime.parse(timeString, FORMAT_INPUT);
    }

    /**
     * Returns true if a given string is a valid TimeOfDay format and in the correct range.
     */
    public static boolean isValidTime(String test) {
        try {
            LocalTime timeToTest = LocalTime.parse(test, FORMAT_INPUT);
            return VALIDATION_PREDICATE.test(timeToTest);
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TimeOfDay // instanceof handles nulls
                && value.equals(((TimeOfDay) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public String toString() {
        return value.format(FORMAT_INPUT);
    }

    public String toUiString() {
        return value.format(FORMAT_OUTPUT);
    }

    public LocalTime getLocalTime() {
        return value;
    }
}
