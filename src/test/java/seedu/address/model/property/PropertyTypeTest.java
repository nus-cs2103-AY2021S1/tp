package seedu.address.model.property;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class PropertyTypeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new PropertyType(null));
    }

    @Test
    public void constructor_invalidPropertyType_throwsIllegalArgumentException() {
        String invalidPropertyType = "";
        assertThrows(IllegalArgumentException.class, () -> new PropertyType(invalidPropertyType));
    }

    @Test
    public void isValidPropertyType() {
        // null type
        assertThrows(NullPointerException.class, () -> PropertyType.isValidPropertyType(null));

        // invalid types
        assertFalse(PropertyType.isValidPropertyType("")); // empty string
        assertFalse(PropertyType.isValidPropertyType(" ")); // spaces only
        assertFalse(PropertyType.isValidPropertyType("^")); // only non-alphanumeric characters
        assertFalse(PropertyType.isValidPropertyType("HDB*")); // contains non-alphanumeric characters

        // valid types
        assertTrue(PropertyType.isValidPropertyType("condo condo")); // alphabets only
        assertTrue(PropertyType.isValidPropertyType("12345")); // numbers only
        assertTrue(PropertyType.isValidPropertyType("bungalow 5 rooms")); // alphanumeric characters
        assertTrue(PropertyType.isValidPropertyType("Capital Rise")); // with capital letters
        assertTrue(PropertyType.isValidPropertyType("Sunrise Heights Block With Balconies 2nd")); // long names
    }
}
