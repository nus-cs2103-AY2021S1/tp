package seedu.pivot.model.investigationcase.caseperson;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.pivot.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;


public class AddressTest {
    private static final String VALID_ADDRESS = "Blk 123";

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Address(null));
    }

    @Test
    public void equals() {
        Address defaultAddress = new Address(VALID_ADDRESS);
        // Same value --> true
        assertTrue(defaultAddress.equals(new Address(VALID_ADDRESS)));

        // same object --> true
        assertTrue(defaultAddress.equals(defaultAddress));

        // null --> false
        assertFalse(defaultAddress.equals(null));

        // different types --> false
        assertFalse(defaultAddress.equals(1));

        // different value --> false
        assertFalse(defaultAddress.equals(new Address("Blk 223"))); // any real number is coincidental.
    }
}
