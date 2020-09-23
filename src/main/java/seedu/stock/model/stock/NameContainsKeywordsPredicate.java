package seedu.stock.model.stock;

import java.util.List;
import java.util.function.Predicate;

import seedu.stock.commons.util.StringUtil;

/**
 * Tests that a {@code Stock}'s {@code Name} matches any of the keywords given.
 */
public class NameContainsKeywordsPredicate implements Predicate<Stock> {
    private final List<String> keywords;

    public NameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Stock stock) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(stock.getName().fullName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((NameContainsKeywordsPredicate) other).keywords)); // state check
    }

}
