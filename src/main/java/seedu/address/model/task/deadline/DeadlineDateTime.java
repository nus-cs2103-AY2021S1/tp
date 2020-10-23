package seedu.address.model.task.deadline;

import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import seedu.address.commons.util.DateUtil;
import seedu.address.model.lesson.Time;

/**
 * Represents a Task's date and time in PlaNus task list.
 * Guarantees: immutable; is valid as declared in {@link #isValidDateTime(String)}
 */
public class DeadlineDateTime {

    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    public static final String MESSAGE_CONSTRAINTS =
            "DateTime should be in the format of dd-MM-yyyy HH:mm.";
    public static final String DAY_MESSAGE_CONSTRAINTS =
            "Day should be in the format of MON, TUE, ..., SUN or MONDAY, TUESDAY, ..., SUNDAY";
    public static final String SEARCH_CONSTRAINTS =
            "Search phrase for date should be in the format of dd-MM-yyyy or HH:mm or dd-MM-yyyy HH:mm.";
    public static final String VALIDATION_REGEX =
            "^(3[01]|[12][0-9]|0[1-9])-(1[0-2]|0[1-9])-[0-9]{4} (2[0-3]|[01][0-9]):([0-5][0-9])$";
    public static final LocalDateTime DEFAULT_DATETIME = LocalDateTime.parse("01-01-1000 00:00", FORMATTER);
    public final LocalDateTime value;
    public final boolean isNull;

    /**
     * Constructs a {@code DateTime}.
     *
     * @param dateTime A valid date and time.
     */
    public DeadlineDateTime(String dateTime) {
        if (dateTime.isEmpty() || dateTime.isBlank() || dateTime == null || dateTime.equals(DEFAULT_DATETIME)) {
            this.isNull = true;
            value = DEFAULT_DATETIME;
        } else {
            checkArgument(isValidDateTime(dateTime), MESSAGE_CONSTRAINTS);
            value = LocalDateTime.parse(dateTime, FORMATTER);
            isNull = false;
        }
    }

    public static DeadlineDateTime createNullDeadlineDateTime() {
        return new DeadlineDateTime("");
    }

    public boolean isNull() {
        return isNull;
    }

    /**
     * Returns true if a given string is a valid dateTime number.
     *
     * @param test the string value to be put to test.
     * @return true if the test string is valid and false otherwise
     */
    public static boolean isValidDateTime(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if a given string is a valid search phrase for date time.
     *
     * @param test the string value to be put to test.
     * @return true if the test string is valid and false otherwise
     */
    public static boolean isValidSearchPhrase(String test) {
        return isValidDateTime(test) || DateUtil.isValidDate(test) || Time.isValidTime(test);
    }

    @Override
    public String toString() {
        if (isNull) {
            return "";
        } else {
            return value.format(FORMATTER);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeadlineDateTime // instanceof handles nulls
                && (value.equals(((DeadlineDateTime) other).value)
                    || isNull && ((DeadlineDateTime) other).isNull())); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
