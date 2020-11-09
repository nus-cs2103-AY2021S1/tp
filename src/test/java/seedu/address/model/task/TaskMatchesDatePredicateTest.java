package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.todolist.TaskBuilder;

public class TaskMatchesDatePredicateTest {

    @Test
    public void equals() {

        TaskMatchesDatePredicate firstPredicate = new TaskMatchesDatePredicate(new Date("2020-01-01"));
        TaskMatchesDatePredicate secondPredicate = new TaskMatchesDatePredicate(new Date("2020-05-05"));

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        TaskMatchesDatePredicate firstPredicateCopy = new TaskMatchesDatePredicate(new Date("2020-01-01"));
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(6));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different values -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_taskDateMatchesSearchDate_returnsTrue() {
        TaskMatchesDatePredicate predicate = new TaskMatchesDatePredicate(new Date("2020-01-01"));
        assertTrue(predicate.test(new TaskBuilder().withDate("2020-01-01").build()));
    }

    @Test
    public void test_taskDateDoesNotMatchSearchDate_returnsFalse() {

        TaskMatchesDatePredicate predicate = new TaskMatchesDatePredicate(new Date("2020-01-01"));

        // non-matching date
        assertFalse(predicate.test(new TaskBuilder().withDate("2020-05-05").build()));
    }
}
