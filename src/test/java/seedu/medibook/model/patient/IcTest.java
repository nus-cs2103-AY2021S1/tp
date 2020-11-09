package seedu.medibook.model.patient;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.medibook.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class IcTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Ic(null));
    }

    @Test
    public void constructor_invalidIc_throwsIllegalArgumentException() {
        String invalidIc = "S9922eq3W";
        assertThrows(IllegalArgumentException.class, () -> new Ic(invalidIc));
    }

    @Test
    public void isValidIc() {
        // invalid ic
        assertFalse(Ic.isValidIc("")); // empty string
        assertFalse(Ic.isValidIc("S123456H")); // less than 7 digit number
        assertFalse(Ic.isValidIc("A0213487Y"));
        assertFalse(Ic.isValidIc("S12345g6Y")); // letters where digits should be
        assertFalse(Ic.isValidIc("ST1234567A")); // more than 1 letter at the start
        assertFalse(Ic.isValidIc("S1234567AA")); // more than 1 letter at the end

        // valid ic
        assertTrue(Ic.isValidIc("S1234567Y"));
        assertTrue(Ic.isValidIc("T0012345Q"));
        assertTrue(Ic.isValidIc("F6565653R"));
    }
}
