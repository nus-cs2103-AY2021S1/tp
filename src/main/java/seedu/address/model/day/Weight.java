package seedu.address.model.day;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's weight in the record.
 * Guarantees: immutable; is valid as declared in {@link #isValidWeight(String)}
 */
public class Weight {


    public static final String MESSAGE_CONSTRAINTS =
            "Weight should only contain numbers, and it can only take up to at most 2 decimal points ";
    public static final String MESSAGE_TOO_LIGHT =
            "are you the world's lightest person?";
    public static final String MESSAGE_TOO_HEAVY =
            "are you the world's heaviest person?";
    public static final String VALIDATION_REGEX = "^[0-9]+(\\.[0-9]{1,2})?$";
    public final String value;

    /**
     * Constructs a {@code Weight}.
     *
     * @param weight A valid weight in kilograms.
     */
    public Weight(String weight) {
        requireNonNull(weight);
        checkArgument(isValidWeight(weight), MESSAGE_CONSTRAINTS);
        value = weight;
    }

    /**
     * Constructs a {@code Weight}.
     * for default profile
     */
    public Weight() {
        value = "DEFAULT";
    }

    /**
     * Returns true if a given string is a valid weight number.
     */
    public static boolean isValidWeight(String test) {
        return test.matches(VALIDATION_REGEX);
    }
    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Weight // instanceof handles nulls
                && value.equals(((Weight) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
