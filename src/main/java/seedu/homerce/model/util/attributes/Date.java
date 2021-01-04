package seedu.homerce.model.util.attributes;

import static java.util.Objects.requireNonNull;
import static seedu.homerce.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Date {

    public static final String MESSAGE_CONSTRAINTS = "Date entered must be in the format of <dd-MM-yyyy>"
            + ",and must be a valid date";

    private static final DateTimeFormatter FORMAT_INPUT = DateTimeFormatter.ofPattern("d-M-yyyy");
    private static final DateTimeFormatter FORMAT_OUTPUT = DateTimeFormatter.ofPattern("dd MMM yyyy");

    protected final LocalDate date;

    /**
     * Represents the date stored for any model object.
     *
     * @param dateString
     */
    public Date(String dateString) {
        requireNonNull(dateString);
        checkArgument(isValidDate(dateString), MESSAGE_CONSTRAINTS);
        this.date = LocalDate.parse(dateString, FORMAT_INPUT);
    }

    /**
     * Returns true if a given string is a valid Date format.
     */
    public static boolean isValidDate(String test) {
        try {
            LocalDate.parse(test, FORMAT_INPUT);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }


    public Month getMonth() {
        return date.getMonth();
    }

    public int getYear() {
        return date.getYear();
    }

    public int getDayOfMonth() {
        return date.getDayOfMonth();
    }

    public LocalDate getLocalDate() {
        return date;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof Date // instanceof handles nulls
            && date.equals(((Date) other).date)); // state check
    }

    @Override
    public int hashCode() {
        return date.hashCode();
    }

    @Override
    public String toString() {
        return date.format(FORMAT_INPUT);
    }

    public String toUiString() {
        return date.format(FORMAT_OUTPUT);
    }
}
