package seedu.address.model.ingredient;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represent the amount of a particular ingredient in tCheck.
 */
public class Amount {

    public static final String MESSAGE_CONSTRAINTS =
            "Amount should only contain numbers, and it should be equals to or greater than 0";
    public static final String VALIDATION_REGEX = "\\d{1,}";

    private static final int RESTOCK_LEVEL = 5;

    public final String amount;

    /**
     * Constructs an amount from the given string representing the amount.
     * @param amount a string representing amount
     */
    public Amount(String amount) {
        requireNonNull(amount);
        checkArgument(isValidAmount(amount), MESSAGE_CONSTRAINTS);
        this.amount = amount;
    }

    /**
     * Returns true if a given string is a valid amount.
     * @param test the given string representing the amount of an ingredient
     */
    public static boolean isValidAmount(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns the amount of ingredients in string format.
     *
     * @return the amount of ingredients in string format.
     */
    public boolean isBelowRestockLevel() {
        return Integer.parseInt(amount) < RESTOCK_LEVEL;
    }

    @Override
    public String toString() {
        return this.amount;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Amount // instanceof handles nulls
                && amount.equals(((Amount) other).amount)); // state check
    }

    @Override
    public int hashCode() {
        return amount.hashCode();
    }

}
