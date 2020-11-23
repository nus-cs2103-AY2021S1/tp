package seedu.stock.model.stock.exceptions;

/**
 * Signals that the operation will result in duplicate Serial Number set (Serial number sets
 * are considered duplicates if they have the same identity).
 */
public class DuplicateSerialNumberSetException extends RuntimeException {
    public DuplicateSerialNumberSetException() {
        super("Operation would result in duplicate serial number.");
    }
}
