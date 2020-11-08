package seedu.expense.model;

import seedu.expense.model.expense.Amount;

/**
 * Statistical calculation of an expense book
 */
public interface Statistics {

    /**
     * Returns the tallied total expenses amount
     *
     * @return tallied amount
     */
    Amount tallyExpenses();

    /**
     * Returns the tallied filtered budgets amount
     *
     * @return tallied amount
     */
    Amount tallyBudgets();

    /**
     * Tallies the balance of filtered budgets and filtered expenses in the expense book.
     *
     * @return tallied balance of the expense book
     */
    Amount tallyBalance();
}
