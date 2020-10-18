package seedu.address.model.calendar;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.id.BidderId;
import seedu.address.model.id.PropertyId;

class CalendarMeetingTest {

    private CalendarMeeting firstMeeting = new CalendarMeeting(new BidderId("B1"),
            new PropertyId("P1"), new CalendarTime("12 Oct 2020"),
            new CalendarVenue("bedok"));
    private CalendarMeeting firstMeetingIdentical = new CalendarMeeting(new BidderId("B1"),
            new PropertyId("P1"), new CalendarTime("12 Oct 2020"),
            new CalendarVenue("bedok"));
    private CalendarMeeting firstMeetingDiff = new CalendarMeeting(new BidderId("B2"),
            new PropertyId("P2"), new CalendarTime("15 Oct 2020"),
            new CalendarVenue("tampines"));
    private CalendarViewing meetingDiffViewingType = new CalendarViewing(new BidderId("B2"),
            new PropertyId("P2"), new CalendarTime("15 Oct 2020"),
            new CalendarVenue("tampines"));
    private CalendarAdmin meetingDiffAdminType = new CalendarAdmin(new BidderId("B2"),
            new PropertyId("P2"), new CalendarTime("15 Oct 2020"),
            new CalendarVenue("tampines"));
    private CalendarPaperwork meetingDiffPaperworkType = new CalendarPaperwork(new BidderId("B2"),
            new PropertyId("P2"), new CalendarTime("15 Oct 2020"),
            new CalendarVenue("tampines"));

    @Test
    void getCalendarBidderId() {
        assertTrue(firstMeeting.getCalendarBidderId().equals(firstMeetingIdentical.getCalendarBidderId()));
        assertFalse(firstMeeting.getCalendarBidderId().equals(firstMeetingDiff.getCalendarBidderId()));
    }

    @Test
    void getCalendarPropertyId() {
        assertTrue(firstMeeting.getCalendarPropertyId().equals(firstMeetingIdentical.getCalendarPropertyId()));
        assertFalse(firstMeeting.getCalendarPropertyId().equals(firstMeetingDiff.getCalendarPropertyId()));
    }

    @Test
    void getCalendarTime() {
        assertTrue(firstMeeting.getCalendarTime().equals(firstMeetingIdentical.getCalendarTime()));
        assertFalse(firstMeeting.getCalendarTime().equals(firstMeetingDiff.getCalendarTime()));
    }

    @Test
    void getCalendarVenue() {
        assertTrue(firstMeeting.getCalendarVenue().equals(firstMeetingIdentical.getCalendarVenue()));
        assertFalse(firstMeeting.getCalendarVenue().equals(firstMeetingDiff.getCalendarVenue()));
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
}
