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

    /**
     * Returns true if the location field of stock matches or contains
     * any of the keywords.
     * @param stock stock to test
     * @return boolean true if location field matches keywords
     */
    @Override
    public boolean test(Stock stock) {
        String stockLocation = stock.getLocation().value.toLowerCase();

        return keywords.stream()
                .anyMatch(keyword -> stockLocation.contains(keyword.toLowerCase()));
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
