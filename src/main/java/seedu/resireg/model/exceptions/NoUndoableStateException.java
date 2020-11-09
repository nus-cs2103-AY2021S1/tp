package seedu.resireg.model.exceptions;

/**
 * Signals that {@code undo()} can't be done as there
 * are no states to go back to
 */
public class NoUndoableStateException extends RuntimeException {
    public NoUndoableStateException() {
        super("There are no states to undo.");
    }
}

