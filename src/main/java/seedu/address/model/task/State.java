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

    /**
     * returns a State of corresponding text.
     */
    public static State toState(String text) {
        for (State s : State.values()) {
            if (s.state.equalsIgnoreCase(text)) {
                return s;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return state;
    }
}
