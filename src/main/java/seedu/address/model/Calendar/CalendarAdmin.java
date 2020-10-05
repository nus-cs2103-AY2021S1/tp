package seedu.address.model.Calendar;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

public class CalendarAdmin extends CalendarMeeting {

    private static final String PREFIX = "a";
    CalendarAdmin (CalendarBidderId calendarBidderId, CalendarPropertyId calendarPropertyId,
                   CalendarTime calendarTime, CalendarVenue calendarVenue) {
        super(calendarBidderId, calendarPropertyId, calendarTime, calendarVenue);
        requireAllNonNull(calendarBidderId, calendarPropertyId, calendarTime, calendarVenue);
        super.isAdmin = true;
    }

    @Override
    public boolean checkMeetingType() {
        return super.isAdmin;
    }
}
