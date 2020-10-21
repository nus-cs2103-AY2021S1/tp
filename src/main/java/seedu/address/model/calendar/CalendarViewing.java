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

    /**
     * Returns true if either the venue, time, bidderId and propertyId is the same.
     *
     * @param other The other property.
     * @return True if both property objects represent the same meeting.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CalendarViewing // instanceof handles nulls
                && this.calendarBidderId.equals(((CalendarViewing) other).getCalendarBidderId())
                && this.calendarPropertyId.equals(((CalendarViewing) other).getCalendarPropertyId())
                && this.calendarTime.equals(((CalendarViewing) other).getCalendarTime())
                && this.calendarVenue.equals(((CalendarViewing) other).getCalendarVenue())); // state check
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
