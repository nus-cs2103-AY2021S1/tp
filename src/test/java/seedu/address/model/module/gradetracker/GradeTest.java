package seedu.address.model.module.gradetracker;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.module.grade.Grade;

public class GradeTest {

    @Test
    public void isValidGrade() {

        // invalid assignment percentage
        assertFalse(Grade.isValidGrade(-1)); // out of range lower bound
        assertFalse(Grade.isValidGrade(100.1)); // out of range upper bound

        // valid assignment percentage
        assertTrue(Grade.isValidGrade(0)); // test lower boundary value
        assertTrue(Grade.isValidGrade(1.5)); // test within range
        assertTrue(Grade.isValidGrade(100)); // test upper boundary value
    }

}
