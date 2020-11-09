package tp.acecs2103.model.exceptions;

/**
 * Represents a general model exception.
 */
public class ModelException extends Exception {
    private static final String ERR_MSG = "There is something wrong with model.";

    public ModelException() {
        super(ERR_MSG);
    }

    public ModelException(String errMsg) {
        super(errMsg);
    }
}
