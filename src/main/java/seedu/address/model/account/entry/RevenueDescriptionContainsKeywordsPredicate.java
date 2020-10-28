package seedu.address.model.account.entry;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

public class RevenueDescriptionContainsKeywordsPredicate implements Predicate<Revenue> {
    private final List<String> keywords;

    public RevenueDescriptionContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Revenue entry) {
        return keywords.stream()
            .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(entry.getDescription().toString(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof RevenueDescriptionContainsKeywordsPredicate // instanceof handles nulls
            && keywords.equals(((RevenueDescriptionContainsKeywordsPredicate) other).keywords)); // state check
    }

}
