package seedu.address.model.student;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Student's year in Reeve.
 * Guarantees: immutable; is valid as declared in {@link #isValidYear(String)}
 */
public class Year {

    public static final String MESSAGE_CONSTRAINTS =
            "Years should only contain numerical digits, and it should not be blank";

    /*
     * The first character of the year must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Digit}][\\p{Digit} ]*";

    public final int year;

    /**
     * Constructs a {@code Year}.
     *
     * @param year A valid school year.
     */
    public Year(String year) {
        requireNonNull(year);
        checkArgument(isValidYear(year), MESSAGE_CONSTRAINTS);
        this.year = Integer.parseInt(year);
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidYear(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return String.valueOf(year);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Name // instanceof handles nulls
                && year == ((Year) other).year); // state check
    }

    @Override
    public int hashCode() {
        return year;
    }

}
