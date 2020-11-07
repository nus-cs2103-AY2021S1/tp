package seedu.address.model.module.gradetracker;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.module.grade.AssignmentPercentage;

public class AssignmentPercentageTest {

    @Test
    public void isValidAssignmentPercentage() {

        // invalid assignment percentage
        assertFalse(AssignmentPercentage.isValidAssignmentPercentage(-1)); // out of range lower bound
        assertFalse(AssignmentPercentage.isValidAssignmentPercentage(100.1)); // out of range upper bound

        // valid assignment percentage
        assertTrue(AssignmentPercentage.isValidAssignmentPercentage(0)); // test lower boundary value
        assertTrue(AssignmentPercentage.isValidAssignmentPercentage(1.5)); // test within range
        assertTrue(AssignmentPercentage.isValidAssignmentPercentage(100)); // test upper boundary value
    }
}
