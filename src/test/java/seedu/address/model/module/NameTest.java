package seedu.address.model.module;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class NameTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new seedu.address.model.module.Name(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new seedu.address.model.module.Name(invalidName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> seedu.address.model.module.Name.isValidName(null));

        // invalid name
        assertFalse(seedu.address.model.module.Name.isValidName("")); // empty string
        assertFalse(seedu.address.model.module.Name.isValidName(" ")); // spaces only
        assertFalse(seedu.address.model.module.Name.isValidName("^")); // only non-alphanumeric characters
        assertFalse(seedu.address.model.module.Name.isValidName("programming*")); // contains non-alphanumeric
        // characters

        // valid name
        assertTrue(seedu.address.model.module.Name.isValidName("programming")); // alphabets only
        assertTrue(seedu.address.model.module.Name.isValidName("2")); // numbers only
        assertTrue(seedu.address.model.module.Name.isValidName("programming 2")); // alphanumeric characters
        assertTrue(seedu.address.model.module.Name.isValidName("Programming Methodology 2")); // with capital letters
    }
}
