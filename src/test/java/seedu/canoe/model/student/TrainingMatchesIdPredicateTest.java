package seedu.canoe.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.canoe.testutil.LocalDateTimeUtil.VALID_LOCAL_DATE_TIME;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.canoe.testutil.StudentBuilder;
import seedu.canoe.testutil.TrainingBuilder;

public class TrainingMatchesIdPredicateTest {

    @Test
    public void equals() {
        Id firstIdValue = new Id("001");
        Id secondIdValue = new Id("002");

        TrainingMatchesIdPredicate firstPredicate = new TrainingMatchesIdPredicate(firstIdValue);
        TrainingMatchesIdPredicate secondPredicate = new TrainingMatchesIdPredicate(secondIdValue);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        TrainingMatchesIdPredicate firstPredicateCopy = new TrainingMatchesIdPredicate(firstIdValue);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different idValue -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_trainingAddStudentContainsStudents_returnsTrue() {
        Student testStudent = new StudentBuilder().withPhone("123456").withId("001").build();
        Set<Student> studentSet = new HashSet<>();
        studentSet.add(testStudent);
        Training testTraining = new TrainingBuilder().withDateTime(VALID_LOCAL_DATE_TIME).build();
        testTraining.addStudent(testStudent);
        TrainingMatchesIdPredicate predicate = new TrainingMatchesIdPredicate(new Id("001"));
        assertTrue(predicate.test(testTraining));
    }

    @Test
    public void test_trainingContainsStudents_returnsTrue() {
        Student testStudent = new StudentBuilder().withPhone("123456").withId("001").build();
        Set<Student> studentSet = new HashSet<>();
        studentSet.add(testStudent);
        TrainingMatchesIdPredicate predicate = new TrainingMatchesIdPredicate(new Id("001"));
        assertTrue(predicate.test(new TrainingBuilder().withDateTime(VALID_LOCAL_DATE_TIME)
                .withStudents(studentSet).build()));
    }

    @Test
    public void test_trainingDoesNotContainStudents_returnsFalse() {
        Student testStudent = new StudentBuilder().withPhone("123456").withId("001").build();
        Set<Student> studentSet = new HashSet<>();
        studentSet.add(testStudent);

        //null argument
        TrainingMatchesIdPredicate predicate = new TrainingMatchesIdPredicate(null);
        assertFalse(predicate.test(new TrainingBuilder().withDateTime(VALID_LOCAL_DATE_TIME)
                .withStudents(studentSet).build()));

        //non-matching keywords
        TrainingMatchesIdPredicate nonMatchingPredicate = new TrainingMatchesIdPredicate(new Id("002"));
        assertFalse(nonMatchingPredicate.test(new TrainingBuilder().withDateTime(VALID_LOCAL_DATE_TIME)
                .withStudents(studentSet).build()));
    }

}
