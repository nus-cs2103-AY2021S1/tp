package seedu.address.model.item;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class QuantityTest {
    /**
     * Tests for valid quantity. Only non-negative integers are allowed.
     */
    @Test
    public void isValidQuantity() {
        assertTrue(Quantity.isValidQuantity("123"));
        assertTrue(Quantity.isValidQuantity("0"));
        assertFalse(Quantity.isValidQuantity("1 2"));
        assertFalse(Quantity.isValidQuantity("-2"));
        assertFalse(Quantity.isValidQuantity("1.2"));
        assertFalse(Quantity.isValidQuantity("a"));
    }

    /**
     * Test for equality between quantities.
     */
    @Test
    public void equals() {
        assertEquals(new Quantity("1"), new Quantity("1"));
        assertNotEquals(new Quantity("1"), new Quantity("2"));
    }
}
