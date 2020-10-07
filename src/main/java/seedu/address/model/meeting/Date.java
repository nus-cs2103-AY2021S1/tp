package seedu.address.model.meeting;

import seedu.address.model.person.Phone;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class Date {
    public static final String MESSAGE_CONSTRAINTS =
            "Dates should be in the format yyyy-mm-dd";
    public static final String VALIDATION_REGEX = "\\d{3,}";
    public final String value;

    /**
     * Constructs a {@code Date}.
     *
     * @param date A valid date.
     */
    public Date(String date) {
        requireNonNull(date);
        checkArgument(isValidDate(date), MESSAGE_CONSTRAINTS);
        value = date;
    }

    /**
     * Returns true if a given string is a valid phone number.
     */
    public static boolean isValidDate(String test) {
        return test.length() == 10
                && test.charAt(4) == '-'
                && test.charAt(7) == '-';
        //todo: check if date exists
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
}
