package seedu.address.model.calendar;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

public class CalendarAdmin extends CalendarMeeting {

    private static final String PREFIX = "a";

    /**
     *  Constructor for admin meeting.
     * @param calendarBidderId Bidder ID.
     * @param calendarPropertyId Property ID.
     * @param calendarTime Calendar Time.
     * @param calendarVenue Calendar Venue.
     */
    public CalendarAdmin(CalendarBidderId calendarBidderId, CalendarPropertyId calendarPropertyId,
                         CalendarTime calendarTime, CalendarVenue calendarVenue) {
        super(calendarBidderId, calendarPropertyId, calendarTime, calendarVenue);
        requireAllNonNull(calendarBidderId, calendarPropertyId, calendarTime, calendarVenue);
        super.isAdmin = true;
    }


    @Override
    public String toString() {
        return super.toString();
    }
}
