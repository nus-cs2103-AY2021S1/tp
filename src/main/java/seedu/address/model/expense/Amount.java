package seedu.address.model.expense;

import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Expense's amount in the expense book.
 * Guarantees: the amount is non-negative; is valid as declared in {@link #isValidAmount(Double)}
 */
public class Amount {
    public static final String MESSAGE_CONSTRAINTS =
            "Amount should only positive numbers.";


    public final Double value;
    public final boolean noValue;

    /**
     * Constructs an amount object.
     *
     * @param value Value should be double.
     */
    public Amount(Double value) {
        checkArgument(isValidAmount(value), MESSAGE_CONSTRAINTS);
        this.value = value;
        this.noValue = false;
    }

    /**
     * Constructs an amount object.
     *
     */
    public Amount() {
        this.noValue = true;
        this.value = -1.0;
    }

    /**
     * Returns true if a given amount is a valid amount.
     */
    public static boolean isValidAmount(Double value) {
        return value > 0;
    }

    @Override
    public String toString() {
        return String.format("%.2f", this.value);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Amount // instanceof handles nulls
                && value.equals(((Amount) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    public double getValue() {
        return this.value;
    }
    public boolean isEmpty() {
        return this.noValue;
    }

}
