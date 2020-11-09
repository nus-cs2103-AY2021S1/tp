package seedu.medibook.model.patient;

import static java.util.Objects.requireNonNull;
import static seedu.medibook.commons.util.AppUtil.checkArgument;

/**
 * Represents a Patient's height in the medi book.
 * Guarantees: immutable; is valid as declared in {@link #isValidHeight(String)}
 */
public class Height {

    public static final String MESSAGE_CONSTRAINTS =
            "Height should only contain numbers, and it should be a value between 0 and 300 exclusive";
    public static final String HEIGHT_UNIT = "cm";
    private static final int HEIGHT_MIN = 0;
    private static final int HEIGHT_MAX = 300;
    public final String value;
    private final int numericValue;

    /**
     * Constructs a {@code Height}.
     *
     * @param height A valid height.
     */
    public Height(String height) {
        requireNonNull(height);
        checkArgument(isValidHeight(height), MESSAGE_CONSTRAINTS);
        value = height;
        this.numericValue = Integer.parseInt(height);
    }

    /**
     * Returns true if a given string is a valid height.
     */
    public static boolean isValidHeight(String test) {
        try {
            int height = Integer.parseInt(test);
            return height < HEIGHT_MAX && height > HEIGHT_MIN;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public int getNumericValue() {
        return numericValue;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Height // instanceof handles nulls
                && value.equals(((Height) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
