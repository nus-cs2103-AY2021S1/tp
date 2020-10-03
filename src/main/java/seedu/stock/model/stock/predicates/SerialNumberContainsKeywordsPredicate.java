package seedu.stock.model.stock.predicates;

import java.util.List;
import java.util.function.Predicate;

import seedu.stock.model.stock.Stock;


/**
 * Tests that a {@code Stock}'s {@code SerialNumber} matches or contains
 * any of the keywords given.
 */
public class SerialNumberContainsKeywordsPredicate implements Predicate<Stock> {

    private final List<String> keywords;

    public SerialNumberContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    /**
     * Returns true if stock's serial number matches or contains any of the keywords.
     * @param stock stock to test
     * @return boolean true if stock matches keywords
     */
    @Override
    public boolean test(Stock stock) {
        String stockSerialNumber = stock.getSerialNumber().getSerialNumber().toLowerCase();

        return keywords.stream()
                .anyMatch(keyword -> stockSerialNumber.contains(keyword.toLowerCase()));
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
