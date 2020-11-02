package seedu.address.model.module;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ZoomLinkTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ZoomLink(null));
    }

    @Test
    public void constructor_invalidZoomLink_throwsIllegalArgumentException() {
        String invalidZoomLink = "";
        assertThrows(IllegalArgumentException.class, () -> new ZoomLink(invalidZoomLink));
    }

    @Test
    public void isValidZoomLink() {
        // null zoom link
        assertThrows(NullPointerException.class, () -> ZoomLink.isValidZoomLink(null));

        // invalid zoom link
        assertFalse(ZoomLink.isValidZoomLink("")); // empty string
        assertFalse(ZoomLink.isValidZoomLink(" ")); // spaces only
        assertFalse(ZoomLink.isValidZoomLink("/j/6396489185?pwd=a")); // missing NUS domain
        assertFalse(ZoomLink.isValidZoomLink("https://nus-sg.zoom.us/")); // missing path
        assertFalse(ZoomLink
                .isValidZoomLink("https://nus-sg.zoom.us/^&*")); // path with only invalid special characters
        assertFalse(ZoomLink
                .isValidZoomLink("https://nus-sg.zoom.us/hasuch83ru^$#")); // path with invalid special characters
        assertFalse(ZoomLink.isValidZoomLink("https://nus-sg.zoom.us/832gfweyf  73rgcyc")); // path with whitespace

        // valid zoom link
        assertTrue(ZoomLink
                .isValidZoomLink("https://nus-sg.zoom.us/j/63nakassdvb")); // path with only alphanumeric characters
        assertTrue(ZoomLink
                .isValidZoomLink("https://nus-sg.zoom.us/=?")); // path with only valid special characters
        assertTrue(ZoomLink
                .isValidZoomLink("https://nus-sg.zoom.us/j/6g23d=9?/87ashcu3r3fnajdscd=?nhusac8")); // long path

    }
}
