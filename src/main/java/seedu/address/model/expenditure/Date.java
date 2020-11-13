package seedu.address.model.expenditure;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Date implements Comparable<Date> {
    public static final String MESSAGE_CONSTRAINTS = "Dates should be able to be parsed by LocalDate.";

    public final String value;

    /**
     * Constructs a {@code Date}.
     *
     * @param value A valid date.
     */
    public Date(String value) {
        requireNonNull(value);
        checkArgument(isValid(value), MESSAGE_CONSTRAINTS);
        this.value = value;
    }

    /**
     * Creates a deep copy of the given {@code Date}.
     * @param toClone the {@code Date} to be copied.
     * @return the deep copy of the given {@code Date}.
     */
    public static Date clone(Date toClone) {
        requireAllNonNull(toClone);
        return new Date(toClone.value);
    }

    /**
     * Returns true if a given string is a valid date.
     */
    public static boolean isValid(String test) {
        try {
            LocalDateTime.parse(test);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    public java.util.Date getFormattedCreatedOn() {
        return Timestamp.valueOf(LocalDateTime.parse(value));
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Date // instanceof handles nulls
                && value.equals(((Date) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public int compareTo(Date date) {
        return LocalDateTime.parse(value).compareTo(LocalDateTime.parse(date.value));
    }
}
