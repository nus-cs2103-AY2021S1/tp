package seedu.address.model.deliverable.deliverable;

import seedu.address.model.util.DateTime;

/**
 * Represents a Deliverable's deadline in the deliverable book.
 */
public class Deadline extends DateTime {

    public static final String MESSAGE_CONSTRAINTS = String.format(CONSTRAINTS, "Deadline");

    /**
     * Constructs a {@code Deadline}.
     *
     * @param deadline A valid Deadline.
     */
    public Deadline(String deadline) {
        super(deadline);
    }

    /**
     * Returns true if a given string is a valid deadline.
     */
    public static boolean isValidDeadline(String test) {
        return isValidDateTime(test);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof Deadline
                && value.equals(((Deadline) other).value));
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
