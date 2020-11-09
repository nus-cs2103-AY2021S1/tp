package seedu.expense.model.expense;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import seedu.expense.commons.util.StringUtil;

/**
 * Tests that a {@code Expense}'s {@code Description} matches any of the keywords given.
 */
public class DescriptionContainsKeywordsPredicate implements Predicate<Expense> {
    private final Set<String> keywords;

    public DescriptionContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = new HashSet<>(keywords);
    }

    @Override
    public boolean test(Expense expense) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(expense.getDescription()
                        .fullDescription, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DescriptionContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((DescriptionContainsKeywordsPredicate) other).keywords)); // state check
    }

    /**
     * Returns true if there are no valid keywords in this predicate. Otherwise, return false.
     */
    public boolean isEmpty() {
        return this.keywords.isEmpty() || (this.keywords.size() == 1 && this.keywords.contains(""));
    }

}
