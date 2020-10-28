package seedu.address.model.task.deadline;

import java.time.LocalDateTime;

import seedu.address.commons.util.DateUtil;
import seedu.address.model.task.DateTime;


/**
 * Represents a Task's date and time in PlaNus task list.
 * Guarantees: immutable; is valid as declared in {@link #isValidDateTime(String)}
 */
public class DoneDateTime extends DateTime {

    public final boolean isFilled;

    /**
     * Constructs a {@code DateTime}.
     *
     * @param dateTime A valid date and time.
     */
    public DoneDateTime(String dateTime) {
        super(dateTime);
        this.isFilled = !value.equals(DateUtil.DEFAULT_DATETIME);
    }

    private DoneDateTime(LocalDateTime value) {
        super(value);
        this.isFilled = true;
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

    public boolean isFilled() {
        return isFilled;
    }

    @Override
    public String toString() {
        if (!isFilled) {
            return "";
        } else {
            return value.format(DateUtil.DATETIME_FORMATTER);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DoneDateTime // instanceof handles nulls
                && (value.equals(((DoneDateTime) other).value)
                || isFilled && ((DoneDateTime) other).isFilled())); // state check
    }
}
