package seedu.stock.model.stock;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.stock.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class NameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Name(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new Name(invalidName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> Name.isValidName(null));

        // invalid name
        assertFalse(Name.isValidName("")); // empty string
        assertFalse(Name.isValidName(" ")); // spaces only
        assertFalse(Name.isValidName("^")); // only non-alphanumeric characters
        assertFalse(Name.isValidName("fruit*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(Name.isValidName("apple")); // alphabets only
        assertTrue(Name.isValidName("134324")); // numbers only
        assertTrue(Name.isValidName("fan 1234")); // alphanumeric characters
        assertTrue(Name.isValidName("Apple Banana")); // with capital letters
        assertTrue(Name.isValidName("Apple Banana Orange Citrus fruit")); // long names
    }
}
