package seedu.stock.model.stock.predicates;

import java.util.List;
import java.util.function.Predicate;

import seedu.stock.commons.util.StringUtil;
import seedu.stock.model.stock.Stock;

/**
 * Tests that a {@code Stock}'s {@code Name} matches or contains
 * any of the keywords given.
 */
public class NameContainsKeywordsPredicate implements Predicate<Stock> {
    private final List<String> keywords;

    public NameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Stock stock) {
        String stockName = stock.getName().fullName.toLowerCase();

//        boolean isFullMatch = keywords.stream()
//                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(stockName, keyword));
        boolean isPartialMatch = keywords.stream()
                .anyMatch(keyword -> stockName.contains(keyword.toLowerCase()));

        return isPartialMatch;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((NameContainsKeywordsPredicate) other).keywords)); // state check
    }

}
