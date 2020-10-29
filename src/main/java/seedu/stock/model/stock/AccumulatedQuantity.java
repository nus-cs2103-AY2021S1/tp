package seedu.stock.model.stock;

import static java.util.Objects.requireNonNull;
import static seedu.stock.commons.util.AppUtil.checkArgument;

/**
 * Represents a source's accumulated quantity.
 */
public class AccumulatedQuantity {

    public static final String MESSAGE_CONSTRAINTS =
            "AccumulatedQuantity numbers should be more than 0.";
    public static final String VALIDATION_REGEX = "\\d+";
    private String accumulatedQuantity;

    /**
     * Constructs a {@code AccumulatedQuantity}.
     *
     * @param accumulatedQuantity A valid accumulated quantity.
     */
    public AccumulatedQuantity(String accumulatedQuantity) {
        requireNonNull(accumulatedQuantity);
        checkArgument(isValidAccumulatedQuantity(accumulatedQuantity), MESSAGE_CONSTRAINTS);
        this.accumulatedQuantity = accumulatedQuantity;
    }

    public AccumulatedQuantity getIncrementedAccumulatedQuantity() {
        try {
            int increased = Integer.parseInt(this.accumulatedQuantity) + 1;
            return new AccumulatedQuantity(Integer.toString(increased));
        } catch (Exception e) {
            //zero denotes integer overflow
            return new AccumulatedQuantity("0");
        }
    }

    public String getAccumulatedQuantity() {
        return this.accumulatedQuantity;
    }

    /**
     * Returns true if a given int is more than zero or more.
     */
    public static boolean isValidAccumulatedQuantity(String test) {
        requireNonNull(test);
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
        return accumulatedQuantity;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AccumulatedQuantity // instanceof handles nulls
                && accumulatedQuantity.equals(((AccumulatedQuantity) other).accumulatedQuantity)); // state check
    }

    @Override
    public int hashCode() {
        return accumulatedQuantity.hashCode();
    }

}
