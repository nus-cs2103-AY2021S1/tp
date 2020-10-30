package seedu.address.model.meeting;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

/**
 * Compares meetings based on date and sorts them chronologically.
 */
public class MeetingTimeComparator extends MeetingComparator implements Comparator<Meeting> {

    public static final String SORT_CRITERIA = "date";
    public static final String MESSAGE_INVALID_TIME = "Date is not in valid format.";

    @Override
    public int compare(Meeting meeting1, Meeting meeting2) {
        String time1 = meeting1.getDate().date;
        String time2 = meeting2.getDate().date;
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
