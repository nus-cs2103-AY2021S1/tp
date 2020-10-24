package nustorage.model.record.exceptions;

/**
 * Signals that the operation will result in duplicate InventoryRecord
 * (InventoryRecords are considered duplicates if they have the same name).
 */
public class DuplicateInventoryRecordException extends RuntimeException {
    public DuplicateInventoryRecordException() {
        super("Operation would result in duplicate inventory records");
    }
}
