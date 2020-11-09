package seedu.address.model.friend;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class MobileTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Mobile(null));
    }

    @Test
    public void constructor_invalidPhone_throwsIllegalArgumentException() {
        String invalidMobile = "";
        assertThrows(IllegalArgumentException.class, () -> new Mobile(invalidMobile));
    }

    @Test
    void isValidMobile() {
        assertThrows(NullPointerException.class, () -> Mobile.isValidMobile(null));

        // invalid phone numbers
        assertFalse(Mobile.isValidMobile("")); // empty string
        assertFalse(Mobile.isValidMobile(" ")); // spaces only
        assertFalse(Mobile.isValidMobile("9138")); // less than 8 numbers
        assertFalse(Mobile.isValidMobile("phone")); // non-numeric
        assertFalse(Mobile.isValidMobile("9011p041")); // alphabets within digits
        assertFalse(Mobile.isValidMobile("9312 1534")); // spaces within digits
        assertFalse(Mobile.isValidMobile("124293842033123")); // long mobile numbers

        // valid mobile numbers
        assertTrue(Mobile.isValidMobile("86249932")); // exactly 8 numbers
        assertTrue(Mobile.isValidMobile("93121534"));

    }
}
