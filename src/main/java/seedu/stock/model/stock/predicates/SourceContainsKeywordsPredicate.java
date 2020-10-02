package seedu.stock.model.stock.predicates;

import seedu.stock.model.stock.Stock;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Stock}'s {@code Source} matches or contains
 * any of the keywords given.
 */
public class SourceContainsKeywordsPredicate implements Predicate<Stock> {

    private final List<String> keywords;

    public SourceContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Stock stock) {
        String stockSource = stock.getSource().value.toLowerCase();

        boolean isPartialMatch = keywords.stream()
                .anyMatch(keyword -> stockSource.contains(keyword.toLowerCase()));

        return isPartialMatch;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SourceContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((SourceContainsKeywordsPredicate) other).keywords)); // state check
    }

}
