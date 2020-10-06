package seedu.address.model.location;

import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Represents an Location in the Inventoryinator.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Location {

    public static final String MESSAGE_CONSTRAINTS = "Location can take any values, and it should not be blank";

    /*
     * The first character of the location must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    private static int idCounter = 0;
    // Identity fields
    private final int id;
    private final String name;


    /**
     * Every field must be present and not null.
     */
    public Location(String name) {
        requireAllNonNull(name);
        checkArgument(isValidLocation(name), MESSAGE_CONSTRAINTS);
        this.id = getIdCounter() + 1;
        this.name = name;
        idCounter++;
    }

    public static void decrementIdCounter() {
        idCounter--;
    }

    public static int getIdCounter() {
        return idCounter;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    /**
     * Returns true if a given string is a valid location.
     */
    public static boolean isValidLocation(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if both locations have the same name.
     * This defines a weaker notion of equality between two items.
     */
    public boolean isSameLocation(Location otherLocation) {
        if (otherLocation == this) {
            return true;
        }

        return otherLocation != null
                && otherLocation.getName().equals(getName());
    }

    /**
     * Returns true if both locations have the same identity fields.
     * This defines a stronger notion of equality between two locations.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Location)) {
            return false;
        }

        Location otherLocation = (Location) other;
        return otherLocation.getName().equals(getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return id + ": " + name;
    }

}
