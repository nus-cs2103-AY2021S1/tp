package seedu.address.model.task.event;

import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import seedu.address.commons.util.DateUtil;
import seedu.address.model.task.DateTime;

/**
 * Represents a Task's date and time in PlaNus task list.
 * Guarantees: immutable; is valid as declared in {@link #isValidDateTime(String)}
 */
public class EndDateTime extends DateTime {

    /**
     * Constructs a {@code EndDateTime} using a date time string.
     *
     * @param dateTime A valid date and time string.
     */
    public EndDateTime(String dateTime) {
        super(dateTime);
    }

    /**
     * Constructs a {@code EndDateTime} using a LocalDateTime object.
     *
     * @param dateTime A LocalDateTime object.
     */
    private EndDateTime(LocalDateTime dateTime) {
        super(dateTime);
    }

    /**
     * Factory method to create EndDateTime object
     * @param date of the endDateTime
     * @param time of the endDateTime
     * @return an EndDateTime object
     */
    public static EndDateTime createEndDateTime(String date, String time) {
        checkArgument(DateUtil.isValidDate(date), DateUtil.DATE_TIME_CONSTRAINTS);
        checkArgument(DateUtil.isValidTime(time), DateUtil.DATE_TIME_CONSTRAINTS);
        String datetime = date + " " + time;
        return new EndDateTime(datetime);
    }

    public static boolean isValidDateTime(String date, String time) {
        return DateUtil.isValidDate(date) && DateUtil.isValidTime(time);
    }

    /**
     * Creates a new EndDateTime object with the date changed.
     *
     * @param date the date to be changed to.
     * @return a new EndDateTime object.
     */
    public EndDateTime editEndDate(LocalDate date) {
        LocalTime time = value.toLocalTime();
        return new EndDateTime(LocalDateTime.of(date, time));
    }

    /**
     * Creates a new EndDateTime object with the start time changed.
     *
     * @param endTime a LocalTime object representing the end time to change to.
     * @return a EndDateTime object with the end time changed.
     */
    public EndDateTime editEndTime(LocalTime endTime) {
        LocalDate date = value.toLocalDate();
        return new EndDateTime(LocalDateTime.of(date, endTime));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EndDateTime // instanceof handles nulls
                && (value.equals(((EndDateTime) other).value))); // state check
    }
}
