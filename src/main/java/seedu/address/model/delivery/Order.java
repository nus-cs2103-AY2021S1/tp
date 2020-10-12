package seedu.address.model.delivery;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Delivery's order in the delivery book.
 * Guarantees: immutable; is always valid
 */
public class Order {

    public final String value;

    /**
     * Constructs an {@code Order}.
     *
     * @param order A valid order.
     */
    public Order(String order) {
        requireNonNull(order);
        value = order;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Order // instanceof handles nulls
                && value.equals(((Order) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
