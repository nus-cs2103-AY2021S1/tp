package seedu.address.testutil;

import static seedu.address.testutil.TypicalTags.getTypicalTagSet;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.item.ItemPrecursor;
import seedu.address.model.item.Quantity;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building ItemPrecursor objects.
 */
public class ItemPrecursorBuilder {

    public static final String DEFAULT_NAME = "Bob's Iridescent Grape";
    public static final String DEFAULT_QUANTITY = "25";
    public static final String DEFAULT_DESCRIPTION = "Rare drop from Bob";
    public static final Set<String> DEFAULT_LOCATIONS = new HashSet<>();
    public static final Set<Tag> DEFAULT_TAGS = new HashSet<>(getTypicalTagSet());

    private String name;
    private Quantity quantity;
    private String description;
    private Set<String> locations;
    private Set<Tag> tags;

    /**
     * Creates a {@code ItemBuilder} with the default details.
     */
    public ItemPrecursorBuilder() {
        this.name = DEFAULT_NAME;
        this.quantity = new Quantity(DEFAULT_QUANTITY);
        this.description = DEFAULT_DESCRIPTION;
        this.locations = DEFAULT_LOCATIONS;
        this.tags = DEFAULT_TAGS;
    }

    /**
     * Initializes the ItemBuilder with the data of {@code itemToCopy}.
     */
    public ItemPrecursorBuilder(ItemPrecursor itemToCopy) {
        name = itemToCopy.getName();
        quantity = itemToCopy.getQuantity();
        description = itemToCopy.getDescription();
        Set<String> locationSet = new HashSet<>();
        locationSet.addAll(itemToCopy.getLocationNames());
        locations = locationSet;
        tags = itemToCopy.getTags();
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
     * Sets the {@code Locations} of the {@code Item} that we are building.
     */
    public ItemPrecursorBuilder withLocations(Set<String> locations) {
        this.locations = locations;
        return this;
    }

    /**
     * Sets the {@code tags} of the {@code Item} that we are building.
     */
    public ItemPrecursorBuilder withTags(Set<Tag> tags) {
        this.tags = tags;
        return this;
    }

    /**
     * Builds an item precursor.
     *
     * @return a sample ItemPrecursor.
     */
    public ItemPrecursor build() {
        return new ItemPrecursor(1, name, quantity, description,
                locations, tags);
    }
}
