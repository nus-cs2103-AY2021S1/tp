package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TypeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Type(null));
    }

    @Test
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        String invalidAddress = "";
        assertThrows(IllegalArgumentException.class, () -> new Type(invalidAddress));
    }

    @Test
    public void isValidAddress() {
        // null address
        assertThrows(NullPointerException.class, () -> Type.isValidAddress(null));

        // invalid addresses
        assertFalse(Type.isValidAddress("")); // empty string
        assertFalse(Type.isValidAddress(" ")); // spaces only

        // valid addresses
        assertTrue(Type.isValidAddress("Blk 456, Den Road, #01-355"));
        assertTrue(Type.isValidAddress("-")); // one character
        assertTrue(Type.isValidAddress("Leng Inc; 1234 Market St; San Francisco CA 2349879; USA")); // long address
    }
}
