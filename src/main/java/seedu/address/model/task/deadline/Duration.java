package seedu.address.model.task.deadline;

/**
 * Represents a Deadline Task's Status in the PlaNus task list.
 * Guarantees: immutable;
 */
public class Duration {

    public static final int NULL_VALUE = -1;
    public static final String INVALID_DURATION_FORMAT = "The valid of duration is not valid";
    public final int valueInMinutes;
    public final boolean isNull;
    /**
     * Constructs a {@code Duration}.
     * @param valueInMinutes
     */
    public Duration(int valueInMinutes) {
        if (valueInMinutes == NULL_VALUE) {
            this.isNull = true;
            this.valueInMinutes = valueInMinutes;
        } else {
            //do a check
            this.valueInMinutes = valueInMinutes;
            this.isNull = false;
        }
    }

    /**
     * factory method that returns a Duration Object represents a null duration;
     */
    public static Duration createNullDuration() {
        return new Duration(NULL_VALUE);
    }

    public boolean isNull() {
        return isNull;
    }

    public static boolean isValidDuration(int duration) {
        return duration == NULL_VALUE || duration >= 0;
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
