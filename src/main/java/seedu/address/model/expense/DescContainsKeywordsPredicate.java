package seedu.address.model.expense;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;


/**
 * Tests that a {@code Expense}'s {@code Description} matches any of the keywords given.
 */
public class DescContainsKeywordsPredicate implements Predicate<Expense> {
    private final List<String> keywords;

    public DescContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Expense expense) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(expense.getDescription().value, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DescContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((DescContainsKeywordsPredicate) other).keywords)); // state check
    }

}
