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
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new ModuleName(invalidName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> ModuleName.isValidName(null));

        // invalid name
        assertFalse(ModuleName.isValidName("")); // empty string
        assertFalse(ModuleName.isValidName(" ")); // spaces only
        assertFalse(ModuleName.isValidName("^")); // only non-alphanumeric characters
        assertFalse(ModuleName.isValidName("programming*")); // contains non-alphanumeric
        // characters

        // valid name
        assertTrue(ModuleName.isValidName("programming")); // alphabets only
        assertTrue(ModuleName.isValidName("2")); // numbers only
        assertTrue(ModuleName.isValidName("programming 2")); // alphanumeric characters
        assertTrue(ModuleName.isValidName("Programming Methodology 2")); // with capital letters
    }
}
