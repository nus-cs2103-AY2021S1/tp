package seedu.stock.model.stock.predicates;

import java.util.List;
import java.util.function.Predicate;

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

    /**
     * Returns true if stock's name matches or contains any of the keywords.
     * @param stock stock to test
     * @return boolean true if stock matches keywords
     */
    @Override
    public boolean test(Stock stock) {
        String stockName = stock.getName().fullName.toLowerCase();

        return keywords.stream()
                .anyMatch(keyword -> stockName.contains(keyword.toLowerCase()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((NameContainsKeywordsPredicate) other).keywords)); // state check
    }

    @Override
    public String toString() {
        return "Name: " + keywords.stream().reduce((keyword, next) -> keyword + " " + next).get();
    }
}
