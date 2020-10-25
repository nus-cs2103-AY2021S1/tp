package seedu.address.model.sales.exception;

/**
 * Signals that the operation will result in duplicate SalesRecordEntry (SalesRecordEntries are considered duplicates
 * if they refer to the same Drink).
 */
public class DuplicateSalesRecordException extends RuntimeException {
    public DuplicateSalesRecordException() {
        super("Operation would result in duplicate sales entry.");
    }
}
