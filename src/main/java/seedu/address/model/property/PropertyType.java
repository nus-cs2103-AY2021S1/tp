package seedu.address.model.property;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a property's type in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidPropertyType(String)}
 */
public class PropertyType {

    public static final String MESSAGE_CONSTRAINTS =
            "Type should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String type;

    /**
     * Constructs a {@code PropertyType}.
     *
     * @param type A valid name.
     */
    public PropertyType(String type) {
        requireNonNull(type);
        checkArgument(isValidPropertyType(type), MESSAGE_CONSTRAINTS);
        this.type = type;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidPropertyType(String test) {
        // TODO: add more validation
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return type;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PropertyType // instanceof handles nulls
                && type.equals(((PropertyType) other).type)); // state check
    }

}

