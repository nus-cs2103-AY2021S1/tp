package seedu.simplykitchen.model.food;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.simplykitchen.testutil.FoodBuilder;

public class PrioritySearchPredicateTest {

    @Test
    public void equals() {
        Priority.Level firstPriorityPredicate = Priority.Level.HIGH;
        Priority.Level secondPriorityPredicate = Priority.Level.MEDIUM;

        PrioritySearchPredicate firstPredicate =
                new PrioritySearchPredicate(firstPriorityPredicate);
        PrioritySearchPredicate secondPredicate =
                new PrioritySearchPredicate(secondPriorityPredicate);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        PrioritySearchPredicate firstPredicateCopy =
                new PrioritySearchPredicate(firstPriorityPredicate);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different food -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_prioritySearch_returnsTrue() {
        // Search for priority
        PrioritySearchPredicate predicate =
                new PrioritySearchPredicate(Priority.Level.HIGH);
        assertTrue(predicate.test(new FoodBuilder().withPriority("high").build()));
    }

    @Test
    public void test_noFoodItemWithMatchingPriority_returnsFalse() {
        // Non-matching priority
        PrioritySearchPredicate predicate = predicate = new PrioritySearchPredicate(Priority.Level.LOW);
        assertFalse(predicate.test(new FoodBuilder().withPriority("high").build()));

        // Priority search query matches description and tag, but does not match priority
        predicate = new PrioritySearchPredicate(Priority.Level.LOW);
        assertFalse(predicate.test(new FoodBuilder().withDescription("LOW").withPriority("high")
                .withTags("LOW").build()));
    }
}

