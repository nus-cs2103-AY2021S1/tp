package seedu.address.model.util;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a name in fitNUS.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class Name {

    public static final String MESSAGE_CONSTRAINTS_FORMAT =
            "Names should only contain alphanumeric characters and spaces, and it should not be blank";

    public static final String MESSAGE_CONSTRAINTS_LIMIT =
            "Names should not have character count exceeding 50.";

    public static final int CHARACTER_LIMIT = 50;

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String fullName;

    /**
     * Constructs a {@code Name}.
     *
     * @param name A valid name.
     */
    public Name(String name) {
        requireNonNull(name);
        checkArgument(isValidName(name), MESSAGE_CONSTRAINTS_FORMAT);
        checkArgument(isValidLength(name), MESSAGE_CONSTRAINTS_LIMIT);
        fullName = name;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if a given string has a valid character limit.
     * @param test The string to be tested.
     * @return true if the given string has a valid character limit.
     */
    public static boolean isValidLength(String test) {
        return test.length() <= CHARACTER_LIMIT;
    }

    @Override
    public String toString() {
        return fullName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Name // instanceof handles nulls
                && fullName.toLowerCase().equals(((Name) other).fullName.toLowerCase())); // state check
    }

    @Override
    public int hashCode() {
        return fullName.hashCode();
    }

}
