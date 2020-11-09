package tp.acecs2103.model.exceptions;

public class InvalidTaskOperationException extends ModelException {
    private static final String ERR_MSG = "Sorry! Your command operation is not valid.";

    public InvalidTaskOperationException() {
        super(ERR_MSG);
    }

    public InvalidTaskOperationException(String errMsg) {
        super(ERR_MSG + " " + errMsg);
    }
}
