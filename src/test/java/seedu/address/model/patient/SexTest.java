package seedu.address.model.patient;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class SexTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Sex(null));
    }

    @Test
    public void constructor_invalidSex_throwsIllegalArgumentException() {
        String invalidSex = "";
        assertThrows(IllegalArgumentException.class, () -> new Sex(invalidSex));
    }

    @Test
    public void isValidSex() {
        // null sex
        assertThrows(NullPointerException.class, () -> Sex.isValidSex(null));

        // blank sex
        assertFalse(Sex.isValidSex("")); // empty string
        assertFalse(Sex.isValidSex(" ")); // spaces only

        // invalid parts
        assertFalse(Sex.isValidSex(" M")); // leading space
        assertFalse(Sex.isValidSex("M ")); // trailing space
        assertFalse(Sex.isValidSex("A")); // invalid alphabet
        assertFalse(Sex.isValidSex("1")); // digit
        assertFalse(Sex.isValidSex("!")); // special character
        assertFalse(Sex.isValidSex("MF")); // two alphabets

        // valid sex
        assertTrue(Sex.isValidSex("M")); // male
        assertTrue(Sex.isValidSex("F")); // female
        assertTrue(Sex.isValidSex("N/A")); // N/A is a valid placeholder
    }
}
