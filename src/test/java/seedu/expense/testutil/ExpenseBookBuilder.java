package seedu.expense.testutil;

import seedu.expense.model.ExpenseBook;
import seedu.expense.model.expense.Expense;

/**
 * A utility class to help with building ExpenseBook objects.
 * Example usage: <br>
 *     {@code ExpenseBook ab = new ExpenseBookBuilder().withExpense(expense).build();}
 */
public class ExpenseBookBuilder {

    private ExpenseBook expenseBook;

    public ExpenseBookBuilder() {
        expenseBook = new ExpenseBook();
    }

    public ExpenseBookBuilder(ExpenseBook expenseBook) {
        this.expenseBook = expenseBook;
    }

    /**
     * Adds a new {@code Expense} to the {@code ExpenseBook} that we are building.
     */
    public ExpenseBookBuilder withExpense(Expense expense) {
        if (!expenseBook.containsCategory(expense.getTag())) {
            expenseBook.addCategory(expense.getTag());
        }
        expenseBook.addExpense(expense);
        return this;
    }

    public ExpenseBook build() {
        return expenseBook;
    }
}
