package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.module.ModuleId;

public class ModuleIdTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ModuleId(null));
    }

    @Test
    public void constructor_invalidModuleId_throwsIllegalArgumentException() {
        String invalidName = "@";
        assertThrows(IllegalArgumentException.class, () -> new ModuleId(invalidName));
    }

    @Test
    public void isValidModuleId() {
        // null name
        assertThrows(NullPointerException.class, () -> ModuleId.isValidModuleId(null));

        // invalid name
        assertFalse(ModuleId.isValidModuleId("")); // empty string
        assertFalse(ModuleId.isValidModuleId(" ")); // spaces only
        assertFalse(ModuleId.isValidModuleId("^")); // only non-alphanumeric characters
        assertFalse(ModuleId.isValidModuleId("peter*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(ModuleId.isValidModuleId("CS1101S")); // alphabets only
        assertTrue(ModuleId.isValidModuleId("12345")); // numbers only
        assertTrue(ModuleId.isValidModuleId("number 1 boring module")); // alphanumeric characters
        assertTrue(ModuleId.isValidModuleId("CS2101")); // with capital letters
        assertTrue(ModuleId.isValidModuleId("David Roger Jackson Ray Jr 2nd")); // long names
    }
}
