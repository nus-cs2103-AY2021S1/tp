package seedu.taskmaster.model.session.exceptions;

public class NoSessionSelectedException extends SessionException {

    private final String message = "Please select a session first!";

    public NoSessionSelectedException() {}

    public String getMessage() {
        return message;
    }

}
