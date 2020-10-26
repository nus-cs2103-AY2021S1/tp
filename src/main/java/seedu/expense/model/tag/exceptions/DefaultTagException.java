package seedu.expense.model.tag.exceptions;

/**
 * Signals that the operation attempts to edit, delete or duplicate the default tag.
 */
public class DefaultTagException extends RuntimeException {
    public DefaultTagException() {
        super("The DEFAULT tag is fixed and cannot be edited, deleted or duplicated.");
    }
}
