package seedu.fma.model.log;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.fma.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class RepTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Rep(null));
    }

    @Test
    public void constructor_invalidRep_throwsIllegalArgumentException() {
        String invalidRep = "";
        assertThrows(IllegalArgumentException.class, () -> new Rep(invalidRep));
    }

    @Test
    public void isValidRep() {
        // null rep number
        assertThrows(NullPointerException.class, () -> Rep.isValidRep(null));

        // invalid rep numbers
        assertFalse(Rep.isValidRep("")); // empty string
        assertFalse(Rep.isValidRep(" ")); // spaces only
        assertFalse(Rep.isValidRep("rep")); // non-numeric
        assertFalse(Rep.isValidRep("9011p041")); // alphabets within digits
        assertFalse(Rep.isValidRep("9312 1534")); // spaces within digits

        // valid rep numbers
        assertTrue(Rep.isValidRep("911")); // exactly 3 numbers
        assertTrue(Rep.isValidRep("93121"));
        assertTrue(Rep.isValidRep("124293")); // long rep numbers
    }


    @Test
    public void equals() {
        // same values -> returns true
        Rep REP_A = new Rep("12");
        Rep REP_B = new Rep("12");
        Rep REP_C = new Rep("14");
        assertTrue(REP_A.equals(REP_B));

        // same object -> returns true
        assertTrue(REP_A.equals(REP_A));

        // null -> returns false
        assertFalse(REP_A.equals(null));

        // different types -> returns false
        assertFalse(REP_A.equals(5));

        // different values -> returns false
        assertFalse(REP_C.equals(REP_B));
    }
}
