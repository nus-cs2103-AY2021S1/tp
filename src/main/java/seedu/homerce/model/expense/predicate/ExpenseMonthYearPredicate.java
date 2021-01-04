package seedu.homerce.model.expense.predicate;

import java.time.Month;
import java.time.Year;
import java.util.function.Predicate;

import seedu.homerce.model.expense.Expense;

/**
 * Tests that a {@code Expense}'s {@code Date} matches the given month and year.
 */
public class ExpenseMonthYearPredicate implements Predicate<Expense> {
    private final Month month;
    private final Year year;

    /**
     * Constructs an Expense Month Year Predicate.
     * @param month
     * @param year
     */
    public ExpenseMonthYearPredicate(Month month, Year year) {
        this.month = month;
        this.year = year;
    }

    @Override
    public boolean test(Expense expense) {
        Month expenseMonth = expense.getDate().getMonth();
        int expenseYear = expense.getDate().getYear();
        return month.equals(expenseMonth) && year.getValue() == expenseYear;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ExpenseMonthYearPredicate // instanceof handles nulls
                && month.equals(((ExpenseMonthYearPredicate) other).month)
                && year.equals(((ExpenseMonthYearPredicate) other).year)); // state check
    }
}
