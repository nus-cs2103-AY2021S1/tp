package seedu.medibook.model.doctor;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.medibook.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class McrTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Mcr(null));
    }

    @Test
    public void constructor_invalidIc_throwsIllegalArgumentException() {
        String invalidMcr = "M2837098P";
        assertThrows(IllegalArgumentException.class, () -> new Mcr(invalidMcr));
    }

    @Test
    public void isValidMcr() {
        // invalid mcr
        assertFalse(Mcr.isValidMcr("")); // empty string
        assertFalse(Mcr.isValidMcr("M9P")); // less than 7 characters
        assertFalse(Mcr.isValidMcr("A08273X")); // first character is not 'm' or 'M'
        assertFalse(Mcr.isValidMcr("M8F927P")); // letter where digit should be
        assertFalse(Mcr.isValidMcr("M928631")); // last character is not a letter
        assertFalse(Mcr.isValidMcr("M_1111a")); // second character is not a letter or digit
        assertFalse(Mcr.isValidMcr("M$1111a")); // second character is not a letter or digit
        assertFalse(Mcr.isValidMcr("M#1111a")); // second character is not a letter or digit
        assertFalse(Mcr.isValidMcr("M 1111a")); // second character is not a letter or digit
        assertFalse(Mcr.isValidMcr("M-1111a")); // second character is not a letter or digit

        // valid mcr
        assertTrue(Mcr.isValidMcr("m28370p"));
        assertTrue(Mcr.isValidMcr("M99999x"));
        assertTrue(Mcr.isValidMcr("mP2838x"));
        assertTrue(Mcr.isValidMcr("MP2838x"));
        assertTrue(Mcr.isValidMcr("MP2838X"));
    }
}
