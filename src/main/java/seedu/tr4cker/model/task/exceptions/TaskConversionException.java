package seedu.tr4cker.model.task.exceptions;

/**
 * Signals that the task cannot be converted to a Countdown event.
 */
public class TaskConversionException extends RuntimeException {
    public TaskConversionException() {
        super("Task cannot be converted to an event");
    }
}
