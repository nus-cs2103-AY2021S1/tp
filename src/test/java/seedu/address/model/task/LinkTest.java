package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class LinkTest {

    @Test
    public void isValidLink() {
        // null url
        assertThrows(NullPointerException.class, () -> Link.isValidUrl(null));

        // invalid url
        assertFalse(Link.isValidUrl(""));
        assertFalse(Link.isValidUrl(" "));
        assertFalse(Link.isValidUrl("zoom"));
        assertFalse(Link.isValidUrl("zoom.com"));
        assertFalse(Link.isValidUrl("98112031234"));
        assertFalse(Link.isValidUrl("https://."));

        // valid url
        assertTrue(Link.isValidUrl("https://nus-sg.zoom.us/j/85350904475?pwd=T0JwTEIwNjRuMnNKUEt4L2RBMFJWZz09"));
        assertTrue(Link.isValidUrl("https://www.example.com"));
    }
}
