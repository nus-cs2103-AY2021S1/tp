package seedu.address.model.item;

import static java.lang.Integer.parseInt;
import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Item's quantity number in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidQuantity(String)}
 */
public class Quantity {

    public static final String MESSAGE_CONSTRAINTS =
            "Quantity should only contain non-negative integers, "
                    + "should be at least 1 digit long, and less than 2147483647 ";
    public static final String MESSAGE_CONSTRAINTS_ALLOW_NEGATIVE =
            "Quantity should be an integer between -2147483648 and 2147483647 ";
    public static final String VALIDATION_REGEX = "\\d+";
    private static final String ALLOW_NEGATIVE_VALIDATION_REGEX = "-?\\d+";
    public final String value;

    /**
     * Constructs a {@code Quantity}.
     *
     * @param quantity A valid quantity number.
     */
    public Quantity(String quantity) {
        requireNonNull(quantity);
        checkArgument(isValidQuantity(quantity), MESSAGE_CONSTRAINTS);
        int truncatedInt = parseInt(quantity);
        value = truncatedInt + "";
    }

    /**
     * Overloaded constructor to allow negative quantities
     */
    public Quantity(String quantity, Boolean allowNegative) {
        requireNonNull(quantity);
        checkArgument(isValidQuantityAllowNegative(quantity), MESSAGE_CONSTRAINTS_ALLOW_NEGATIVE);
        int truncatedInt = parseInt(quantity);
        value = truncatedInt + "";
    }

    /**
     * Returns the numerical value for ease of access
     */
    public int getNumber() {
        return parseInt(value);
    }

    /**
     * Returns true if a given string is a valid quantity number.
     */
    public static boolean isValidQuantity(String test) {
        try {
            parseInt(test);
        } catch (NumberFormatException e) {
            return false;
        }
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Same as isValidQuantity, except it allows negative numbers
     */
    public static boolean isValidQuantityAllowNegative(String test) {
        try {
            parseInt(test);
        } catch (NumberFormatException e) {
            return false;
        }
        return test.matches(ALLOW_NEGATIVE_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Quantity // instanceof handles nulls
                && value.equals(((Quantity) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    public int toInt() {
        return parseInt(value);
    }
}
