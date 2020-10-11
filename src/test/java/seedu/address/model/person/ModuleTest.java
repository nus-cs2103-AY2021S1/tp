package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ModuleTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Module(null));
    }

    @Test
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        String invalidAddress = "";
        assertThrows(IllegalArgumentException.class, () -> new Module(invalidAddress));
    }

    @Test
    public void isValidAddress() {
        // null address
        assertThrows(NullPointerException.class, () -> Module.isValidModuleId(null));

        // invalid addresses
        assertFalse(Module.isValidModuleId("")); // empty string
        assertFalse(Module.isValidModuleId(" ")); // spaces only

        // valid addresses
        assertTrue(Module.isValidModuleId("CS2103T"));
        assertTrue(Module.isValidModuleId("-")); // one character
        assertTrue(Module.isValidModuleId("GER1000H")); // long address
    }
}
