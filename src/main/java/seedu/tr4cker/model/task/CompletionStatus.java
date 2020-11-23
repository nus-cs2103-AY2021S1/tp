package seedu.tr4cker.model.task;

import static seedu.tr4cker.commons.util.AppUtil.checkArgument;

/**
 * Represents a Task's completion status in Tr4cker.
 * Guarantees: immutable; is valid as declared in {@link #isValidCompletionStatus(int)}
 */
public class CompletionStatus {

    public static final String MESSAGE_CONSTRAINTS =
            "Completion status percentage should be an integer value, ranging only between 0 and 100";

    public final int value;

    /**
     * Constructs a {@code CompletionStatus}.
     *
     * @param percentage A valid percentage.
     */
    public CompletionStatus(int percentage) {
        checkArgument(isValidCompletionStatus(percentage), MESSAGE_CONSTRAINTS);
        value = percentage;
    }

    /**
     * Returns if a given string is a valid percentage.
     * @param test
     */
    public static boolean isValidCompletionStatus(int test) {
        return test >= 0 && test <= 100;
    }

    @Override
    public String toString() {
        return value + "%";
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CompletionStatus // instanceof handles nulls
                && this.value == ((CompletionStatus) other).value); // state check
    }

    @Override
    public int hashCode() {
        return value;
    }

    public int compareTo(CompletionStatus other) {
        return Integer.compare(value, other.value);
    }

}
