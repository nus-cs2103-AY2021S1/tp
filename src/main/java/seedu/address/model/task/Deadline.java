package seedu.address.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

/**
 * Represents the deadline of an assignment in the assignment list.
 * Guarantees: immutable; is valid as declared in {@link #isValidDeadline(String)}
 */
public class Deadline {
    public static final String MESSAGE_CONSTRAINTS =
            "Deadlines should only be in the format 'dd-MM-uuuu HHmm', and contain a valid date and time";
    public static final String DEADLINE_DATE_TIME_FORMAT = "dd-MM-uuuu HHmm";
    public final String value;
    private final DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern(DEADLINE_DATE_TIME_FORMAT)
            .withResolverStyle(ResolverStyle.STRICT);

    /**
     * Constructs a {@code Deadline}.
     *
     * @param deadline A valid deadline.
     */
    public Deadline(String deadline) {
        requireNonNull(deadline);
        checkArgument(isValidDeadline(deadline), MESSAGE_CONSTRAINTS);
        value = deadline;
    }

    /**
     * Constructs a {@code Deadline}.
     *
     * @param deadline A valid deadline with format LocalDateTime.
     */
    public Deadline(LocalDateTime deadline) {
        requireNonNull(deadline);
        value = deadline.format(inputFormat);
    }

    /**
     * Returns true if a given string is a valid deadline.
     */
    public static boolean isValidDeadline(String test) {
        try {
            DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern(DEADLINE_DATE_TIME_FORMAT)
                    .withResolverStyle(ResolverStyle.STRICT);
            LocalDateTime taskDate = LocalDateTime.parse(test, inputFormat);
            taskDate.format(inputFormat);
            return true;
        } catch (DateTimeException e) {
            return false;
        }
    }

    /**
     * Returns true if the deadline is before another deadline
     */
    public boolean isBefore(Deadline other) {
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
                || (other instanceof Deadline // instanceof handles nulls
                && value.equals(((Deadline) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
