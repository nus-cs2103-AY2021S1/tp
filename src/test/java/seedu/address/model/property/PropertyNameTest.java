package seedu.address.model.property;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.property.PropertyCommandTestUtil;

public class PropertyNameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new PropertyName(null));
    }

    @Test
    public void constructor_invalidPropertyName_throwsIllegalArgumentException() {
        String invalidPropertyName = "";
        assertThrows(IllegalArgumentException.class, () -> new PropertyName(invalidPropertyName));
        assertThrows(IllegalArgumentException.class, () ->
                new PropertyName(PropertyCommandTestUtil.getStringWithCharacters(PropertyName.MAX_LENGTH + 1)));
    }

    @Test
    public void isValidPropertyName() {
        // null name
        assertThrows(NullPointerException.class, () -> PropertyName.isValidPropertyName(null));

        // invalid name
        assertFalse(PropertyName.isValidPropertyName("")); // empty string
        assertFalse(PropertyName.isValidPropertyName(" ")); // spaces only
        assertFalse(PropertyName.isValidPropertyName("^")); // only non-alphanumeric characters
        assertFalse(PropertyName.isValidPropertyName("sunrise*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(PropertyName.isValidPropertyName("sunrise heights")); // alphabets only
        assertTrue(PropertyName.isValidPropertyName("12345")); // numbers only
        assertTrue(PropertyName.isValidPropertyName("sunrise the 2nd")); // alphanumeric characters
        assertTrue(PropertyName.isValidPropertyName("Capital Rise")); // with capital letters
        assertTrue(PropertyName.isValidPropertyName("Sunrise Heights Block With Balconies 2nd")); // long names
        assertTrue(PropertyName.isValidPropertyName(PropertyCommandTestUtil
                .getStringWithCharacters(PropertyName.MAX_LENGTH)));
    }

    @Test
    public void equals() {
        PropertyName name = new PropertyName("Property");

        // same object
        assertTrue(name.equals(name));

        // different type
        assertFalse(name.equals(new Address("Property")));

        // same name
        assertTrue(name.equals(new PropertyName("Property")));

        // different name
        assertFalse(name.equals(new PropertyName("property")));

    }
}
