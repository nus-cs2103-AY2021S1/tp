package seedu.fma.model.util;

import static java.util.Objects.requireNonNull;
import static seedu.fma.commons.util.AppUtil.checkArgument;

/**
 * Represents a name in the log book.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class Name {

    public static final String MESSAGE_CONSTRAINTS =
            "Names should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the name must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String value;

    /**
     * Constructs a {@code Name}.
     *
     * @param value A valid name.
     */
    public Name(String value) {
        requireNonNull(value);
        checkArgument(isValidName(value), MESSAGE_CONSTRAINTS);
        this.value = value;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Name)) {
            return false;
        }

        return ((Name) other).value.replaceAll("\\s+", "")
                .equals(this.value.replaceAll("\\s+", ""));
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
