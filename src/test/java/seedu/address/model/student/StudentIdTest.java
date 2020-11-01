package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class StudentIdTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new StudentId(null));
    }

    @Test
    public void constructor_invalidStudentId_throwsIllegalArgumentException() {
        String invalidStudentId = "";
        assertThrows(IllegalArgumentException.class, () -> new StudentId(invalidStudentId));
    }

    @Test
    public void isValidStudentId() {
        // null student id
        assertThrows(NullPointerException.class, () -> StudentId.isValidStudentId(null));

        // invalid student id
        assertFalse(StudentId.isValidStudentId("")); // empty string
        assertFalse(StudentId.isValidStudentId("  ")); // spaces only
        assertFalse(StudentId.isValidStudentId("myId")); // alphabets only
        assertFalse(StudentId.isValidStudentId("1234AX567")); // alphabets within digits
        assertFalse(StudentId.isValidStudentId("A123456X7"));
        assertFalse(StudentId.isValidStudentId("A123456X")); // less than 7 digits
        assertFalse(StudentId.isValidStudentId("a1234567x")); // non-capital alphabets
        assertFalse(StudentId.isValidStudentId("1234567X")); // missing first alphabet

        // valid student id
        assertTrue(StudentId.isValidStudentId("A1234567X"));
    }
}
