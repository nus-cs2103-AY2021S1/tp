package seedu.medibook.model.patient;

import static java.util.Objects.requireNonNull;
import static seedu.medibook.commons.util.AppUtil.checkArgument;
import static seedu.medibook.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Represents a Person's BMI in the medi book, whose value is defined to 1 dp (zero-padded).
 * Guarantees: immutable; is valid as declared in {@link #isValidBmi(String)}
 */
public class Bmi {

    public static final String MESSAGE_CONSTRAINTS =
            "BMI should only contain positive numbers with 1 decimal place, and it should not be blank";
    private static final double conversionFactor = 100;
    public final String value;
    private final double numericValue;

    /**
     * Constructs a {@code bmi} from known weight and height.
     *
     * @param weight A valid weight object.
     * @param height A valid height object.
     */
    public Bmi(Weight weight, Height height) {
        requireAllNonNull(weight, height);

        double numericWeight = weight.getNumericValue();
        int numericHeight = height.getNumericValue();

        this.numericValue = computeBmiValue(numericWeight, numericHeight);
        this.value = String.valueOf(numericValue);
    }

    /**
     * Constructs a {@code bmi}.
     *
     * @param bmi A valid bmi.
     */
    public Bmi(String bmi) {
        requireNonNull(bmi);
        checkArgument(isValidBmi(bmi), MESSAGE_CONSTRAINTS);
        this.value = bmi;
        this.numericValue = Double.parseDouble(bmi);
    }

    private static double computeBmiValue(double numericWeight, int numericHeight) {
        double numericHeightInMetres = ((double) numericHeight) / conversionFactor;
        double numericBmi = numericWeight / Math.pow(numericHeightInMetres, 2);
        return ((double) Math.round(numericBmi * 10)) / 10;
    }

    /**
     * Returns true if a given string is a valid bmi.
     */
    public static boolean isValidBmi(String test) {
        try {
            // check if valid number
            Double bmi = Double.parseDouble(test);

            // check if specified with 1 decimal place
            if (!hasDotAsSecondLastCharacter(test)) {
                return false;
            }

            if (bmi <= 0) {
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

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Bmi // instanceof handles nulls
                && value.equals(((Bmi) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
