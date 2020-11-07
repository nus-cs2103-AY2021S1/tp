package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.todolist.TaskBuilder;

public class TaskMatchesPriorityPredicateTest {


    @Test
    public void equals() {
        TaskMatchesPriorityPredicate firstPredicate = new TaskMatchesPriorityPredicate(Priority.HIGH);
        TaskMatchesPriorityPredicate secondPredicate = new TaskMatchesPriorityPredicate(Priority.LOW);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        TaskMatchesPriorityPredicate firstPredicateCopy = new TaskMatchesPriorityPredicate(Priority.HIGH);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(6));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different values -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_taskPriorityMatchesSearchPriority_returnsTrue() {
        TaskMatchesPriorityPredicate predicate = new TaskMatchesPriorityPredicate(Priority.HIGH);
        assertTrue(predicate.test(new TaskBuilder().withPriority("HIGH").build()));
    }

    @Test
    public void test_taskPriorityDoesNotMatchSearchPriority_returnsFalse() {

        TaskMatchesPriorityPredicate predicate = new TaskMatchesPriorityPredicate(Priority.HIGH);

        // non-matching date
        assertFalse(predicate.test(new TaskBuilder().withPriority("LOW").build()));
    }
}
