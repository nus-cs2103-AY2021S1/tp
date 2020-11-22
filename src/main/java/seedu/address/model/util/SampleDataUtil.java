package seedu.address.model.util;

import seedu.address.model.ExpenseBook;
import seedu.address.model.ReadOnlyExpenseBook;
import seedu.address.model.expense.Amount;
import seedu.address.model.expense.Category;
import seedu.address.model.expense.Date;
import seedu.address.model.expense.Description;
import seedu.address.model.expense.Expense;


/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static final Description EMPTY_DESCRIPTION = new Description("");
    public static Expense[] getSampleExpenses() {
        return new Expense[]{
            new Expense(new Amount(11.2), new Date("2"), new Category("entertainment"),
                    new Description("movie")),
            new Expense(new Amount(1.2), new Date("1"), new Category("foodbeverage"),
                    new Description("macdonald")),
            new Expense(new Amount(5.2), new Date("5"), new Category("transportation"),
                    new Description("bus")),
            new Expense(new Amount(6.2), new Date("7"), new Category("entertainment"),
                    EMPTY_DESCRIPTION),
            new Expense(new Amount(7.2), new Date("6"), new Category("entertainment"),
                    EMPTY_DESCRIPTION),
            new Expense(new Amount(8.2), new Date("3"), new Category("entertainment"),
                    EMPTY_DESCRIPTION),
        };
    }

    public static ReadOnlyExpenseBook getSampleExpenseBook() {
        ExpenseBook sampleAb = new ExpenseBook();
        for (Expense sampleExpense : getSampleExpenses()) {
            sampleAb.addExpense(sampleExpense);
        }
        return sampleAb;
    }

    public static ReadOnlyExpenseBook getExpenseBookWithoutSample(ExpenseBook expenseBook) {
        for (Expense sampleExpense : getSampleExpenses()) {
            expenseBook.removeExpense(sampleExpense);
        }
        return expenseBook;
    }

}
