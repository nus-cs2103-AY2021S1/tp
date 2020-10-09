package seedu.address.model.expense;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Expense}'s {@code Description} matches any of the keywords given.
 */
public class NameContainsKeywordsPredicate implements Predicate<Expense> {
    private final Set<String> keywords;

    public NameContainsKeywordsPredicate(List<String> keywords) {
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
                || (other instanceof NameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((NameContainsKeywordsPredicate) other).keywords)); // state check
    }

    /**
     * Returns true if there are no valid keywords in this predicate. Otherwise, return false.
     */
    public boolean isEmpty() {
        return this.keywords.isEmpty() || (this.keywords.size() == 1 && this.keywords.contains(""));
    }

}
