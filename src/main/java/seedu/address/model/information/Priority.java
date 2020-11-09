package seedu.address.model.information;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Job's priority in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidPriority(String)}
 */
public class Priority {

    public static final String MESSAGE_CONSTRAINTS =
            "Priority can only take one of these three values: high, moderate, low";

    /*
     * The priority must be one of the three types
     */
    public static final String PR_HIGH = "high";
    public static final String PR_MOD = "moderate";
    public static final String PR_LOW = "low";

    public final String value;

    /**
     * Constructs an {@code Priority}.
     *
     * @param priority A valid priority.
     */
    public Priority(String priority) {
        requireNonNull(priority);
        checkArgument(isValidPriority(priority), MESSAGE_CONSTRAINTS);
        value = priority;
    }

    /**
     * Returns true if a given string is a valid priority.
     */
    public static boolean isValidPriority(String test) {
        return test.equals(PR_HIGH) || test.equals(PR_MOD) || test.equals(PR_LOW);
    }

    /**
     * Parses the priority value into an integer.
     *
     * @return Integer value according to the priority.
     * High will return 3, Moderate will return 2 and Low will return 1.
     */
    public int parsePriorityToInt() {
        final int highPriority = 3;
        final int modPriority = 2;
        final int lowPriority = 1;

        switch (this.value) {
        case PR_HIGH:
            return highPriority;

        case PR_MOD:
            return modPriority;

        case PR_LOW:
            return lowPriority;

        default:
            assert false;
            return 0;
        }
    }

    @Override
    public String toString() {
        assert value != null;
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Priority // instanceof handles nulls
                && value.equals(((Priority) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
