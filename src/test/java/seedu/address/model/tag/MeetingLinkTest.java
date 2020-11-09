package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class MeetingLinkTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new MeetingLink(null));
    }

    @Test
    public void constructor_invalidTagName_throwsIllegalArgumentException() {
        String invalidMeetingLink = "";
        assertThrows(IllegalArgumentException.class, () -> new MeetingLink(invalidMeetingLink));
    }

    @Test
    public void isValidMeetingLink() {
        // null meeting link
        assertThrows(NullPointerException.class, () -> MeetingLink.isValidMeetingLink(null));

        // Invalid meeting link
        assertFalse(MeetingLink.isValidMeetingLink("http.com"));
        assertFalse(MeetingLink.isValidMeetingLink("http://"));
        assertFalse(MeetingLink.isValidMeetingLink("http://.com"));
        assertFalse(MeetingLink.isValidMeetingLink("@#$%"));
        assertFalse(MeetingLink.isValidMeetingLink("www.google.com"));

        // Valid meeting link
        assertTrue(MeetingLink.isValidMeetingLink("http://google.com"));
        assertTrue(MeetingLink.isValidMeetingLink("https://nus-sg.zoom.us/j/87576Z4OXkveS9HNUV5QT09"));
    }

}
