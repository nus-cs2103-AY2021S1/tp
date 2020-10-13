package seedu.address.model.student;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Student's year in Reeve.
 * Guarantees: immutable; is valid as declared in {@link #isValidYear(String)}
 */
public class Year {

    public static final String MESSAGE_CONSTRAINTS =
            "Years should only contain numerical characters, and it should not be blank";

    /*
     * The first character of the year must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     * year string contains alphanumerical characters.
     */
    public static final String VALIDATION_REGEX = "^[a-zA-Z0-9][a-zA-Z0-9 ]*$";

    public final String year;

    /**
     * Constructs a {@code Year}.
     *
     * @param year A valid school year.
     */
    public Year(String year) {
        requireNonNull(year);
        checkArgument(isValidYear(year), Year.MESSAGE_CONSTRAINTS);
        this.year = year;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidYear(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return year;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Year // instanceof handles nulls
                && year.equals (((Year) other).year)); // state check
    }

    @Override
    public int hashCode() {
        return year.hashCode();
    }

}
