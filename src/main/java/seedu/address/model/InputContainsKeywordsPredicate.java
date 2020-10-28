package seedu.address.model;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

public class InputContainsKeywordsPredicate implements Predicate<SalesRecordEntry> {
    private final List<String> keywords;

    public InputContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(SalesRecordEntry salesRecordEntry) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(salesRecordEntry.getDrink().getShortFormName(),
                        keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof InputContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((InputContainsKeywordsPredicate) other).keywords)); // state check
    }
}
