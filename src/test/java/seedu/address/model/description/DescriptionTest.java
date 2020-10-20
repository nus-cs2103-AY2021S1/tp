package seedu.address.model.description;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class DescriptionTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Description(null));
    }

    @Test
    public void constructor_invalidDescription_throwsIllegalArgumentException() {
        String invalidDescription = "";
        assertThrows(IllegalArgumentException.class, () -> new Description(invalidDescription));
    }

    @Test
    public void isValidDescription() {
        // null description
        assertThrows(NullPointerException.class, () -> Description.isValidDescription(null));

        // invalid description
        assertFalse(Description.isValidDescription("")); // empty string
        assertFalse(Description.isValidDescription(" ")); // spaces only
        assertFalse(Description.isValidDescription("^")); // only non-alphanumeric characters
        assertFalse(Description.isValidDescription("CS2103T*")); // contains non-alphanumeric characters
        assertFalse(Description.isValidDescription("CS2103T Software Engineering")); // long descriptions

        // valid description
        assertTrue(Description.isValidDescription("cs")); // alphabets only
        assertTrue(Description.isValidDescription("12345")); // numbers only
        assertTrue(Description.isValidDescription("cs2103T")); // alphanumeric characters
        assertTrue(Description.isValidDescription("CS")); // with capital letters

    }
}
