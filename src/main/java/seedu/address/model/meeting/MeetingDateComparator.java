package seedu.address.model.meeting;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

/**
 * Compares meetings based on meetingDate and sorts them chronologically.
 */
public class MeetingDateComparator extends MeetingComparator implements Comparator<Meeting> {

    public static final String SORT_CRITERIA = "meetingDate";
    public static final String MESSAGE_INVALID_TIME = "MeetingDate is not in valid format.";

    @Override
    public int compare(Meeting meeting1, Meeting meeting2) {
        String date1 = meeting1.getMeetingDate().date;
        String date2 = meeting2.getMeetingDate().date;
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

        try {
            Date parsedDate1 = formatter.parse(date1);
            Date parsedDate2 = formatter.parse(date2);
            if (parsedDate1.before(parsedDate2)) {
                return -1;
            } else if (parsedDate2.before(parsedDate1)) {
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
