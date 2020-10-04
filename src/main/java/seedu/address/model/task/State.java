package seedu.address.model.task;

public enum State {
    DONE("done"), PENDING("pending");

    private String string;

    State(String name) {
        this.string = name;
    }

    @Override
    public String toString() {
        return string;
    }
}
