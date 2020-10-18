package seedu.address.model.task;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Task's Status in the PlaNus task list.
 * Guarantees: immutable;
 */
public class Status {


    public static final String MESSAGE_CONSTRAINTS =
            "Values must be either 'complete' or 'incomplete'";
    public final State value;

    /**
     * Constructs a {@code Status}.
     *
     * @param state of the task
     */
    public Status(State state) {
        requireNonNull(state);
        this.value = state;
    }

    /**
     * Constructs a default {@code Status} that has a state of incomplete.
     */
    private Status() {
        value = State.INCOMPLETE;
    }

    /**
     * Returns a default Status object which has a state of incomplete.
     */
    public static Status defaultStatus() {
        return new Status();
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Status // instanceof handles nulls
                && value.equals(((Status) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
