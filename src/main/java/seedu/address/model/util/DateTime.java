package seedu.address.model.util;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateTime implements Comparable<DateTime> {
    public static final String DATE_REGEX = "(([0-2]\\d)|(3[0-1]))-((0[1-9])|(1[0-2]))-(\\d{4})";
    public static final String EARLIEST_DATE_STRING = "01-01-2019 00:00";
    public static final String CONSTRAINTS =
            "%s should be in dd-MM-yyyy HH:mm format and must not be earlier than the year 2019.";

    public static final String MESSAGE_CONSTRAINTS = String.format(CONSTRAINTS, "Date");

    public static final String VALIDATION_REGEX = String.format("%s(\\s(%s))",
            DATE_REGEX, Time.TIME_REGEX);

    public static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    public final LocalDateTime value;

    /**
     * Constructs a {@code date}.
     *
     * @param date A valid date.
     */
    public DateTime(String date) {
        requireNonNull(date);

        // Check for constraints
        checkArgument(isValidDateTime(date), MESSAGE_CONSTRAINTS);

        //Parse value
        this.value = LocalDateTime.parse(date, DATE_TIME_FORMATTER);
    }

    /**
     * Returns the LocalDateTime value object.
     *
     * @return the value of DateTime.
     */
    public LocalDateTime getLocalDateTime() {
        return this.value;
    }

    /**
     * Returns true if a given string is a valid DateTime.
     *
     * @param test string to test.
     * @return result of match.
     */
    public static boolean isValidDateTime(String test) {
        return isValidDateTimePattern(test) && isValidDateTimeRange(test);
    }


    /**
     * Returns true if a given string is a valid DateTime.
     *
     * @param test string to test.
     * @return result of match.
     */
    public static boolean isValidDateTimePattern(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if a given string is a valid DateTime.
     *
     * @param test string to test.
     * @return result of match.
     */
    public static boolean isValidDateTimeRange(String test) {
        try {
            DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm");
            df.setLenient(false);
            Date date = df.parse(test);
            Date earliestDate = df.parse(EARLIEST_DATE_STRING);
            return !date.before(earliestDate);

        } catch (ParseException e) {
            return false;
        }
    }

    @Override
    public int compareTo(DateTime o) {
        return this.value.compareTo(o.value);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DateTime) // instanceof handles nulls
                && ((this.value.equals(((DateTime) other).value)));
    }

    @Override
    public String toString() {
        return this.value.format(DATE_TIME_FORMATTER);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
