package seedu.zookeep.model.animal;

import static java.util.Objects.requireNonNull;
import static seedu.zookeep.commons.util.AppUtil.checkArgument;

/**
 * Represents an Animal's species in the application.
 * Guarantees: immutable; is valid as declared in {@link #isValidSpecies(String)}
 */
public class Species {

    public static final String MESSAGE_CONSTRAINTS = "Species can take any values, but it should not be blank";

    /*
     * The first character of the species must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String value;

    /**
     * Constructs a {@code Species}.
     *
     * @param species A valid species.
     */
    public Species(String species) {
        requireNonNull(species);
        checkArgument(isValidSpecies(species), MESSAGE_CONSTRAINTS);
        value = species;
    }

    /**
     * Returns true if a given string is a valid species.
     */
    public static boolean isValidSpecies(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Species // instanceof handles nulls
                && value.equals(((Species) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
