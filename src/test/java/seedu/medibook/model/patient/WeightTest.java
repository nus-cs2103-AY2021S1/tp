package seedu.medibook.model.patient;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.medibook.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class WeightTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Weight(null));
    }

    @Test
    public void constructor_invalidWeight_throwsIllegalArgumentException() {
        String invalidWeight = "55.55";
        assertThrows(IllegalArgumentException.class, () -> new Weight(invalidWeight));
    }

    @Test
    public void isValidWeight() {
        // invalid weight
        assertFalse(Weight.isValidWeight(null)); // null input
        assertFalse(Weight.isValidWeight("")); // empty string
        assertFalse(Weight.isValidWeight("-10.5")); // negative weight
        assertFalse(Weight.isValidWeight("40.49")); // 2 decimal point weight
        assertFalse(Weight.isValidWeight("70.y")); // non-numeric character

        // valid weight
        assertTrue(Weight.isValidWeight("50.5"));
        assertTrue(Weight.isValidWeight("195.0"));
        assertTrue(Weight.isValidWeight("88.8"));
    }
}
