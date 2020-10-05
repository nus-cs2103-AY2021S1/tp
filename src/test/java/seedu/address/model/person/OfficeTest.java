package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class OfficeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Office(null));
    }

    @Test
    public void constructor_invalidDepartment_throwsIllegalArgumentException() {
        String invalidOffice = "";
        assertThrows(IllegalArgumentException.class, () -> new Department(invalidOffice));
    }

    @Test
    public void isValidOffice() {
        // null department
        assertThrows(NullPointerException.class, () -> Office.isValidOffice(null));

        // invalid department
        assertFalse(Office.isValidOffice("")); // empty string
        assertFalse(Office.isValidOffice(" ")); // spaces only

        // valid department
        assertTrue(Office.isValidOffice("COM2-02-1"));
        assertTrue(Office.isValidOffice("-")); // one character
    }

}
