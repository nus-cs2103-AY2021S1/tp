package seedu.address.model.task.deadline;

import java.time.LocalDateTime;

import seedu.address.commons.util.DateUtil;
import seedu.address.model.lesson.Time;

import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Task's date and time in PlaNus task list.
 * Guarantees: immutable; is valid as declared in {@link #isValidDoneDateTime(String)}
 */
public class DoneDateTime {

    public final LocalDateTime value;
    public final boolean isNull;

    /**
     * Constructs a {@code DateTime}.
     *
     * @param dateTime A valid date and time.
     */
    public DoneDateTime(String dateTime) {
        if (dateTime.isEmpty() || dateTime.isBlank() || dateTime == null) {
            this.isNull = true;
            this.value = DateUtil.DEFAULT_DATETIME;
        } else {
            checkArgument(isValidDoneDateTime(dateTime), DateUtil.MESSAGE_CONSTRAINTS);
            this.value = LocalDateTime.parse(dateTime, DateUtil.DATETIME_FORMATTER);
            this.isNull = false;
        }
    }

    private DoneDateTime(LocalDateTime value) {
        this.value = value;
        this.isNull = false;
    }

    /**
     * Constructs a DoneDateTime object of default value when the task is not done.
     */
    public static DoneDateTime createNullDoneDateTime() {
        return new DoneDateTime("");
    }

    public static DoneDateTime createDoneNow() {
        return new DoneDateTime(LocalDateTime.now());
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
    public static boolean isValidDoneDateTime(String test) {
        return DateUtil.isValidDateTime(test);
    }

    /**
     * Returns true if a given string is a valid search phrase for date time.
     *
     * @param test the string value to be put to test.
     * @return true if the test string is valid and false otherwise
     */
    public static boolean isValidSearchPhrase(String test) {
        return isValidDoneDateTime(test) || DateUtil.isValidDate(test) || Time.isValidTime(test);
    }

    @Override
    public String toString() {
        if (isNull) {
            return "";
        } else {
            return value.format(DateUtil.DATETIME_FORMATTER);
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
