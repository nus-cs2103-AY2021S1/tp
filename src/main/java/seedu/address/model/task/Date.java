package seedu.address.model.task;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a date that is related to a Task's.
 * For example, it can be used as a deadline.
 * Guarantees: immutable; is valid as declared in {@link #isValidDate(String)}
 */
public class Date {
    public static final String MESSAGE_CONSTRAINTS = "Date should be in the format of YYYY-MM-DD.";
    private static final DateTimeFormatter validDateFormat = DateTimeFormatter.ISO_LOCAL_DATE;

    private final LocalDate value;

    /**
     * Constructs a {@code Date}.
     *
     * @param date valid date.
     */
    public Date(String date) {
        requireNonNull(date);
        this.value = LocalDate.parse(date);
    }

    /**
     * Returns true if a given string is a valid date.
     *
     * @param test given string
     * @return true if date is valid
     */
    public static boolean isValidDate(String test) {
        try {
            LocalDate.parse(test);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    public LocalDate getValue() {
        return this.value;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof Date)) {
            return false;
        }

        return value.equals(((Date) other).value);
    }

    @Override
    public String toString() {
        return value.format(validDateFormat);
    }
}
