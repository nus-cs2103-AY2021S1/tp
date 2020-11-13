package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class IdTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ID(null));
    }

    @Test
    public void constructor_invalidID_throwsIllegalArgumentException() {
        String invalidID = "    ";
        assertThrows(IllegalArgumentException.class, () -> new ID(invalidID));
    }

    @Test
    void isValidId() {
        // null Id
        assertThrows(NullPointerException.class, () -> ID.isValidId(null));

        // invalid Id
        assertFalse(ID.isValidId("    ")); // spaces only
        assertFalse(ID.isValidId("abc1"));
        assertFalse(ID.isValidId("ab 1"));

        // invalid first 2 digits
        assertFalse(ID.isValidId("0111"));
        assertFalse(ID.isValidId("1011"));
        assertFalse(ID.isValidId("0011"));
        assertFalse(ID.isValidId("5411"));
        assertFalse(ID.isValidId("4511"));

        // invalid last 2 digits
        assertFalse(ID.isValidId("1234"));
        assertFalse(ID.isValidId("1200"));
        assertFalse(ID.isValidId("1221"));

        // valid Id
        assertTrue(ID.isValidId("1101"));
        assertTrue(ID.isValidId("1120"));
        assertTrue(ID.isValidId("4120"));
        assertTrue(ID.isValidId("4420"));
        assertTrue(ID.isValidId("4401"));

    }
}
