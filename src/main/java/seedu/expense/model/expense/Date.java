package seedu.expense.model.expense;

import static java.util.Objects.requireNonNull;
import static seedu.expense.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents the date of an expense in the expense book.
 * Guarantees: immutable; is valid as declared in {@link #isValidDate(String)}
 */
public class Date {
    // private static final String SPECIAL_CHARACTERS = "!#$%&'*+/=?`{|}~^.-";
    public static final String MESSAGE_CONSTRAINTS = "Dates should be of the format dd-MM-yyyy,"
            + "and it should adhere to the following constraints:\n"
            + "1. String should not be blank.\n"
            + "2. Date given should be a valid date that exists.\n"
            + "** Note: Date input is optional.";

    // dd-MM-yyyy
    public static final String VALIDATION_REGEX = "^(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[012])-\\d{4}";

    public final LocalDate value;

    /**
     * Constructs an {@code Date}.
     *
     * @param date A valid date string.
     */
    public Date(String date) {
        requireNonNull(date);
        checkArgument(isValidDate(date), MESSAGE_CONSTRAINTS);
        value = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    /**
     * Constructs an {@code Date} using current Date
     *
     */
    public Date() {
        value = LocalDate.now();
    }

    /**
     * Returns if a given string is a valid date.
     */
    public static boolean isValidDate(String test) {
        if (test.matches(VALIDATION_REGEX)) {
            try {
                LocalDate.parse(test, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                return true;
            } catch (DateTimeParseException e) {
                return false;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return value.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
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

}
