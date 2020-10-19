package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.order.OrderItem;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building OrderItem objects.
 */
public class OrderItemBuilder {

    public static final String DEFAULT_NAME = "Plain Prata";
    public static final double DEFAULT_PRICE = 1.20;
    public static final String DEFAULT_PRICE_STRING = "1.20";
    public static final int DEFAULT_QUANTITY = 1;


    private String name;
    private double price;
    private int quantity;
    private Set<Tag> tags;

    /**
     * Creates a {@code OrderItemBuilder} with the default details.
     */
    public OrderItemBuilder() {
        this.name = DEFAULT_NAME;
        this.price = DEFAULT_PRICE;
        this.quantity = DEFAULT_QUANTITY;
        this.tags = new HashSet<>();
    }

    /**
     * Initializes the OrderItemBuilder with the data of {@code orderItemToCopy}.
     */
    public OrderItemBuilder(OrderItem orderItemToCopy) {
        name = orderItemToCopy.getName();
        price = orderItemToCopy.getPrice();
        quantity = orderItemToCopy.getQuantity();
        tags = new HashSet<>(orderItemToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code OrderItem} that we are building.
     */
    public OrderItemBuilder withName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Sets the {@code Price} of the {@code OrderItem} that we are building.
     */
    public OrderItemBuilder withPrice(double price) {
        this.price = price;
        return this;
    }

    /**
     * Sets the {@code Quantity} of the {@code OrderItem} that we are building.
     */
    public OrderItemBuilder withQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code OrderItem} that we are building.
     */
    public OrderItemBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }


    public OrderItem build() {
        return new OrderItem(name, price, tags, quantity);
    }
}
