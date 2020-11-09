package seedu.address.model.module.gradetracker;

import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAssignments.QUIZ_2;

import org.junit.jupiter.api.Test;

import seedu.address.model.module.grade.GradeTracker;
import seedu.address.model.module.grade.exceptions.DuplicateAssignmentException;

public class DuplicateAssignmentExceptionTest {

    @Test
    void execute_addAssignment_throwsDuplicateAssignmentException() {
        GradeTracker gradeTracker = new GradeTracker();
        gradeTracker.addAssignment(QUIZ_2);
        assertThrows(DuplicateAssignmentException.class, () -> gradeTracker.addAssignment(QUIZ_2));
    }

}
