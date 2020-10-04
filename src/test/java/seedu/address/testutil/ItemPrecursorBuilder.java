package seedu.address.testutil;

import java.util.HashSet;

import seedu.address.model.item.ItemPrecursor;
import seedu.address.model.item.Quantity;

/**
 * A utility class to help with building ItemPrecursor objects.
 */
public class ItemPrecursorBuilder {

    public static final String DEFAULT_NAME = "Bob's Iridescent Grape";
    public static final String DEFAULT_QUANTITY = "25";
    public static final String DEFAULT_DESCRIPTION = "Rare drop from Bob";

    private String name;
    private Quantity quantity;
    private String description;

    /**
     * Creates a {@code ItemBuilder} with the default details.
     */
    public ItemPrecursorBuilder() {
        this.name = DEFAULT_NAME;
        this.quantity = new Quantity(DEFAULT_QUANTITY);
        this.description = DEFAULT_DESCRIPTION;
    }

    /**
     * Initializes the ItemBuilder with the data of {@code itemToCopy}.
     */
    public ItemPrecursorBuilder(ItemPrecursor itemToCopy) {
        name = itemToCopy.getName();
        quantity = itemToCopy.getQuantity();
        description = itemToCopy.getDescription();
    }

    /**
     * Sets the {@code Name} of the {@code Item} that we are building.
     */
    public ItemPrecursorBuilder withName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Sets the {@code Quantity} of the {@code Item} that we are building.
     */
    public ItemPrecursorBuilder withQuantity(String quantity) {
        this.quantity = new Quantity(quantity);
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code Item} that we are building.
     */
    public ItemPrecursorBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * Builds an item precursor.
     *
     * @return a sample ItemPrecursor.
     */
    public ItemPrecursor build() {
        return new ItemPrecursor(0, name, quantity, description,
                new HashSet<>(), new HashSet<>());
    }
}
