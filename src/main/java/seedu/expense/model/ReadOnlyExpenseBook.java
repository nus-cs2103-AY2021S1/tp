package seedu.expense.model;

import javafx.collections.ObservableList;
import seedu.expense.model.budget.CategoryBudget;
import seedu.expense.model.budget.UniqueCategoryBudgetList;
import seedu.expense.model.expense.Expense;
import seedu.expense.model.tag.Tag;

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
     * Returns an unmodifiable view of the budgets list.
     * This list will not contain any duplicate budgets.
     */
    ObservableList<CategoryBudget> getBudgetList();

    /**
     * Returns the budgets.
     */
    UniqueCategoryBudgetList getBudgets();

    /**
     * Returns the tags.
     */
    ObservableList<Tag> getTags();
}
