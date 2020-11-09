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
        assertFalse(Nric.isValidNric("S12345A")); // contains less than 7 digits
        assertFalse(Nric.isValidNric("S1234567")); // missing ending alphabet
        assertFalse(Nric.isValidNric("%1234567*")); // contains non-alphanumeric characters
        assertFalse(Nric.isValidNric("SA1234567AC")); // contains more than the 2 required alphabets

        // valid nric
        assertTrue(Nric.isValidNric("S1234567A")); // one alphabet, followed by 7 digits and another alphabet
    }
}
