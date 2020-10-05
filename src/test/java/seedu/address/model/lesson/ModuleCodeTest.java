package seedu.address.model.lesson;

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
        assertTrue(ModuleCode.isValidModuleCode("CS2100"));
        assertTrue(ModuleCode.isValidModuleCode("CFG1002")); // one character
        assertTrue(ModuleCode.isValidModuleCode("CS1101S"));
    }
}
