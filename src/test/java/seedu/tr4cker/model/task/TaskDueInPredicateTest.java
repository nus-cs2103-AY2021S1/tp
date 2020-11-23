package seedu.tr4cker.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.YearMonth;

import org.junit.jupiter.api.Test;

import seedu.tr4cker.model.util.GotoDateUtil;
import seedu.tr4cker.testutil.TaskBuilder;

class TaskDueInPredicateTest {

    @Test
    public void equals() {
        TaskDueInPredicate firstPredicate = new TaskDueInPredicate();
        TaskDueInPredicate secondPredicate = new TaskDueInPredicate();
        TaskDueInPredicate thirdPredicate = new TaskDueInPredicate(LocalDate.of(2020, 10, 10));
        TaskDueInPredicate fourthPredicate = new TaskDueInPredicate(LocalDate.of(2020, 10, 10));
        TaskDueInPredicate fifthPredicate = new TaskDueInPredicate(YearMonth.of(2020, 10));
        TaskDueInPredicate sixthPredicate = new TaskDueInPredicate(YearMonth.of(2020, 10));

        // same object -> returns true
        assertEquals(firstPredicate, firstPredicate);
        assertEquals(thirdPredicate, thirdPredicate);
        assertEquals(fifthPredicate, fifthPredicate);

        // same values -> returns true
        assertEquals(firstPredicate, secondPredicate);
        assertEquals(thirdPredicate, fourthPredicate);
        assertEquals(fifthPredicate, sixthPredicate);

        // different types -> returns false
        assertNotEquals(firstPredicate, 1);

        // null -> returns false
        assertNotEquals(firstPredicate, null);

        // different predicates -> returns false
        assertNotEquals(firstPredicate, fifthPredicate);
    }

    @Test
    public void test_taskDueIn_returnsTrue() {
        LocalDate today = LocalDate.now();
        String test = GotoDateUtil.parseGotoDay(today) + " 2359";
        TaskDueInPredicate todayPredicate = new TaskDueInPredicate();
        assertTrue(todayPredicate.test(new TaskBuilder().withDeadline(test).build()));

        LocalDate localDate1 = LocalDate.of(2020, 12, 9);
        String test1 = GotoDateUtil.parseGotoDay(localDate1) + " 2359";
        TaskDueInPredicate predicate1 = new TaskDueInPredicate(localDate1);
        assertTrue(predicate1.test(new TaskBuilder().withDeadline(test1).build()));
    }

    @Test
    public void test_taskDoesNotDueIn_returnsFalse() {
        LocalDate today = LocalDate.now();
        String test = GotoDateUtil.parseGotoDay(today) + " 2359";
        TaskDueInPredicate todayPredicate = new TaskDueInPredicate();

        LocalDate localDate1 = LocalDate.of(2020, 12, 9);
        String test1 = GotoDateUtil.parseGotoDay(localDate1) + " 2359";
        TaskDueInPredicate predicate1 = new TaskDueInPredicate(localDate1);

        assertFalse(predicate1.test(new TaskBuilder().withDeadline(test).build()));
        assertFalse(todayPredicate.test(new TaskBuilder().withDeadline(test1).build()));
    }

}
