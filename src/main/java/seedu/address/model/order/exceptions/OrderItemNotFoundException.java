package seedu.address.model.order.exceptions;

/**
 * Signals that the operation is unable to find the specified food.
 */
public class OrderItemNotFoundException extends RuntimeException {
    public OrderItemNotFoundException() {
        super("OrderItem not found");
    }
}
