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

        // blank zoom link
        assertFalse(ZoomLink.isValidZoomLink("")); // empty string
        assertFalse(ZoomLink.isValidZoomLink(" ")); // spaces only

        // missing parts
        assertFalse(ZoomLink.isValidZoomLink("/j/6396489185?pwd=a")); // missing front part
        assertFalse(ZoomLink.isValidZoomLink("https://nus-sg.zoom.us/")); // missing path

        // valid name
        assertTrue(ZoomLink.isValidZoomLink(
                "https://nus-sg.zoom.us/j/6396489185?pwd=a0NSY3ZhcFgzbkcrd0ptN1FZbnpRQT09"));

    }
}
