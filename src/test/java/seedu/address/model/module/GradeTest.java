package seedu.address.model.module;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class GradeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Grade(null));
    }

    @Test
    public void constructor_invalidGrade_throwsIllegalArgumentException() {
        String invalidGrade = "";
        assertThrows(IllegalArgumentException.class, () -> new Grade(invalidGrade));
    }

    @Test
    public void isValidGrade() {
        // null address
        assertThrows(NullPointerException.class, () -> Grade.isValidGrade(null));

        // invalid addresses
        assertFalse(Grade.isValidGrade("")); // empty string
        assertFalse(Grade.isValidGrade(" ")); // spaces only

        // valid addresses
        assertTrue(Grade.isValidGrade("A-"));
        assertTrue(Grade.isValidGrade("B")); // one character
        // Not relevant for grade
        // assertTrue(Grade.isValidGrade("Leng Inc; 1234 Market St; San Francisco CA 2349879; USA")); // long address
    }
}
