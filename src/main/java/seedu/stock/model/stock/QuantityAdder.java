package seedu.stock.model.stock;

import static java.util.Objects.requireNonNull;
import static seedu.stock.commons.util.AppUtil.checkArgument;
import static seedu.stock.model.stock.Quantity.LOW_QUANTITY_MESSAGE_CONSTRAINTS;
import static seedu.stock.model.stock.Quantity.isValidQuantity;

import java.math.BigInteger;
import java.util.Optional;

/**
 * Represents functionalities to add value to an quantity object.
 * Guarantees: immutable; is valid as declared in {@link #isValidValue(String)} }
 */
public class QuantityAdder {

    public static final String MESSAGE_CONSTRAINTS =
            "Increment value should be an integer";
    // Matches any signed integer
    public static final String VALIDATION_REGEX = "^(\\+|-)?\\d+$";
    public final String valueToBeAdded;
    public final String lowQuantityToBeUpdated;

    /**
     * Constructs a new quantity adder object.
     *
     * @param valueToBeAdded The value to be added into a quantity object.
     */
    public QuantityAdder(String valueToBeAdded) {
        requireNonNull(valueToBeAdded);
        checkArgument(isValidValue(valueToBeAdded), MESSAGE_CONSTRAINTS);
        this.valueToBeAdded = valueToBeAdded;
        this.lowQuantityToBeUpdated = null;
    }

    /**
     * Constructs a new quantity adder object.
     *
     * @param valueToBeAdded The value to be added into a quantity object.
     * @param lowQuantityToBeUpdated The low quantity value to be updated.
     */
    public QuantityAdder(String valueToBeAdded, String lowQuantityToBeUpdated) {
        requireNonNull(valueToBeAdded);
        requireNonNull(lowQuantityToBeUpdated);
        checkArgument(isValidValue(valueToBeAdded), MESSAGE_CONSTRAINTS);
        checkArgument(isValidQuantity(lowQuantityToBeUpdated), LOW_QUANTITY_MESSAGE_CONSTRAINTS);
        this.valueToBeAdded = valueToBeAdded;
        this.lowQuantityToBeUpdated = lowQuantityToBeUpdated;
    }

    /**
     * Tests whether {@code test} is valid.
     *
     * @param test The input to be tested.
     * @return A boolean value indicating if the test passes.
     */
    public static boolean isValidValue(String test) {
        try {
            //protective layer against huge string input.
            Integer.parseInt(test);
            return test.matches(VALIDATION_REGEX);
        } catch (Exception e) {
            return false;
        }
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
     *
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
        if (lowQuantityToBeUpdated != null) {
            return Optional.of(new Quantity(currentQuantity.toString(), lowQuantityToBeUpdated));
        } else {
            return Optional.of(new Quantity(currentQuantity.toString()));
        }
    }

    public QuantityAdder updateLowQuantity(String trimmedLowQuantity) {
        return new QuantityAdder(valueToBeAdded, trimmedLowQuantity);
    }
}
