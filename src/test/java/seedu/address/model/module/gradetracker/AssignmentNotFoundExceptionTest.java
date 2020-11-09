package seedu.address.model.module.gradetracker;

import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.gradetracker.TypicalAssignments.LAB_1;
import static seedu.address.testutil.gradetracker.TypicalAssignments.QUIZ_2;

import org.junit.jupiter.api.Test;

import seedu.address.model.module.grade.GradeTracker;
import seedu.address.model.module.grade.exceptions.AssignmentNotFoundException;

public class AssignmentNotFoundExceptionTest {

    @Test
    void execute_setAssignment_throwsAssignmentNotFoundException() {
        GradeTracker gradeTracker = new GradeTracker();
        gradeTracker.addAssignment(QUIZ_2);
        assertThrows(AssignmentNotFoundException.class, () -> gradeTracker.setAssignment(LAB_1, QUIZ_2));
    }
}
