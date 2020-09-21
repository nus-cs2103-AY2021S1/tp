package seedu.address.testutil;

import java.util.Collections;
import java.util.HashSet;

import seedu.address.model.item.Item;
import seedu.address.model.item.Quantity;

/**
 * A utility class to help with building Item objects.
 */
public class ItemBuilder {

    public static final String DEFAULT_NAME = "Bob's Iridescent Grape";
    public static final String DEFAULT_QUANTITY = "25";
    public static final String DEFAULT_DESCRIPTION = "Rare drop from bob";

    private String name;
    private Quantity quantity;
    private String description;

    /**
     * Creates a {@code ItemBuilder} with the default details.
     */
    public ItemBuilder() {
        this.name = DEFAULT_NAME;
        this.quantity = new Quantity(DEFAULT_QUANTITY);
        this.description = DEFAULT_DESCRIPTION;
    }

    /**
     * Initializes the ItemBuilder with the data of {@code itemToCopy}.
     */
    public ItemBuilder(Item itemToCopy) {
        name = itemToCopy.getName();
        quantity = itemToCopy.getQuantity();
        description = itemToCopy.getDescription();
    }

    /**
     * Sets the {@code Name} of the {@code Item} that we are building.
     */
    public ItemBuilder withName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Sets the {@code Quantity} of the {@code Item} that we are building.
     */
    public ItemBuilder withQuantity(String quantity) {
        this.quantity = new Quantity(quantity);
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code Item} that we are building.
     */
    public ItemBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * builds an item
     * @return an Item
     */
    public Item build() {
        return new Item(0, name, quantity, description,
                new HashSet<>(Collections.singletonList(1)), new HashSet<>(), new HashSet<>(), false);
    }

}
