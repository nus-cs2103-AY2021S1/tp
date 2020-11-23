package seedu.tr4cker.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.tr4cker.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Task's deadline time in Tr4cker.
 * Guarantees: immutable; is valid as declared in {@link #isValidDeadline(String)}
 */
public class Deadline {

    public static final String MESSAGE_CONSTRAINTS =
            "Invalid format to input Deadline. Please ensure the following:\n"
                    + "1. Enter a valid date and time (in 24Hr)\n"
                    + "2. Formats accepted: dd-MM-yyyy HHmm and dd-MMM-yyyy HHmm\n"
                    + "(e.g. 25-01-2021 1800 and 25-Jan-2021 1800)\n"
                    + "3. First alphabet of Month is in upper-case, (e.g. Feb instead of feb)\n"
                    + "4. Natural dates accepted: days of week (e.g. Today, Monday, Sunday)\n"
                    + "If deadline date/time is not entered, it will be set to today/2359 by default";
    public static final String MESSAGE_FUTURE_CONSTRAINT =
            "Deadline should be a time in the future";
    public static final String VALIDATION_REGEX_MM = "\\d{2}-\\d{2}-\\d{4} \\d{4}";
    public static final String VALIDATION_REGEX_MMM = "\\d{2}-[a-zA-Z]{3}-\\d{4} \\d{4}";

    public static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("dd-[MM][MMM]-yyyy HHmm");
    public static final String DEFAULT_TIME = " 2359";
    public static final String DEFAULT_DATE_TODAY = LocalDateTime.now().format(DATE_TIME_FORMAT).split(" ")[0];
    private final LocalDateTime dateTime;

    /**
     * Constructs a {@code Deadline}.
     *
     * @param deadline A valid deadline time.
     * @param isNewTask if deadline belongs to a new Task.
     */
    public Deadline(String deadline, boolean isNewTask) {
        requireNonNull(deadline);
        checkArgument(isValidDeadline(deadline), MESSAGE_CONSTRAINTS);
        if (isNewTask) {
            checkArgument(isFutureDeadline(deadline), MESSAGE_FUTURE_CONSTRAINT);
        }
        dateTime = LocalDateTime.parse(deadline, DATE_TIME_FORMAT);
    }

    /**
     * Returns the deadline of a task.
     *
     * @return Date time of task.
     */
    public LocalDateTime getDateTime() {
        return this.dateTime;
    }

    /**
     * Returns true if a given string is a valid deadline time.
     */
    public static boolean isValidDeadline(String test) {
        try {
            LocalDateTime localDateTime = LocalDateTime.parse(test, DATE_TIME_FORMAT);
            Month month = localDateTime.getMonth();
            int day = Integer.parseInt(test.substring(0, 2));
            if (month.equals(Month.APRIL) || month.equals(Month.JUNE)
                    || month.equals(Month.SEPTEMBER) || month.equals(Month.NOVEMBER)) {
                return day <= 30;
            } else if (month.equals(Month.FEBRUARY)) {
                return localDateTime.toLocalDate().isLeapYear() ? day <= 29 : day <= 28;
            }

        } catch (DateTimeParseException ex) {
            return false;
        }
        return true;
    }

    public static boolean isDeadlineWithTime(String test) {
        return test.matches(VALIDATION_REGEX_MM) || test.matches(VALIDATION_REGEX_MMM);
    }

    /**
     * Returns true if a given string is a future time.
     */
    public static boolean isFutureDeadline(String test) {
        LocalDateTime now = LocalDateTime.now();
        return LocalDateTime.parse(test, DATE_TIME_FORMAT).isAfter(now);
    }

    @Override
    public String toString() {
        return dateTime.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy HHmm"));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Deadline // instanceof handles nulls
                && dateTime.equals(((Deadline) other).dateTime)); // state check
    }

    @Override
    public int hashCode() {
        return dateTime.hashCode();
    }

    /**
     * Returns the Date only component of {@code dateTime}.
     */
    public String toDate() {
        return toString().split(" ")[0];
    }

}
