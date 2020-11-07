package seedu.fma.model.log;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.fma.logic.commands.CommandTestUtil.VALID_REP_A;
import static seedu.fma.logic.commands.CommandTestUtil.VALID_REP_A_INT;
import static seedu.fma.logic.commands.CommandTestUtil.VALID_REP_B;

import org.junit.jupiter.api.Test;

public class RepTest {

    @Test
    public void isValidRep_validRep_returnTrue() {
        // valid rep numbers
        assertTrue(Rep.isValidRep(911)); // exactly 3 numbers
        assertTrue(Rep.isValidRep(1));
        assertTrue(Rep.isValidRep(1000)); // long rep numbers
    }

    @Test
    public void isValidRep_invalidRep_returnFalse() {
        // invalid rep numbers
        assertFalse(Rep.isValidRep(0)); // empty string
        assertFalse(Rep.isValidRep(-1)); // spaces only
        assertFalse(Rep.isValidRep(1001)); // non-numeric
        assertFalse(Rep.isValidRep(Integer.MAX_VALUE)); // alphabets within digits
    }


    @Test
    public void equals_equalRep_returnTrue() {
        Rep repA = new Rep(VALID_REP_A_INT);

        // same values -> returns true
        assertTrue(repA.equals(new Rep(VALID_REP_A_INT)));

        // same instance -> returns true
        assertTrue(repA.equals(repA));
    }

    @Test
    public void equals_differentRep_returnFalse() {

        // different types -> returns false
        assertFalse(VALID_REP_A.equals(5));

        // different values -> returns false
        assertFalse(VALID_REP_A.equals(VALID_REP_B));
    }

    @Test
    void testHashCode() {
        Rep repA = new Rep(VALID_REP_A_INT);
        assertTrue(repA.hashCode() == repA.hashCode());
    }
}
