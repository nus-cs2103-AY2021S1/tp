package seedu.address.model.task;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;

/**
 * Represents a Task's Status in the PlaNus task list.
 * Guarantees: immutable;
 */
public class Status {


    public static final String ACCEPTED_STATUS = listAcceptedStatus();
    public static final String MESSAGE_CONSTRAINTS = String.format("Value for status can only be one of "
            + "the following: %s.", ACCEPTED_STATUS);
    public static final String SEARCH_CONSTRAINTS = String.format("Search phrase for Status can only be one of "
            + "the following: %s.", ACCEPTED_STATUS);


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

    /**
     * Returns all possible status of a task.
     * @return a String containing all possible status of a task.
     */
    private static String listAcceptedStatus() {
        ArrayList<String> listOfAcceptedStatus = new ArrayList<>();
        for (State state : State.values()) {
            listOfAcceptedStatus.add(state.toString());
        }
        return String.join(", ", listOfAcceptedStatus);
    }

    /**
     * Return true if the state in param is same as value, else return false.
     * @param state a State object.
     */
    public boolean is(State state) {
        return value.equals(state);
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
