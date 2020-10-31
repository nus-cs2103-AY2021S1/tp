package seedu.cc.model.account.entry;

import java.util.List;
import java.util.function.Predicate;

import seedu.cc.commons.util.StringUtil;

public class ExpenseDescriptionContainsKeywordsPredicate implements Predicate<Expense> {
    private final List<String> keywords;

    public ExpenseDescriptionContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Expense entry) {
        return keywords.stream()
            .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(entry.getDescription().toString(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof ExpenseDescriptionContainsKeywordsPredicate // instanceof handles nulls
            && keywords.equals(((ExpenseDescriptionContainsKeywordsPredicate) other).keywords)); // state check
    }
}
