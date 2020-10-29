package seedu.address.model.exercise.exceptions;


/**
 * Signals that the operation will result in duplicate Goals
 * (Exercises are considered duplicates if they have the same identity).
 */
public class DuplicateGoalException extends RuntimeException {
    public DuplicateGoalException() {
        super("Operation would result in duplicate goals");
    }
}