package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class NusnetIDTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new NusnetID(null));
    }

    @Test
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        String invalidAddress = "";
        assertThrows(IllegalArgumentException.class, () -> new NusnetID(invalidAddress));
    }

    @Test
    public void isValidAddress() {
        // null address
        assertThrows(NullPointerException.class, () -> NusnetID.isValidAddress(null));

        // invalid addresses
        assertFalse(NusnetID.isValidAddress("")); // empty string
        assertFalse(NusnetID.isValidAddress(" ")); // spaces only

        // valid addresses
        assertTrue(NusnetID.isValidAddress("Blk 456, Den Road, #01-355"));
        assertTrue(NusnetID.isValidAddress("-")); // one character
        assertTrue(NusnetID.isValidAddress("Leng Inc; 1234 Market St; San Francisco CA 2349879; USA")); // long address
    }
}
