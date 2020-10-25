package seedu.address.model.meeting;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.id.BidderId;
import seedu.address.model.id.PropertyId;

class MeetingTest {

    private Meeting firstMeeting = new Meeting(new BidderId("B1"),
            new PropertyId("P1"), new Time("12 Oct 2020"),
            new Venue("bedok"));
    private Meeting firstMeetingIdentical = new Meeting(new BidderId("B1"),
            new PropertyId("P1"), new Time("12 Oct 2020"),
            new Venue("bedok"));
    private Meeting firstMeetingDiff = new Meeting(new BidderId("B2"),
            new PropertyId("P2"), new Time("15 Oct 2020"),
            new Venue("tampines"));
    private Viewing meetingDiffViewingType = new Viewing(new BidderId("B2"),
            new PropertyId("P2"), new Time("15 Oct 2020"),
            new Venue("tampines"));
    private Admin meetingDiffAdminType = new Admin(new BidderId("B2"),
            new PropertyId("P2"), new Time("15 Oct 2020"),
            new Venue("tampines"));
    private Paperwork meetingDiffPaperworkType = new Paperwork(new BidderId("B2"),
            new PropertyId("P2"), new Time("15 Oct 2020"),
            new Venue("tampines"));
    private Paperwork meetingPaperworkType = new Paperwork(new BidderId("B2"),
            new PropertyId("P2"), new Time("15 Oct 2020"),
            new Venue("tampines"));

    @Test
    void getMeetingBidderId() {
        assertTrue(firstMeeting.getBidderId().equals(firstMeetingIdentical.getBidderId()));
        assertFalse(firstMeeting.getBidderId().equals(firstMeetingDiff.getBidderId()));
        assert (firstMeeting.getBidderId().equals(firstMeetingIdentical.getBidderId()));
    }

    @Test
    void getMeetingPropertyId() {
        assertTrue(firstMeeting.getPropertyId().equals(firstMeetingIdentical.getPropertyId()));
        assertFalse(firstMeeting.getPropertyId().equals(firstMeetingDiff.getPropertyId()));
    }

    @Test
    void getMeetingTime() {
        assertTrue(firstMeeting.getTime().equals(firstMeetingIdentical.getTime()));
        assertFalse(firstMeeting.getTime().equals(firstMeetingDiff.getTime()));
    }

    @Test
    void getMeetingVenue() {
        assertTrue(firstMeeting.getVenue().equals(firstMeetingIdentical.getVenue()));
        assertFalse(firstMeeting.getVenue().equals(firstMeetingDiff.getVenue()));
    }

    @Test
    void isViewing() {
        assertFalse(firstMeeting.isViewing() && firstMeetingDiff.isViewing());
        assertTrue(meetingDiffViewingType.isViewing());
    }

    @Test
    void isPaperWork() {
        assertFalse(firstMeeting.isViewing() && firstMeetingDiff.isViewing());
        assertTrue(meetingDiffViewingType.isViewing());
    }

    @Test
    void isAdmin() {
        assertFalse(firstMeeting.isAdmin() && firstMeetingDiff.isAdmin());
        assertTrue(meetingDiffAdminType.isAdmin());
    }

    @Test
    void equals() {
        assertTrue(firstMeeting.equals(firstMeetingIdentical));
        assertFalse(firstMeeting.equals(firstMeetingDiff));
    }

}
