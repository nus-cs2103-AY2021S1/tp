package seedu.address.model.routine.exceptions;

public class DuplicateRoutineException extends RuntimeException {
    public DuplicateRoutineException() {
        super("Operation would result in duplicate routines.");
    }
}
