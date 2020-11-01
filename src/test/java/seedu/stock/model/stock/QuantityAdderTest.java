package seedu.stock.model.stock;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.stock.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class QuantityAdderTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new QuantityAdder(null));
    }

    @Test
    public void constructor_invalidQuantity_throwsIllegalArgumentException() {
        String invalidQuantity = "";
        assertThrows(IllegalArgumentException.class, () -> new QuantityAdder(invalidQuantity));
    }

    @Test
    public void isValidValue() {
        // EP: null quantity adder
        assertThrows(NullPointerException.class, () -> QuantityAdder.isValidValue(null));

        // EP: invalid values
        assertFalse(QuantityAdder.isValidValue("")); // empty string
        assertFalse(QuantityAdder.isValidValue("    ")); // spaces only
        assertFalse(QuantityAdder.isValidValue("^#$%")); // only non-alphanumeric characters
        assertFalse(QuantityAdder.isValidValue("audrey")); // alphabets only
        assertFalse(QuantityAdder.isValidValue("2147483648")); // Overflow
        assertFalse(QuantityAdder.isValidValue("-2147483649")); // Underflow

        // EP: valid values
        assertTrue(QuantityAdder.isValidValue("0")); // zero
        assertTrue(QuantityAdder.isValidValue("14")); // small positive number
        assertTrue(QuantityAdder.isValidValue("1000000000")); // large positive number
        assertTrue(QuantityAdder.isValidValue("-69")); // small negative number
        assertTrue(QuantityAdder.isValidValue("-1000000007")); // large negative number
        assertTrue(QuantityAdder.isValidValue(Integer.toString(Integer.MAX_VALUE))); // Max integer value
        assertTrue(QuantityAdder.isValidValue(Integer.toString(Integer.MIN_VALUE))); // Min integer value
    }
}
