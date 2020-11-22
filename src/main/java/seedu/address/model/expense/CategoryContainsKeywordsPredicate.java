package seedu.address.model.expense;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;


/**
 * Tests that a {@code Expense}'s {@code Category} matches any of the keywords given.
 */
public class CategoryContainsKeywordsPredicate implements Predicate<Expense> {
    private final List<String> keywords;

    public CategoryContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Expense expense) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(expense.getCategory().categoryName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CategoryContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((CategoryContainsKeywordsPredicate) other).keywords)); // state check
    }

}
