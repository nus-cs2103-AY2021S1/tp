package seedu.address.model.item;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class SupplierTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Supplier(null));
    }

    @Test
    public void constructor_invalidSupplier_throwsIllegalArgumentException() {
        String invalidSupplier = "";
        assertThrows(IllegalArgumentException.class, () -> new Supplier(invalidSupplier));
    }

    @Test
    public void isValidSupplier() {
        // null Supplier
        assertThrows(NullPointerException.class, () -> Supplier.isValidSupplier(null));

        // invalid Supplieres
        assertFalse(Supplier.isValidSupplier("")); // empty string
        assertFalse(Supplier.isValidSupplier(" ")); // spaces only

        // valid Supplieres
        assertTrue(Supplier.isValidSupplier("NTUC"));
        assertTrue(Supplier.isValidSupplier("No Supplier")); // two character
        assertTrue(Supplier.isValidSupplier("Sheng Shong Supermarket")); // long Supplier
    }
}

