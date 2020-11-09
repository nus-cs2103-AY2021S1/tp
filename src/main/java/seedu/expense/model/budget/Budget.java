package seedu.expense.model.budget;

import seedu.expense.model.expense.Amount;

/**
 * Represents a Budget in the ledger.
 */
public interface Budget {

    /**
     * Tops up the budget by the specified {@code Amount}.
     */
    void topupBudget(Amount toAdd);

    /**
     * Reduces the budget by the specified {@code Amount}.
     */
    void reduceBudget(Amount toSubtract);

    Amount getAmount();

    /**
     * Returns true if the budget contains the specified amount or more.
     */
    boolean hasAmount(Amount amount);

    /**
     * Resets the amount in the {@code Budget} to have zero value.
     */
    void reset();
}
