package seedu.stock.model.stock;

import static java.util.Objects.requireNonNull;
import static seedu.stock.commons.util.AppUtil.checkArgument;

import java.math.BigInteger;
import java.util.Optional;

public class QuantityAdder {

    public static final String MESSAGE_CONSTRAINTS =
            "Increment value should be an integer";
    // Matches any signed integer
    public static final String VALIDATION_REGEX = "^(\\+|-)?\\d+$";
    public final String valueToBeAdded;
    
    public QuantityAdder(String valueToBeAdded) {
        requireNonNull(valueToBeAdded);
        checkArgument(isValidValue(valueToBeAdded), MESSAGE_CONSTRAINTS);
        this.valueToBeAdded = valueToBeAdded;
    }

    public static boolean isValidValue(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return valueToBeAdded;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof QuantityAdder // instanceof handles nulls
                && valueToBeAdded.equals(((QuantityAdder) other).valueToBeAdded)); // state check
    }

    @Override
    public int hashCode() {
        return valueToBeAdded.hashCode();
    }

    /**
     * Returns the incremented or decremented quantity.
     * @param toBeAddedInto The quantity to be added.
     * @return The quantity result after being incremented.
     */
    public Optional<Quantity> incrementQuantity(Quantity toBeAddedInto) {
        BigInteger incrementValue = new BigInteger(valueToBeAdded);
        BigInteger currentQuantity = new BigInteger(toBeAddedInto.quantity);
        currentQuantity = currentQuantity.add(incrementValue);
        if (currentQuantity.signum() == -1) {
            return Optional.empty();
        }
        return Optional.of(new Quantity(currentQuantity.toString()));
    }
}
