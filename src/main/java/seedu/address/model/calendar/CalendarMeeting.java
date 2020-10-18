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
    private final BidderId calendarBidderId;
    private final PropertyId calendarPropertyId;
    private final CalendarTime calendarTime;
    private final CalendarVenue calendarVenue;
    private boolean isMeeting;


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
     * Returns true if either the property id is the same or if the address is the same.
     *
     * @param otherMeeting The other property.
     * @return True if both property objects represent the same property.
     */
    public boolean isSameMeeting(CalendarMeeting otherMeeting) {
        if (otherMeeting == this) {
            return true;
        }

        return otherMeeting != null
                && otherMeeting.getCalendarPropertyId().equals(getCalendarPropertyId())
                && (otherMeeting.getCalendarVenue().equals(getCalendarVenue()))
                && ((otherMeeting.getCalendarTime() == getCalendarTime()))
                && ((otherMeeting.getCalendarBidderId() == getCalendarBidderId()));
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

