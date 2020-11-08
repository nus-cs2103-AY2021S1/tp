package seedu.address.model.assignment;

/**
 * Represents a done tag of an assignment.
 * Guarantees: immutable; assignment is marked as done as declared in {@link #isMarkedDone()}
 */
public class Done {
    private boolean isMarkedDone;

    /**
     * Constructs a {@code Done}.
     */
    public Done() {
        this.isMarkedDone = false;
    }

    public Done(boolean isMarkedDone) {
        this.isMarkedDone = isMarkedDone;
    }

    /**
     * Sets done tag to true.
     *
     * @return Done object with done tag set to true
     */
    public Done markAsDone() {
        Done markedDone = new Done();
        markedDone.isMarkedDone = true;
        return markedDone;
    }

    /**
     * Returns true if done tag is set to true. Otherwise, returns false.
     *
     * @return true if done tag is set to true. Otherwise, returns false
     */
    public boolean isMarkedDone() {
        return isMarkedDone;
    }

    /**
     * Returns a tick symbol if done. Otherwise, returns a cross symbol.
     */
    public String toString() {
        return isMarkedDone ? "Done \u2714" : "Undone \u2718";
    }
}
