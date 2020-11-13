package seedu.address.model.calorie;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Input food
 */
public class Food {

    public static final String MESSAGE_CONSTRAINTS =
            "Food can take any values, and it should not be blank";

    public static final String MESSAGE_CONSTRAINTS_WRONG_TYPE =
            "Food can only be used with Calorie Type: Input";

    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String food;

    /**
     * Constructs a {@code Food}.
     *
     * @param food A valid food.
     */
    public Food(String food) {
        requireNonNull(food);
        checkArgument(isValidFood(food), MESSAGE_CONSTRAINTS);
        this.food = food;
    }

    /**
     * Checks if input is valid
     *
     * @param test
     */
    public static boolean isValidFood(String test) {
        assert test != null : "test must be not be empty";
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return food;
    }

    /**
     * Returns true if both Foods have the same food string.
     * This defines a stronger notion of equality between two foods.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Food)) {
            return false;
        }

        Food otherFood = (Food) other;
        return otherFood.food.equals(this.food);
    }

}
