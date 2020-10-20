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

    Amount getAmount();

    /**
     * Resets the amount in the {@code Budget} to have zero value.
     */
    void reset();
}
