package chopchop.model.attributes;

import static java.util.Objects.requireNonNull;
import static chopchop.commons.util.AppUtil.checkArgument;

/**
 * Represents a FoodEntry's name in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 * Comparisons are case insensitive.
 */
public class Name {

    public static final String MESSAGE_CONSTRAINTS =
            "Names should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the address must be a alphanumeric char. No restrictions on subsequent chars
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][a-zA-Z0-9\\s\\W]*";

    public final String fullName;

    /**
     * Constructs a {@code Name}.
     *
     * @param name A valid name.
     */
    public Name(String name) {
        requireNonNull(name);
        checkArgument(isValidName(name), MESSAGE_CONSTRAINTS);
        fullName = name;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return fullName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Name // instanceof handles nulls
                    && this.fullName.toLowerCase().equals(((Name) other).fullName.toLowerCase()))
                || (other instanceof String
                    && this.fullName.toLowerCase().equals(((String) other).toLowerCase()));
    }

    @Override
    public int hashCode() {
        return this.fullName.toLowerCase().hashCode();
    }
}
