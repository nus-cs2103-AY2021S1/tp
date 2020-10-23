package seedu.address.model.lesson;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Lesson's date in PlaNus task list.
 * Guarantees: immutable; is valid as declared in {@link #isValidDate(String)}
 */
public class Date {
    public static final String MESSAGE_CONSTRAINTS =
            "Date should be in the format of dd-MM-yyyy";
    public static final String RANGE_CONSTRAINTS = "Start date should be before end date";
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    public static final LocalDate DEFAULT_DATE = LocalDate.parse("01-01-1000", FORMATTER);
    public static final String VALIDATION_REGEX = "^(3[01]|[12][0-9]|0[1-9])-(1[0-2]|0[1-9])-[0-9]{4}$";
    public final LocalDate value;
    public final boolean isDefault;

    /**
     * Constructs a {@code Date}.
     *
     * @param date A valid date.
     */
    public Date(String date) {
        requireNonNull(date);
        checkArgument(isValidDate(date), MESSAGE_CONSTRAINTS);
        value = LocalDate.parse(date, FORMATTER);
        isDefault = false;
    }

    /**
     * Constructs a default {@code Date}.
     *
     * Caveat: Only called by defaultDate method.
     */
    private Date() {
        value = DEFAULT_DATE; // a dummy value
        isDefault = true;
    }

    /**
     * Returns true if a given string is a valid date number.
     *
     * @param test the string value to be put to test.
     * @return true if the test string is valid and false otherwise
     */
    public static boolean isValidDate(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        if (isDefault) {
            assert value.equals(DEFAULT_DATE) : "default date using real date value.";
            return "";
        }
        return value.format(FORMATTER);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Date // instanceof handles nulls
                && (value.equals(((Date) other).value)
                || isDefault && ((Date) other).isDefault)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
