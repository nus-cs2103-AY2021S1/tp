package seedu.address.model.property;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a property's name in the property book.
 * Guarantees: immutable; is valid as declared in {@link #isValidPropertyName(String)}
 */
public class PropertyName {

    public static final String MESSAGE_CONSTRAINTS =
            "Names should only contain alphanumeric characters and spaces, it should not be blank "
            + "and its maximum length is 100 characters.";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";
    public static final int MAX_LENGTH = 100;

    public final String propertyName;

    /**
     * Constructs a {@code PropertyName}.
     *
     * @param name A valid name.
     */
    public PropertyName(String name) {
        requireNonNull(name);
        checkArgument(isValidPropertyName(name), MESSAGE_CONSTRAINTS);
        propertyName = name;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidPropertyName(String test) {
        return test.matches(VALIDATION_REGEX) && test.length() <= MAX_LENGTH;
    }


    @Override
    public String toString() {
        return propertyName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PropertyName // instanceof handles nulls
                && propertyName.equals(((PropertyName) other).propertyName)); // state check
    }

}
