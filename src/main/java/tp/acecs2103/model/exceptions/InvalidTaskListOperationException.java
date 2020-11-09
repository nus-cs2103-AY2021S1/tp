package tp.acecs2103.model.exceptions;

/**
 * Represents an exception for invalid task list operation.
 */
public class InvalidTaskListOperationException extends ModelException {
    private static final String ERR_MSG = "Sorry! Your command operation is not valid.";

    public InvalidTaskListOperationException() {
        super(ERR_MSG);
    }

    public InvalidTaskListOperationException(String errMsg) {
        super(ERR_MSG + " " + errMsg);
    }
}
