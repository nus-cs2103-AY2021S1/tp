package seedu.stock.model.stock;

import static java.util.Objects.requireNonNull;
import static seedu.stock.commons.util.AppUtil.checkArgument;

/**
 * Represents a source's accumulated quantity.
 */
public class AccQuantity {

    public static final String MESSAGE_CONSTRAINTS =
            "AccQuantity numbers should be more than 0.";
    public static final String VALIDATION_REGEX = "\\d+";
    private String accQuantity;

    /**
     * Constructs a {@code AccQuantity}.
     *
     * @param accQuantity A valid accumulated quantity.
     */
    public AccQuantity(String accQuantity) {
        requireNonNull(accQuantity);
        checkArgument(isValidAccQuantity(accQuantity), MESSAGE_CONSTRAINTS);
        this.accQuantity = accQuantity;
    }

    public AccQuantity getIncrementedAccQuantity() {
        int increased = Integer.parseInt(this.accQuantity) + 1;
        return new AccQuantity(Integer.toString(increased));
    }

    public String getAccQuantity() {
        return this.accQuantity;
    }

    /**
     * Returns true if a given int is more than zero or more.
     */
    public static boolean isValidAccQuantity(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return accQuantity;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AccQuantity // instanceof handles nulls
                && accQuantity.equals(((AccQuantity) other).accQuantity)); // state check
    }

    @Override
    public int hashCode() {
        return accQuantity.hashCode();
    }

}
