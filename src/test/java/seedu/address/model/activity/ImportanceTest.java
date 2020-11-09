package seedu.address.model.activity;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ImportanceTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Importance(null));
    }

    @Test
    public void constructor_invalidImportance_throwsIllegalArgumentException() {
        String invalidImportance = "";
        assertThrows(IllegalArgumentException.class, () -> new Importance(invalidImportance));
    }

    @Test
    public void isValidImportance() {
        // null Importance number
        assertThrows(NullPointerException.class, () -> Importance.isValidImportance(null));

        // invalid Importance numbers
        assertFalse(Importance.isValidImportance("")); // empty string
        assertFalse(Importance.isValidImportance(" ")); // spaces only
        assertFalse(Importance.isValidImportance("-91")); // less than 3 numbers
        assertFalse(Importance.isValidImportance("Importance")); // non-numeric
        assertFalse(Importance.isValidImportance("9011p041")); // alphabets within digits
        assertFalse(Importance.isValidImportance("9312 1534")); // spaces within digits
        assertFalse(Importance.isValidImportance("6")); // out of range


        // valid Importance numbers
        assertTrue(Importance.isValidImportance("1"));
        assertTrue(Importance.isValidImportance("2"));
        assertTrue(Importance.isValidImportance("5"));
    }
}
