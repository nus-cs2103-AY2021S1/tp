package seedu.address.model.attraction;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class PriceRangeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new PriceRange(null));
    }

    @Test
    public void constructor_invalidPriceRange_throwsIllegalArgumentException() {
        String invalidPriceRange = "average";
        assertThrows(IllegalArgumentException.class, () -> new PriceRange(invalidPriceRange));
    }

    @Test
    public void isValidPriceRange() {
        // null price range
        assertThrows(NullPointerException.class, () -> PriceRange.isValidPriceRange(null));

        // invalid price ranges
        assertFalse(PriceRange.isValidPriceRange(" ")); // spaces only
        assertFalse(PriceRange.isValidPriceRange("lo")); //incomplete
        assertFalse(PriceRange.isValidPriceRange("average"));
        assertFalse(PriceRange.isValidPriceRange(" MEDIUM")); // leading space
        assertFalse(PriceRange.isValidPriceRange("MEDIUM ")); // trailing space

        // valid price ranges
        assertTrue(PriceRange.isValidPriceRange("")); // empty string
        assertTrue(PriceRange.isValidPriceRange("LOW"));
        assertTrue(PriceRange.isValidPriceRange("MEDIUM"));
        assertTrue(PriceRange.isValidPriceRange("HIGH"));
        assertTrue(PriceRange.isValidPriceRange("HiGh"));
    }
}
