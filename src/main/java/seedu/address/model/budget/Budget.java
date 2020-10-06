package seedu.address.model.budget;

import seedu.address.model.expense.Amount;

/**
 * Represents a Budget in the ledger.
 */
public class Budget {

    private Amount amount;

    /**
     * Constructs a {@code Budget} with zero value.
     */
    public Budget() {
        amount = new Amount("0");
    }

    /**
     * Tops up the budget by the specified {@code Amount}.
     */
    public void topupBudget(Amount toAdd) {
        amount = amount.add(toAdd);
    }

    public Amount getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return String.format("Budget: %s", amount.toString());
    }
}
