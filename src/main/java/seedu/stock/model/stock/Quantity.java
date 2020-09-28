package seedu.stock.model.stock;

import static java.util.Objects.requireNonNull;
import static seedu.stock.commons.util.AppUtil.checkArgument;

/**
 * Represents a Stock's quantity in the stock book.
 * Guarantees: immutable; is valid as declared in {@link #isValidQuantity(String)} } (to be changed)
 */
public class Quantity {


    public static final String MESSAGE_CONSTRAINTS =
            "Quantity numbers should be more than 0.";
    public static final String VALIDATION_REGEX = "\\d+";
    public final String quantity;

    /**
     * Constructs a {@code Phone}.
     *
     * @param quantity A valid phone number.
     */
    public Quantity(String quantity) {
        requireNonNull(quantity);
        checkArgument(isValidQuantity(quantity), MESSAGE_CONSTRAINTS);
        this.quantity = quantity;
    }

    /**
     * Returns true if a given int is more than zero or more.
     */
    public static boolean isValidQuantity(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return quantity;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Quantity // instanceof handles nulls
                && quantity.equals(((Quantity) other).quantity)); // state check
    }

    @Override
    public int hashCode() {
        return quantity.hashCode();
    }

}
