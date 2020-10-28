package seedu.stock.model.stock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.stock.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AccumulatedQuantityTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AccumulatedQuantity(null));
    }

    @Test
    public void constructor_invalidAccumulatedQuantity_throwsIllegalArgumentException() {
        String invalidAccumulatedQuantity = "";
        assertThrows(IllegalArgumentException.class, () ->
                                        new AccumulatedQuantity(invalidAccumulatedQuantity));
    }

    @Test
    public void correctlyIncrementedAccumulatedQuantity() {

        AccumulatedQuantity accumulatedQuantity = new AccumulatedQuantity("99");
        AccumulatedQuantity incrementedAccumulatedQuantity = accumulatedQuantity
                                                                    .getIncrementedAccumulatedQuantity();
        AccumulatedQuantity expectedIncrementedAccumulatedQuantity = new AccumulatedQuantity("100");
        assertNotEquals(accumulatedQuantity, incrementedAccumulatedQuantity);
        assertEquals(incrementedAccumulatedQuantity, expectedIncrementedAccumulatedQuantity);
    }

    @Test
    public void invalidAccumulatedQuantityFormats() {
        // null accumulated quantity
        assertThrows(NullPointerException.class, () -> AccumulatedQuantity.isValidAccumulatedQuantity(null));

        // invalid accumulated quantity
        assertFalse(AccumulatedQuantity.isValidAccumulatedQuantity("")); // empty string
        assertFalse(AccumulatedQuantity.isValidAccumulatedQuantity(" ")); // spaces only
        assertFalse(AccumulatedQuantity.isValidAccumulatedQuantity("^")); // only non-numeric characters
        assertFalse(AccumulatedQuantity.isValidAccumulatedQuantity("100*")); // contains non-numeric characters
        assertFalse(AccumulatedQuantity.isValidAccumulatedQuantity("100.00")); // only non-integer values
        assertFalse(AccumulatedQuantity.isValidAccumulatedQuantity("-100")); // only negative values
        assertFalse(AccumulatedQuantity.isValidAccumulatedQuantity(
                Integer.toString(Integer.MAX_VALUE + 1))); // larger than INTEGER.MAX_VALUE

    }

    @Test
    public void validAccumulatedQuantityFormats() {
        // valid accumulated quantity
        assertTrue(AccumulatedQuantity.isValidAccumulatedQuantity("100")); // integer
        assertTrue(AccumulatedQuantity.isValidAccumulatedQuantity("1000000000")); // large integer
        assertTrue(AccumulatedQuantity.isValidAccumulatedQuantity(
                Integer.toString(Integer.MAX_VALUE))); //Max integer value
    }

    @Test
    public void equals() {
        AccumulatedQuantity accumulatedQuantity = new AccumulatedQuantity("100");
        AccumulatedQuantity accumulatedQuantityCopy = new AccumulatedQuantity("100");
        AccumulatedQuantity differentAccumulatedQuantity = new AccumulatedQuantity("99");

        // returns true
        assertTrue(accumulatedQuantity.equals(accumulatedQuantity)); // compare to self
        assertTrue(accumulatedQuantity.equals(accumulatedQuantityCopy)); // compare to a copy of itself

        // returns false, different accumulated quantity
        assertFalse(accumulatedQuantity.equals(differentAccumulatedQuantity));


    }
}
