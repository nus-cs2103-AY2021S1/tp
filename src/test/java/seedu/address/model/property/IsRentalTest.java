package seedu.address.model.property;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class IsRentalTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new IsRental(null));
    }

    @Test
    public void constructor_invalidIsRental_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new IsRental(""));
    }

    @Test
    public void isRental() {
        assertTrue(new IsRental("yes").isRental());
        assertTrue(new IsRental("YES").isRental());
        assertTrue(new IsRental("y").isRental());
        assertTrue(new IsRental("Y").isRental());
        assertTrue(new IsRental("Yes").isRental());

        assertFalse(new IsRental("no").isRental());
        assertFalse(new IsRental("NO").isRental());
        assertFalse(new IsRental("n").isRental());
        assertFalse(new IsRental("N").isRental());
        assertFalse(new IsRental("No").isRental());
    }

    @Test
    public void isValidIsRental() {
        // null isRental
        assertThrows(NullPointerException.class, () -> IsRental.isValidIsRental(null));

        // invalid isRental - not yes / no / y / n (caps insensitive)
        assertFalse(IsRental.isValidIsRental("abc"));
        assertFalse(IsRental.isValidIsRental("123"));
        assertFalse(IsRental.isValidIsRental("**&#"));
        assertFalse(IsRental.isValidIsRental("a1b*"));
        assertFalse(IsRental.isValidIsRental("noo"));

        // valid isRental
        assertTrue(IsRental.isValidIsRental("yes"));
        assertTrue(IsRental.isValidIsRental("YES"));
        assertTrue(IsRental.isValidIsRental("Yes"));
        assertTrue(IsRental.isValidIsRental("y"));
        assertTrue(IsRental.isValidIsRental("Y"));
        assertTrue(IsRental.isValidIsRental("no"));
        assertTrue(IsRental.isValidIsRental("NO"));
        assertTrue(IsRental.isValidIsRental("No"));
        assertTrue(IsRental.isValidIsRental("n"));
        assertTrue(IsRental.isValidIsRental("N"));
    }

    @Test
    public void equals() {
        // same object
        IsRental isRental = new IsRental("yes");
        assertTrue(isRental.equals(isRental));

        // different type
        assertFalse(isRental.equals(new IsClosedDeal("close")));

        // same is rental
        assertTrue(isRental.equals(new IsRental("y")));

        // different is rental
        assertFalse(isRental.equals(new IsRental("no")));
    }
}
