package seedu.homerce.model.expense;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.homerce.testutil.expense.TypicalExpenses.CONDITIONER;

import org.junit.jupiter.api.Test;

import seedu.homerce.model.expense.predicate.ExpenseDatePredicate;
import seedu.homerce.model.util.attributes.Date;

public class ExpenseDatePredicateTest {
    @Test
    public void equals() {
        Date firstDate = new Date("20-10-2020");
        Date secondDate = new Date("21-10-2020");

        ExpenseDatePredicate firstPredicate = new ExpenseDatePredicate(firstDate);
        ExpenseDatePredicate secondPredicate = new ExpenseDatePredicate(secondDate);


        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        ExpenseDatePredicate firstPredicateCopy = new ExpenseDatePredicate(firstDate);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different dates -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_expenseDoesNotContainDate_returnsFalse() {
        ExpenseDatePredicate predicate = new ExpenseDatePredicate(new Date("25-10-2020"));
        assertFalse(predicate.test(CONDITIONER));

    }

    @Test
    public void test_expenseContainSDate_returnsTrue() {
        ExpenseDatePredicate predicate = new ExpenseDatePredicate(new Date("10-12-2020"));
        assertTrue(predicate.test(CONDITIONER));
    }
}

