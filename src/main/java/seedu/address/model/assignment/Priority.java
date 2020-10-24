package seedu.address.model.assignment;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Priority Tag in ProductiveNUS.
 */
public class Priority {
    public static final String LOW_PRIORITY = "LOW";
    public static final String MEDIUM_PRIORITY = "MEDIUM";
    public static final String HIGH_PRIORITY = "HIGH";
    public static final String NONE_PRIORITY = "NONE";
    public static final String MESSAGE_CONSTRAINTS = "Priority levels should be either LOW, MEDIUM or HIGH.";
    public enum Level {
        NONE, LOW, HIGH, MEDIUM
    }

    public final Level priority;

    /**
     * Constructs a none Priority.
     */
    public Priority() {
        priority = Level.NONE;
    }

    /**
     * Constructs a {@code Priority}.
     */
    public Priority(String priority) {
        requireNonNull(priority);
        checkArgument(isValidPriority(priority), MESSAGE_CONSTRAINTS);
        this.priority = getLevel(priority);
    }

    /**
     * Returns true if a given string is a valid priority.
     */
    public static boolean isValidPriority(String priority) {
        String toTest = priority.toUpperCase();
        return (toTest.equals("LOW")) || (toTest.equals("MEDIUM")) || (toTest.equals("HIGH")) || (
                toTest.equals("NONE"));
    }

    private static Level getLevel(String priority) {
        String level = priority.toUpperCase();
        if (level.equals("LOW")) {
            return Level.LOW;
        }
        if (level.equals("MEDIUM")) {
            return Level.MEDIUM;
        }
        if (level.equals("HIGH")) {
            return Level.HIGH;
        }
        return Level.NONE;
    }

    public boolean hasPriority() {
        return priority != Level.NONE;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Priority // instanceof handles nulls
                && priority.equals(((Priority) other).priority)); // state check
    }

    @Override
    public int hashCode() {
        return priority.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return priority.toString();
    }
}
