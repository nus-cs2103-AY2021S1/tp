package seedu.address.model.event;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an Event's occurrence date in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidEventDate(String)}
 */
public class EventDate {
    public static final String MESSAGE_CONSTRAINTS =
            "Event dates must be of the format DD/MM/YYYY HH:mm. e.g. 01/01/2020 15:00 for 01 Jan 2020, 3pm.";

    public static final DateTimeFormatter VALIDATION_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    /**
     * See also {@link #toString()} to get a proper string format.
     */
    public final LocalDateTime eventDate;

    /**
     * Constructs a {@code EventDate}.
     *
     * @param date A valid event date.
     */
    public EventDate(String date) {
        requireNonNull(date);
        checkArgument(isValidEventDate(date), MESSAGE_CONSTRAINTS);
        this.eventDate = LocalDateTime.parse(date, VALIDATION_FORMATTER);
    }

    /**
     * Returns true if a given string is a valid date.
     */
    public static boolean isValidEventDate(String test) {
        requireNonNull(test);
        try {
            LocalDateTime.parse(test, VALIDATION_FORMATTER);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return VALIDATION_FORMATTER.format(eventDate);
    }

    @Override
    public boolean equals(Object obj) {
        return obj == this
            || (obj instanceof EventDate
                && eventDate.equals(((EventDate) obj).eventDate));
    }

    @Override
    public int hashCode() {
        return eventDate.hashCode();
    }
}
