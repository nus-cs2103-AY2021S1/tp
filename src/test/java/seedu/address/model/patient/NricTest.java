package seedu.address.model.patient;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class NricTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Nric(null));
    }

    @Test
    public void constructor_invalidNric_throwsIllegalArgumentException() {
        String invalidNric = "";
        assertThrows(IllegalArgumentException.class, () -> new Nric(invalidNric));
    }

    @Test
    void isValidNric() {
        // null name
        assertThrows(NullPointerException.class, () -> Nric.isValidNric(null));

        // invalid nric
        assertFalse(Nric.isValidNric("")); // empty string
        assertFalse(Nric.isValidNric(" ")); // spaces only
        assertFalse(Nric.isValidNric("S12345A")); // contains less than 8 digits
        assertFalse(Nric.isValidNric("S12345678")); // missing ending alphabet
        assertFalse(Nric.isValidNric("%12345678*")); // contains non-alphanumeric characters
        assertFalse(Nric.isValidNric("SA12345678AC")); // contains more than the 2 required alphabets

        // valid nric
        assertTrue(Nric.isValidNric("S12345678A")); // one alphabet, followed by 8 digits and another alphabet
    }
}
