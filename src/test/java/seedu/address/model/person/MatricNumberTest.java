package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class MatricNumberTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new MatricNumber(null));
    }

    @Test
    public void constructor_invalidMatricNumber_throwsIllegalArgumentException() {
        String invalidMatricNumber = "";
        assertThrows(IllegalArgumentException.class, () -> new MatricNumber(invalidMatricNumber));
    }

    @Test
    public void isValidMatricNumber() {
        // null address
        assertThrows(NullPointerException.class, () -> MatricNumber.isValidMatricNumber(null));

        // invalid addresses
        assertFalse(MatricNumber.isValidMatricNumber("")); // empty string
        assertFalse(MatricNumber.isValidMatricNumber(" ")); // spaces only
        assertFalse(MatricNumber.isValidMatricNumber("Joe")); // 3 characters
        assertFalse(MatricNumber.isValidMatricNumber("B0001111Z")); // Start with B instead

        // valid addresses
        assertTrue(MatricNumber.isValidMatricNumber("A1234000Z"));
        assertTrue(MatricNumber.isValidMatricNumber("A4321000A"));
    }
}
