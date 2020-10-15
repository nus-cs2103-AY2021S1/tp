package seedu.address.model.delivery.exception;

/**
 * Signals that the operation will result in duplicate Deliveries
 * (Deliveries are considered duplicates if they have the same identity).
 */
public class DuplicateDeliveryException extends RuntimeException {
    public DuplicateDeliveryException() {
        super("Operation would result in duplicate Deliveries");
    }
}
