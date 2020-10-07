package seedu.address.model.expense;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Expense}'s {@code Description} matches any of the keywords given.
 */
public class DateMatchesPredicate implements Predicate<Expense> {
    private final Date date;

    public DateMatchesPredicate(String dateString) {
        Date temp;
        try {
            temp = new Date(dateString);
        } catch (IllegalArgumentException e) {
            temp = null;
        }
        this.date = temp;
    }

    @Override
    public boolean test(Expense expense) {
        return this.date.equals(expense.getDate());
    }
    
    public boolean isEmpty() {
        return this.date == null;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DateMatchesPredicate // instanceof handles nulls
                && date.equals(((DateMatchesPredicate) other).date)); // state check
    }

}
