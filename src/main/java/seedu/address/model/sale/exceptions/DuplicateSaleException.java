package seedu.address.model.sale.exceptions;

/**
 * Signals that the operation will result in duplicate Sales (Sales are considered duplicates if they have the same
 * identity).
 */
public class DuplicateSaleException extends RuntimeException {
    public DuplicateSaleException() {
        super("Operation would result in duplicate sales");
    }
}
