package seedu.address.model.calendar;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

public class CalendarViewing extends CalendarMeeting {

    private static final String PREFIX = "v";

    /**
     *  Constructor for viewing meeting.
     * @param calendarBidderId Bidder ID.
     * @param calendarPropertyId Property ID.
     * @param calendarTime Calendar Time.
     * @param calendarVenue Calendar Venue.
     */
    public CalendarViewing(CalendarBidderId calendarBidderId, CalendarPropertyId calendarPropertyId,
                           CalendarTime calendarTime, CalendarVenue calendarVenue) {
        super(calendarBidderId, calendarPropertyId, calendarTime, calendarVenue);
        requireAllNonNull(calendarBidderId, calendarPropertyId, calendarTime, calendarVenue);
        super.isViewing = true;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
