package seedu.address.model.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TestUtil.basicEqualsTests;

import org.junit.jupiter.api.Test;

public class AddressTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Address(null));
    }

    @Test
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        String invalidAddress = "";
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
    }

    @Test
    public void equals() {
        Address address = new Address("Blk 456, Den Road, #01-355");
        // basic equals tests
        basicEqualsTests(address);

        // same values -> returns true
        assertTrue(address.equals(address));
        assertTrue(new Address("-").equals(new Address("-")));;

        // different values -> returns false
        Address differentAddress = new Address("Blk 456, Den Road, #01-354");
        assertFalse(address.equals(differentAddress));
        assertFalse(new Address("-").equals(new Address("+")));
    }

    @Test
    public void hashCode_test() {
        assertEquals(new Address("Blk 456, Den Road, #01-355").hashCode(),
                new Address("Blk 456, Den Road, #01-355").hashCode());
        assertEquals(new Address("-").hashCode(), new Address("-").hashCode());

        assertNotEquals(new Address("Blk 456, Den Road, #01-355").hashCode(),
                new Address("Blk 456, Den Road, #01-354").hashCode());
        assertNotEquals(new Address("-").hashCode(), new Address("+").hashCode());
    }
}
