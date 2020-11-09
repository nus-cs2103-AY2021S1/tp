package seedu.address.model.task.deadline;

import seedu.address.commons.util.DateTimeUtil;
import seedu.address.model.task.DateTime;

/**
 * Represents a Task's date and time in PlaNus task list.
 * Guarantees: immutable; is valid as declared in {@link #isValidDateTime(String)}
 */
public class DeadlineDateTime extends DateTime {

    public final boolean isFilled;

    /**
     * Constructs a {@code DateTime}.
     *
     * @param dateTime A valid date and time.
     */
    public DeadlineDateTime(String dateTime) {
        super(dateTime);
        this.isFilled = !value.isEqual(DateTimeUtil.DEFAULT_DATETIME);
    }

    public static DeadlineDateTime createNullDeadlineDateTime() {
        return new DeadlineDateTime("");
    }

    public boolean isFilled() {
        return isFilled;
    }

    @Override
    public String toString() {
        if (!isFilled || value == DateTimeUtil.DEFAULT_DATETIME) {
            return "";
        } else {
            return value.format(DateTimeUtil.DATETIME_FORMATTER);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeadlineDateTime // instanceof handles nulls
                && (value.isEqual(((DeadlineDateTime) other).value))); // state check
    }
}
