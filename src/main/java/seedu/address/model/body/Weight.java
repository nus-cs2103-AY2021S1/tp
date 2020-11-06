package seedu.address.model.body;

import java.util.Objects;

/**
 * Represents the user's weight measured in kilograms in fitNUS.
 */
public class Weight {
    public static final String MESSAGE_CONSTRAINTS_FORMAT =
            "Weight should only contain positive numbers up to 2 decimal places, and it should not be blank";

    public static final String MESSAGE_CONSTRAINTS_LIMIT =
            "Weight should be greater than 30kg and smaller than 200kg.";

    public static final double WEIGHT_LOWER_LIMIT = 30;

    public static final double WEIGHT_UPPER_LIMIT = 200;

    public static final String VALIDATION_REGEX = "\\d+(\\.\\d{1,2})?";

    private final double weight;

    /**
     * Constructs a {@code Weight}.
     *
     * @param weight A valid weight.
     */
    public Weight(double weight) {
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    /**
     * Returns true if a given string is a valid weight.
     *
     * @param test The string to be tested.
     * @return True if the given string is a valid weight.
     */
    public static boolean isValidWeight(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if a given weight is within a reasonable range.
     *
     * @param test The weight to be tested.
     * @return True if the given weight is within a reasonable range.
     */
    public static boolean isValidWeight(double test) {
        return test > WEIGHT_LOWER_LIMIT && test < WEIGHT_UPPER_LIMIT;
    }

    @Override
    public String toString() {
        return String.format("%.2f kg", weight);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Weight // instanceof handles nulls
                && weight == ((Weight) other).getWeight()); // state check
    }

    @Override
    public int hashCode() {
        return Objects.hash(weight);
    }
}
