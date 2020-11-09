package chopchop.model.attributes;

import static chopchop.commons.util.AppUtil.checkArgument;
import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import chopchop.commons.util.Result;

public class ExpiryDate implements Comparable<ExpiryDate> {
    public static final String MESSAGE_CONSTRAINTS = "Expiry date should be of the form yyyy-MM-dd";
    // TODO: Accept a wider range of date/time formats
    public static final DateTimeFormatter FORMAT = DateTimeFormatter.ISO_LOCAL_DATE;
    private final LocalDate date;

    /**
     * Constructs an {@code ExpiryDate}.
     *
     * @param date A valid string in the date format, yyyy-MM-dd
     */
    public ExpiryDate(String date) {
        requireNonNull(date);
        checkArgument(isValidDate(date), MESSAGE_CONSTRAINTS);
        this.date = LocalDate.parse(date);
    }

    /**
     * Constructs an {@code ExpiryDate}.
     *
     * @param date A valid LocalDate
     */
    public ExpiryDate(LocalDate date) {
        requireNonNull(date);
        this.date = date;
    }

    /**
     * Checks if the input date is valid.
     *
     * @param test A string of unknown date format
     * @return true if the date format is valid. Otherwise, false.
     */
    public static boolean isValidDate(String test) {
        try {
            LocalDate.parse(test, FORMAT);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    /**
     * Returns an expiry date from the given string, or an error message if it
     * was in an invalid format.
     */
    public static Result<ExpiryDate> of(String date) {
        if (isValidDate(date)) {
            return Result.of(new ExpiryDate(date));
        } else {
            return Result.error(MESSAGE_CONSTRAINTS);
        }
    }

    public LocalDate getDate() {
        return this.date;
    }

    @Override
    public String toString() {
        return this.date.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof ExpiryDate
                && this.date.equals(((ExpiryDate) other).date));
    }

    @Override
    public int compareTo(ExpiryDate other) {
        return this.date.compareTo(other.date);
    }
}
