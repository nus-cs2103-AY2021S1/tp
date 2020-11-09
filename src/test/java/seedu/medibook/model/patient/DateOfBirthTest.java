package seedu.medibook.model.patient;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.medibook.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class DateOfBirthTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DateOfBirth(null));
    }

    @Test
    public void constructor_invalidDateOfBirth_throwsIllegalArgumentException() {
        String invalidDateOfBirth = "13/12/1989";
        assertThrows(IllegalArgumentException.class, () -> new DateOfBirth(invalidDateOfBirth));
    }

    @Test
    public void isValidDateOfBirth() {
        // invalid date of birth
        assertFalse(DateOfBirth.isValidDateOfBirth(null)); // null input
        assertFalse(DateOfBirth.isValidDateOfBirth("")); // empty string
        assertFalse(DateOfBirth.isValidDateOfBirth("21/03/89")); // wrong format with /
        assertFalse(DateOfBirth.isValidDateOfBirth("23-04-00")); // wrong year format
        assertFalse(DateOfBirth.isValidDateOfBirth("2 Feb 1996")); // wrong format with letters
        assertFalse(DateOfBirth.isValidDateOfBirth("14-09-3000")); // future date

        // valid date of birth
        assertTrue(DateOfBirth.isValidDateOfBirth("23-03-2000"));
        assertTrue(DateOfBirth.isValidDateOfBirth("01-07-1995"));
        assertTrue(DateOfBirth.isValidDateOfBirth("31-12-1995"));
    }
}
