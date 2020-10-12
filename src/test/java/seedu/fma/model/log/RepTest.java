package seedu.fma.model.log;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.fma.logic.commands.CommandTestUtil.VALID_REP_A;
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

        Rep repA = new Rep(VALID_REP_A);
        Rep repB = new Rep(VALID_REP_B);

        // same values -> returns true
        assertTrue(repA.equals(new Rep(VALID_REP_A)));

        // same object -> returns true
        assertTrue(repA.equals(repA));

        // null -> returns false
        assertFalse(repA.equals(null));

        // different types -> returns false
        assertFalse(repA.equals(5));

        // different values -> returns false
        assertFalse(repA.equals(repB));
    }
}
