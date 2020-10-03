package seedu.address.model.Order;

import seedu.address.model.Vendor.Vendor;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

import static java.util.Objects.requireNonNull;

public class Order {
    protected final Vendor vendor;
    protected final List<OrderItem> orderItems;

    public Order(Vendor vendor) {
        this.vendor = vendor;
        this.orderItems = new ArrayList<>();
    }

    public Vendor getVendor() {
        return vendor;
        /* TODO: Refine Later
            (Might want similar CRUD operations as OrderItem)
         */
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
        /* TODO: Refine Later (Maybe just show as String for UI?
            Can get OrderItem using index)
        */
    }

    public OrderItem getOrderItem(int index) {
        return orderItems.get(index);
    }

    public void deleteOrderItem(int index) {
        orderItems.remove(index);
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
    }

    public void updateOrderItem(int index, int quantity) {
        if (quantity <= 0) {
            orderItems.remove(index);
        } else {
            orderItems.get(index).setQuantity(quantity);
        }
    }

    /**
     * Returns true if a order with the same identity as {@code orderItem} exists in the order.
     */
    public boolean hasOrderItem(OrderItem orderItem) {
        requireNonNull(orderItem);
        // Comparison between orderItem do not take into account quantity
        return orderItems.contains(orderItem);
    }

    public Iterator<OrderItem> iterator() {
        return orderItems.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Order // instanceof handles nulls
                && vendor.equals(((Order) other).vendor)
                && orderItems.equals(((Order) other).orderItems));
    }

    @Override
    public int hashCode() {
        return Objects.hash(vendor.hashCode(), orderItems.hashCode());
    }

    @Override
    public String toString() {
        return "";
        // TODO: refine later
    }
}
