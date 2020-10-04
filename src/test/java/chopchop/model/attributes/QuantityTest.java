package chopchop.model.attributes;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import org.junit.jupiter.api.Test;

public class QuantityTest {

    @Test
    public void constructor_invalidQuantity_throwsIllegalArgumentException() {
        double invalidQuantity = -0.1;
        assertThrows(IllegalArgumentException.class, () -> new Quantity(invalidQuantity));
    }

    @Test
    public void isValidQuantity() {
        // invalid qty
        assertFalse(Quantity.isValidQuantity(-1)); // negative

        // valid qty
        assertTrue(Quantity.isValidQuantity(200000)); // large int
        assertTrue(Quantity.isValidQuantity(0.23)); // floating point num
    }
}
