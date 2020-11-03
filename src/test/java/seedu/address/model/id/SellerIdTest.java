package seedu.address.model.id;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class SellerIdTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SellerId(null));
    }

    @Test
    public void constructor_invalidSellerId_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new SellerId(""));
    }

    @Test
    void increment() {
        SellerId sellerId = new SellerId(1);
        List<Integer> expected = Arrays.asList(2, 3, 4, 5, 6);
        for (int value : expected) {
            sellerId = sellerId.increment();
            assertEquals(new SellerId(value), sellerId);
        }
    }

    @Test
    public void isValidId() {
        // null seller id
        assertThrows(NullPointerException.class, () -> SellerId.isValidId(null));

        // invalid seller ids
        assertFalse(SellerId.isValidId("B1"));
        assertFalse(SellerId.isValidId("s1"));
        assertFalse(SellerId.isValidId("random string"));
        assertFalse(SellerId.isValidId("1"));
        assertFalse(SellerId.isValidId("S-1"));
        assertFalse(SellerId.isValidId("S0.1"));

        // valid seller ids
        assertTrue(SellerId.isValidId("S1"));
        assertTrue(SellerId.isValidId("S0"));
        assertTrue(SellerId.isValidId("S" + Integer.MAX_VALUE));
    }

    @Test
    public void equals() {

        // same object
        SellerId sellerId = new SellerId(1);
        assertTrue(sellerId.equals(sellerId));

        // same prefix and id
        SellerId other = new SellerId(1);
        assertTrue(sellerId.equals(other));

        // different id (not possible to have different prefix)
        other = new SellerId(2);
        assertFalse(sellerId.equals(other));

        // different type
        assertFalse(sellerId.equals(new PropertyId(1)));

    }
}