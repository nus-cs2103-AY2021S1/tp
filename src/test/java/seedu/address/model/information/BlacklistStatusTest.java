package seedu.address.model.information;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class BlacklistStatusTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Date(null));
    }

    @Test
    public void constructor_invalidBlacklistStatus_throwsIllegalArgumentException() {
        String empty = "";
        String word = "yes";
        assertThrows(IllegalArgumentException.class, () -> new Date(empty));
        assertThrows(IllegalArgumentException.class, () -> new Date(word));
    }

    @Test
    public void isValidBlacklistStatus() {
        // null status
        assertThrows(NullPointerException.class, () -> BlacklistStatus.isValidBlacklistStatus(null));

        // invalid status
        assertFalse(BlacklistStatus.isValidBlacklistStatus("")); // empty string
        assertFalse(BlacklistStatus.isValidBlacklistStatus("  ")); // spaces only
        assertFalse(BlacklistStatus.isValidBlacklistStatus("yes")); // yes string
        assertFalse(BlacklistStatus.isValidBlacklistStatus("blacklist")); // blacklist string
        assertFalse(BlacklistStatus.isValidBlacklistStatus("1")); // 1 string

        // valid status
        assertTrue(BlacklistStatus.isValidBlacklistStatus("true")); // true string
        assertTrue(BlacklistStatus.isValidBlacklistStatus("false")); // false string
        assertTrue(BlacklistStatus.isValidBlacklistStatus("TrUe")); // true string with some uppercase
        assertTrue(BlacklistStatus.isValidBlacklistStatus("FALSE")); // false string with all uppercase
        assertTrue(BlacklistStatus.isValidBlacklistStatus("   true   ")); // true string with whitespace
    }

    @Test
    public void isEquals() {
        // same string
        assertEquals(new BlacklistStatus("true"), new BlacklistStatus("true"));

        // strings with mix of uppercase and lowercase
        assertEquals(new BlacklistStatus("tRUE"), new BlacklistStatus("TRue"));
        assertEquals(new BlacklistStatus("TRUE"), new BlacklistStatus("true"));
        assertEquals(new BlacklistStatus("FalsE"), new BlacklistStatus("false"));
        assertEquals(new BlacklistStatus("fALSe"), new BlacklistStatus("FALSE"));
    }
}
