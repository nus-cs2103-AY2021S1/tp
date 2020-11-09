package seedu.address.model.commons;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DescriptionTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Description(null));
    }

    @Test
    public void isValidDescription() {
        // null description
        assertThrows(NullPointerException.class, () -> Description.isValidDescription(null));

        // valid descriptions
        assertTrue(Description.isValidDescription("")); // empty string
        assertTrue(Description.isValidDescription(" ")); // spaces only
        assertTrue(Description.isValidDescription("World's first Gudetama themed cafe."));
        assertTrue(Description.isValidDescription("-")); // one character
        assertTrue(Description.isValidDescription(
                "The park offers a haven for close to 3500 birds across 400 species.")); // long address
    }
}
