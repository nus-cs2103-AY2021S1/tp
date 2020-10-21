package seedu.stock.model.stock.predicates;

import java.util.function.Predicate;

import seedu.stock.model.stock.Stock;

/**
 * Tests that a {@code Stock}'s {@code Field} matches or contains
 * any of the keywords given.
 */
public abstract class FieldContainsKeywordsPredicate implements Predicate<Stock> {

    /**
     * Returns true if stock's field matches or contains all of the keywords.
     * @param stock stock to test
     * @return boolean true if stock matches keywords
     */
    @Override
    public abstract boolean test(Stock stock);

}
