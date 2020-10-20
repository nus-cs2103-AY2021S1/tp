package seedu.expense.model;

import javafx.collections.ObservableList;
import seedu.expense.model.budget.UniqueCategoryBudgetList;
import seedu.expense.model.expense.Expense;

/**
 * Unmodifiable view of an expense book
 */
public interface ReadOnlyExpenseBook {

    /**
     * Returns an unmodifiable view of the expenses list.
     * This list will not contain any duplicate expenses.
     */
    ObservableList<Expense> getExpenseList();

    /**
     * Returns the budget.
     */
    UniqueCategoryBudgetList getBudgets();

    /**
     * Returns the total sum of the expenses in the expenses list.
     */
    double tallyExpenses();
}
