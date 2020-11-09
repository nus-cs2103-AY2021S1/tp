package seedu.address.model.commons;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class CostTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Cost(null));
    }

    @Test
    public void constructor_invalidCost_throwsIllegalArgumentException() {
        String invalidCost = "";
        assertThrows(IllegalArgumentException.class, () -> new Cost(invalidCost));
    }

    @Test
    public void isValidCost() {
        // null Cost number
        assertFalse(Cost.isValidCost(null));

        // invalid Cost numbers
        assertFalse(Cost.isValidCost("")); // empty string
        assertFalse(Cost.isValidCost(" ")); // spaces only
        assertFalse(Cost.isValidCost("-91")); // negative cost
        assertFalse(Cost.isValidCost("Cost")); // non-numeric
        assertFalse(Cost.isValidCost("9011p041")); // alphabets within digits
        assertFalse(Cost.isValidCost("9312 1534")); // spaces within digits

        // valid Cost numbers
        assertTrue(Cost.isValidCost("911"));
        assertTrue(Cost.isValidCost("93121534"));
        assertFalse(Cost.isValidCost("124293842033123")); // long Cost numbers
    }
}
