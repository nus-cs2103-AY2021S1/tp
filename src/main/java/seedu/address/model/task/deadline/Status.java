package seedu.address.model.task.deadline;

/**
 * Represents a Deadline Task's Status in the PlaNus task list.
 * Guarantees: immutable;
 */
public class Status {

    public static final String COMPLETE_TEXT = "complete";
    public static final String INCOMPLETE_TEXT = "incomplete";

    public static final String ACCEPTED_STATUS = COMPLETE_TEXT + "," + INCOMPLETE_TEXT;
    public static final String MESSAGE_CONSTRAINTS = String.format("Value for status can only be one of "
            + "the following: %s.", ACCEPTED_STATUS);
    public static final String SEARCH_CONSTRAINTS = String.format("Search phrase for Status can only be one of "
            + "the following: %s.", ACCEPTED_STATUS);

    public final boolean isCompleted;

    /**
     * Constructs a {@code Status}.
     * @param isCompleted
     */
    public Status(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    /**
     * factory method that returns a Status Object represents a deadline task is complete.
     */
    public static Status createCompleteStatus() {
        return new Status(true);
    }

    /**
     * factory method that returns a Status Object represents a deadline task is incomplete.
     */
    public static Status createIncompleteStatus() {
        return new Status(false);
    }

    public static boolean isComplete(String status) {
        return status.equals(COMPLETE_TEXT);
    }

    public static boolean isValidStatus(String test) {
        return test.equals(COMPLETE_TEXT) || test.equals(INCOMPLETE_TEXT);
    }

    @Override
    public String toString() {
        if (isCompleted) {
            return COMPLETE_TEXT;
        } else {
            return INCOMPLETE_TEXT;
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Status // instanceof handles nulls
                && isCompleted == (((Status) other).isCompleted)); // state check
    }

}
