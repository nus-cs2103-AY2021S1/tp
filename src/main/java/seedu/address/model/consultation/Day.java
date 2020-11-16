package seedu.address.model.consultation;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Objects;


/**
 * Represents a Consultation's date in classes.
 */
public class Day implements Comparable<Day> {
    public static final String MESSAGE_CONSTRAINTS = "Please check if the date provided is valid."
            + "\n\n"
            + "Accepted date format: dd/MM/yyyy (e.g. 27/03/1998)."
            + "\n"
            + "Day should be in the range 1-31"
            + "\n"
            + "Month should be 01-12"
            + "\n"
            + "Year should be a 4 digit numeric value, with the exception of 0000";
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/uuuu")
            .withResolverStyle(ResolverStyle.STRICT);

    public final LocalDate date;

    /**
     * Users should only use fromDateString to safely generate the day of consultation
     * @param date Date.
     */
    private Day(LocalDate date) {
        assert date != null;
        this.date = date;
    }

    /**
     * Creates a new {@code Day} using a date string that will be parsed.
     *
     * @param input date string in dd/MM/yyyy.
     * @return {@code Day} that corresponds to the input date.
     */
    public static final Day fromDateString(String input) {
        requireNonNull(input);

        LocalDate date;
        String trimmedInput = input.trim();
        try {
            date = LocalDate.parse(trimmedInput, DATE_FORMAT);
        } catch (DateTimeParseException e) {

            checkArgument(false, MESSAGE_CONSTRAINTS);
            return null; // Never triggers as the above will throw an invalid argument exception
        }
        return new Day(date);
    }

    /**
     * Returns true if a given string is a valid name.
     *
     * @param test Input date.
     * @return Presence of valid day.
     */
    public static boolean isValidDay(String test) {
        assert(test != null);
        String trimmedTest = test.trim();
        try {
            LocalDate date = LocalDate.parse(trimmedTest, DATE_FORMAT);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    /**
     * Returns string representation of the date.
     *
     * @return date in string.
     */
    @Override
    public String toString() {
        return DATE_FORMAT.format(date);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Day that = (Day) o;
        return date.equals(that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date);
    }

    @Override
    public int compareTo(Day o) {
        assert(this.date != null);
        return this.date.compareTo(o.date);
    }
}
