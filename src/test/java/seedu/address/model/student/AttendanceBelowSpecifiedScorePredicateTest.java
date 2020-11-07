package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.StudentBuilder;

public class AttendanceBelowSpecifiedScorePredicateTest {

    @Test
    public void equals() {
        int firstPredicateScore = 1;
        int secondPredicateScore = 2;

        AttendanceBelowSpecifiedScorePredicate firstPredicate =
                new AttendanceBelowSpecifiedScorePredicate(firstPredicateScore);
        AttendanceBelowSpecifiedScorePredicate secondPredicate =
                new AttendanceBelowSpecifiedScorePredicate(secondPredicateScore);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        AttendanceBelowSpecifiedScorePredicate firstPredicateCopy =
                new AttendanceBelowSpecifiedScorePredicate(firstPredicateScore);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_studentAttendanceBelow_returnsTrue() {
        // One keyword
        AttendanceBelowSpecifiedScorePredicate predicate = new AttendanceBelowSpecifiedScorePredicate(5);
        assertTrue(predicate.test(new StudentBuilder().withName("Alice Bob")
                .withAttendance("1", "2", "3", "4").build()));
        predicate = new AttendanceBelowSpecifiedScorePredicate(5);
        assertTrue(predicate.test(new StudentBuilder().withName("Alice Bob").withAttendance("1", "2", "3").build()));
    }

    @Test
    public void test_studentAttendanceNotBelow_returnsFalse() {
        // Zero
        AttendanceBelowSpecifiedScorePredicate predicate = new AttendanceBelowSpecifiedScorePredicate(1);
        assertFalse(predicate.test(new StudentBuilder().withName("Alice Bob")
                .withAttendance("1", "2", "3", "4", "5").build()));

        // Not below
        predicate = new AttendanceBelowSpecifiedScorePredicate(5);
        assertFalse(predicate.test(new StudentBuilder().withName("Alice Bob")
                .withAttendance("1", "2", "3", "4", "5").build()));
    }
}
