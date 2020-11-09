package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TagTaskTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TagTask(null, true));
    }

    @Test
    public void constructor_invalidTaskDescription_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new TagTask(" ", true));
    }

    @Test
    public void isValidTaskDescription() {
        // null description
        assertThrows(NullPointerException.class, () -> TagTask.isValidTaskDescription(null));

        // Invalid description
        assertFalse(TagTask.isValidTaskDescription(""));
        assertFalse(TagTask.isValidTaskDescription(" "));
        assertFalse(TagTask.isValidTaskDescription(" abc "));
        assertFalse(TagTask.isValidTaskDescription("_abc"));

        // Valid description
        assertTrue(TagTask.isValidTaskDescription("abc"));
        assertTrue(TagTask.isValidTaskDescription("ABC"));
        assertTrue(TagTask.isValidTaskDescription("123"));
        assertTrue(TagTask.isValidTaskDescription("with spacing and underscore_ "));
        assertTrue(TagTask.isValidTaskDescription("valid symbol !@#"));
    }
}
