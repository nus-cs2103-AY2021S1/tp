package seedu.homerce.model.util.attributes;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.homerce.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AmountTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        Double d = null;
        assertThrows(NullPointerException.class, () -> new Amount(d));
    }

    @Test
    public void constructor_invalidAmount_throwsIllegalArgumentException() {
        double invalidAmount = -1.0;
        assertThrows(IllegalArgumentException.class, () -> new Amount(invalidAmount));
    }

    @Test
    public void isValidAmount() {
        // null amount
        assertThrows(NullPointerException.class, () -> Amount.isValidAmount(null));

        // invalid amounts
        assertFalse(Amount.isValidAmount(-10.0)); // Negative amount
        assertFalse(Amount.isValidAmount(0.0)); // zero

        // valid amounts are whole numbers or with two decimal places
        assertTrue(Amount.isValidAmount(12.0)); // whole number
        assertTrue(Amount.isValidAmount(0.12)); // cents
        assertTrue(Amount.isValidAmount(1.23)); // ones
        assertTrue(Amount.isValidAmount(12.34)); // tens
        assertTrue(Amount.isValidAmount(121.45)); // hundreds
    }
}
