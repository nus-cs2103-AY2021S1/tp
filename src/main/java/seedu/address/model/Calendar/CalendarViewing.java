package seedu.address.model.Calendar;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

public class CalendarViewing extends CalendarMeeting {

    private static final String PREFIX = "v";

    CalendarViewing(CalendarBidderId calendarBidderId, CalendarPropertyId calendarPropertyId,
                    CalendarTime calendarTime, CalendarVenue calendarVenue) {
        super(calendarBidderId, calendarPropertyId, calendarTime, calendarVenue);
        requireAllNonNull(calendarBidderId, calendarPropertyId, calendarTime, calendarVenue);
        super.isViewing = true;
    }

    @Override
    public boolean checkMeetingType() {
        return super.isViewing;
    }
}
