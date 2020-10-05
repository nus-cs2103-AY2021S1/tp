package seedu.address.model.price;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class PriceTest {

    @Test
    public void constructor_invalidPrice_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Price(-5));
    }

    @Test
    public void isValidPrice() {

        // valid prices
        assertTrue(Price.isValidPrice(100));

        // invalid prices
        assertFalse(Price.isValidPrice(-100));
    }
}
