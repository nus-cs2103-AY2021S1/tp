package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's office in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidOffice(String)}
 */
public class Office {

    public static final String MESSAGE_CONSTRAINTS = "Offices can take any values, and it should not be blank";

    /*
     * The first character of the office must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String value;

    /**
     * Constructs an {@code Office}.
     *
     * @param office A valid department.
     */
    public Office(String office) {
        requireNonNull(office);
        checkArgument(isValidOffice(office), MESSAGE_CONSTRAINTS);
        value = office;
    }

    /**
     * Returns true if a given string is a valid email.
     */
    public static boolean isValidOffice(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Office // instanceof handles nulls
                && value.equals(((Office) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
