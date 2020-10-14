package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DescriptionTest {

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

        // blank description
        assertFalse(Description.isValidDescription("")); // empty string
        assertFalse(Description.isValidDescription(" ")); // spaces only

        // invalid description
        assertFalse(Description.isValidDescription("peterjack@-")); // invalid symbols used.
        assertFalse(Description.isValidDescription("!#$%&'*+/=?`{|}~^.-@example.org")); // invalid symbols used.
        assertFalse(Description.isValidDescription(" fdsjkfsd")); // start with space.

        // valid description
        assertTrue(Description.isValidDescription("amy,example.com"));
        assertTrue(Description.isValidDescription("abc")); // minimal
        assertTrue(Description.isValidDescription("testlocalhost")); // alphabets only
        assertTrue(Description.isValidDescription("123  145")); // white space allowed
        assertTrue(Description.isValidDescription("sadf !!!!")); // exclamation mark allowed
        assertTrue(Description.isValidDescription("????????sdfsdf ?????adsfaf???")); // question mark allowed
        assertTrue(Description.isValidDescription("this is a trolling test case."
                + "Happy debugging! Are you enjoying it?")); // long description
    }
}
