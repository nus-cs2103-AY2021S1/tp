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
        assertTrue(Price.isValidPrice(0.01));
        assertTrue(Price.isValidPrice(100));
        assertTrue(Price.isValidPrice(10.2));
        assertTrue(Price.isValidPrice(10.333333));
        assertTrue(Price.isValidPrice(Math.pow(10, 12)));

        // invalid prices
        assertFalse(Price.isValidPrice(0));
        assertFalse(Price.isValidPrice(0.004));
        assertFalse(Price.isValidPrice(-100));
        assertFalse(Price.isValidPrice(-10.2));
        assertFalse(Price.isValidPrice(-10.333333));
        assertFalse(Price.isValidPrice(Math.pow(10, 12) + 0.1));
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
    public void getPrice() {
        assertEquals(1.00, new Price(1).getPrice());        // 0 dp
        assertEquals(10.20, new Price(10.2).getPrice());    // 1 dp
        assertEquals(Math.pow(10, 12), new Price(Math.pow(10, 12)).getPrice());     // max price
        assertEquals(10.22, new Price(10.223456).getPrice());       // round off (less than 0.5)
        assertEquals(10.57, new Price(10.569).getPrice());          // round off (more than 0.5)
    }

    @Test
    void compareTo() {
        Price small = new Price(99);
        Price big = new Price(100);
        assertTrue(small.compareTo(big) < 0);
        assertTrue(small.compareTo(small) == 0);
        assertTrue(big.compareTo(small) > 0);
    }

    @Test
    public void equals() {
        Price price = new Price(10);

        // same object
        assertTrue(price.equals(price));

        // different type
        assertFalse(price.equals(new PriceFilter("< 10")));

        // equal price
        assertTrue(price.equals(new Price(10)));
        assertTrue(price.equals(new Price(10.00)));
        assertTrue(price.equals(new Price(10.00456)));

        // unequal price
        assertFalse(price.equals(new Price(11)));
        assertFalse(price.equals(new Price(10.1)));
        assertFalse(price.equals(new Price(10.005)));
    }
}
