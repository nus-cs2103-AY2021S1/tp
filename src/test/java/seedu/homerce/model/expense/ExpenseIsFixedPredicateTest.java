package seedu.homerce.model.expense;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.homerce.testutil.expense.TypicalExpenses.CONDITIONER;

import org.junit.jupiter.api.Test;

import seedu.homerce.model.expense.predicate.ExpenseIsFixedPredicate;

public class ExpenseIsFixedPredicateTest {
    @Test
    public void equals() {
        IsFixed firstIsFixed = new IsFixed("y");
        IsFixed secondIsFixed = new IsFixed("n");

        ExpenseIsFixedPredicate firstPredicate = new ExpenseIsFixedPredicate(firstIsFixed);
        ExpenseIsFixedPredicate secondPredicate = new ExpenseIsFixedPredicate(secondIsFixed);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        ExpenseIsFixedPredicate firstPredicateCopy = new ExpenseIsFixedPredicate(firstIsFixed);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different titles -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_expenseDoesNotContainIsFixed_returnsFalse() {
        ExpenseIsFixedPredicate predicate = new ExpenseIsFixedPredicate(new IsFixed("y"));
        assertFalse(predicate.test(CONDITIONER));
    }

    @Test
    public void test_expenseContainIsFixed_returnsTrue() {
        ExpenseIsFixedPredicate predicate = new ExpenseIsFixedPredicate(new IsFixed("n"));
        assertTrue(predicate.test(CONDITIONER));
    }
}

