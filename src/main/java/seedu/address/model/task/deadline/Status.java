package seedu.address.model.task.deadline;

/**
 * Represents a Deadline Task's Status in the PlaNus task list.
 * Guarantees: immutable;
 */
public class Status {

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

    @Override
    public String toString() {
        if (isCompleted) {
            return "completed";
        } else {
            return "incomplete";
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Status // instanceof handles nulls
                && isCompleted == (((Status) other).isCompleted)); // state check
    }

}
