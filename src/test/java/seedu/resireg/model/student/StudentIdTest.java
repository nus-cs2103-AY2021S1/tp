package seedu.resireg.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.resireg.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.resireg.model.student.faculty.Faculty;

public class StudentIdTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new StudentId(null));
    }

    @Test
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        String invalidStudentId = "";
        assertThrows(IllegalArgumentException.class, () -> new Faculty(invalidStudentId));
    }

    @Test
    public void isValidFaculty() {
        // null student ID
        assertThrows(NullPointerException.class, () -> Faculty.isValidFaculty(null));

        // invalid student IDs
        assertFalse(Faculty.isValidFaculty("")); // empty string
        assertFalse(Faculty.isValidFaculty(" ")); // spaces only
        assertFalse(Faculty.isValidFaculty("e")); // missing "0" and 6 digits
        assertFalse(Faculty.isValidFaculty("E0")); // missing 6 digits
        assertFalse(Faculty.isValidFaculty("E012345")); // only 5 digits


        // valid student IDs
        assertTrue(StudentId.isValidStudentId("E0407889"));
        assertTrue(StudentId.isValidStudentId("E0123456"));
        assertTrue(StudentId.isValidStudentId("E0123457"));
    }
}
