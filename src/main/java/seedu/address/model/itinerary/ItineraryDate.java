package seedu.address.model.itinerary;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.time.temporal.ChronoUnit;

public class ItineraryDate {

    public static final String MESSAGE_CONSTRAINTS = "Date must be a valid date in the format dd-mm-yyyy"
            + " and should not be blank.";
    private static final DateTimeFormatter DTF = new DateTimeFormatterBuilder()
            .appendPattern("dd-MM-uuuu")
            .toFormatter()
            .withResolverStyle(ResolverStyle.STRICT);

    public final String value;

    /**
     * Constructs a {@code ItineraryDate}.
     *
     * @param date A valid date.
     */
    public ItineraryDate(String date) {
        requireNonNull(date);
        checkArgument(isValidDate(date), MESSAGE_CONSTRAINTS);
        value = date;
    }

    /**
     * Returns the itinerary date in {@code LocalDate} format.
     */
    public LocalDate getLocalDate() {
        return LocalDate.parse(value, DTF);
    }

    /**
     * Returns if a given string is a valid itinerary date.
     */
    public static boolean isValidDate(String test) {
        try {
            LocalDate.parse(test, DTF);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Returns if this itinerary date is before the given itinerary date.
     */
    public boolean isBefore(ItineraryDate otherDate) {
        return getLocalDate().isBefore(otherDate.getLocalDate());
    }

    /**
     * Returns if this itinerary date is the same as the given itinerary date.
     */
    public boolean isEqual(ItineraryDate otherDate) {
        return getLocalDate().isEqual(otherDate.getLocalDate());
    }

    /**
     * Returns if this itinerary date is after the given itinerary date.
     */
    public boolean isAfter(ItineraryDate otherDate) {
        return getLocalDate().isAfter(otherDate.getLocalDate());
    }

    /**
     * Returns the number of days between two itinerary dates.
     */
    public static int daysBetween(ItineraryDate startDate, ItineraryDate endDate) {
        return (int) ChronoUnit.DAYS.between(startDate.getLocalDate(), endDate.getLocalDate()) + 1;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ItineraryDate// instanceof handles nulls
                && value.equals(((ItineraryDate) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
