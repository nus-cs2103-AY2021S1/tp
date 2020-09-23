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
    public void constructor_invalidPhone_throwsIllegalArgumentException() {
        String invalidPhone = "";
        assertThrows(IllegalArgumentException.class, () -> new Quantity(invalidPhone));
    }

    @Test
    public void isValidPhone() {
        // null phone number
        assertThrows(NullPointerException.class, () -> Quantity.isValidPhone(null));

        // invalid phone numbers
        assertFalse(Quantity.isValidPhone("")); // empty string
        assertFalse(Quantity.isValidPhone(" ")); // spaces only
        assertFalse(Quantity.isValidPhone("91")); // less than 3 numbers
        assertFalse(Quantity.isValidPhone("phone")); // non-numeric
        assertFalse(Quantity.isValidPhone("9011p041")); // alphabets within digits
        assertFalse(Quantity.isValidPhone("9312 1534")); // spaces within digits

        // valid phone numbers
        assertTrue(Quantity.isValidPhone("911")); // exactly 3 numbers
        assertTrue(Quantity.isValidPhone("93121534"));
        assertTrue(Quantity.isValidPhone("124293842033123")); // long phone numbers
    }
}
