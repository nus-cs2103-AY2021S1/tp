package seedu.address.model.deliverable.deliverable.exceptions;

/**
 * Signals that the operation will result in duplicate Deliverables
 * (Deliverables are considered duplicates if they have the same identity).
 */
public class DuplicateDeliverableException extends RuntimeException {
    public DuplicateDeliverableException() {
        super("Operation would result in duplicate deliverables");
    }
}
