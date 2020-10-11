package seedu.address.storage;

import java.util.HashSet;
import java.util.Optional;

import seedu.address.model.order.OrderItem;
import seedu.address.model.order.OrderManager;
import seedu.address.model.order.ReadOnlyOrderManager;

public class OrderItemStorageStub {
    public static final OrderItem EGG_PRATA = new OrderItem("Egg Prata", 1.20, new HashSet<>(), 2);
    public static final OrderItem CHEESE_PRATA = new OrderItem("Cheese Prata", 1, new HashSet<>(), 3);
    public static final OrderItem MILO = new OrderItem("Milo", 1.5, new HashSet<>(), 1);

    /**
     * Generates a ReadOnlyOrderManager wrapped in Optional class
     */
    public Optional<ReadOnlyOrderManager> readOrderManager() {
        OrderManager orderManager = new OrderManager();
        orderManager.addOrderItem(EGG_PRATA);
        orderManager.addOrderItem(CHEESE_PRATA);
        orderManager.addOrderItem(MILO);

        return Optional.of(orderManager);
    }
}
