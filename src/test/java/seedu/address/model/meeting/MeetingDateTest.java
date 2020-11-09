package seedu.address.model.meeting;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class MeetingDateTest {

    @Test
    void testToString() {
        MeetingDate meetingDate = new MeetingDate("12 Oct 2012");
        String timeTest = "12 Oct 2012";
        assertTrue(timeTest.equals(meetingDate.toString()));

        MeetingDate meetingDateError = new MeetingDate("14 Oct 2012");
        String timeDiff = "12 Oct 2012";
        assertFalse(timeDiff.equals(meetingDateError.toString()));
    }

    @Test
    void testEquals() {
        MeetingDate meetingDate = new MeetingDate("12 Oct 2012");
        MeetingDate meetingDateSecond = new MeetingDate("12 Oct 2012");
        assertTrue(meetingDate.equals(meetingDateSecond));

        MeetingDate meetingDateDiff = new MeetingDate("12 Oct 2012");
        MeetingDate meetingDateDiffSecond = new MeetingDate("14 Oct 2012");
        assertFalse(meetingDateDiff.equals(meetingDateDiffSecond));
    }

    @Test
    void testHashCode() {
        Venue venue = new Venue("bedok");
        Venue venueSecond = new Venue("bedok");
        assertTrue(venue.hashCode() == venueSecond.hashCode());

        Venue venueDiff = new Venue("bedok");
        Venue venueDiffSecond = new Venue("tampines");
        assertFalse(venueDiff.hashCode() == venueDiffSecond.hashCode());
    }
}
