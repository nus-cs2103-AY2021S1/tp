package seedu.address.model.calendar;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

public class CalendarPaperwork extends CalendarMeeting {

    private static final String PREFIX = "p";

    CalendarPaperwork (CalendarBidderId calendarBidderId, CalendarPropertyId calendarPropertyId,
                   CalendarTime calendarTime, CalendarVenue calendarVenue) {
        super(calendarBidderId, calendarPropertyId, calendarTime, calendarVenue);
        requireAllNonNull(calendarBidderId, calendarPropertyId, calendarTime, calendarVenue);
        super.isPaperWork = true;
    }

    @Override
    public boolean checkMeetingType() {
        return super.isPaperWork;
    }
}
