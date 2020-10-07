package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class YearTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Year(null));
    }

    @Test
    public void constructor_invalidYear_throwsIllegalArgumentException() {
        String invalidYear = "";
        assertThrows(IllegalArgumentException.class, () -> new Year(invalidYear));
    }

    @Test
    public void isValidYear() {
        // null year
        assertThrows(NullPointerException.class, () -> Year.isValidYear(null));

        // invalid year
        assertFalse(Year.isValidYear("")); // empty string
        assertFalse(Year.isValidYear(" ")); // space only
        assertFalse(Year.isValidYear(" Primary 2")); //spacing in front
        assertFalse(Year.isValidYear("!@#$")); // characters

        // valid year
        assertTrue(Year.isValidYear("0"));
        assertTrue(Year.isValidYear("11")); // double digits
        assertTrue(Year.isValidYear("92312432423")); // long number
        assertTrue(Year.isValidYear("1 2 3 ")); //numbers with spacing
        assertTrue(Year.isValidYear("abc")); // only letters
        assertTrue(Year.isValidYear("a b c ")); //letters with spacing
        assertTrue(Year.isValidYear("Primary 1")); // alphanumeric
        assertTrue(Year.isValidYear("Primary 1     ")); //spacing behind acceptable
    }
}
