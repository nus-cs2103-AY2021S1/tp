package seedu.address.model.task.event;

import static seedu.address.commons.util.AppUtil.checkArgument;

import seedu.address.commons.util.DateUtil;
import seedu.address.model.lesson.Time;
import seedu.address.model.task.DateTime;

/**
 * Represents a Task's date and time in PlaNus task list.
 */
public class StartDateTime extends DateTime {

    public final boolean isNull;

    /**
     * Constructs a {@code DateTime}.
     *
     * @param dateTime A valid date and time.
     */
    public StartDateTime(String dateTime) {
        super(dateTime);
        if (dateTime.isEmpty() || dateTime.isBlank() || dateTime == null) {
            this.isNull = true;
        } else {
            isNull = false;
        }
    }

    /**
     * Factory method to create EndDateTime object
     * @param date of the endDateTime
     * @param time of the endDateTime
     * @return an EndDateTime object
     */
    public static StartDateTime createStartDateTime(String date, String time) {
        checkArgument(DateUtil.isValidDate(date), DateUtil.MESSAGE_CONSTRAINTS);
        checkArgument(DateUtil.isValidTime(time), DateUtil.MESSAGE_CONSTRAINTS);
        String datetime = date + " " + time;
        return new StartDateTime(datetime);
    }

    public boolean isNull() {
        return isNull;
    }

    /**
     * Returns true if a given string is a valid dateTime number.
     *
     * @param date the string value of date to be put to test.
     * @param time the string value of time to be put to test.
     * @return true if the test string is valid and false otherwise
     */
    public static boolean isValidDateTime(String date, String time) {
        return DateUtil.isValidDate(date) && DateUtil.isValidTime(time);
    }

    @Override
    public String toString() {
        if (isNull) {
            return "";
        } else {
            return value.format(DateUtil.DATETIME_FORMATTER);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StartDateTime // instanceof handles nulls
                && (value.equals(((StartDateTime) other).value)
                    || isNull && ((StartDateTime) other).isNull())); // state check
    }

}
