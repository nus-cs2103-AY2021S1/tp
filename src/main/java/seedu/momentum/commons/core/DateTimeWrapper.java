//@@author boundtotheearth

package seedu.momentum.commons.core;

import static java.util.Objects.requireNonNull;
import static seedu.momentum.commons.util.AppUtil.checkArgument;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

import seedu.momentum.commons.util.DateTimeUtil;

/**
 * Represents a date and time in the project book.
 * Guarantees: immutable; is valid as declared in {@link #isValid(String)}
 */
public class DateTimeWrapper implements InstanceWrapper<LocalDateTime>, Comparable<DateTimeWrapper> {

    public static final String MESSAGE_CONSTRAINTS =
            "Dates and Times should be in ISO8601 format. e.g. 2020-09-23T16:55:12";

    public static final DateTimeWrapper MAX = new DateTimeWrapper(LocalDateTime.MAX);

    private final LocalDateTime dateTime;

    /**
     * Constructs a {@code DateTimeWrapper}.
     *
     * @param dateTime A valid DateTimeWrapper.
     */
    public DateTimeWrapper(String dateTime) {
        requireNonNull(dateTime);
        checkArgument(isValid(dateTime), MESSAGE_CONSTRAINTS);
        this.dateTime = LocalDateTime.parse(dateTime, DateTimeUtil.FORMAT_DATA);
    }

    public DateTimeWrapper(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * Returns true if a given string is a valid dateTime.
     */
    public static boolean isValid(String test) {
        try {
            DateTimeUtil.FORMAT_DATA.parse(test);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    /**
     * Calculates the amount of time between two instances of DateTimeWrapper, in the provided units.
     *
     * @param time1 The earlier instance of DateTimeWrapper.
     * @param time2 The later instance of DateTimeWrapper.
     * @param units The units to the DateTimeWrapper.
     * @return The amount of time between the 2 instances, in the units provided.
     */
    public static long getTimeBetween(DateTimeWrapper time1, DateTimeWrapper time2, ChronoUnit units) {
        return units.between(time1.get(), time2.get());
    }

    //@@author khoodehui

    /**
     * Returns a new DateTimeWrapper that is after this DateTimeWrapper by a specified amount
     *
     * @param amount Amount to increase by.
     * @param unit   Unit to increase with.
     * @return The new DateTimeWrapper.
     */
    public DateTimeWrapper plus(long amount, ChronoUnit unit) {
        return new DateTimeWrapper(dateTime.plus(amount, unit));
    }
    //@@author

    /**
     * Returns a new DateTimeWrapper that is before this DateTimeWrapper by a specified amount.
     *
     * @param amount Amount to decrease by.
     * @param unit   Unit to decrease with.
     * @return The new DateTimeWrapper.
     */
    public DateTimeWrapper minus(long amount, ChronoUnit unit) {
        return new DateTimeWrapper(dateTime.minus(amount, unit));
    }

    /**
     * Adjusts the DateTimeWrapper to the start of the timeframe as specified in a {@code StatisticTimeframe}.
     *
     * @param timeframe Timeframe to adjust to.
     * @return The new DateTimeWrapper.
     */
    public DateTimeWrapper adjustToStartOfTimeframe(StatisticTimeframe timeframe) {
        ChronoUnit timeframeUnit = timeframe.toChronoUnit();

        if (timeframeUnit == ChronoUnit.DAYS) {
            return new DateTimeWrapper(dateTime.truncatedTo(ChronoUnit.DAYS));
        } else if (timeframeUnit == ChronoUnit.WEEKS) {
            return new DateTimeWrapper(dateTime.with(DayOfWeek.MONDAY).truncatedTo(ChronoUnit.DAYS));
        }
        return new DateTimeWrapper(dateTime.withDayOfMonth(1).truncatedTo(ChronoUnit.DAYS));
    }

    /**
     * Checks if an instance in another DateTimeWrapper is before this instance.
     *
     * @param otherTime The DateTimeWrapper to check.
     * @return True if the other time is before this instance, false otherwise.
     */
    public boolean isBefore(DateTimeWrapper otherTime) {
        return dateTime.isBefore(otherTime.get());
    }

    @Override
    public LocalDateTime get() {
        return dateTime;
    }
    //@@author claracheong4

    @Override
    public String getFormatted() {
        return dateTime.format(DateTimeUtil.FORMAT_DATE_TIME_MEDIUM);
    }

    /**
     * Checks if an instance in another DateTimeWrapper is after this instance.
     *
     * @param otherTime The DateTimeWrapper to check.
     * @return True if the other time is after this instance, false otherwise.
     */
    public boolean isAfter(DateTimeWrapper otherTime) {
        return dateTime.isAfter(otherTime.get());
    }

    /**
     * Returns only the date portion of the {@code DateTimeWrapper}.
     *
     * @return A {@code DateWrapper} representing the date of this instance.
     */
    public DateWrapper getDateWrapper() {
        return new DateWrapper(dateTime.toLocalDate());
    }

    /**
     * Returns only the time portion of the {@code DateTimeWrapper}.
     *
     * @return A {@code TimeWrapper} representing the time of this instance.
     */
    public TimeWrapper getTimeWrapper() {
        return new TimeWrapper(dateTime.toLocalTime());
    }

    @Override
    public int hashCode() {
        return dateTime.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DateTimeWrapper // instanceof handles nulls
                && dateTime.equals(((DateTimeWrapper) other).get())); // state check
    }

    @Override
    public String toString() {
        return this.dateTime.toString();
    }

    //@@author kkangs0226
    @Override
    public int compareTo(DateTimeWrapper other) {
        LocalDateTime thisLocalDateTime = this.get();
        LocalDateTime otherLocalDateTime = other.get();
        if (thisLocalDateTime.isBefore(otherLocalDateTime)) {
            return -1;
        } else if (thisLocalDateTime.isAfter(otherLocalDateTime)) {
            return 1;
        }
        return 0;
    }

}
