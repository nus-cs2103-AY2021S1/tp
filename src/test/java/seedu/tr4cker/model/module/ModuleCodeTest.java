package seedu.tr4cker.model.module;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.tr4cker.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;



class ModuleCodeTest {

    @Test
    void isValidModuleCode() {
        // null name
        assertThrows(NullPointerException.class, () -> ModuleCode.isValidModuleCode(null));

        // invalid code
        assertFalse(ModuleCode.isValidModuleCode("@@@")); // non-alphanumeric
        assertFalse(ModuleCode.isValidModuleCode("      ")); // spaces
        assertFalse(ModuleCode.isValidModuleCode("")); // empty string
        assertFalse(ModuleCode.isValidModuleCode("a b")); // spaces
        assertFalse(ModuleCode.isValidModuleCode("del")); // delete prefix
        assertFalse(ModuleCode.isValidModuleCode("DEL")); // delete caps

        // valid code
        assertTrue(ModuleCode.isValidModuleCode("a"));
        assertTrue(ModuleCode.isValidModuleCode("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa")); // long
    }

    @Test
    void testEquals() {
        ModuleCode moduleCode = new ModuleCode("a");

        // same
        assertTrue(moduleCode.equals(new ModuleCode("a")));

        // same object
        assertTrue(moduleCode.equals(moduleCode));

        // different
        assertFalse(moduleCode.equals(new ModuleCode("b")));

        // different type
        assertFalse(moduleCode.equals(5));
    }

    @Test
    void testHashCode() {
        String validCodeName = "CS1231S";
        ModuleCode moduleCode1 = new ModuleCode(validCodeName);
        ModuleCode moduleCode2 = new ModuleCode(validCodeName);
        assertEquals(moduleCode1, moduleCode2);
    }
}
