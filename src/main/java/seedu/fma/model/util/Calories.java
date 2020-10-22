package seedu.fma.model.util;

import static java.util.Objects.requireNonNull;
import static seedu.fma.commons.util.IntegerUtil.requirePositiveInteger;

/**
 * Represents calories
 */
public class Calories {

    public static final String MESSAGE_CONSTRAINTS =
            "Calories should be a positive number";

    public final int value;

    /**
     * Constructs a {@code Calories}.
     */
    public Calories(int value) {
        requireNonNull(value);
        requirePositiveInteger(value);
        this.value = value;
    }

    /**
     * Validates that calories is a positive integer
     * @param calories String representation of calories
     * @return True if is valid
     */
    public static boolean isValidCalories(String calories) {
        if (calories.length() == 0) {
            return false;
        }

        for (char c : calories.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Calories // instanceof handles nulls
                && value == (((Calories) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(value);
    }

}
