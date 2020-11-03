package seedu.address.model.id;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class BidderIdTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new BidderId(null));
    }

    @Test
    public void constructor_invalidBidderId_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new BidderId(""));
    }

    @Test
    void increment() {
        BidderId bidderId = new BidderId(1);
        List<Integer> expected = Arrays.asList(2, 3, 4, 5, 6);
        for (int value : expected) {
            bidderId = bidderId.increment();
            assertEquals(new BidderId(value), bidderId);
        }
    }

    @Test
    public void isValidId() {
        // null bidder id
        assertThrows(NullPointerException.class, () -> BidderId.isValidId(null));

        // invalid bidder ids
        assertFalse(BidderId.isValidId("P1"));
        assertFalse(BidderId.isValidId("b1"));
        assertFalse(BidderId.isValidId("random string"));
        assertFalse(BidderId.isValidId("1"));
        assertFalse(BidderId.isValidId("B-1"));
        assertFalse(PropertyId.isValidId("B0.1"));

        // valid bidder ids
        assertTrue(BidderId.isValidId("B1"));
        assertTrue(BidderId.isValidId("B0"));
        assertTrue(BidderId.isValidId("B" + Integer.MAX_VALUE));
    }

    @Test
    public void equals() {

        // same object
        BidderId bidderId = new BidderId(1);
        assertTrue(bidderId.equals(bidderId));

        // same prefix and id
        BidderId other = new BidderId(1);
        assertTrue(bidderId.equals(other));

        // different id (not possible to have different prefix)
        other = new BidderId(2);
        assertFalse(bidderId.equals(other));

        // different type
        assertFalse(bidderId.equals(new PropertyId(1)));

    }
}