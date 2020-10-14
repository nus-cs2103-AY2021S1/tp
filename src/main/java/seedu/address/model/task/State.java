package seedu.address.model.task;

/**
 * Represents a Task's state.
 * A task's state can be either complete or imcomplete.
 */
public enum State {
    COMPLETE("Complete"), INCOMPLETE("Incomplete");

    private String state;

    State(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return state;
    }
}
