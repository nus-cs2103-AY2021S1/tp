package seedu.address.model.information;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class UrlLinkTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new UrlLink(null));
    }

    @Test
    public void constructor_invalidUrlLink_throwsIllegalArgumentException() {
        String invalidUrlLink = "";
        assertThrows(IllegalArgumentException.class, () -> new UrlLink(invalidUrlLink));
    }

    @Test
    public void isValidUrlLink() {
        // null url links
        assertThrows(NullPointerException.class, () -> UrlLink.isValidLink(null));

        // invalid url links
        assertFalse(UrlLink.isValidLink("")); // empty string
        assertFalse(UrlLink.isValidLink(" ")); // spaces only
        assertFalse(UrlLink.isValidLink("88")); // only numbers
        assertFalse(UrlLink.isValidLink("google")); // only alphabet
        assertFalse(UrlLink.isValidLink("9011p041")); // alphabets within digits
        assertFalse(UrlLink.isValidLink("http:/www.google.com")); // http scheme with only 1 slash
        assertFalse(UrlLink.isValidLink("https:/www.google.com")); // https scheme with only 1 slash
        assertFalse(UrlLink.isValidLink("ftp:/www.google.com")); // ftp scheme with only 1 slash
        assertFalse(UrlLink.isValidLink("simp://www.google.com")); // fake nonsense scheme

        // valid url links
        assertTrue(UrlLink.isValidLink("http://www.google.com")); // http scheme with 2 slash
        assertTrue(UrlLink.isValidLink("https://www.google.com")); // https scheme with 2 slash
        assertTrue(UrlLink.isValidLink("ftp://www.google.com")); // ftp scheme with 2 slash
        assertTrue(UrlLink.isValidLink("http://google.com")); // http scheme with 2 slash amd without www.
        assertTrue(UrlLink.isValidLink("https://google.com")); // https scheme with 2 slash amd without www.
        assertTrue(UrlLink.isValidLink("ftp://google.com")); // ftp sch with 2 slash and without www.
        assertTrue(UrlLink.isValidLink("google.com")); // without any scheme
        assertTrue(UrlLink.isValidLink("linkedin.com")); // without any scheme
        assertTrue(UrlLink.isValidLink("www.google.com")); // without any scheme but with www.
        assertTrue(UrlLink.isValidLink("www.linkedin.com")); // without any scheme but with www.
    }
}
