package seedu.address.model.student.admin;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ClassVenueTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ClassVenue(null));
    }

    @Test
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        String invalidAddress = "";
        assertThrows(IllegalArgumentException.class, () -> new ClassVenue(invalidAddress));
    }

    @Test
    public void isValidAddress() {
        // null address
        assertThrows(NullPointerException.class, () -> ClassVenue.isValidClassVenue(null));

        // invalid addresses
        assertFalse(ClassVenue.isValidClassVenue("")); // empty string
        assertFalse(ClassVenue.isValidClassVenue(" ")); // spaces only

        // valid addresses
        assertTrue(ClassVenue.isValidClassVenue("Blk 456, Den Road, #01-355"));
        assertTrue(ClassVenue.isValidClassVenue("-")); // one character
        assertTrue(ClassVenue.isValidClassVenue("Leng Inc; 1234 Market St; "
                + "San Francisco CA 2349879; USA")); // long address
    }
}
