package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class SchoolTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new School(null));
    }

    @Test
    public void constructor_invalidSchool_throwsIllegalArgumentException() {
        String invalidSchool = "";
        assertThrows(IllegalArgumentException.class, () -> new School(invalidSchool));
    }

    @Test
    public void isValidSchool() {
        // null school
        assertThrows(NullPointerException.class, () -> School.isValidSchool(null));

        // invalid school names
        assertFalse(School.isValidSchool("")); // empty string
        assertFalse(School.isValidSchool(" ")); // spaces only

        // valid school name
        assertTrue(School.isValidSchool("a")); //1 small letter
        assertTrue(School.isValidSchool("A")); // 1 capital letter

        assertTrue(School.isValidSchool("NUS High School")); // strings with spacing
        assertTrue(School.isValidSchool("abc")); // no spacing
        assertTrue(School.isValidSchool("abcAbcqweretrytyuiuiopsadfghjkxzcvbnm")); //long word

    }
}
