package seedu.address.model.student.admin;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalStudents.ALICE;
import static seedu.address.testutil.TypicalStudents.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.model.student.Student;
import seedu.address.testutil.StudentBuilder;

public class ClassTimeClashPredicateTest {

    private final ClassTimeClashPredicate predicate = new ClassTimeClashPredicate(ALICE);

    @Test
    public void constructor_null_throwsException() {
        assertThrows(NullPointerException.class, () -> new ClassTimeClashPredicate(null));
    }

    @Test
    public void test() {
        // same class time -> true
        assertTrue(predicate.test(ALICE));

        // end after ALICE start -> true
        Student test = new StudentBuilder(ALICE).withClassTime("5 1400-1501").build();
        assertTrue(predicate.test(test));

        // start before ALICE end -> true
        test = new StudentBuilder(ALICE).withClassTime("5 1659-1800").build();
        assertTrue(predicate.test(test));

        // complete overlap -> true
        test = new StudentBuilder(ALICE).withClassTime("5 1459-1701").build();
        assertTrue(predicate.test(test));

        // different day -> false
        test = new StudentBuilder(ALICE).withClassTime("3 1500-1700").build();
        assertFalse(predicate.test(test));

        // end just as ALICE start
        test = new StudentBuilder(ALICE).withClassTime("5 1400-1500").build();
        assertFalse(predicate.test(test));

        // start just as ALICE end
        test = new StudentBuilder(ALICE).withClassTime("5 1700-1800").build();
        assertFalse(predicate.test(test));
    }

    @Test
    public void equals() {
        // same obj -> true
        assertTrue(predicate.equals(predicate));

        // same state -> true
        Student test = ALICE; // same student parsed in constructor
        assertTrue(predicate.equals(new ClassTimeClashPredicate(test)));
        test = new StudentBuilder(BOB).withClassTime("5 1500-1700").build(); // different student, same class time
        assertTrue(predicate.equals(new ClassTimeClashPredicate(test)));

        // different class -> false
        assertFalse(predicate.equals(test));

        // different state -> false
        test = new StudentBuilder(ALICE).withClassTime("5 1500-1600").build();
        assertFalse(predicate.equals(new ClassTimeClashPredicate(test)));
    }
}
