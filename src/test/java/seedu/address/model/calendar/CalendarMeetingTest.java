package seedu.address.model.calendar;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class CalendarMeetingTest {

    private CalendarMeeting firstMeeting = new CalendarMeeting(new CalendarBidderId("b1"),
            new CalendarPropertyId("p1"), new CalendarTime("12 Oct 2020"),
            new CalendarVenue("bedok"));
    private CalendarMeeting firstMeetingIdentical = new CalendarMeeting(new CalendarBidderId("b1"),
            new CalendarPropertyId("p1"), new CalendarTime("12 Oct 2020"),
            new CalendarVenue("bedok"));
    private CalendarMeeting firstMeetingDiff = new CalendarMeeting(new CalendarBidderId("b2"),
            new CalendarPropertyId("p2"), new CalendarTime("15 Oct 2020"),
            new CalendarVenue("tampines"));
    private CalendarViewing meetingDiffViewingType = new CalendarViewing(new CalendarBidderId("b2"),
            new CalendarPropertyId("p2"), new CalendarTime("15 Oct 2020"),
            new CalendarVenue("tampines"));
    private CalendarAdmin meetingDiffAdminType = new CalendarAdmin(new CalendarBidderId("b2"),
            new CalendarPropertyId("p2"), new CalendarTime("15 Oct 2020"),
            new CalendarVenue("tampines"));
    private CalendarPaperwork meetingDiffPaperworkType = new CalendarPaperwork(new CalendarBidderId("b2"),
            new CalendarPropertyId("p2"), new CalendarTime("15 Oct 2020"),
            new CalendarVenue("tampines"));
    private CalendarPaperwork meetingPaperworkType = new CalendarPaperwork(new CalendarBidderId("b2"),
            new CalendarPropertyId("p2"), new CalendarTime("15 Oct 2020"),
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

    @Test
    void equals() {
        assertTrue(firstMeeting.equals(firstMeetingIdentical));
        assertFalse(firstMeeting.equals(firstMeetingDiff));
    }

}
