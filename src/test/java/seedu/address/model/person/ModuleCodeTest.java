package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ModuleCodeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ModuleCode(null));
    }

    @Test
    public void constructor_invalidModuleCode_throwsIllegalArgumentException() {
        String invalidModuleCode = "";
        assertThrows(IllegalArgumentException.class, () -> new ModuleCode(invalidModuleCode));
    }

    @Test
    public void isValidModuleCode() {
        // null address
        assertThrows(NullPointerException.class, () -> ModuleCode.isValidModuleCode(null));

        // invalid addresses
        assertFalse(ModuleCode.isValidModuleCode("")); // empty string
        assertFalse(ModuleCode.isValidModuleCode(" ")); // spaces only

        // valid addresses
        assertTrue(ModuleCode.isValidModuleCode("Blk 456, Den Road, #01-355"));
        assertTrue(ModuleCode.isValidModuleCode("-")); // one character
        assertTrue(ModuleCode.isValidModuleCode(
                "Leng Inc; 1234 Market St; San Francisco CA 2349879; USA")); // long address
    }
}
