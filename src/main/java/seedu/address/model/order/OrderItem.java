package seedu.address.model.order;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;
import java.util.Set;

import seedu.address.model.food.Food;
import seedu.address.model.tag.Tag;

/**
 * Represents an OrderItem in the Order.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class OrderItem extends Food {
    // Identity fields
    protected int quantity;

    /**
     * Every field must be present and not null.
     */
    public OrderItem(String name, double price, Set<Tag> tags, int quantity) {
        super(name, price, tags);
        requireAllNonNull(quantity);
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int qty) {
        this.quantity = qty;
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(super.name, super.price, super.tags, quantity);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append(" Price: ")
                .append(getPriceString())
                .append(" Quantity: ")
                .append(getQuantity())
                .append(" Tags: ");
        getTags().forEach(builder::append);
        return builder.toString();
    }
}
