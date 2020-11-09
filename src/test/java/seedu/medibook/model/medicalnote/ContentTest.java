package seedu.medibook.model.medicalnote;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.medibook.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class ContentTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Content(null));
    }

    @Test
    public void constructor_emptyString_throwsIllegalArgumentException() {
        String invalidContent = "";
        assertThrows(IllegalArgumentException.class, () -> new Content(invalidContent));
    }

    @Test
    void isValidContent() {
        // valid content string
        assertTrue(Content.isValidContent("Patient is good.")); // alphabets only
        assertTrue(Content.isValidContent("Patient turned up with 39.0C fever.")); // alphanumeric
        assertTrue(Content.isValidContent("012345")); // numbers only


        // invalid content string
        assertFalse(Content.isValidContent("")); // empty string

        // null content string
        assertThrows(NullPointerException.class, () -> Content.isValidContent(null));
    }

    @Test
    void equals() {
        assertEquals(new Content("Patient is good."), new Content("Patient is good."));
    }
}
