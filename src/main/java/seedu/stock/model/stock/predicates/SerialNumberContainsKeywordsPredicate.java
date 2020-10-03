package seedu.stock.model.stock.predicates;

import seedu.stock.model.stock.Stock;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Stock}'s {@code SerialNumber} matches or contains
 * any of the keywords given.
 */
public class SerialNumberContainsKeywordsPredicate implements Predicate<Stock> {

    private final List<String> keywords;

    public SerialNumberContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Stock stock) {
        String stockSerialNumber = stock.getSerialNumber().serialNumber.toLowerCase();

        boolean isPartialMatch = keywords.stream()
                .anyMatch(keyword -> stockSerialNumber.contains(keyword.toLowerCase()));

        return isPartialMatch;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SerialNumberContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((SerialNumberContainsKeywordsPredicate) other).keywords)); // state check
    }

    @Override
    public String toString() {
        return "Serial Number: " + keywords.stream().reduce((keyword, next) -> keyword + " " + next).get();
    }
}
