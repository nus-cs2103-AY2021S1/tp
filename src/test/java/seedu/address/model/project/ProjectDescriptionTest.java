package seedu.address.model.project;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ProjectDescriptionTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ProjectDescription(null));
    }

    @Test
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        String invalidAddress = "";
        assertThrows(IllegalArgumentException.class, () -> new ProjectDescription(invalidAddress));
    }

    @Test
    public void isValidAddress() {
        // null address
        assertThrows(NullPointerException.class, () -> ProjectDescription.isValidProjectDescription(null));

        // invalid addresses
        assertFalse(ProjectDescription.isValidProjectDescription("")); // empty string
        assertFalse(ProjectDescription.isValidProjectDescription(" ")); // spaces only

        // valid addresses
        assertTrue(ProjectDescription.isValidProjectDescription("Blk 456, Den Road, #01-355"));
        assertTrue(ProjectDescription.isValidProjectDescription("-")); // one character
        assertTrue(ProjectDescription.isValidProjectDescription("Leng Inc; 1234 Market St; San Francisco CA 2349879; USA")); // long address
    }
}
