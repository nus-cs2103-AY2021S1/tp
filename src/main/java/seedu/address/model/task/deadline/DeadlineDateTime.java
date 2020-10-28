package seedu.address.model.task.deadline;

import seedu.address.commons.util.DateUtil;
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
        if (value.equals(DateUtil.DEFAULT_DATETIME)) {
            this.isFilled = false;
        } else {
            isFilled = true;
        }
    }

    public static DeadlineDateTime createNullDeadlineDateTime() {
        return new DeadlineDateTime("");
    }

    public boolean isFilled() {
        return isFilled;
    }

    @Override
    public String toString() {
        if (!isFilled || value == DateUtil.DEFAULT_DATETIME) {
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
                    || isFilled && ((DeadlineDateTime) other).isFilled())); // state check
    }
}
