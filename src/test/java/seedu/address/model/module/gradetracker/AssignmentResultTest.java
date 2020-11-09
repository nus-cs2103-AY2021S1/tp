package seedu.address.model.module.gradetracker;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.module.grade.AssignmentResult;

public class AssignmentResultTest {

    @Test
    public void isValidAssignmentResult() {

        // invalid assignment percentage
        assertFalse(AssignmentResult.isValidAssignmentResult(-1)); // out of range lower bound
        assertFalse(AssignmentResult.isValidAssignmentResult(100.1)); // out of range upper bound

        // valid assignment percentage
        assertTrue(AssignmentResult.isValidAssignmentResult(0)); // test lower boundary value
        assertTrue(AssignmentResult.isValidAssignmentResult(1.5)); // test within range
        assertTrue(AssignmentResult.isValidAssignmentResult(100)); // test upper boundary value
    }
}
