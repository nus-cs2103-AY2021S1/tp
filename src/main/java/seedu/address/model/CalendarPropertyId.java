package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a propertyId in the address book to be used in calendar.
 * Guarantees: immutable; is valid as declared in {@link #isValidPropertyId(String)}
 */
public class CalendarPropertyId {

    public static final String MESSAGE_CONSTRAINTS =
            "Names should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String propertyId;

    /**
     * Constructs a {@code CalendarPropertyId}.
     *
     * @param name A valid name.
     */
    public CalendarPropertyId(String name) {
        requireNonNull(name);
        checkArgument(isValidPropertyId(name), MESSAGE_CONSTRAINTS);
        propertyId = name;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidPropertyId(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return propertyId;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.address.model.CalendarPropertyId // instanceof handles nulls
                && propertyId.equals(((CalendarPropertyId) other).propertyId)); // state check
    }

    @Override
    public int hashCode() {
        return propertyId.hashCode();
    }
}
