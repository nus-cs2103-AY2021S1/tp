package seedu.homerce.model.revenue;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.homerce.testutil.revenue.TypicalRevenues.LASH_LIFT;

import java.time.Month;
import java.time.Year;

import org.junit.jupiter.api.Test;

import seedu.homerce.model.revenue.predicate.RevenueMonthYearPredicate;

public class RevenueMonthYearPredicateTest {

    @Test
    public void equals() {
        RevenueMonthYearPredicate firstPredicate = new RevenueMonthYearPredicate(Month.DECEMBER, Year.of(2020));
        RevenueMonthYearPredicate secondPredicate = new RevenueMonthYearPredicate(Month.JANUARY, Year.of(2019));

        // same object -> return true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> return true
        RevenueMonthYearPredicate firstPredicateCopy = new RevenueMonthYearPredicate(Month.DECEMBER, Year.of(2020));
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> return false
        assertFalse(firstPredicate.equals(1));

        // null -> return false
        assertFalse(firstPredicate.equals(null));

        // different values -> return false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_revenueIsNotSameMonthYear_returnFalse() {
        RevenueMonthYearPredicate predicate = new RevenueMonthYearPredicate(Month.DECEMBER, Year.of(2020));
        assertFalse(predicate.test(LASH_LIFT));
    }

    @Test
    public void test_revenueIsSameMonthYear_returnTrue() {
        RevenueMonthYearPredicate predicate = new RevenueMonthYearPredicate(Month.OCTOBER, Year.of(2020));
        assertTrue(predicate.test(LASH_LIFT));
    }
}
