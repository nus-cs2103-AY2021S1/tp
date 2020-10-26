package seedu.address.model.exercise;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import seedu.address.logic.parser.exceptions.ParseException;

public class Date {
    public static final String MESSAGE_CONSTRAINTS =
            "Dates should be in the format of DD-MM-YYYY";
    public final String value;
    public java.util.Date actualDate;

    /**
     * Constructs a {@code Date}.
     *
     * @param date A valid input.
     */
    public Date(String date) {
        requireNonNull(date);
        checkArgument(isValidDate(date), MESSAGE_CONSTRAINTS);
        value = date;
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            actualDate = formatter.parse(value);
        } catch (java.text.ParseException e) {
            actualDate = null;
        }
    }

    /**
     * Returns true if a given string is a valid date.
     */
    public static boolean isValidDate(String test) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        try {
            timeFormatter.parse(test);

        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    public boolean isBefore(Date other) {
        return actualDate.before(other.actualDate);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.address.model.exercise.Date // instanceof handles nulls
                && value.equals(((Date) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
