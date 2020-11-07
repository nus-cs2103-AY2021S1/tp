package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.todolist.TaskBuilder;

public class TaskMatchesStatusPredicateTest {

    @Test
    public void equals() {
        TaskMatchesStatusPredicate firstPredicate = new TaskMatchesStatusPredicate(Status.COMPLETED);
        TaskMatchesStatusPredicate secondPredicate = new TaskMatchesStatusPredicate(Status.NOT_COMPLETED);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        TaskMatchesStatusPredicate firstPredicateCopy = new TaskMatchesStatusPredicate(Status.COMPLETED);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(6));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different values -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_taskStatusMatchesSearchStatus_returnsTrue() {
        TaskMatchesStatusPredicate predicate = new TaskMatchesStatusPredicate(Status.COMPLETED);
        assertTrue(predicate.test(new TaskBuilder().withStatus("COMPLETED").build()));
    }

    @Test
    public void test_taskStatusDoesNotMatchSearchStatus_returnsFalse() {

        TaskMatchesStatusPredicate predicate = new TaskMatchesStatusPredicate(Status.NOT_COMPLETED);

        // non-matching status
        assertFalse(predicate.test(new TaskBuilder().withStatus("COMPLETED").build()));
    }
}
