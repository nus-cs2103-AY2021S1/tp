package seedu.homerce.model.expense;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.homerce.testutil.expense.TypicalExpenses.CONDITIONER;

import java.time.Month;
import java.time.Year;

import org.junit.jupiter.api.Test;

import seedu.homerce.model.expense.predicate.ExpenseMonthYearPredicate;

public class ExpenseMonthYearPredicateTest {
    @Test
    public void equals() {
        ExpenseMonthYearPredicate firstPredicate = new ExpenseMonthYearPredicate(Month.DECEMBER, Year.of(2020));
        ExpenseMonthYearPredicate secondPredicate = new ExpenseMonthYearPredicate(Month.JANUARY, Year.of(2019));

        // same object -> return true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> return true
        ExpenseMonthYearPredicate firstPredicateCopy = new ExpenseMonthYearPredicate(Month.DECEMBER, Year.of(2020));
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> return false
        assertFalse(firstPredicate.equals(1));

        // null -> return false
        assertFalse(firstPredicate.equals(null));

        // different values -> return false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_expenseIsNotSameMonthYear_returnFalse() {
        ExpenseMonthYearPredicate predicate = new ExpenseMonthYearPredicate(Month.OCTOBER, Year.of(2020));
        assertFalse(predicate.test(CONDITIONER));
    }

    @Test
    public void test_expenseIsSameMonthYear_returnTrue() {
        ExpenseMonthYearPredicate predicate = new ExpenseMonthYearPredicate(Month.DECEMBER, Year.of(2020));
        assertTrue(predicate.test(CONDITIONER));
    }
}
