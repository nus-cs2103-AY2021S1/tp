package seedu.address.model.module;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ModuleNameTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ModuleName(null));
    }

    @Test
    public void constructor_invalidMeetingName_throwsIllegalArgumentException() {
        String invalidModuleName = "";
        assertThrows(IllegalArgumentException.class, () -> new ModuleName(invalidModuleName));
    }

    @Test
    public void isValidMeetingName() {
        // null name
        assertThrows(NullPointerException.class, () -> ModuleName.isValidModuleName(null));

        // invalid name
        assertFalse(ModuleName.isValidModuleName("")); // empty string
        assertFalse(ModuleName.isValidModuleName(" ")); // spaces only
        assertFalse(ModuleName.isValidModuleName("^")); // only non-alphanumeric characters
        assertFalse(ModuleName.isValidModuleName("peter*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(ModuleName.isValidModuleName("csabcde")); // alphabets only
        assertTrue(ModuleName.isValidModuleName("12345")); // numbers only
        assertTrue(ModuleName.isValidModuleName("cs12345")); // alphanumeric characters
        assertTrue(ModuleName.isValidModuleName("CSABCDE")); // with capital letters
        assertTrue(ModuleName.isValidModuleName("CS100000000000000000000000000000")); // long names
    }
}
