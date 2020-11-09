package seedu.address.model.property;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class IsClosedDealTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new IsClosedDeal(null));
    }

    @Test
    public void constructor_invalidInput_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new IsClosedDeal(""));
    }

    @Test
    public void isValidIsClosedDeal() {

        // null
        assertThrows(NullPointerException.class, () -> IsClosedDeal.isValidIsClosedDeal(null));

        // invalid input
        assertFalse(IsClosedDeal.isValidIsClosedDeal("abc"));
        assertFalse(IsClosedDeal.isValidIsClosedDeal("123"));
        assertFalse(IsClosedDeal.isValidIsClosedDeal(""));
        assertFalse(IsClosedDeal.isValidIsClosedDeal("abc123**_"));
        assertFalse(IsClosedDeal.isValidIsClosedDeal("closed"));
        assertFalse(IsClosedDeal.isValidIsClosedDeal("actives"));

        // valid input
        assertTrue(IsClosedDeal.isValidIsClosedDeal("Close"));
        assertTrue(IsClosedDeal.isValidIsClosedDeal("close"));
        assertTrue(IsClosedDeal.isValidIsClosedDeal("CLOSE"));
        assertTrue(IsClosedDeal.isValidIsClosedDeal("cLOsE"));
        assertTrue(IsClosedDeal.isValidIsClosedDeal("Active"));
        assertTrue(IsClosedDeal.isValidIsClosedDeal("ACTIVE"));
        assertTrue(IsClosedDeal.isValidIsClosedDeal("active"));
        assertTrue(IsClosedDeal.isValidIsClosedDeal("aCTIvE"));
    }

    @Test
    public void equals() {
        IsClosedDeal close = new IsClosedDeal("close");
        IsClosedDeal active = new IsClosedDeal("active");

        // same object
        assertTrue(close.equals(close));
        assertTrue(active.equals(active));

        // different type
        assertFalse(close.equals(new IsRental("yes")));

        // same is closed deal
        assertTrue(close.equals(new IsClosedDeal("close")));

        // different is closed deal
        assertFalse(close.equals(active));
    }
}
