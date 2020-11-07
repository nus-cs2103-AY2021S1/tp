package seedu.simplykitchen.model.food;

import static java.util.Objects.requireNonNull;
import static seedu.simplykitchen.commons.util.AppUtil.checkArgument;

/**
 * Represents a Food item priority level in the food inventory system.
 * Guarantees: immutable; is valid as declared in {@link #isValidPriority(String)}
 */
public class Priority {
    public enum Level {
        LOW,
        MEDIUM,
        HIGH
    }

    public static final String MESSAGE_CONSTRAINTS =
            "Priorities should be either high, medium or low.";
    public final Level value;

    /**
     * Constructs a {@code Priority}.
     *
     * @param priority A valid priority level.
     */
    public Priority(String priority) {
        priority = priority.toLowerCase();
        requireNonNull(priority);
        checkArgument(isValidPriority(priority), MESSAGE_CONSTRAINTS);
        switch(priority) {
        case "low":
            value = Level.LOW;
            break;
        case "medium":
            value = Level.MEDIUM;
            break;
        case "high":
            value = Level.HIGH;
            break;
        default:
            throw new IllegalArgumentException(MESSAGE_CONSTRAINTS);
        }
    }

    /**
     * Returns true if a given string is a valid priority level.
     */
    public static boolean isValidPriority(String priority) {
        priority = priority.toLowerCase();
        return priority.equals("low") || priority.equals("medium") || priority.equals("high");
    }

    /**
     * Returns true if a given {@code otherPriority} has a lower priority level.
     */
    public boolean isHigherPriority(Priority otherPriority) {
        return ((this.value == Level.HIGH) && (otherPriority.value == Level.MEDIUM))
                || ((this.value == Level.HIGH) && (otherPriority.value == Level.LOW))
                || ((this.value == Level.MEDIUM) && (otherPriority.value == Level.LOW));
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Priority // instanceof handles nulls
                && (value.equals(((Priority) other).value))); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
