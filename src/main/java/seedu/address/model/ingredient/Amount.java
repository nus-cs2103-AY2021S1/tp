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

    private static final int LIQUID_RESTOCK_LEVEL = 50;

    private static final int SOLID_RESTOCK_LEVEL = 20;

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
     * Returns true if the liquid ingredient is below the restock level, which is the default level of the ingredient.
     * @return true if the liquid ingredient is below the restock level, false otherwise.
     */
    public boolean isLiquidBelowRestockLevel() {
        return Integer.parseInt(amount) < LIQUID_RESTOCK_LEVEL;
    }

    /**
     * Returns true if the solid ingredient is below the restock level, which is the default level of the ingredient.
     * @return true if the solid ingredient is below the restock level, false otherwise.
     */
    public boolean isSolidBelowRestockLevel() {
        return Integer.parseInt(amount) < SOLID_RESTOCK_LEVEL;
    }

    /**
     * Returns the amount of liquid ingredient needed to reach the restock level, which is the default level
     * of the ingredient.
     * @return the amount of liquid ingredient needed to reach the restock level in string format.
     */
    public String liquidAmountToReachRestockLevel() {
        return Integer.toString(LIQUID_RESTOCK_LEVEL - Integer.parseInt(amount));
    }

    /**
     * Returns the amount of solid ingredient needed to reach the restock level, which is the default level
     * of the ingredient.
     * @return the amount of solid ingredient needed to reach the restock level in string format.
     */
    public String solidAmountToReachRestockLevel() {
        return Integer.toString(SOLID_RESTOCK_LEVEL - Integer.parseInt(amount));
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
