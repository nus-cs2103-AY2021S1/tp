package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleId;

public class ModuleTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Module(null));
    }

    @Test
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        String invalidAddress = "";
        assertThrows(IllegalArgumentException.class, () -> new Module(new ModuleId(invalidAddress)));
    }

    @Test
    public void isValidAddress() {
        // null address
        assertThrows(NullPointerException.class, () -> ModuleId.isValidModuleId(null));

        // invalid addresses
        assertFalse(ModuleId.isValidModuleId("")); // empty string
        assertFalse(ModuleId.isValidModuleId(" ")); // spaces only

        // valid addresses
        assertTrue(ModuleId.isValidModuleId("CS2103T"));
        assertFalse(ModuleId.isValidModuleId("-")); // one character
        assertTrue(ModuleId.isValidModuleId("GER1000H")); // long address
    }
}
