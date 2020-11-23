package seedu.stock.model.stock.predicates;

import java.util.function.Predicate;

import seedu.stock.model.stock.Stock;

/**
 * Tests that a {@code Stock}'s {@code Source} matches or contains
 * any of the keywords given.
 */
public class LowStocksPredicate implements Predicate<Stock> {

    public LowStocksPredicate () { }

    /**
     * Returns true if stock low is stock.
     * @param stock stock to test
     * @return boolean true if stock is low
     */
    @Override
    public boolean test(Stock stock) {
        // test returns true if stock is low in quantity
        return stock.getQuantity().isLowOnQuantity();
    }

}
