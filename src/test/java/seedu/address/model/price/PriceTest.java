package seedu.address.model.price;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class PriceTest {

    @Test
    public void constructor_invalidPrice_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Price(-5));
        assertThrows(IllegalArgumentException.class, () -> new Price(-0.99));
    }

    @Test
    public void isValidPrice() {

        // valid prices
        assertTrue(Price.isValidPrice(100));
        assertTrue(Price.isValidPrice(10.2));
        assertTrue(Price.isValidPrice(10.333333));

        // invalid prices
        assertFalse(Price.isValidPrice(-100));
        assertFalse(Price.isValidPrice(-10.2));
        assertFalse(Price.isValidPrice(-10.333333));
    }

    @Test
    public void priceToString() {
        Price price1 = new Price(10);
        Price price2 = new Price(10.2);
        Price price3 = new Price(10.22222);
        assertEquals("$10.00", price1.toString());
        assertEquals("$10.20", price2.toString());
        assertEquals("$10.22", price3.toString());

    }

    @Test
    void compareTo() {
        Price small = new Price(99);
        Price big = new Price(100);
        assertTrue(small.compareTo(big) < 0);
        assertTrue(small.compareTo(small) == 0);
        assertTrue(big.compareTo(small) > 0);
    }
}
