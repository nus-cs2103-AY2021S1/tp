package seedu.homerce.testutil.expense;

import seedu.homerce.model.expense.Expense;
import seedu.homerce.model.manager.ExpenseTracker;

/**
 * A utility class to help with building ExpenseTracker objects.
 */
public class ExpenseTrackerBuilder {

    private ExpenseTracker expenseTracker;

    public ExpenseTrackerBuilder() {
        this.expenseTracker = new ExpenseTracker();
    }

    public ExpenseTrackerBuilder(ExpenseTracker expenseTracker) {
        this.expenseTracker = expenseTracker;
    }

    /**
     * Adds a new {@code Expense} to the {@code ExpenseTracker} that we are building.
     */
    public ExpenseTrackerBuilder withExpense(Expense expense) {
        expenseTracker.addExpense(expense);
        return this;
    }

    public ExpenseTracker build() {
        return expenseTracker;
    }
}

