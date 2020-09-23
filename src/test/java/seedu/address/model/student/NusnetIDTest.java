package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class NusnetIdTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new NusnetId(null));
    }

    @Test
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        String invalidAddress = "";
        assertThrows(IllegalArgumentException.class, () -> new NusnetId(invalidAddress));
    }

    @Test
    public void isValidAddress() {
        // null address
        assertThrows(NullPointerException.class, () -> NusnetId.isValidAddress(null));

        // invalid addresses
        assertFalse(NusnetId.isValidAddress("")); // empty string
        assertFalse(NusnetId.isValidAddress(" ")); // spaces only

        // valid addresses
        assertTrue(NusnetId.isValidAddress("Blk 456, Den Road, #01-355"));
        assertTrue(NusnetId.isValidAddress("-")); // one character
        assertTrue(NusnetId.isValidAddress("Leng Inc; 1234 Market St; San Francisco CA 2349879; USA")); // long address
    }
}
