package seedu.address.model.calendar;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.model.id.BidderId;
import seedu.address.model.id.PropertyId;


/**
 * Represents a meeting in the calendar book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class CalendarMeeting {

    // Identity fields
    protected boolean isPaperWork;
    protected boolean isViewing;
    protected boolean isAdmin;
    protected final BidderId calendarBidderId;
    protected final PropertyId calendarPropertyId;
    protected final CalendarTime calendarTime;
    protected final CalendarVenue calendarVenue;
    protected boolean isMeeting;

    /**
     * Every field must be present and not null.
     */
    public CalendarMeeting(BidderId calendarBidderId, PropertyId calendarPropertyId,
                           CalendarTime calendarTime, CalendarVenue calendarVenue) {
        requireAllNonNull(calendarBidderId, calendarPropertyId, calendarTime, calendarVenue);
        this.calendarBidderId = calendarBidderId;
        this.calendarPropertyId = calendarPropertyId;
        this.calendarTime = calendarTime;
        this.calendarVenue = calendarVenue;
        this.isMeeting = true;
        this.isPaperWork = false;
        this.isViewing = false;
        this.isAdmin = false;
    }

    /**
     * Checks the type of meeting.
     * @return String containing the correct meeting type.
     */
    public String checkMeetingType() {
        if (isPaperWork) {
            return "Paperwork";
        } else if (isViewing) {
            return "Viewing";
        } else if (isAdmin) {
            return "Admin";
        } else {
            //throw new AssertionError("no such meeting type");
            return "General";
        }
    }

    /**
     * Creates the type of meeting based on the meeting type.
     * @return CalendarMeeting of the specific meeting type.
     */
    public CalendarMeeting createMeeting(String type, BidderId bidderId,
                                         PropertyId propertyId, CalendarTime time, CalendarVenue venue) {
        if (type.equalsIgnoreCase("Paperwork")) {
            return new CalendarPaperwork(bidderId, propertyId, time, venue);
        } else if (type.equalsIgnoreCase("Admin")) {
            return new CalendarAdmin(bidderId, propertyId, time, venue);
        } else if (type.equalsIgnoreCase("Viewing")) {
            return new CalendarViewing(bidderId, propertyId, time, venue);
        } else {
            return new CalendarMeeting(bidderId, propertyId, time, venue);
        }
    }

    public BidderId getCalendarBidderId() {
        return this.calendarBidderId;
    }

    public PropertyId getCalendarPropertyId() {
        return this.calendarPropertyId;
    }

    public CalendarTime getCalendarTime() {
        return this.calendarTime;
    }

    public CalendarVenue getCalendarVenue() {
        return this.calendarVenue;
    }

    public boolean isViewing() {
        return this.isViewing;
    }

    public boolean isPaperWork() {
        return this.isPaperWork;
    }

    public boolean isAdmin() {
        return this.isAdmin;
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
                || (other instanceof CalendarMeeting // instanceof handles nulls
                && this.calendarBidderId.equals(((CalendarMeeting) other).getCalendarBidderId())
                && this.calendarPropertyId.equals(((CalendarMeeting) other).getCalendarPropertyId())
                && this.calendarTime.equals(((CalendarMeeting) other).getCalendarTime())
                && this.calendarVenue.equals(((CalendarMeeting) other).getCalendarVenue())); // state check
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(checkMeetingType());

        builder.append(" Bidder Id: ")
                .append(getCalendarBidderId())
                .append(" Property Id: ")
                .append(getCalendarPropertyId())
                .append(" Venue: ")
                .append(getCalendarVenue())
                .append(" Time: ")
                .append(getCalendarTime());
        return builder.toString();
    }
}

