package seedu.address.model.task.deadline;

import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Deadline Task's Status in the PlaNus task list.
 * Guarantees: immutable;
 */
public class Duration {

    public static final int NULL_VALUE = -1;
    public static final String INVALID_DURATION_FORMAT = "The duration must be a positive integer";
    public final int valueInMinutes;
    public final boolean isFill;
    /**
     * Constructs a {@code Duration}.
     * @param valueInMinutes
     */
    public Duration(int valueInMinutes) {
        if (valueInMinutes == NULL_VALUE) {
            this.isFill = false;
            this.valueInMinutes = valueInMinutes;
        } else {
            checkArgument(isValidDuration(valueInMinutes), Duration.INVALID_DURATION_FORMAT);
            this.valueInMinutes = valueInMinutes;
            this.isFill = true;
        }
    }

    /**
     * factory method that returns a Duration Object represents a null duration;
     */
    public static Duration createNullDuration() {
        return new Duration(NULL_VALUE);
    }

    public boolean isFilled() {
        return isFill;
    }

    public static boolean isValidDuration(int duration) {
        return duration >= 0;
    }

    @Override
    public String toString() {
        return String.valueOf(valueInMinutes);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Duration // instanceof handles nulls
                && valueInMinutes == (((Duration) other).valueInMinutes)); // state check
    }

}
