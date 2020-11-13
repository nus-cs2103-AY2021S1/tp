package seedu.address.model.day;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class WeightTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Weight(null));
    }

    @Test
    public void constructor_invalidWeight_throwsIllegalArgumentException() {
        String invalidWeight = "";
        assertThrows(IllegalArgumentException.class, () -> new Weight(invalidWeight));
    }

    @Test
    public void isValidWeight() {
        // null phone number
        assertThrows(NullPointerException.class, () -> Weight.isValidWeight(null));

        // invalid weight numbers
        assertFalse(Weight.isValidWeight("")); // empty string
        assertFalse(Weight.isValidWeight(" ")); // spaces only
        assertFalse(Weight.isValidWeight("phone")); // non-numeric
        assertFalse(Weight.isValidWeight("9p1")); // alphabets within digits
        assertFalse(Weight.isValidWeight("9 1")); // spaces within digits

        // valid weight numbers
        assertTrue(Weight.isValidWeight("70")); // exactly 2 digits
        assertTrue(Weight.isValidWeight("120")); // exactly 3 digits
    }
}
