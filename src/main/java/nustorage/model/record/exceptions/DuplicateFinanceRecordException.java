package nustorage.model.record.exceptions;

/**
 * Signals that the operation will result in duplicate FinanceRecord
 * (FinanceRecords are considered duplicates if they have the same ID).
 */
public class DuplicateFinanceRecordException extends RuntimeException {
    public DuplicateFinanceRecordException() {
        super("Operation would result in duplicate finance records");
    }
}
