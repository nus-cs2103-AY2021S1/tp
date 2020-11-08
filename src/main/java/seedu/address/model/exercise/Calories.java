package seedu.address.model.exercise;

import static seedu.address.commons.util.AppUtil.checkArgument;

import java.math.BigInteger;
import java.util.Objects;

import seedu.address.logic.parser.exceptions.CaloriesOverflow;

public class Calories {

    public static final String MESSAGE_CONSTRAINTS = "Calories should be a integer.";

    public final String value;

    /**
     * Constructs a {@code Calories}.
     *
     * @param calories A valid input.
     */
    public Calories(String calories) throws CaloriesOverflow {
        if (calories == null) {
            value = "0";
        } else {
            checkArgument(isValidCalories(calories), MESSAGE_CONSTRAINTS);
            value = calories;
        }
    }

    /**
     * Returns true if a given string is a valid input.
     */
    public static boolean isValidCalories(String test) throws CaloriesOverflow {
        if (Objects.isNull(test)) {
            throw new NullPointerException();
        }

        int x;
        try {
            x = Integer.parseInt(test);
        } catch (Exception err) {
            try {
                new BigInteger(test);
            } catch (Exception e1) {
                //Test is invalid because it can't be casted into Integer or BigInteger.
                return false;
            }
            throw new CaloriesOverflow();
        }

        return x >= 0;
    }


    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.address.model.exercise.Calories // instanceof handles nulls
                && value.equals(((Calories) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    /**
     * Subtracts a {@code Calories}.
     *
     * @param calories A valid input.
     */
    public Calories subtract(Calories calories) {
        Integer currentCalories = Integer.parseInt(value);
        Integer removedCalories = Integer.parseInt(calories.value);
        Integer newCalorie = currentCalories - removedCalories;
        if (newCalorie < 0) {
            newCalorie = 0;
        }
        return new Calories(String.valueOf(newCalorie));
    }

}
