package seedu.resireg.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.resireg.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class StudentIdTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new StudentId(null));
    }

    @Test
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        String invalidStudentId = "";
        assertThrows(IllegalArgumentException.class, () -> new StudentId((invalidStudentId)));
    }

    @Test
    public void isValidStudentId() {
        // null student ID
        assertThrows(NullPointerException.class, () -> StudentId.isValidStudentId((null)));

        // invalid student IDs
        assertFalse(StudentId.isValidStudentId((""))); // empty string
        assertFalse(StudentId.isValidStudentId((" "))); // spaces only
        assertFalse(StudentId.isValidStudentId(("e"))); // missing "0" and 6 digits
        assertFalse(StudentId.isValidStudentId(("E0"))); // missing 6 digits
        assertFalse(StudentId.isValidStudentId(("E012345"))); // only 5 digits


        // valid student IDs
        assertTrue(StudentId.isValidStudentId("E0407889"));
        assertTrue(StudentId.isValidStudentId("E0123456"));
        assertTrue(StudentId.isValidStudentId("E0123457"));
    }
}
