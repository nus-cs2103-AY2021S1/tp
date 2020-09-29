package seedu.address.model.item;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Item's quantity in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidQuantity(String)}
 */
public class Quantity {

    public static final String MESSAGE_CONSTRAINTS =
            "Quantity should only contain numbers, and it should be at least 1 digits long";
    public static final String VALIDATION_REGEX = "\\d{1,}";
    public final String value;

    /**
     * Constructs a {@code Phone}.
     *
     * @param quantity A valid phone number.
     */
    public Quantity(String quantity) {
        requireNonNull(quantity);
        checkArgument(isValidQuantity(quantity), MESSAGE_CONSTRAINTS);
        value = quantity;
    }

    /**
     * Returns true if a given string is a valid quantity.
     */
    public static boolean isValidQuantity(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * adds a Quantity's value to another quantity's value
     * @param quantity another quantity
     * @return Quantity of both quantity's value added up
     */
    public Quantity add(Quantity quantity) {
        int value = Integer.parseInt(this.value) + Integer.parseInt(quantity.value);
        return new Quantity(Integer.toString(value));
    }

    /**
     * subtracts a Quantity's value from another quantity's value
     * @param quantity another quantity
     * @return Quantity after subtraction
     */
    public Quantity subtract(Quantity quantity) {
        int value = Integer.parseInt(this.value) - Integer.parseInt(quantity.value);
        return new Quantity(Integer.toString(value));
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

}
