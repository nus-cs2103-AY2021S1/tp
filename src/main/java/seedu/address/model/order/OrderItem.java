package seedu.address.model.order;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.food.Food;
import seedu.address.model.food.MenuItem;
import seedu.address.model.tag.Tag;

/**
 * Represents an OrderItem in the Order.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class OrderItem extends Food {
    // Identity fields
    private int quantity;

    /**
     * Every field must be present and not null.
     */
    public OrderItem(String name, double price, Set<Tag> tags, int quantity) {
        super(name, price, tags);
        requireAllNonNull(quantity);
        this.quantity = quantity;
    }

    /**
     * Alternative constructor that takes in a menu item and quantity. Every field must be present and not null.
     */
    public OrderItem(MenuItem menuItem, int quantity) {
        super(menuItem.getName(), menuItem.getPrice(), menuItem.getTags());
        requireAllNonNull(quantity);
        tags.clear();
        this.quantity = quantity;
    }

    /**
     * Alternative constructor that takes in a order item. Every field must be present and not null.
     */
    public OrderItem(OrderItem orderItem, int quantity) {
        super(orderItem.getName(), orderItem.getPrice(), orderItem.getTags());
        requireAllNonNull(quantity);
        this.quantity = quantity;
    }

    public OrderItem makeCopy() {
        return new OrderItem(getName(), getPrice(), getTags(), getQuantity());
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public static boolean isValidQuantity(int quantity) {
        return quantity > 0;
    }

    /**
     * Returns true only if both OrderItems have the same exact identity fields for all attributes.
     */
    public boolean isSameOrderItem(OrderItem orderItem) {
        if (orderItem == this) {
            return true;
        }

        return orderItem != null
                && orderItem.getName().equals(getName())
                && (orderItem.getPrice() == (getPrice()))
                && (orderItem.getQuantity() == getQuantity())
                && (orderItem.getTags().equals(getTags()));
    }

    /**
     * Returns true if both OrderItems have the same identity fields for all attributes except for quantity.
     * This defines a weaker notion of equality between two OrderItems.
     */
    public boolean isSameOrderItemDescription(OrderItem orderItem) {
        if (orderItem == this) {
            return true;
        }

        return orderItem != null
                && orderItem.getName().equals(getName())
                && (orderItem.getPrice() == (getPrice()));
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    @Override
    public void setTags(Set<Tag> newTags) {
        tags.clear();
        tags.addAll(newTags);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof OrderItem)) {
            return false;
        }

        OrderItem otherFood = (OrderItem) other;
        return otherFood.getName().equals(getName())
                && otherFood.getPrice() == getPrice();
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(super.name, super.price, super.tags, quantity);
    }

    /**
     * Creates a text suitable for order.
     */
    public String toOrderText() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName() + " x " + getQuantity() + "\n");
        for (Tag tag: tags) {
            builder.append(" - " + tag.tagName + "\n");
        }
        return builder.toString();
    }

    public String addCommandString() {
        return String.format("%s x%d", getName(), getQuantity());
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

    public void addTag(Tag tag) {
        tags.add(tag);
    }
}
