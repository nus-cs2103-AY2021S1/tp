package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

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

        // invalid address
        assertFalse(Address.isValidAddress("")); // empty string
        assertFalse(Address.isValidAddress(" ")); // space only
        assertFalse(Address.isValidAddress(" 13 Evelyn Road")); // space before address

        // valid address
        assertTrue(Address.isValidAddress("evelyn road")); // letters only, no capitalization
        assertTrue(Address.isValidAddress("Evelyn Road")); // letters only
        assertTrue(Address.isValidAddress("TANJONG RU")); // letters, fully capitalized
        assertTrue(Address.isValidAddress("12 Evelyn Road")); // letters with numbers
        assertTrue(Address.isValidAddress("evelyn road #02-453")); // letters and symbols
        assertTrue(Address.isValidAddress("Block 232 Tanjong Pagar Terminal street 22 industrial park 3 Bizman "
            + "#102-323")); // long address
    }


}
