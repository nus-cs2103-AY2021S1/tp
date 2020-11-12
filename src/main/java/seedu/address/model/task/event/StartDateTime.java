package seedu.address.model.task.event;

import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import seedu.address.commons.util.DateTimeUtil;
import seedu.address.model.task.DateTime;

/**
 * Represents a Task's date and time in PlaNus task list.
 */
public class StartDateTime extends DateTime {

    /**
     * Constructs a {@code StartDateTime} using a date time string.
     *
     * @param dateTime A valid date and time string.
     */
    public StartDateTime(String dateTime) {
        super(dateTime);
    }

    /**
     * Constructs a {@code StartDateTime} using a LocalDateTime object.
     *
     * @param dateTime A LocalDateTime object.
     */
    private StartDateTime(LocalDateTime dateTime) {
        super(dateTime);
    }

    /**
     * Factory method to create EndDateTime object
     * @param date of the endDateTime
     * @param time of the endDateTime
     * @return an EndDateTime object
     */
    public static StartDateTime createStartDateTime(String date, String time) {
        checkArgument(DateTimeUtil.isValidDate(date), DateTimeUtil.DATE_TIME_CONSTRAINTS);
        checkArgument(DateTimeUtil.isValidTime(time), DateTimeUtil.DATE_TIME_CONSTRAINTS);
        String datetime = date + " " + time;
        return new StartDateTime(datetime);
    }

    /**
     * Creates a new StartDateTime object with the date changed.
     *
     * @param date the date to be changed to.
     * @return a new StartDateTime object.
     */
    public StartDateTime editStartDate(LocalDate date) {
        LocalTime time = value.toLocalTime();
        return new StartDateTime(LocalDateTime.of(date, time));
    }

    /**
     * Creates a new StartDateTime object with the start time changed.
     *
     * @param startTime a LocalTime object representing the start time to change to.
     * @return a StartDateTime object with the start time changed.
     */
    public StartDateTime editStartTime(LocalTime startTime) {
        LocalDate date = value.toLocalDate();
        return new StartDateTime(LocalDateTime.of(date, startTime));
    }

    public boolean isBeforeEndDateTime(EndDateTime endDateTime) {
        return value.isBefore(endDateTime.getValue());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StartDateTime // instanceof handles nulls
                && (value.isEqual(((StartDateTime) other).value))); // state check
    }

}
