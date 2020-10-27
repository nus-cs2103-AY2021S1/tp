package seedu.expense.model.budget.exceptions;

/**
 * Signals that the operation is unable to find the specified budget.
 */
public class CategoryBudgetNotFoundException extends RuntimeException {
    public CategoryBudgetNotFoundException() {
        super("The given CategoryBudget does not exist in expense book");
    }
}
