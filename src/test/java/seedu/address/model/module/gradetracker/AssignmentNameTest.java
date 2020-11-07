package seedu.address.model.module.gradetracker;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.module.grade.AssignmentName;

public class AssignmentNameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AssignmentName(null));
    }

    @Test
    public void constructor_invalidAssignmentName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new AssignmentName(invalidName));
    }

    @Test
    public void isValidAssignmentName() {

        // null name
        assertThrows(NullPointerException.class, () -> AssignmentName.isValidAssignmentName(null));

        // invalid name
        assertFalse(AssignmentName.isValidAssignmentName("")); // empty string
        assertFalse(AssignmentName.isValidAssignmentName(" ")); // spaces only
        assertFalse(AssignmentName.isValidAssignmentName("^")); // contains non-alphanumeric characters only
        assertFalse(AssignmentName.isValidAssignmentName("Test(2)")); // contains non-alphanumeric characters only

        // valid name
        assertTrue(AssignmentName.isValidAssignmentName("a")); // single alphabet character
        assertTrue(AssignmentName.isValidAssignmentName("quiz")); // alphabets only
        assertTrue(AssignmentName.isValidAssignmentName("1234567")); // numbers only
        assertTrue(AssignmentName.isValidAssignmentName("quiz 2")); // alphanumeric characters
        assertTrue(AssignmentName.isValidAssignmentName("Quiz")); // contains capital letters
        assertTrue(AssignmentName.isValidAssignmentName("Oral Presentation Quiz 2 Week 12")); // long names
    }
}
