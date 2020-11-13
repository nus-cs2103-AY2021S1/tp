package seedu.bagel.model.flashcard;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.bagel.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class LinkTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Link(null));
    }

    @Test
    public void constructor_invalidLink_throwsIllegalArgumentException() {
        String invalidLink = "h";
        assertThrows(IllegalArgumentException.class, () -> new Link(invalidLink));
    }

    @Test
    public void isValidLink() {
        // null link
        assertThrows(NullPointerException.class, () -> Link.isValidLink(null));

        // invalid links
        assertFalse(Link.isValidLink(" ")); // spaces only
        assertFalse(Link.isValidLink("-"));

        // valid links
        assertTrue(Link.isValidLink("https://www.google.com/"));
        assertTrue(Link.isValidLink("")); // empty string
    }
}
