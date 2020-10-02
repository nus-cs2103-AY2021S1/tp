package seedu.address.model.module;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class CodeTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new seedu.address.model.module.Code(null));
    }

    @Test
    public void constructor_invalidCode_throwsIllegalArgumentException() {
        String invalidCode = "";
        assertThrows(IllegalArgumentException.class, () -> new seedu.address.model.module.Code(invalidCode));
    }

    @Test
    public void isValidCode() {
        // null Code
        assertThrows(NullPointerException.class, () -> seedu.address.model.module.Code.isValidCode(null));

        // invalid Code
        assertFalse(seedu.address.model.module.Code.isValidCode("")); // empty string
        assertFalse(seedu.address.model.module.Code.isValidCode(" ")); // spaces only
        assertFalse(seedu.address.model.module.Code.isValidCode("^")); // only non-alphanumeric characters
        assertFalse(seedu.address.model.module.Code.isValidCode("cs1010*")); // contains non-alphanumeric
        // characters

        // valid Code
        assertTrue(seedu.address.model.module.Code.isValidCode("cs")); // alphabets only
        assertTrue(seedu.address.model.module.Code.isValidCode("1010")); // numbers only
        assertTrue(seedu.address.model.module.Code.isValidCode("cs1010s")); // alphanumeric characters
    }
}
