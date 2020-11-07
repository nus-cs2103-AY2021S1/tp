package seedu.address.model.task.comparator;

import java.util.Comparator;

import seedu.address.model.task.Date;

/**
 * Comparator to compare two dates.
 */
public class DateComparator implements Comparator<Date> {
    /**
     * Compares two dates.
     *
     * @param date first date
     * @param otherDate second date
     * @return -1 if date is earlier, 0 if both dates are the same, and 1 if
     *         otherDate is earlier
     */
    @Override
    public int compare(Date date, Date otherDate) {
        return date.getValue().compareTo(otherDate.getValue());
    }

}
