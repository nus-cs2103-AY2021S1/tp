package seedu.address.model.project;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class LeaderTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Leader(null));
    }

    @Test
    public void constructor_invalidPhone_throwsIllegalArgumentException() {
        String invalidPhone = "";
        assertThrows(IllegalArgumentException.class, () -> new Leader(invalidPhone));
    }

    @Test
    public void isValidPhone() {
        // null phone number
        assertThrows(NullPointerException.class, () -> Leader.isValidPhone(null));

        // invalid phone numbers
        assertFalse(Leader.isValidPhone("")); // empty string
        assertFalse(Leader.isValidPhone(" ")); // spaces only
        assertFalse(Leader.isValidPhone("91")); // less than 3 numbers
        assertFalse(Leader.isValidPhone("phone")); // non-numeric
        assertFalse(Leader.isValidPhone("9011p041")); // alphabets within digits
        assertFalse(Leader.isValidPhone("9312 1534")); // spaces within digits

        // valid phone numbers
        assertTrue(Leader.isValidPhone("911")); // exactly 3 numbers
        assertTrue(Leader.isValidPhone("93121534"));
        assertTrue(Leader.isValidPhone("124293842033123")); // long phone numbers
    }
}
