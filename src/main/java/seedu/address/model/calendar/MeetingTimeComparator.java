package seedu.address.model.calendar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

public class MeetingTimeComparator extends MeetingComparator implements Comparator<CalendarMeeting> {

    public static final String SORT_CRITERIA = "time";
    public static final String MESSAGE_INVALID_TIME = "Time is not in valid format.";

    @Override
    public int compare(CalendarMeeting meeting1, CalendarMeeting meeting2) {
        String time1 = meeting1.getCalendarTime().time;
        String time2 = meeting2.getCalendarTime().time;
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

        try {
            Date date1 = formatter.parse(time1);
            Date date2 = formatter.parse(time2);
            if (date1.before(date2)) {
                return -1;
            } else if (date2.before(date1)) {
                return 1;
            } else {
                return 0;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
