package seedu.stock.model.stock.predicates;

import java.util.List;

import seedu.stock.model.stock.Stock;

/**
 * Tests that a {@code Stock}'s {@code Source} matches or contains
 * any of the keywords given.
 */
public class SourceContainsKeywordsPredicate extends FieldContainsKeywordsPredicate {

    private final List<String> keywords;

    public SourceContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    /**
     * Returns true if stock's source matches or contains all of the keywords.
     * @param stock stock to test
     * @return boolean true if stock matches keywords
     */
    @Override
    public boolean test(Stock stock) {
        String stockSource = stock.getSource().value.toLowerCase();

        if (!keywords.isEmpty() && keywords.stream().noneMatch(String::isEmpty)) {
            // test returns true if stock source contains all the keywords specified
            return keywords.stream()
                    .allMatch(keyword -> stockSource.contains(keyword.toLowerCase()));
        } else {
            return false;
        }
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
