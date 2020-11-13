package seedu.address.model.tag;

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
        String invalidAddress = "::";
        assertThrows(IllegalArgumentException.class, () -> new FileAddress(invalidAddress));
    }

    @Test
    public void isValidFileAddress() {
        // null address
        assertThrows(NullPointerException.class, () -> FileAddress.isValidFileAddress(null));

        // invalid addresses
        assertFalse(FileAddress.isValidFileAddress("*")); // forbidden symbol
        assertFalse(FileAddress.isValidFileAddress(" ")); // spaces only

        // valid addresses
        assertTrue(FileAddress.isValidFileAddress("d:\\a\\b\\abc.txt"));
        assertTrue(FileAddress.isValidFileAddress("d:\\haha\\..")); // appending ..
        assertTrue(FileAddress.isValidFileAddress("d:\\haha\\.")); // appending .
        assertTrue(FileAddress.isValidFileAddress("z:\\a\\b\\abc.txt")); // z drive
        assertTrue(FileAddress.isValidFileAddress("d")); // one character
        assertTrue(FileAddress.isValidFileAddress("d:/ab/abc.txt")); // backward slash
        assertTrue(FileAddress.isValidFileAddress("/usr/abc.txt")); // linux
        assertTrue(FileAddress.isValidFileAddress("./")); // root
        assertTrue(FileAddress.isValidFileAddress(".\\")); // root
        assertTrue(FileAddress.isValidFileAddress(
                "a:\\b\\c\\d\\e\\f\\g.exe")); // long address
    }
}
