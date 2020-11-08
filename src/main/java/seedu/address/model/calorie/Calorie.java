package seedu.address.model.calorie;

import java.util.Objects;

/**
 * Represents a user input Calorie in fitNUS.
 */
public class Calorie {
    public static final String MESSAGE_CONSTRAINTS_FORMAT =
            "Calorie input must be a valid integer and not more than the Java's maximum"
                    + " integer value e.g. calorie_add c/100";

    public static final String MESSAGE_CONSTRAINTS_LIMIT =
            "Calorie input should never be less than or equal to 0!";

    public static final double CALORIE_LOWER_LIMIT = 0;

    public static final String VALIDATION_REGEX = "\\d+";

    private final int calorie;

    /**
     * Constructs a {@code Calorie}.
     *
     * @param calorie A valid calorie.
     */
    public Calorie(int calorie) {
        this.calorie = calorie;
    }

    public int getCalorie() {
        return calorie;
    }

    /**
     * Returns true if a given string is a valid calorie.
     *
     * @param test The string to be tested.
     * @return True if the given string is a valid calorie.
     */
    public static boolean isValidCalorie(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if a given calorie is within a reasonable range.
     *
     * @param test The calorie to be tested.
     * @return True if the given calorie is within a reasonable range.
     */
    public static boolean isValidCalorie(int test) {
        return test > CALORIE_LOWER_LIMIT;
    }

    @Override
    public String toString() {
        return String.valueOf(calorie);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Calorie // instanceof handles nulls
                && calorie == ((Calorie) other).getCalorie()); // state check
    }

    @Override
    public int hashCode() {
        return Objects.hash(calorie);
    }
}
