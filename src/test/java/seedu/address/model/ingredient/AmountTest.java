package seedu.address.model.ingredient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class AmountTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Amount(null));
    }

    @Test
    public void constructor_invalidAmount_throwsIllegalArgumentException() {
        String invalidAmount = "";
        assertThrows(IllegalArgumentException.class, () -> new Amount(invalidAmount));
    }

    @Test
    public void isValidAmount() {
        assertThrows(NullPointerException.class, () -> Amount.isValidAmount(null));

        // invalid amount numbers
        assertFalse(Amount.isValidAmount("")); // empty string
        assertFalse(Amount.isValidAmount(" ")); // spaces only
        assertFalse(Amount.isValidAmount("-91")); // negative number
        assertFalse(Amount.isValidAmount("phone")); // non-numeric
        assertFalse(Amount.isValidAmount("1p0")); // alphabets within digits
        assertFalse(Amount.isValidAmount("2 1")); // spaces within digits

        // valid amount numbers
        assertTrue(Amount.isValidAmount("1")); // exactly 1 number
        assertTrue(Amount.isValidAmount("93121534")); //large amounts
    }

    @Test
    public void isAmountBelowRestockLevel() {
        Amount testAmount1 = new Amount("1");
        Amount testAmount2 = new Amount("2");
        Amount testAmount4 = new Amount("4");
        Amount testAmount5 = new Amount("0");
        Amount testAmount6 = new Amount("5");
        assertTrue(testAmount1.isBelowRestockLevel());
        assertTrue(testAmount2.isBelowRestockLevel());
        assertTrue(testAmount4.isBelowRestockLevel());
        assertTrue(testAmount5.isBelowRestockLevel());
        assertFalse(testAmount6.isBelowRestockLevel());
    }

    @Test
    public void testToString() {
        Amount test1 = new Amount("90"); // two digits
        Amount test2 = new Amount("0"); // edge case, 0
        Amount test3 = new Amount("93121543"); // large amounts
        assertEquals(test1.toString(), "90");
        assertEquals(test2.toString(), "0");
        assertEquals(test3.toString(), "93121543");
    }
}
