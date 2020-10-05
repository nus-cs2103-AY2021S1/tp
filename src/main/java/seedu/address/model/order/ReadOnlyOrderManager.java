package seedu.address.model.order;

import javafx.collections.ObservableList;

/**
 * Unmodifiable view of an order
 */
public interface ReadOnlyOrderManager {
    /**
     * Returns an unmodifiable view of the OrderItem list.
     * This list will not contain any duplicate OrderItems.
     */
    ObservableList<OrderItem> getOrderItemList();
}
