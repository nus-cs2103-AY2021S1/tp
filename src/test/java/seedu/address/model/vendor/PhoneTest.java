package seedu.address.model.vendor;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TypicalVendors;

public class PhoneTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new seedu.address.model.vendor.Phone(null));
    }

    @Test
    public void constructor_invalidPhone_throwsIllegalArgumentException() {
        String invalidPhone = "";
        assertThrows(IllegalArgumentException.class, () -> new seedu.address.model.vendor.Phone(invalidPhone));
    }

    @Test
    public void isValidPhone() {
        // null phone number
        assertThrows(NullPointerException.class, () -> seedu.address.model.vendor.Phone.isValidPhone(null));

        // invalid phone numbers
        assertFalse(seedu.address.model.vendor.Phone.isValidPhone("")); // empty string
        assertFalse(seedu.address.model.vendor.Phone.isValidPhone(" ")); // spaces only
        assertFalse(seedu.address.model.vendor.Phone.isValidPhone("91")); // less than 3 numbers
        assertFalse(seedu.address.model.vendor.Phone.isValidPhone("911")); // exactly 3 numbers
        assertFalse(Phone.isValidPhone("124293842033123")); // long phone numbers
        assertFalse(seedu.address.model.vendor.Phone.isValidPhone("phone")); // non-numeric
        assertFalse(seedu.address.model.vendor.Phone.isValidPhone("9011p041")); // alphabets within digits
        assertFalse(seedu.address.model.vendor.Phone.isValidPhone("9312 1534")); // spaces within digits

        // valid phone numbers
        assertTrue(seedu.address.model.vendor.Phone.isValidPhone("93121534"));
        assertTrue(seedu.address.model.vendor.Phone.isValidPhone(TypicalVendors.ALICE.getPhone().toString()));
    }

}
