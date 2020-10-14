package seedu.fma.model.log;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.fma.logic.commands.CommandTestUtil.VALID_REP_A;
import static seedu.fma.logic.commands.CommandTestUtil.VALID_REP_A_STR;
import static seedu.fma.logic.commands.CommandTestUtil.VALID_REP_B;
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
    public void isValidRep_null_throwsNullPointerException() {
        // null rep number
        assertThrows(NullPointerException.class, () -> Rep.isValidRep(null));
    }

    @Test
    public void isValidRep_validRep_returnTrue() {
        // valid rep numbers
        assertTrue(Rep.isValidRep("911")); // exactly 3 numbers
        assertTrue(Rep.isValidRep("93121"));
        assertTrue(Rep.isValidRep("124293")); // long rep numbers
    }

    @Test
    public void isValidRep_invalidRep_returnFalse() {
        // invalid rep numbers
        assertFalse(Rep.isValidRep("")); // empty string
        assertFalse(Rep.isValidRep(" ")); // spaces only
        assertFalse(Rep.isValidRep("rep")); // non-numeric
        assertFalse(Rep.isValidRep("9011p041")); // alphabets within digits
        assertFalse(Rep.isValidRep("9312 1534")); // spaces within digits
    }


    @Test
    public void equals_equalRep_returnTrue() {
        Rep repA = new Rep(VALID_REP_A_STR);

        // same values -> returns true
        assertTrue(repA.equals(new Rep(VALID_REP_A_STR)));

        // same instance -> returns true
        assertTrue(repA.equals(repA));
    }

    @Test
    public void equals_differentRep_returnFalse() {

        // null -> returns false
        assertFalse(VALID_REP_A.equals(null));

        // different types -> returns false
        assertFalse(VALID_REP_A.equals(5));

        // different values -> returns false
        assertFalse(VALID_REP_A.equals(VALID_REP_B));
    }

    @Test
    void testHashCode() {
        Rep repA = new Rep(VALID_REP_A_STR);
        assertTrue(repA.hashCode() == repA.hashCode());
    }
}
