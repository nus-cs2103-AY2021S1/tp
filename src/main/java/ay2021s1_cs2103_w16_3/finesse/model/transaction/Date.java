package ay2021s1_cs2103_w16_3.finesse.model.transaction;

import static ay2021s1_cs2103_w16_3.finesse.commons.util.AppUtil.checkArgument;
import static java.util.Objects.requireNonNull;

import java.time.Clock;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

/**
 * Represents a Transaction's date in the finance tracker.
 * Guarantees: immutable; is valid as declared in {@link #isValidDate(String, Clock)}
 */
public class Date implements Comparable<Date> {

    public static final String EPOCH_STRING = "01/01/1970";
    public static final String MESSAGE_CONSTRAINTS = "Dates should be a valid calendar date of the format dd/mm/yyyy, "
            + "cannot be earlier than " + EPOCH_STRING + ", and cannot be later than the current date";
    public static final DateTimeFormatter VALIDATION_FORMAT = DateTimeFormatter.ofPattern("dd/MM/uuuu")
            .withResolverStyle(ResolverStyle.STRICT);
    public static final String MESSAGE_EMPTY_DATE = "Dates cannot be empty.";
    private static final LocalDate EPOCH = LocalDate.parse(EPOCH_STRING, VALIDATION_FORMAT);

    private final LocalDate value;

    /**
     * Constructs an {@code Date}.
     *
     * @param date A valid date.
     */
    public Date(String date) {
        requireNonNull(date);
        checkArgument(isValidDate(date), MESSAGE_CONSTRAINTS);
        value = LocalDate.parse(date, VALIDATION_FORMAT);
    }

    /**
     * Returns if a given string is a valid date.
     * Uses {@link #isValidDate(String, Clock)} with the current system time.
     * A valid date must be in dd/mm/yyyy format representing a correct date (all parts within range),
     * and additionally can only be at or before the current date.
     */
    public static boolean isValidDate(String test) {
        return isValidDate(test, Clock.systemDefaultZone());
    }

    /**
     * Returns if a given string is a valid date. Allows for a custom Clock to be used for testing purposes.
     * A valid date must be in dd/mm/yyyy format representing a correct date (all parts within range),
     * and additionally can only be at or before the current date.
     */
    public static boolean isValidDate(String test, Clock currentTime) {
        try {
            LocalDate value = LocalDate.parse(test, VALIDATION_FORMAT);
            LocalDate today = LocalDate.now(currentTime);
            return (value.isBefore(today) || value.isEqual(today))
                    && (value.isAfter(EPOCH) || value.isEqual(EPOCH));
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public LocalDate getDate() {
        return value;
    }

    public static Date getCurrentDate() {
        LocalDate currentDate = LocalDate.now();
        return new Date(VALIDATION_FORMAT.format(currentDate));
    }

    /**
     * Returns a String representation of this Date that can be used to construct an identical Date.
     * @return A String representation of this Date.
     */
    @Override
    public String toString() {
        return VALIDATION_FORMAT.format(value);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Date // instanceof handles nulls
                && value.equals(((Date) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public int compareTo(Date date) {
        return value.compareTo(date.value);
    }
}
