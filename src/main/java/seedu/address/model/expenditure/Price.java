package seedu.address.model.expenditure;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.logic.parser.ParserUtil.isValidDouble;

public class Price {
    public static final String MESSAGE_CONSTRAINTS =
            "Prices should only contain numbers, and it can have at most 2 decimal places.";
    public static final String NON_ZERO_CONSTRAINTS =
            "Sorry! Prices cannot be 0 or less! It can only exist in the range between $0.01 and $10,000, inclusive.";
    public static final String EXCEEDED_VALUE_ERROR =
            "Sorry! Prices cannot exceed $10,000. "
                    + "It can only exist in the range between $0.01 and $10,000, inclusive.";
    public static final String VALIDATION_REGEX = "-?[0-9]+(\\.[0-9]?[0-9])?$";
    public static final double MAX_VALUE = 10000.00;
    public final String value;

    /**
     * Constructs a {@code Price}.
     *
     * @param price A valid price.
     */
    public Price(String price) {
        requireNonNull(price);
        checkArgument(isValid(price), MESSAGE_CONSTRAINTS);
        value = price;
    }

    /**
     * Creates a deep copy of the given {@code Price}.
     * @param toClone the {@code Price} to be copied
     * @return the deep copy of the given {@code Price}
     */
    public static Price clone(Price toClone) {
        requireNonNull(toClone);
        return new Price(toClone.value);
    }

    /**
     * Returns true if a given string is a valid price.
     */
    public static boolean isValid(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public static boolean isZeroOrLess(String test) {
        return Double.parseDouble(test) <= 0;
    }

    public static boolean isExceededValue(String test) {
        return Double.parseDouble(test) > Price.MAX_VALUE && isValidDouble(test);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Price // instanceof handles nulls
                && value.equals(((Price) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
