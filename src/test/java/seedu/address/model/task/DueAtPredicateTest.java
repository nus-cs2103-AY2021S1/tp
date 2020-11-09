package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.EventBuilder;
import seedu.address.testutil.TodoBuilder;

public class DueAtPredicateTest {
    @Test
    public void test_tasksDueAt_returnsTrue() {
        DueAtPredicate predicate = new DueAtPredicate("14-12-2020 2359");

        //To-do (homework)
        assertTrue(predicate.test(new TodoBuilder().build()));

        //Event (meeting)
        predicate = new DueAtPredicate("12-12-2020 1130");
        assertTrue(predicate.test(new EventBuilder().build()));
    }

    @Test
    public void test_noTasksDueAt_returnsFalse() {
        DueAtPredicate predicate = new DueAtPredicate("15-12-2020 2359");

        //To-do
        assertFalse(predicate.test(new TodoBuilder().build()));

        //Event
        assertFalse(predicate.test(new EventBuilder().build()));
    }

    @Test
    public void equals() {
        String firstDateTime = "10-10-2010 1010";
        String secondDateTime = "20-12-2020 2359";

        DueAtPredicate firstPredicate = new DueAtPredicate(firstDateTime);
        DueAtPredicate secondPredicate = new DueAtPredicate(secondDateTime);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        DueAtPredicate firstPredicateCopy = new DueAtPredicate(firstDateTime);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }
}
