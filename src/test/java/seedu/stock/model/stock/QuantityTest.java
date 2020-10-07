package seedu.stock.model.stock;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.stock.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class QuantityTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Quantity(null));
    }

    @Test
    public void constructor_invalidQuantity_throwsIllegalArgumentException() {
        String invalidQuantity = "";
        assertThrows(IllegalArgumentException.class, () -> new Quantity(invalidQuantity));
    }

    @Test
    public void isValidQuantity() {
        // null quantity
        assertThrows(NullPointerException.class, () -> Quantity.isValidQuantity(null));

        // invalid quantity
        assertFalse(Quantity.isValidQuantity("")); // empty string
        assertFalse(Quantity.isValidQuantity(" ")); // spaces only
        assertFalse(Quantity.isValidQuantity("^")); // only non-alphanumeric characters
        assertFalse(Quantity.isValidQuantity("fdfs")); // alphabets only
        assertFalse(Quantity.isValidQuantity("-199")); // negative number

        // valid quantity
        assertTrue(Quantity.isValidQuantity("0")); // zero
        assertTrue(Quantity.isValidQuantity("14")); // numbers only
        assertTrue(Quantity.isValidQuantity("2324254")); // large number
    }
}
