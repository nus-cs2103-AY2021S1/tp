package seedu.medibook.model.commonfields;

import static java.util.Objects.requireNonNull;
import static seedu.medibook.commons.util.AppUtil.checkArgument;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

/**
 * Represents a date in MediBook.
 * Guarantees: immutable; is valid as declared in {@link #isValidDate(String)}
 */
public class Date implements Comparable<Date> {
    public static final String MESSAGE_CONSTRAINTS =
            "Date should be a valid calendar date and of the format \"DD-MM-YYYY\" "
                    + "where D, M and Y represent digits of the day, month and year of the date respectively.";
    public static final String MESSAGE_NON_FUTURE = "Date should not be in the future.";
    private static final String INPUT_STRING_PATTERN = "dd-MM-uuuu";
    private static final String INPUT_STRING_PATTERN_SIMPLE = "dd-MM-yyyy";
    private static final String OUTPUT_STRING_PATTERN = "d MMM uuuu";
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern(INPUT_STRING_PATTERN)
            .withResolverStyle(ResolverStyle.STRICT);
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern(OUTPUT_STRING_PATTERN);
    public final String inputValue;
    public final String outputValue;
    private final LocalDate localDate;

    /**
     * Constructs a {@code Date} set to the given date in string form.
     * @param dateString a valid string representing the date
     * @param isNonFuture true if date object represents a date in the past or today
     */
    public Date(String dateString, boolean isNonFuture) {
        requireNonNull(dateString);
        checkArgument(isValidDate(dateString), MESSAGE_CONSTRAINTS);
        localDate = LocalDate.parse(dateString, INPUT_FORMATTER);
        if (isNonFuture) {
            checkArgument(isOnOrBeforeToday(localDate), MESSAGE_NON_FUTURE);
        }
        inputValue = dateString;
        outputValue = localDate.format(OUTPUT_FORMATTER);
    }

    /**
     * Constructs a {@code Date} set to today's date.
     */
    public Date() {
        java.util.Date todayDate = new java.util.Date();
        SimpleDateFormat formatter = new SimpleDateFormat(INPUT_STRING_PATTERN_SIMPLE);
        String todayDateInString = formatter.format(todayDate);
        localDate = LocalDate.parse(todayDateInString, INPUT_FORMATTER);
        inputValue = todayDateInString;
        outputValue = localDate.format(OUTPUT_FORMATTER);
    }

    /**
     * Returns true if the given string is a valid date.
     */
    public static boolean isValidDate(String test) {
        try {
            LocalDate.parse(test, INPUT_FORMATTER);
            return true;
        } catch (DateTimeParseException | NullPointerException e) {
            return false;
        }
    }

    /**
     * Returns true if this date is in the past or today.
     */
    private static boolean isOnOrBeforeToday(LocalDate date) {
        LocalDate todayDate = LocalDate.now();
        return date.isBefore(todayDate) || date.isEqual(todayDate);
    }

    /**
     * Returns today's date as a string.
     * @return A string representing today's date in the format dd-MM-yyyy
     */
    public static String getTodayDate() {
        java.util.Date date = new java.util.Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        return formatter.format(date);
    }

    /**
     * Returns the localDate of the Date as a LocalDate object
     * @return localDate of the Date object
     */
    public LocalDate getLocalDate() {
        return localDate;
    }

    /**
     * Returns the string that was the input by user when defining a date.
     * @return string input by user.
     */
    public String getInputString() {
        return inputValue;
    }

    @Override
    public String toString() {
        return outputValue;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Date // instanceof handles nulls
                && localDate.equals(((Date) other).localDate)); // state check
    }

    @Override
    public int hashCode() {
        return localDate.hashCode();
    }

    @Override
    public int compareTo(Date o) {
        return this.localDate.compareTo(o.localDate);
    }
}
