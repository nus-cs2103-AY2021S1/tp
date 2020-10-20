package seedu.expense.model.budget.exceptions;

/**
 * Signals that the operation will result in duplicate CategoryBudgets
 * (CategoryBudgets are considered duplicates if they have the same identity).
 */
public class DuplicateCategoryBudgetException extends RuntimeException {
    public DuplicateCategoryBudgetException() {
        super("Operation would result in duplicate category-budgets");
    }
}
