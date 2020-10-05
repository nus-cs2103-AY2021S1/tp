package seedu.address.model.task;

public enum State {
    DONE("done"), PENDING("pending");

    private String state;

    State(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return state;
    }
}
