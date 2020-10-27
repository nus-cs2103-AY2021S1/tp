package seedu.address.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Represents a Date, with which the user can set a time range to filter tasks.
 * Guarantees: immutable; is valid as declared in {@link #isValidDate(String)}
 */
public class Date {
    public static final String MESSAGE_CONSTRAINTS =
        "Date should only be in the format of dd-MM-yyyy";
    public static final String VALIDATION_REGEX = "(\\d{2}-\\d{2}-\\d{4})";
    private final LocalDate date;

    /**
     * Constructs a {@code Date}.
     *
     * @param date A valid date string.
     */
    public Date(String date) {
        requireNonNull(date);
        checkArgument(isValidDate(date), MESSAGE_CONSTRAINTS);
        this.date = convertIntoDate(date);
    }

    /**
     * Converts a valid date string into LocalDateTime.
     * @param dateString  the date string to be converted
     * @return  an LocalDate object that corresponds to the date string
     */
    public LocalDate convertIntoDate(String dateString) {
        assert isValidDate(dateString);
        String[] date = dateString.split("-");
        int day = Integer.parseInt(date[0]);
        int month = Integer.parseInt(date[1]);
        int year = Integer.parseInt(date[2]);
        return LocalDate.of(year, month, day);
    }

    /**
     * Return true if a given string is a valid date.
     */
    public static boolean isValidDate(String date) {
        if (!date.matches(VALIDATION_REGEX)) {
            return false;
        }
        String[] strings = date.split("-");
        int day = Integer.parseInt(strings[0]);
        int month = Integer.parseInt(strings[1]);
        int year = Integer.parseInt(strings[2]);
        boolean isValidDay = day <= 31 && day >= 1;
        boolean isValidMonth = month <= 12 && month >= 1;
        //year is always valid because it matches the regex as 4 digits of integers (1000 - 9999)
        if (day == 29 && month == 2) {
            if (year % 400 == 0) {
                return true;
            } else if (year % 100 == 0) {
                return false;
            } else {
                return year % 4 == 0;
            }
        } else if ((day == 30 || day == 31) && month == 2) {
            return false;
        } else if (day == 31 && (month == 4 || month == 6
            || month == 9 || month == 11)) {
            return false;
        } else {
            return isValidDay && isValidMonth;
        }
    }
    public boolean isBefore(Date other) {
        return this.date.isBefore(other.date);
    }
    public LocalDate getDate() {
        return this.date;
    }

    public LocalDateTime atStartOfDay() {
        return this.date.atStartOfDay();
    }
    @Override
    public String toString() {
        return this.date.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof Date// instanceof handles nulls
            && date.equals(((Date) other).date)); // state check
    }

    @Override
    public int hashCode() {
        return date.hashCode();
    }

}
