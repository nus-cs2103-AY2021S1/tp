package seedu.homerce.model.expense;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.homerce.testutil.expense.TypicalExpenses.CONDITIONER;

import org.junit.jupiter.api.Test;

import seedu.homerce.model.expense.predicate.ExpenseDescriptionPredicate;
import seedu.homerce.model.util.attributes.Description;
import seedu.homerce.testutil.expense.ExpenseBuilder;

public class ExpenseDescriptionPredicateTest {
    @Test
    public void equals() {
        Description firstDescription = new Description("Conditioner");
        Description secondDescription = new Description("Chair");

        ExpenseDescriptionPredicate firstPredicate = new ExpenseDescriptionPredicate(firstDescription);
        ExpenseDescriptionPredicate secondPredicate = new ExpenseDescriptionPredicate(secondDescription);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        ExpenseDescriptionPredicate firstPredicateCopy = new ExpenseDescriptionPredicate(firstDescription);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different titles -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_expenseDoesNotContainDescription_returnsFalse() {
        ExpenseDescriptionPredicate predicate = new ExpenseDescriptionPredicate(new Description("Chair"));
        assertFalse(predicate.test(CONDITIONER));
    }

    @Test
    public void test_expenseContainsDescription_returnsTrue() {
        ExpenseDescriptionPredicate predicate = new ExpenseDescriptionPredicate(new Description("Conditioner"));
        assertTrue(predicate.test(CONDITIONER));
    }

    @Test
    public void test_expenseDoesNotContainFullKeyword_returnsFalse() {
        ExpenseDescriptionPredicate predicate = new ExpenseDescriptionPredicate(new Description("exten"));
        assertFalse(predicate.test(new ExpenseBuilder().withDescription("Lash Extension Glue").build()));
    }

    @Test
    public void test_expenseContainsKeyword_returnsTrue() {
        ExpenseDescriptionPredicate predicate = new ExpenseDescriptionPredicate(new Description("extension"));
        assertTrue(predicate.test(new ExpenseBuilder().withDescription("Lash Extension Glue").build()));
    }

    @Test
    public void test_expenseContainsKeywords_returnsTrue() {
        ExpenseDescriptionPredicate predicate = new ExpenseDescriptionPredicate(new Description("lash extension"));
        assertTrue(predicate.test(new ExpenseBuilder().withDescription("Lash Extension Glue").build()));
    }
}

