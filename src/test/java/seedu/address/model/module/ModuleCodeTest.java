package seedu.address.model.module;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
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

        // invalid module code
        assertFalse(ModuleCode.isValidModuleCode("")); // empty string
        assertFalse(ModuleCode.isValidModuleCode(" ")); // spaces only
        assertFalse(ModuleCode.isValidModuleCode("^")); // only non-alphanumeric characters
        assertFalse(ModuleCode.isValidModuleCode("2100t")); // no module prefix
        assertFalse(ModuleCode.isValidModuleCode("abcd")); // no digits
        assertFalse(ModuleCode.isValidModuleCode("c2100")); // only 1-letter prefix
        assertFalse(ModuleCode.isValidModuleCode("c  2100")); // only 1-letter prefix but has spaces
        assertFalse(ModuleCode.isValidModuleCode("cs2100*")); // contains non-alphanumeric characters
        assertFalse(ModuleCode.isValidModuleCode("cs 2100 T")); // contains spaces within

        // valid module code
        assertTrue(ModuleCode.isValidModuleCode("cs2100")); // 2-letter prefix followed by digits
        assertTrue(ModuleCode.isValidModuleCode("CS2100")); // with capital letters
        assertTrue(ModuleCode.isValidModuleCode("CS2103T")); // with 1-letter suffix
        assertTrue(ModuleCode.isValidModuleCode("GER1000")); // 3-letter prefix
        assertTrue(ModuleCode.isValidModuleCode("GER1000H")); // 3-letter prefix with 1-letter suffix
    }

    @Test
    public void hashCodeTest() {
        ModuleCode code1 = new ModuleCode("cs2100");
        ModuleCode code2 = new ModuleCode("CS2100");
        assertEquals(code1.hashCode(), code1.hashCode());
        assertNotEquals(code1.hashCode(), code2.hashCode());
    }
}
