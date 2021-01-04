package seedu.homerce.model.expense;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.homerce.testutil.expense.TypicalExpenses.CONDITIONER;

import org.junit.jupiter.api.Test;

import seedu.homerce.model.util.attributes.Tag;

public class TagPredicateTest {
    @Test
    public void equals() {
        Tag firstTag = new Tag("hairsupplies");
        Tag secondTag = new Tag("others");

        TagPredicate firstPredicate = new TagPredicate(firstTag);
        TagPredicate secondPredicate = new TagPredicate(secondTag);

        // same object -> return true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> return true
        TagPredicate firstPredicateCopy = new TagPredicate(firstTag);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> return false
        assertFalse(firstPredicate.equals(1));

        // null -> return false
        assertFalse(firstPredicate.equals(null));

        // different values -> return false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_expenseDoesNotContainTag_returnFalse() {
        TagPredicate predicate = new TagPredicate(new Tag("others"));
        assertFalse(predicate.test(CONDITIONER));
    }

    @Test
    public void test_expenseContainsTag_returnTrue() {
        TagPredicate predicate = new TagPredicate(new Tag("hairsupplies"));
        assertTrue(predicate.test(CONDITIONER));
    }
}
