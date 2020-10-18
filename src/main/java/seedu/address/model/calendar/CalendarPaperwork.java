package seedu.address.model.calendar;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.model.id.BidderId;
import seedu.address.model.id.PropertyId;

public class CalendarPaperwork extends CalendarMeeting {

    /**
     * Constructor for paperwork meeting.
     * @param calendarBidderId Bidder ID.
     * @param calendarPropertyId Property ID.
     * @param calendarTime Calendar Time.
     * @param calendarVenue Calendar Venue.
     */
    public CalendarPaperwork(BidderId calendarBidderId, PropertyId calendarPropertyId,
                             CalendarTime calendarTime, CalendarVenue calendarVenue) {
        super(calendarBidderId, calendarPropertyId, calendarTime, calendarVenue);
        requireAllNonNull(calendarBidderId, calendarPropertyId, calendarTime, calendarVenue);
        super.isPaperWork = true;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
