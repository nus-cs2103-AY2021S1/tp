package seedu.address.model.order;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.order.exceptions.DuplicateOrderItemException;
import seedu.address.model.order.exceptions.OrderItemNotFoundException;
import seedu.address.model.vendor.Vendor;

public class Order implements Iterable<OrderItem> {
    protected Vendor vendor;

    private final ObservableList<OrderItem> internalList = FXCollections.observableArrayList();
    private final ObservableList<OrderItem> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    /**
     * Returns true if the list contains an equivalent OrderItem as the given argument.
     */
    public boolean contains(OrderItem toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameOrderItem);
    }

    /**
     * Adds OrderItem to the list.
     */
    public void add(OrderItem toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            OrderItem existingItem = internalList.stream()
                    .filter(toAdd::isSameOrderItem)
                    .findFirst()
                    .get();
            existingItem.setQuantity(existingItem.getQuantity() + toAdd.getQuantity());
        } else {
            internalList.add(toAdd);
        }
    }

    /**
     * Replaces the OrderItem {@code target} in the list with {@code editedOrderItem}.
     * {@code target} must exist in the list.
     * The OrderItem identity of {@code editedOrderItem} must not be the same as another existing OrderItem in the list.
     */
    public void setOrderItem(OrderItem target, OrderItem editedOrderItem) {
        requireAllNonNull(target, editedOrderItem);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new OrderItemNotFoundException();
        }

        if (!target.isSameOrderItem(editedOrderItem) && contains(editedOrderItem)) {
            throw new DuplicateOrderItemException();
        }

        internalList.set(index, editedOrderItem);
    }

    /**
     * Removes the equivalent OrderItem from the list.
     * The OrderItem must exist in the list.
     */
    public void remove(OrderItem toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new OrderItemNotFoundException();
        }
    }

    public void setOrderItems(Order replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<OrderItem> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<OrderItem> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Order // instanceof handles nulls
                && internalList.equals(((Order) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code orderItems} contains only unique orderItems.
     */
    private boolean orderItemsAreUnique(List<OrderItem> orderItems) {
        for (int i = 0; i < orderItems.size() - 1; i++) {
            for (int j = i + 1; j < orderItems.size(); j++) {
                if (orderItems.get(i).isSameOrderItem(orderItems.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
