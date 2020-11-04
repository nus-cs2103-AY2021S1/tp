package seedu.address.model.task;

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
        // null module code
        assertThrows(NullPointerException.class, () -> ModuleCode.isValidModuleCode(null));

        // invalid module codes
        assertFalse(ModuleCode.isValidModuleCode("")); // empty string
        assertFalse(ModuleCode.isValidModuleCode(" ")); // spaces only
        assertFalse(ModuleCode.isValidModuleCode("C2300")); // begins with one alphabet
        assertFalse(ModuleCode.isValidModuleCode("ABCD1000")); // begins with more than 3 alphabets
        assertFalse(ModuleCode.isValidModuleCode("GER10")); // only has 2 numbers
        assertFalse(ModuleCode.isValidModuleCode("ABC10000")); // has 5 numbers

        // valid module codes
        assertTrue(ModuleCode.isValidModuleCode("CS2103T"));
        assertTrue(ModuleCode.isValidModuleCode("CS2040S"));
        assertTrue(ModuleCode.isValidModuleCode("GER1000"));
        assertTrue(ModuleCode.isValidModuleCode(
                "CS2101"));
    }
}
