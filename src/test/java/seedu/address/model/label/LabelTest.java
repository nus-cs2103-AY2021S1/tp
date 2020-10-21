package seedu.address.model.label;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class LabelTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Label(null));
    }

    @Test
    public void constructor_invalidLabel_throwsIllegalArgumentException() {
        String invalidDescription = "";
        assertThrows(IllegalArgumentException.class, () -> new Label(invalidDescription));
    }

    @Test
    public void isValidLabel() {
        // null description
        assertThrows(NullPointerException.class, () -> Label.isValidLabel(null));

        // invalid description
        assertFalse(Label.isValidLabel("")); // empty string
        assertFalse(Label.isValidLabel(" ")); // spaces only
        assertFalse(Label.isValidLabel("^")); // only non-alphanumeric characters
        assertFalse(Label.isValidLabel("CS2103T*")); // contains non-alphanumeric characters
        assertFalse(Label.isValidLabel("CS2103T Software Engineering")); // long labels

        // valid description
        assertTrue(Label.isValidLabel("cs")); // alphabets only
        assertTrue(Label.isValidLabel("12345")); // numbers only
        assertTrue(Label.isValidLabel("cs2103T")); // alphanumeric characters
        assertTrue(Label.isValidLabel("CS")); // with capital letters

    }
}
