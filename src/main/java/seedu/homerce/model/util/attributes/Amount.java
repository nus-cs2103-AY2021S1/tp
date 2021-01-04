package seedu.homerce.model.util.attributes;

import static java.util.Objects.requireNonNull;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.function.Predicate;

import seedu.homerce.commons.util.AppUtil;

/**
 * The price of a service provided by homerce.
 */
public class Amount {
    public static final String MESSAGE_CONSTRAINTS = "Amount (in dollars and cents - eg: 15.00) "
            + "must be a positive value greater than 0.00 and less than 1,000,000.00";

    private static final double MIN_VALUE = 0.0;
    private static final double MAX_VALUE = 1000000.00;
    public static final Predicate<Double> VALIDATION_PREDICATE = i -> i > MIN_VALUE && i < MAX_VALUE;


    /** Representing money in Singapore Dollars */
    public final BigDecimal value;

    /**
     * The cost of a Service in Singapore Dollars.
     * @param amount double value input representing dollars and cents.
     */
    public Amount(Double amount) {
        requireNonNull(amount);
        AppUtil.checkArgument(isValidAmount(amount), MESSAGE_CONSTRAINTS);
        value = new BigDecimal(amount).setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * Returns true if a given amount value is a valid.
     */
    public static boolean isValidAmount(Double test) {
        return VALIDATION_PREDICATE.test(test);
    }

    /**
     * Gives a string representation of the amount, in Singapore Dollars.
     *
     * @return a string representing the amount of Singapore Dollars the amount is.
     */
    @Override
    public String toString() {
        return value.setScale(2, RoundingMode.HALF_UP).toString(); // Two decimal places
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Amount// instanceof handles nulls
                && value.equals(((Amount) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
