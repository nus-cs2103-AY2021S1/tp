package seedu.stock.model.stock.predicates;

import seedu.stock.model.stock.Stock;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Stock}'s {@code Location} matches or contains
 * any of the keywords given.
 */
public class LocationContainsKeywordsPredicate implements Predicate<Stock> {

    private final List<String> keywords;

    public LocationContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Stock stock) {
        String stockLocation = stock.getLocation().value.toLowerCase();

        boolean isPartialMatch = keywords.stream()
                .anyMatch(keyword -> stockLocation.contains(keyword.toLowerCase()));

        return isPartialMatch;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof LocationContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((LocationContainsKeywordsPredicate) other).keywords)); // state check
    }

    @Override
    public String toString() {
        return "Location: " + keywords.stream().reduce((keyword, next) -> keyword + " " + next).get();
    }
}
