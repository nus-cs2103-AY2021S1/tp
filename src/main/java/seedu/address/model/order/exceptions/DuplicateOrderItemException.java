package seedu.address.model.order.exceptions;

public class DuplicateOrderItemException extends RuntimeException {
    public DuplicateOrderItemException() {
        super("Operation would result in duplicate order item");
    }
}
