package seedu.tr4cker.model.countdown;

import static java.time.temporal.ChronoUnit.DAYS;
import static java.util.Objects.requireNonNull;
import static seedu.tr4cker.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents the date of an event in TR4CKER.
 * Guarantees: immutable; is valid as declared in {@link #isValidDate(String)}
 */
public class EventDate {

    public static final String MESSAGE_CONSTRAINTS =
            "Invalid format to input date. Please ensure the following:\n"
                    + "1. Enter a valid date\n"
                    + "2. Formats accepted: dd-MM-yyyy dd-MMM-yyyy\n"
                    + "(e.g. 25-01-2021 25-Jan-2021)\n"
                    + "3. First alphabet of Month is in upper-case, (e.g. Feb instead of feb)\n"
                    + "4. Date should be in the future\n";
    public static final String MESSAGE_FUTURE_CONSTRAINT =
            "Date should be in the future";
    public static final String VALIDATION_REGEX_MM = "\\d{2}-\\d{2}-\\d{4}";
    public static final String VALIDATION_REGEX_MMM = "\\d{2}-[a-zA-Z]{3}-\\d{4}";

    public static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("dd-[MM][MMM]-yyyy");
    public static final String DEFAULT_DATE_TODAY = LocalDate.now().format(DATE_TIME_FORMAT).split(" ")[0];
    private final LocalDate date;

    /**
     * Constructs a {@code EventDate}.
     *
     * @param date A valid date.
     * @param isNewEvent If event is a new event added from command line.
     */
    public EventDate(String date, boolean isNewEvent) {
        requireNonNull(date);
        checkArgument(isValidDate(date), MESSAGE_CONSTRAINTS);
        if (isNewEvent) {
            checkArgument(isFutureDate(date), MESSAGE_FUTURE_CONSTRAINT);
            // would mean that you cant add events that have passed.
        }
        this.date = LocalDate.parse(date, DATE_TIME_FORMAT);
    }

    /**
     * Returns the date of a event.
     *
     * @return Date of event.
     */
    public LocalDate getDate() {
        return this.date;
    }

    /**
     * Returns true if a given string is a valid date.
     */
    public static boolean isValidDate(String test) {
        try {
            LocalDate.parse(test, DATE_TIME_FORMAT);
        } catch (DateTimeParseException ex) {
            return false;
        }
        return isDate(test);
    }

    /**
     * Returns true if given string is a valid date based on month of year and whether it is a leap year.
     */
    public static boolean isDate(String test) {
        LocalDate localDate = LocalDate.parse(test, DATE_TIME_FORMAT);
        Month month = localDate.getMonth();
        int dayOfMonth = Integer.parseInt(test.substring(0, 2));
        if (month == Month.APRIL || month == Month.JUNE || month == Month.SEPTEMBER || month == Month.NOVEMBER) {
            return dayOfMonth <= 30;
        } else if (month == Month.FEBRUARY) {
            return localDate.isLeapYear() ? dayOfMonth <= 29 : dayOfMonth <= 28;
        } else {
            return dayOfMonth <= 31;
        }
    }

    /**
     * Returns true if a given string is a future date.
     */
    public static boolean isFutureDate(String test) {
        LocalDate now = LocalDate.now();
        return LocalDate.parse(test, DATE_TIME_FORMAT).isAfter(now);
    }

    /**
     * Returns the number of days remaining to the event as an int.
     * Returns -1 if event has passed.
     */
    public int getDaysTill() {
        int daysUntil = (int) getCurrentDate().until(date, DAYS);
        if (daysUntil < 0) {
            return -1;
        }
        return daysUntil;
    }


    /**
     * Returns the current LocalDate, for use in {@code getDaysTill}.
     */
    public LocalDate getCurrentDate() {
        return LocalDate.now();
    }

    @Override
    public String toString() {
        return date.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy"));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.tr4cker.model.countdown.EventDate // instanceof handles nulls
                && date.equals(((seedu.tr4cker.model.countdown.EventDate) other).date)); // state check
    }

    @Override
    public int hashCode() {
        return date.hashCode();
    }

}
