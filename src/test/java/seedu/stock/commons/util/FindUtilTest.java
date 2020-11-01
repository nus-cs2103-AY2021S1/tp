package seedu.stock.commons.util;

import org.junit.jupiter.api.Test;
import seedu.stock.model.stock.Stock;
import seedu.stock.model.stock.predicates.FieldContainsKeywordsPredicate;
import seedu.stock.model.stock.predicates.LocationContainsKeywordsPredicate;
import seedu.stock.model.stock.predicates.NameContainsKeywordsPredicate;
import seedu.stock.testutil.StockBuilder;
import seedu.stock.testutil.TypicalStocks;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FindUtilTest {

    @Test
    public void getCombinedPredicatesWithOr() {

        // positive test case
        Stock stockToTest = new StockBuilder(TypicalStocks.APPLE).build();

        List<FieldContainsKeywordsPredicate> predicates =
                Arrays.asList(new NameContainsKeywordsPredicate(Collections.singletonList("apple")),
                        new LocationContainsKeywordsPredicate(Collections.singletonList("location")));

        assertTrue(FindUtil.getCombinedPredicatesWithOr(predicates).test(stockToTest));

        // negative test case
        List<FieldContainsKeywordsPredicate> predicatesTwo =
                Arrays.asList(new NameContainsKeywordsPredicate(Arrays.asList("apple", "two")),
                        new LocationContainsKeywordsPredicate(Collections.singletonList("location")));

        assertFalse(FindUtil.getCombinedPredicatesWithOr(predicatesTwo).test(stockToTest));

    }

    @Test
    public void getCombinedPredicatesWithAnd() {

        // positive test case
        Stock stockToTest = new StockBuilder(TypicalStocks.APPLE).build();

        List<FieldContainsKeywordsPredicate> predicates =
                Arrays.asList(new NameContainsKeywordsPredicate(Collections.singletonList("apple")),
                        new LocationContainsKeywordsPredicate(Collections.singletonList("fruit")));

        assertTrue(FindUtil.getCombinedPredicatesWithAnd(predicates).test(stockToTest));

        // negative test case
        List<FieldContainsKeywordsPredicate> predicatesTwo =
                Arrays.asList(new NameContainsKeywordsPredicate(Collections.singletonList("apple")),
                        new LocationContainsKeywordsPredicate(Collections.singletonList("location")));

        assertFalse(FindUtil.getCombinedPredicatesWithAnd(predicatesTwo).test(stockToTest));

    }
}
