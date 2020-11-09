package seedu.address.model.information;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AddressTest {

    public static final String HUNDRED_CHARACTER_STRING = "@/=pF9sp:dN&c^wnPppoLNc(oW{61bBv#][^\\F7M#DV7("
            + ".LM~-D||Zj3?e$H[d.FT>Ol'nq)BP$h2^g$z!U7'K:K$M59mT3*O<nS";

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
        assertFalse(Address.isValidAddress("1")); // one character
        assertFalse(Address.isValidAddress("12")); // two character
        assertFalse(Address.isValidAddress("      ")); // whitespace only
        assertFalse(Address.isValidAddress(HUNDRED_CHARACTER_STRING + "a")); // 101 characters

        // valid addresses
        assertTrue(Address.isValidAddress("Blk 456, Den Road, #01-355"));
        assertTrue(Address.isValidAddress("-*13")); // three character
        assertTrue(Address.isValidAddress("Leng Inc; 1234 Market St; San Francisco CA 2349879; USA")); // long address
        assertTrue(Address.isValidAddress(HUNDRED_CHARACTER_STRING)); // 100 characters
    }
}
