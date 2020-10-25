package seedu.address.model.project;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;

/**
 * Represents a Project's deadline in the main catalogue.
 * Guarantees: immutable; is valid as declared in {@link #isValidDeadline(String)}
 */
public class Deadline implements Comparable<Deadline> {
    public static final String MESSAGE_CONSTRAINTS =
            "Deadline should only be in the format of dd-MM-yyyy HH:mm:ss, "
                    + "and the time should only be in the format of 24-Hour";
    public static final String VALIDATION_REGEX = "(\\d{2}-\\d{2}-\\d{4} \\d{2}:\\d{2}:\\d{2})";
    private final LocalDateTime dateTime;

    /**
     * Constructs a {@code Deadline}.
     *
     * @param deadline A valid deadline string.
     */
    public Deadline(String deadline) {
        requireNonNull(deadline);
        checkArgument(isValidDeadline(deadline), MESSAGE_CONSTRAINTS);
        dateTime = convertIntoDateTime(deadline);
    }

    /**
     * Converts a valid deadline string into LocalDateTime.
     * @param deadline  the deadline string to be converted
     * @return  an LocalDateTime object that corresponds to the deadline string
     */
    public LocalDateTime convertIntoDateTime(String deadline) {
        assert isValidDeadline(deadline);
        String[] dateTime = deadline.split(" ");
        String dateString = dateTime[0];
        String timeString = dateTime[1];
        String[] date = dateString.split("-");
        String[] time = timeString.split("-");
        int day = Integer.parseInt(date[0]);
        int month = Integer.parseInt(date[1]);
        int year = Integer.parseInt(date[2]);
        int hour = Integer.parseInt(time[0]);
        int minute = Integer.parseInt(time[1]);
        int second = Integer.parseInt(time[2]);
        return LocalDateTime.of(year, month, day, hour, minute, second);
    }

    /**
     * Returns true if a given string is a valid deadline.
     */
    public static boolean isValidDeadline(String test) {
        boolean isValidDate = false;
        boolean isValidTime = false;
        if (test.matches(VALIDATION_REGEX)) {
            String[] dateTime = test.split(" ");
            isValidDate = isValidDate(dateTime[0]);
            isValidTime = isValidTime(dateTime[1]);
        }
        return isValidDate && isValidTime;
    }

    /**
     * Return true if a given string is a valid date.
     */
    public static boolean isValidDate(String date) {
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

    /**
     * Return true if a given string is a valid time.
     */
    private static boolean isValidTime(String time) {
        String[] strings = time.split(":");
        String hour = strings[0];
        String minute = strings[1];
        String second = strings[2];
        boolean isValidHour = Integer.parseInt(hour) <= 23 && Integer.parseInt(hour) >= 0;
        boolean isValidMinute = Integer.parseInt(minute) <= 59 && Integer.parseInt(minute) >= 0;
        boolean isValidSecond = Integer.parseInt(second) <= 59 && Integer.parseInt(second) >= 0;
        return isValidHour && isValidMinute && isValidSecond;
    }
    public LocalDateTime getDateTime() {
        return this.dateTime;
    }

    @Override
    public String toString() {
        return dateTime.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Deadline // instanceof handles nulls
                && dateTime.equals(((Deadline) other).dateTime)); // state check
    }

    @Override
    public int hashCode() {
        return dateTime.hashCode();
    }

    @Override
    public int compareTo(Deadline deadline) {
        return this.dateTime.compareTo(deadline.getDateTime());
    }
}
