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

    /**
     * Returns true if either the venue, time, bidderId and propertyId is the same.
     *
     * @param other The other property.
     * @return True if both property objects represent the same meeting.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CalendarPaperwork // instanceof handles nulls
                && this.calendarBidderId.equals(((CalendarPaperwork) other).getCalendarBidderId())
                && this.calendarPropertyId.equals(((CalendarPaperwork) other).getCalendarPropertyId())
                && this.calendarTime.equals(((CalendarPaperwork) other).getCalendarTime())
                && this.calendarVenue.equals(((CalendarPaperwork) other).getCalendarVenue())); // state check
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
