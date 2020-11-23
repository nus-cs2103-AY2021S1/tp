package seedu.stock.model.stock;

import static java.util.Objects.requireNonNull;
import static seedu.stock.commons.util.AppUtil.checkArgument;

/**
 * Represents a Stock's quantity in the stock book.
 * Guarantees: immutable; is valid as declared in {@link #isValidQuantity(String)} }
 */
public class Quantity {

    public static final String MESSAGE_CONSTRAINTS =
            "Invalid quantity entered! \n"
            + "Quantity numbers should be a number from 0 to 2,147,483,647.";
    public static final String LOW_QUANTITY_MESSAGE_CONSTRAINTS =
            "Invalid low quantity entered! \n"
            + "Low quantity numbers should be a number from 0 to 2,147,483,647.";
    public static final String MESSAGE_CONSTRAINTS_INCREMENT =
            "Invalid quantity result after increment! \n"
            + "Quantity numbers should be a number from 0 to 2,147,483,647.";
    public static final String VALIDATION_REGEX = "\\d+";
    public static final String DEFAULT_LOW_QUANTITY = "0";
    public final String quantity;
    public final String lowQuantity;

    /**
     * Constructs a {@code Quantity}.
     *
     * @param quantity A valid quantity.
     */
    public Quantity(String quantity) {
        requireNonNull(quantity);
        checkArgument(isValidQuantity(quantity), MESSAGE_CONSTRAINTS);
        this.quantity = quantity;
        this.lowQuantity = DEFAULT_LOW_QUANTITY;
    }

    /**
     * Constructs a {@code Quantity}.
     *
     * @param quantity A valid quantity.
     * @param lowQuantity A valid LowQuantity.
     */
    public Quantity(String quantity, String lowQuantity) {
        requireNonNull(quantity);
        requireNonNull(lowQuantity);
        checkArgument(isValidQuantity(quantity), MESSAGE_CONSTRAINTS);
        checkArgument(isValidQuantity(lowQuantity), LOW_QUANTITY_MESSAGE_CONSTRAINTS);
        this.quantity = quantity;
        this.lowQuantity = lowQuantity;
    }

    /**
     * Returns true if a given string is a number and is more than zero or more.
     *
     * @param test The quantity to be tested.
     * @return A boolean value indicating if the quantity is valid.
     */
    public static boolean isValidQuantity(String test) {
        requireNonNull(test);
        try {
            //protective layer against huge string input.
            Integer.parseInt(test);
            return test.matches(VALIDATION_REGEX);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Returns true if a given quantity is equal or less than lowQuantity.
     */
    public boolean isLowOnQuantity() {
        return Integer.parseInt(quantity) <= Integer.parseInt(lowQuantity);
    }

    /**
     * Updates the low quantity field in quantity.
     * @param newLowQuantity The new low quantity value.
     * @return The updated Quantity.
     */
    public Quantity updateLowQuantity(String newLowQuantity) {
        requireNonNull(newLowQuantity);
        return new Quantity(quantity, newLowQuantity);
    }

    public String getLowQuantity() {
        return lowQuantity;
    }

    @Override
    public String toString() {
        return quantity;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Quantity // instanceof handles nulls
                && quantity.equals(((Quantity) other).quantity) // state check
                && lowQuantity.equals(((Quantity) other).lowQuantity)); // state check
    }

    @Override
    public int hashCode() {
        return quantity.hashCode();
    }
}
