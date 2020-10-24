package seedu.stock.model.stock.predicates;

import java.util.function.Predicate;

import seedu.stock.model.stock.Stock;

/**
 * Tests that a {@code Stock}'s {@code Source} matches or contains
 * any of the keywords given.
 */
public class BookmarkedPredicate implements Predicate<Stock> {

    public BookmarkedPredicate () { }

    /**
     * Returns true if stock isBookmarked.
     * @param stock stock to test
     * @return boolean true if stock is bookmarked
     */
    @Override
    public boolean test(Stock stock) {
        boolean stockIsBookmarked = stock.getIsBookmarked();

        if (stockIsBookmarked) {
            // test returns true if stock isBookmarked
            return true;
        } else {
            return false;
        }
    }


}
