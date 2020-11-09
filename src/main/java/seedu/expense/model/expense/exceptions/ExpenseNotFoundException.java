package seedu.expense.model.expense.exceptions;

/**
 * Signals that the operation is unable to find the specified expense.
 */
public class ExpenseNotFoundException extends RuntimeException {
    public ExpenseNotFoundException() {
        super("The given Expense does not exist in expense book");
    }
}
