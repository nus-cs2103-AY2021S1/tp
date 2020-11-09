package seedu.address.model.module;

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
    public void constructor_invalidCode_throwsIllegalArgumentException() {
        String invalidCode = "";
        assertThrows(IllegalArgumentException.class, () -> new ModuleCode(invalidCode));
    }

    @Test
    public void isValidCode() {
        // null Code
        assertThrows(NullPointerException.class, () -> ModuleCode.isValidCode(null));

        // invalid Code
        assertFalse(ModuleCode.isValidCode("")); // empty string
        assertFalse(ModuleCode.isValidCode(" ")); // spaces only
        assertFalse(ModuleCode.isValidCode("^")); // only non-alphanumeric characters
        assertFalse(ModuleCode.isValidCode("cs1010*")); // contains non-alphanumeric
        // characters

        // valid Code
        assertTrue(ModuleCode.isValidCode("cs")); // alphabets only
        assertTrue(ModuleCode.isValidCode("1010")); // numbers only
        assertTrue(ModuleCode.isValidCode("cs1010s")); // alphanumeric characters
    }
}
