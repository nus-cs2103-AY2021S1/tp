package seedu.medibook.model.patient;

import static java.util.Objects.requireNonNull;
import static seedu.medibook.commons.util.AppUtil.checkArgument;

/**
 * Represents a Patient's weight in the medi book.
 * Guarantees: immutable; is valid as declared in {@link #isValidWeight(String)}
 */
public class Weight {

    public static final String MESSAGE_CONSTRAINTS =
            "Weight (kg) should only contain positive numbers with 1 decimal place, and it should not be blank";
    public static final String WEIGHT_UNIT = "kg";

    public final String value;
    private final double numericValue;

    /**
     * Constructs a {@code weight}.
     *
     * @param weight A valid weight.
     */
    public Weight(String weight) {
        requireNonNull(weight);
        checkArgument(isValidWeight(weight), MESSAGE_CONSTRAINTS);
        this.value = weight;
        this.numericValue = Double.parseDouble(weight);
    }

    /**
     * Returns true if a given string is a valid weight.
     */
    public static boolean isValidWeight(String test) {
        try {
            // check if valid number
            Double weight = Double.parseDouble(test);

            // check if specified with 1 decimal place
            if (!hasDotAsSecondLastCharacter(test)) {
                return false;
            }

            if (weight <= 0) {
                return false;
            }

            return true;
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
    }

    private static boolean hasDotAsSecondLastCharacter(String test) {
        if (!test.contains(".")) {
            return false;
        }
        int indexOfDot = test.indexOf(".");
        if (indexOfDot != test.length() - 2) {
            return false;
        }
        return true;
    }

    public double getNumericValue() {
        return numericValue;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
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
