package seedu.stock.model.stock.predicates;

import java.util.List;

import seedu.stock.model.stock.Stock;

/**
 * Tests that a {@code Stock}'s {@code Location} matches or contains
 * any of the keywords given.
 */
public class LocationContainsKeywordsPredicate extends FieldContainsKeywordsPredicate {

    private final List<String> keywords;

    public LocationContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    /**
     * Returns true if the location field of stock matches or contains
     * all of the keywords.
     * @param stock stock to test
     * @return boolean true if location field matches keywords
     */
    @Override
    public boolean test(Stock stock) {
        String stockLocation = stock.getLocation().value.toLowerCase();

        if (!keywords.isEmpty() && keywords.stream().noneMatch(String::isEmpty)) {
            // test returns true if stock's location contains all of the keywords specified
            return keywords.stream()
                    .allMatch(keyword -> stockLocation.contains(keyword.toLowerCase()));
        } else {
            return false;
        }
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
