package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.EventBuilder;
import seedu.address.testutil.TodoBuilder;

public class DueBeforePredicateTest {
    @Test
    public void test_tasksDueBefore_returnsTrue() {
        DueBeforePredicate predicate = new DueBeforePredicate("20-12-2020 2359");

        //To-do (homework)
        assertTrue(predicate.test(new TodoBuilder().build()));

        //Event (meeting)
        assertTrue(predicate.test(new EventBuilder().build()));
    }

    @Test
    public void test_noTasksDueBefore_returnsFalse() {
        DueBeforePredicate predicate = new DueBeforePredicate("01-01-2020 2359");

        //To-do
        assertFalse(predicate.test(new TodoBuilder().build()));

        //Event
        assertFalse(predicate.test(new EventBuilder().build()));
    }

    @Test
    public void equals() {
        String firstDateTime = "10-10-2010 1010";
        String secondDateTime = "20-12-2020 2359";

        DueBeforePredicate firstPredicate = new DueBeforePredicate(firstDateTime);
        DueBeforePredicate secondPredicate = new DueBeforePredicate(secondDateTime);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        DueBeforePredicate firstPredicateCopy = new DueBeforePredicate(firstDateTime);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }
}
