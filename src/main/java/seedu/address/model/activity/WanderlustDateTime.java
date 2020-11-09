package seedu.address.model.activity;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import seedu.address.model.commons.WanderlustDate;


/**
 * Represents a Activity's date time in Wanderlust.
 * Guarantees: immutable; is valid as declared in {@link #isValidWanderlustDateTime(String)}
 */
public class WanderlustDateTime {

    public static final String FORMAT = "YYYY-MM-DD HH:mm";
    public static final String MESSAGE_CONSTRAINTS = "Date Time should be of the format " + FORMAT;

    /**
     * Date time must be in the format YYYY-MM-DD HH:mm.
     */
    public static final String VALIDATION_REGEX = "\\d{4}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])"
           + "( [0-1][0-9]|2[0-4]):([0-5][0-9])";
    public static final DateFormat VALID_DATE_STRING = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    private final String dateTime;
    private final LocalDateTime value;

    /**
     * Constructs a {@code WanderlustDateTime}.
     *
     * @param dateTime A valid date and time.
     */
    public WanderlustDateTime(String dateTime) {
        requireNonNull(dateTime);
        checkArgument(isValidWanderlustDateTime(dateTime), MESSAGE_CONSTRAINTS);
        this.dateTime = dateTime;
        value = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

    }

    /**
     * Returns if a given string is a valid activity date time.
     */
    public static boolean isValidWanderlustDateTime(String test) {
        try {
            VALID_DATE_STRING.parse(test);
            LocalDateTime.parse(test, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            return true;
        } catch (ParseException | DateTimeParseException e) {
            return false;
        }
    }

    public LocalDateTime getValue() {
        return value;
    }

    public String getDateTime() {
        return dateTime;
    }

    /**
     * Checks if the current WanderlustDateTime comes before the given WanderlustDate chronologically.
     * @return true if current WanderlustDateTime is before the given WanderlustDate.
     */
    public boolean isBefore(WanderlustDate toCompare) {
        return value.toLocalDate().isBefore(toCompare.getValue());
    }

    /**
     * Checks if the current WanderlustDateTime comes after the given WanderlustDate chronologically.
     * @return true if current WanderlustDateTime is after the given WanderlustDate.
     */
    public boolean isAfter(WanderlustDate toCompare) {
        return value.toLocalDate().isAfter(toCompare.getValue());
    }

    @Override
    public String toString() {
        return value.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof WanderlustDateTime // instanceof handles nulls
                && dateTime.equals(((WanderlustDateTime) other).dateTime)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
