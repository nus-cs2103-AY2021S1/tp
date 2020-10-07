package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AddressTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new FileAddress(null));
    }

    @Test
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        String invalidAddress = "";
        assertThrows(IllegalArgumentException.class, () -> new FileAddress(invalidAddress));
    }

    @Test
    public void isValidAddress() {
        // null address
        assertThrows(NullPointerException.class, () -> FileAddress.isValidAddress(null));

        // invalid addresses
        assertFalse(FileAddress.isValidAddress("")); // empty string
        assertFalse(FileAddress.isValidAddress(" ")); // spaces only

        // valid addresses
        assertTrue(FileAddress.isValidAddress("d:\\a\\b\\abc.txt"));
        assertTrue(FileAddress.isValidAddress("d:\\a\\b")); // one character
        assertTrue(FileAddress.isValidAddress(
                "a:\\b\\c\\d\\e\\f\\g.exe")); // long address
    }
}
