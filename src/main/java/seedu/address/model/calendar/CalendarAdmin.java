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

    /**
     * Returns true if either the venue, time, bidderId and propertyId is the same.
     *
     * @param other The other property.
     * @return True if both property objects represent the same meeting.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CalendarAdmin // instanceof handles nulls
                && this.calendarBidderId.equals(((CalendarAdmin) other).getCalendarBidderId())
                && this.calendarPropertyId.equals(((CalendarAdmin) other).getCalendarPropertyId())
                && this.calendarTime.equals(((CalendarAdmin) other).getCalendarTime())
                && this.calendarVenue.equals(((CalendarAdmin) other).getCalendarVenue())); // state check
    }


    @Override
    public String toString() {
        return super.toString();
    }
}
