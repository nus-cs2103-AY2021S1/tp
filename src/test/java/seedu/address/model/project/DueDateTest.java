package seedu.address.model.project;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DueDateTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DueDate(null));
    }

    @Test
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        String invalidAddress = "";
        assertThrows(IllegalArgumentException.class, () -> new DueDate(invalidAddress));
    }

    @Test
    public void isValidAddress() {
        // null address
        assertThrows(NullPointerException.class, () -> DueDate.isValidDueDate(null));

        // invalid addresses
        assertFalse(DueDate.isValidDueDate("")); // empty string
        assertFalse(DueDate.isValidDueDate(" ")); // spaces only

        // valid addresses
        assertTrue(DueDate.isValidDueDate("Blk 456, Den Road, #01-355"));
        assertTrue(DueDate.isValidDueDate("-")); // one character
        assertTrue(DueDate.isValidDueDate("Leng Inc; 1234 Market St; San Francisco CA 2349879; USA")); // long address
    }
}
