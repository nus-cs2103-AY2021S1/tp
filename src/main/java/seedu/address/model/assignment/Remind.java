package seedu.address.model.assignment;

/**
 * Represents a remind tag of an assignment.
 * Guarantees: immutable; assignment is reminded as declared in {@link #isReminded()}
 */
public class Remind {
    private boolean isReminded;

    /**
     * Constructs a {@code Remind}.
     */
    public Remind() {
        this.isReminded = false;
    }

    public Remind(boolean isReminded) {
        this.isReminded = isReminded;
    }

    /**
     * Sets remind tag to true.
     *
     * @return Remind object with remind tag set to true
     */
    public Remind setReminder() {
        Remind reminded = new Remind();
        reminded.isReminded = true;
        return reminded;
    }

    /**
     * Returns true if remind tag is set to true. Otherwise, returns false.
     *
     * @return true if remind tag is set to true. Otherwise, returns false
     */
    public boolean isReminded() {
        return isReminded;
    }
}
