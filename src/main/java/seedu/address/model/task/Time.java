package seedu.address.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

/**
 * Represents the time found in a task in ProductiveNUS.
 * Guarantees: immutable; is valid as declared in {@link #isValidTime(String)}
 */
public class Time {
    public static final String MESSAGE_CONSTRAINTS =
            "Deadlines should only be in the format 'dd-MM-yyyy HHmm'"
                    + "\nDate and time (24 hour format) should be valid.";
    public static final String TIME_DATE_TIME_FORMAT = "dd-MM-uuuu HHmm";
    public final String value;
    private final DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern(TIME_DATE_TIME_FORMAT)
            .withResolverStyle(ResolverStyle.STRICT);

    /**
     * Constructs a {@code Time}.
     *
     * @param time A valid time.
     */
    public Time(String time) {
        requireNonNull(time);
        checkArgument(isValidTime(time), MESSAGE_CONSTRAINTS);
        value = time;
    }

    /**
     * Constructs a {@code Time}.
     *
     * @param deadline A valid time with format LocalDateTime.
     */
    public Time(LocalDateTime deadline) {
        requireNonNull(deadline);
        value = deadline.format(inputFormat);
    }

    /**
     * Returns true if a given string is a valid time.
     */
    public static boolean isValidTime(String test) {
        try {
            DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern(TIME_DATE_TIME_FORMAT)
                    .withResolverStyle(ResolverStyle.STRICT);
            LocalDateTime taskDate = LocalDateTime.parse(test, inputFormat);
            taskDate.format(inputFormat);
            return true;
        } catch (DateTimeException e) {
            return false;
        }
    }

    /**
     * Returns true if the time is before another time
     */
    public boolean isBefore(Time other) {
        LocalDateTime thisTime = LocalDateTime.parse(value, inputFormat);
        LocalDateTime thatTime = LocalDateTime.parse(other.value, inputFormat);
        return thisTime.isBefore(thatTime);
    }

    public LocalDateTime toLocalDateTime() {
        return LocalDateTime.parse(value, inputFormat);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Time // instanceof handles nulls
                && value.equals(((Time) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
