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
    public void isValidYear() {
        // null year
        assertThrows(NullPointerException.class, () -> Year.isValidYear(null));

        // invalid year
        assertFalse(Year.isValidYear("")); // empty string
        assertFalse(Year.isValidYear(" ")); // space only
        assertFalse(Year.isValidYear(" Primary 2")); //spacing in front
        assertFalse(Year.isValidYear("!@#$")); // characters
        assertFalse(Year.isValidYear("NS 1")); // not a valid school type
        assertFalse(Year.isValidYear("JC 3")); // not a valid level
        assertFalse(Year.isValidYear("j")); // not a valid level
        assertFalse(Year.isValidYear("0")); // just digits
        assertFalse(Year.isValidYear("11")); // just digits
        assertFalse(Year.isValidYear("92312432423")); // long number
        assertFalse(Year.isValidYear("1 2 3 ")); //numbers with spacing
        assertFalse(Year.isValidYear("abc")); // only letters
        assertFalse(Year.isValidYear("a b c ")); //letters with spacing

        // valid year
        assertTrue(Year.isValidYear("Primary 1")); // alphanumeric
        assertTrue(Year.isValidYear("JC 2"));
        assertTrue(Year.isValidYear("S4")); // no spacing and short form acceptable
        assertTrue(Year.isValidYear("sec4")); // no spacing acceptable
        assertTrue(Year.isValidYear("S 4")); // short form acceptable
        assertTrue(Year.isValidYear("p          6")); // spacing in year acceptable
        assertTrue(Year.isValidYear("Secondary 4"));
        assertTrue(Year.isValidYear("Primary 1     ")); //spacing behind acceptable
    }
}
