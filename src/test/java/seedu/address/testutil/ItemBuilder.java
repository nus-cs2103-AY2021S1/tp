package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.item.Item;
import seedu.address.model.item.Quantity;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building Item objects.
 */
public class ItemBuilder {

    public static final int DEFAULT_ID = 1;
    public static final String DEFAULT_NAME = "Bob's Iridescent Grape";
    public static final String DEFAULT_QUANTITY = "25";
    public static final String DEFAULT_DESCRIPTION = "Rare drop from Bob";
    public static final Set<Integer> DEFAULT_RECIPES = new HashSet<>();
    public static final Set<Tag> DEFAULT_TAG = new HashSet<>();

    private int id;
    private String name;
    private Quantity quantity;
    private String description;
    private Set<Integer> recipes;
    private Set<Tag> tags;

    /**
     * Creates a {@code ItemBuilder} with the default details.
     */
    public ItemBuilder() {
        this.id = DEFAULT_ID;
        this.name = DEFAULT_NAME;
        this.quantity = new Quantity(DEFAULT_QUANTITY);
        this.description = DEFAULT_DESCRIPTION;
        this.recipes = DEFAULT_RECIPES;
        this.tags = DEFAULT_TAG;
    }

    /**
     * Initializes the ItemBuilder with the data of {@code itemToCopy}.
     */
    public ItemBuilder(Item itemToCopy) {
        id = itemToCopy.getId();
        name = itemToCopy.getName();
        quantity = itemToCopy.getQuantity();
        description = itemToCopy.getDescription();
        recipes = new HashSet<>(itemToCopy.getRecipeIds());
    }

    /**
     * Sets the {@code id} of the {@code Item} that we are building.
     */
    public ItemBuilder withId(int id) {
        this.id = id;
        return this;
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
     * Sets the {@code Recipes} of the {@code Item} that we are building.
     */
    public ItemBuilder withRecipe(Set<Integer> recipes) {
        this.recipes = recipes;
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
     * Sets the {@code Tags} of the {@code Item} that we are building.
     */
    public ItemBuilder withTags(Set<Tag> tags) {
        this.tags = tags;
        return this;
    }

    /**
     * Builds an item.
     *
     * @return a sample Item
     */
    public Item build() {
        return new Item(id, name, quantity, description,
                new HashSet<>(), recipes, new HashSet<>());
    }
}
