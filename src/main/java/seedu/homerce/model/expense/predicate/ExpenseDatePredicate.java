package seedu.homerce.model.expense.predicate;

import java.util.function.Predicate;

import seedu.homerce.model.expense.Expense;
import seedu.homerce.model.util.attributes.Date;

/**
 * Tests that a {@code Expense}'s {@code Date} matches the given date.
 */
public class ExpenseDatePredicate implements Predicate<Expense> {
    private final Date date;

    public ExpenseDatePredicate(Date date) {
        this.date = date;
    }

    @Override
    public boolean test(Expense expense) {
        return date.equals(expense.getDate());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ExpenseDatePredicate // instanceof handles nulls
                && date.equals(((ExpenseDatePredicate) other).date)); // state check
    }
}
