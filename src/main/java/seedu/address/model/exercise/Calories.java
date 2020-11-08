package seedu.address.model.exercise;

import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Objects;

public class Calories {

    public static final String MESSAGE_CONSTRAINTS = "Calories should be a integer.";

    public final String value;

    /**
     * Constructs a {@code Calories}.
     *
     * @param calories A valid input.
     */
    public Calories(String calories) {
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
    public static boolean isValidCalories(String test) {
        if (Objects.isNull(test)) {
            throw new NullPointerException();
        }

        int x;
        try {
            x = Integer.parseInt(test);
        } catch (Exception err) {
            //Exception rises when test can't be parsed into Integer.
            return false;
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

}
