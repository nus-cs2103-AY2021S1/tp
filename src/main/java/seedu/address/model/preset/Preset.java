package seedu.address.model.preset;

import java.util.List;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.food.Food;
import seedu.address.model.order.OrderItem;

import static java.util.Objects.requireNonNull;

public class Preset {
    public static final String MESSAGE_DUPLICATE_ORDERITEM = "Order contains duplicate orderItems.";

    private String name;
    private List<OrderItem> orderItems;

    /**
     * Creates a Preset with the given name and the order items.
     */
    public Preset(String name, List<OrderItem> orderItems) {
        this.name = name;
        this.orderItems = orderItems;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<OrderItem> getOrderItems() {
        return this.orderItems;
    }

    /**
     * Adds an order item to the preset.
     */
    public void addOrderItem(OrderItem orderItem) throws IllegalValueException {
        if (orderItems.contains(orderItem)) {
            throw new IllegalValueException(MESSAGE_DUPLICATE_ORDERITEM);
        }
        orderItems.add(orderItem);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Preset)) {
            return false;
        }

       Preset otherPreset = (Preset) other;
        return otherPreset.getName().equals(getName())
                && otherPreset.getOrderItems().equals(getOrderItems());
    }

    /////// Methods below are not in use
    public boolean contains(OrderItem orderItem) {
        requireNonNull(orderItem);
        return this.orderItems.contains(orderItem);
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public void addAllOrderItems(List<OrderItem> orderItems) {
        this.orderItems.addAll(orderItems);
    }
}
