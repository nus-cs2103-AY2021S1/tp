package seedu.address.model.commons;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents activity's or accommodation's cost in the Wanderlust.
 * Guarantees: immutable; is valid as declared in {@link #isValidCost(String)}
 */
public class Cost {


    public static final String MESSAGE_CONSTRAINTS =
            "Cost should only contain positive integer and must not exceed MAX_VALUE";

    public static final String VALIDATION_REGEX = "\\p{Digit}+(.\\p{Digit}\\p{Digit})?";

    private final String value;

    /**
     * Constructs a {@code Cost}.
     *
     * @param cost A valid cost.
     */
    public Cost(String cost) {
        requireNonNull(cost);
        checkArgument(isValidCost(cost), MESSAGE_CONSTRAINTS);
        value = cost;
    }

    /**
     * Returns true if a given string is a valid cost.
     */
    public static boolean isValidCost(String test) {
        try {
            Integer.parseInt(test);
            return test.matches(VALIDATION_REGEX);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Cost // instanceof handles nulls
                && value.equals(((Cost) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
