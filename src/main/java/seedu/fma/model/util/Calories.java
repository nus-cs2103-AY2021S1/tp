package seedu.fma.model.util;

/**
 * Represents calories
 */
public class Calories {
    public static final String NUMBER_CONSTRAINTS = "Don't try to break our app! "
            + "Calories should be an integer within range 1-1000 inclusive.";
    public static final String MESSAGE_CONSTRAINTS =
            "Don't try to cheat us! Calories should be within range 1-1000 inclusive.";

    public final int value;

    /**
     * Constructs a {@code Calories}.
     */
    public Calories(int value) {
        this.value = value;
    }

    /**
     * Validates that calories is a positive integer
     *
     * @param calories String representation of calories
     * @return True if is valid
     */
    public static boolean isValidCalories(int calories) {
        return 1 <= calories && calories <= 1000;
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
