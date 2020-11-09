package seedu.resireg.model.exceptions;

/**
 * Signals that {@code redo()} can't be done as there
 * are no states to go forward to
 */
public class NoRedoableStateException extends RuntimeException {
    public NoRedoableStateException() {
        super("There are no states to redo.");
    }
}
