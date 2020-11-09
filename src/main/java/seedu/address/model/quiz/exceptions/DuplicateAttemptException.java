package seedu.address.model.quiz.exceptions;

/**
 * Signals that the operation will result in duplicate Questions (Questions are considered duplicates if they have
 * the same identity).
 */
public class DuplicateAttemptException extends RuntimeException {
    public DuplicateAttemptException() {
        super("Operation would result in duplicate attempts");
    }
}
