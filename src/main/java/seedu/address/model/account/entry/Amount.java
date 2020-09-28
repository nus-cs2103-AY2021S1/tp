package seedu.address.model.account.entry;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an Entry's monetary value.
 * Guarantees: immutable; is valid as declared in {@link #isValidAmount(String)}
 */
public class Amount {
    public static final String MESSAGE_CONSTRAINTS = "Amount should be of format Dollar.Cents and adhere "
            + "to the following constrants: \n"
            + "1. The Dollars part should be all numbers and should not start with the digit 0.\n"
            + "2. The . between Dollars and Cents is optional"
            + "3. The Cents part should be all numbers and can only contain 0-2 digits";
    public static final String VALIDATION_REGEX = "(^[1-9][0-9]*(\\.?[0-9]?[0-9]?)|^\\.[1-9][0-9]?|^\\.[0-9][1-9])";
    private final Double value;

    /**
     * Constructs an {@code Amount}.
     *
     * @param amount A valid monetary value of an Entry.
     */
    public Amount(String amount) {
        requireNonNull(amount);
        checkArgument(isValidAmount(amount), MESSAGE_CONSTRAINTS);
        this.value = Double.parseDouble(amount);
    }

    /**
     * Returns true if a given string is a valid monetary value.
     */
    public static boolean isValidAmount(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return String.format("%.2f", this.value);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Amount // instanceof handles nulls
                && value.equals(((Amount) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
