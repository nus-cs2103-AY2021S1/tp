package seedu.address.model.task;

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
