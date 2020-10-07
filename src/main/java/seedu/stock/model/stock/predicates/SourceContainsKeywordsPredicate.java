package seedu.stock.model.stock.predicates;

import java.util.List;
import java.util.function.Predicate;

import seedu.stock.model.stock.Stock;

/**
 * Tests that a {@code Stock}'s {@code Source} matches or contains
 * any of the keywords given.
 */
public class SourceContainsKeywordsPredicate implements Predicate<Stock> {

    private final List<String> keywords;

    public SourceContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    /**
     * Returns true if stock's source matches or contains any of the keywords.
     * @param stock stock to test
     * @return boolean true if stock matches keywords
     */
    @Override
    public boolean test(Stock stock) {
        String stockSource = stock.getSource().value.toLowerCase();

        return keywords.stream()
                .anyMatch(keyword -> stockSource.contains(keyword.toLowerCase()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SourceContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((SourceContainsKeywordsPredicate) other).keywords)); // state check
    }

    @Override
    public String toString() {
        return "Source: " + keywords.stream().reduce((keyword, next) -> keyword + " " + next).get();
    }

}
