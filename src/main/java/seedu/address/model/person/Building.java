package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's building in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidBuilding(String)}
 */
public class Building {

    public static final String MESSAGE_CONSTRAINTS = "Buildings can take any values, and it should not be blank";

    /*
     * The first character of the building must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String value;

    /**
     * Constructs an {@code Building}.
     *
     * @param building A valid department.
     */
    public Building(String building) {
        requireNonNull(building);
        checkArgument(isValidBuilding(building), MESSAGE_CONSTRAINTS);
        value = building;
    }

    /**
     * Returns true if a given string is a valid email.
     */
    public static boolean isValidBuilding(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Building // instanceof handles nulls
                && value.equals(((Building) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
