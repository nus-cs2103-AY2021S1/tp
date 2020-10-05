package seedu.address.model.vendor;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AddressTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new seedu.address.model.vendor.Address(null));
    }

    @Test
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        String invalidAddress = "";
        assertThrows(IllegalArgumentException.class, () -> new seedu.address.model.vendor.Address(invalidAddress));
    }

    @Test
    public void isValidAddress() {
        // null address
        assertThrows(NullPointerException.class, () -> seedu.address.model.vendor.Address.isValidAddress(null));

        // invalid addresses
        assertFalse(seedu.address.model.vendor.Address.isValidAddress("")); // empty string
        assertFalse(seedu.address.model.vendor.Address.isValidAddress(" ")); // spaces only

        // valid addresses
        assertTrue(seedu.address.model.vendor.Address.isValidAddress("Blk 456, Den Road, #01-355"));
        assertTrue(seedu.address.model.vendor.Address.isValidAddress("-")); // one character
        assertTrue(Address.isValidAddress("Leng Inc; 1234 Market St; San Francisco CA 2349879; USA")); // long address
    }
}
