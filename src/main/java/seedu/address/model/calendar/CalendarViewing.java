package seedu.address.model.calendar;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.model.id.BidderId;
import seedu.address.model.id.PropertyId;

public class CalendarViewing extends CalendarMeeting {

    private static final String PREFIX = "v";

    /**
     *  Constructor for viewing meeting.
     * @param calendarBidderId Bidder ID.
     * @param calendarPropertyId Property ID.
     * @param calendarTime Calendar Time.
     * @param calendarVenue Calendar Venue.
     */
    public CalendarViewing(BidderId calendarBidderId, PropertyId calendarPropertyId,
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
