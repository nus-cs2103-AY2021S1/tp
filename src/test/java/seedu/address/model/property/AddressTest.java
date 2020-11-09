package seedu.address.model.property;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.property.PropertyCommandTestUtil;

public class AddressTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Address(null));
    }

    @Test
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        String blankAddress = "";
        assertThrows(IllegalArgumentException.class, () -> new Address(blankAddress));
        String invalidAddress = PropertyCommandTestUtil.getStringWithCharacters(Address.MAX_LENGTH + 1);
        assertThrows(IllegalArgumentException.class, () -> new Address(invalidAddress));
    }

    @Test
    public void isValidAddress() {
        // null address
        assertThrows(NullPointerException.class, () -> Address.isValidAddress(null));

        // invalid addresses
        assertFalse(Address.isValidAddress("")); // empty string
        assertFalse(Address.isValidAddress(" ")); // spaces only

        // valid addresses
        assertTrue(Address.isValidAddress("Blk 456, Den Road, #01-355"));
        assertTrue(Address.isValidAddress("-")); // one character
        assertTrue(Address.isValidAddress("Leng Inc; 1234 Market St; San Francisco CA 2349879; USA")); // long address
        assertTrue(Address.isValidAddress(PropertyCommandTestUtil.getStringWithCharacters(Address.MAX_LENGTH)));
    }

    @Test
    public void equals() {
        Address address = new Address("Block 123");

        // same object
        assertTrue(address.equals(address));

        // different type
        assertFalse(address.equals(new PropertyName("Block 123")));

        // same address
        assertTrue(address.equals(new Address("Block 123")));

        // different address
        assertFalse(address.equals(new Address("Block 456")));
    }
}
