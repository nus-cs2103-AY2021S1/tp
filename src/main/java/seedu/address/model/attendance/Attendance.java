package seedu.address.model.attendance;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Objects;

/**
 * Represents a Person's attendance in classes.
 * Guarantees: immutable
 */
public class Attendance implements Comparable<Attendance> {

    public static final String MESSAGE_CONSTRAINTS = "Accepted date format: dd/MM/yyyy (e.g. 27/03/1998)."
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
     * Creates a new {@code Attendance} using a {@code LocalDate}
     * @param date {@code LocalDate} that corresponds to input date
     */
    public Attendance(LocalDate date) {
        assert date != null;
        this.date = date;
    }

    /**
     * Creates a new {@code Attendance} using a date string that will be parsed.
     *
     * @param input date string in dd/MM/yyyy
     * @return {@code Attendance} that corresponds to the input date
     */
    public static Attendance fromDateString(String input) {
        requireNonNull(input);

        LocalDate date;
        String trimmedInput = input.trim();
        try {
            date = LocalDate.parse(trimmedInput, DATE_FORMAT);
        } catch (DateTimeParseException e) {

            checkArgument(false, MESSAGE_CONSTRAINTS);
            return null; // Never triggers as the above will throw an invalid argument exception
        }
        return new Attendance(date);
    }

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
        Attendance that = (Attendance) o;
        return date.equals(that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date);
    }

    @Override
    public int compareTo(Attendance o) {
        assert(this.date != null);
        return this.date.compareTo(o.date);
    }
}
