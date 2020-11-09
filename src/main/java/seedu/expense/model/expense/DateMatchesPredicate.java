package seedu.expense.model.expense;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

/**
 * Tests that any of an {@code Expense}'s {@code Dates} matches any of the keywords given.
 */
public class DateMatchesPredicate implements Predicate<Expense> {
    private final Set<Date> dates;

    /**
     * Constructor that takes in a list of strings representing dates.
     * It stores parsable strings into the dates HashSet.
     */
    public DateMatchesPredicate(List<String> dateStrings) {
        Set<Date> temp = new HashSet<>();
        for (String s: dateStrings) {
            if (Date.isValidDate(s)) {
                temp.add(new Date(s));
            }
        }
        this.dates = temp;
    }

    @Override
    public boolean test(Expense expense) {
        if (this.isEmpty()) {
            return false;
        }
        return this.dates.contains(expense.getDate());
    }

    /**
     * Returns true if there are no dates to match in this predicate. Otherwise, return false.
     */
    public boolean isEmpty() {
        return this.dates.isEmpty();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DateMatchesPredicate // instanceof handles nulls
                && dates.equals(((DateMatchesPredicate) other).dates)); // state check
    }
}
