package seedu.address.model.expense;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Expense}'s {@code Description} matches any of the keywords given.
 */
public class DateMatchesPredicate implements Predicate<Expense> {
    private final Set<Date> dates;

    public DateMatchesPredicate(List<String> dateStrings) {
        Set<Date> temp = new HashSet<Date>();
        for (String s: dateStrings) {
            if (Date.isValidDate(s)) {
                temp.add(new Date(s));
            }
        }
        if (temp.isEmpty()) {
            temp = null;
        }
        this.dates = temp;
    }

    @Override
    public boolean test(Expense expense) {
        if (this.dates == null) {
            return false;
        }
        return this.dates.contains(expense.getDate());
    }
    
    public boolean isEmpty() {
        return this.dates == null;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DateMatchesPredicate // instanceof handles nulls
                && dates.equals(((DateMatchesPredicate) other).dates)); // state check
    }

}
