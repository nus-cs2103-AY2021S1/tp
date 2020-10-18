package seedu.address.model.module;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

public class ModuleNameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ModuleName(null));
    }

    @Test
    public void constructor_invalidModuleName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new ModuleName(invalidName));
    }

    @Test
    public void isValidModuleName() {

        // null name
        assertThrows(NullPointerException.class, () -> ModuleName.isValidName(null));

        // invalid name
        assertFalse(ModuleName.isValidName("")); // empty string
        assertFalse(ModuleName.isValidName(" ")); // spaces only
        assertFalse(ModuleName.isValidName("^")); // contains non-alphanumeric characters only
        assertFalse(ModuleName.isValidName("cs")); // contains alphabetic characters only
        assertFalse(ModuleName.isValidName("cs201*")); // contains non-alphanumeric characters
        assertFalse(ModuleName.isValidName("2030")); // contains numeric characters only
        assertFalse(ModuleName.isValidName("c200")); // does not fulfil the 2 alphabet criteria
        assertFalse(ModuleName.isValidName("cs2")); // does not fulfil the numeric criteria

        // valid name
        assertTrue(ModuleName.isValidName("CS2030")); // normal module code
        assertTrue(ModuleName.isValidName("FSC5201")); // 3 alphabet character code
        assertTrue(ModuleName.isValidName("CS1101S")); // module code with alphabet at the end
    }
}
