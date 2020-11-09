package seedu.taskmaster.model.session.exceptions;

/**
 * Signals that the operation cannot be executed as the SessionList is empty.
 */
public class NoSessionException extends SessionException {

    private final String message = "There are no sessions yet!";

    public NoSessionException() {}

    public String getMessage() {
        return message;
    }

}
