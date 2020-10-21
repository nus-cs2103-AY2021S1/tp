package seedu.stock.commons.util;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import seedu.stock.model.stock.Stock;
import seedu.stock.model.stock.predicates.FieldContainsKeywordsPredicate;

/**
 * Utility to combines a list of predicates to test into one predicate.
 */
public class FindUtil {

    /**
     * Returns a predicate where if only one of the predicate in the list of predicates provided
     * tests true, then it tests true.
     * @param predicates list of predicates to test
     * @return combined predicate
     */
    public static Predicate<Stock> getCombinedPredicatesWithOr(List<FieldContainsKeywordsPredicate> predicates) {
        List<Predicate<Stock>> predicateList = new ArrayList<>();
        predicates.forEach(predicate -> predicateList.add(predicate));
        return predicateList.stream().reduce(x -> false, Predicate::or);
    }

    /**
     * Returns a predicate where if all of the predicate in the list of predicates provided
     * tests true, then it tests true.
     * @param predicates list of predicates to test
     * @return combined predicate
     */
    public static Predicate<Stock> getCombinedPredicatesWithAnd(List<FieldContainsKeywordsPredicate> predicates) {
        List<Predicate<Stock>> predicateList = new ArrayList<>();
        predicates.forEach(predicate -> predicateList.add(predicate));
        return predicateList.stream().reduce(x -> true, Predicate::and);
    }

}
