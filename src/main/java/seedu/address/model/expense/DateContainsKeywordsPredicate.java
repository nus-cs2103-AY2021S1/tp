package seedu.address.model.expense;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;


/**
 * Tests that a {@code Expense}'s {@code Date} matches any of the keywords given.
 */
public class DateContainsKeywordsPredicate implements Predicate<Expense> {
    private final List<String> keywords;

    public DateContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Expense expense) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(expense.getDate().date, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DateContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((DateContainsKeywordsPredicate) other).keywords)); // state check
    }

}
