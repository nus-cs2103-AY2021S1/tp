package seedu.address.model.item;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.InventoryComponent;
import seedu.address.model.tag.Tag;
import seedu.address.ui.DisplayedInventoryType;

/**
 * Represents an Item in the Inventoryinator.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Item extends InventoryComponent {
    private static int idCounter = 0;

    // Identity fields
    private final int id;
    private final String name;

    // Data fields
    private final Quantity quantity;
    private final String description;
    private final List<Integer> locationIds = new ArrayList<>();
    private final List<Integer> recipeIds = new ArrayList<>();
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Creates a new item. Every field must be present and not null.
     */
    public Item(int id, String name, Quantity quantity, String description, Set<Integer> locationIds,
                Set<Integer> recipeIds, Set<Tag> tags) {
        requireAllNonNull(id, name, quantity, description, locationIds, recipeIds, tags);
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.description = description;
        this.locationIds.addAll(locationIds);
        this.recipeIds.addAll(recipeIds);
        this.tags.addAll(tags);
        if (!this.getType().equals(DisplayedInventoryType.DETAILED_ITEM)) {
            idCounter++;
        }
    }

    /**
     * Creates an edited item without increasing id counter.
     * Every field must be present and not null.
     */
    public Item(int id, String name, Quantity quantity, String description, Set<Integer> locationIds,
                Set<Integer> recipeIds, Set<Tag> tags, Boolean isEdited) {
        requireAllNonNull(id, name, quantity, description, locationIds, recipeIds, tags);
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.description = description;
        this.locationIds.addAll(locationIds);
        this.recipeIds.addAll(recipeIds);
        this.tags.addAll(tags);
        if (!isEdited) {
            idCounter++;
        }
    }

    public static int getIdCounter() {
        return idCounter;
    }

    /**
     * Used only when parsing items to populate list for first time.
     * @param i id counter for next item.
     */
    public static void setIdCounter(int i) {
        assert i >= 0 : "Item ID should never be negative";
        idCounter = i;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Quantity getQuantity() {
        return quantity;
    }

    public String getDescription() {
        return description;
    }

    public List<Integer> getLocationIds() {
        return locationIds;
    }

    public List<Integer> getRecipeIds() {
        return recipeIds;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Adds Recipe Id to item's recipe ids.
     *
     * @param recipeId recipe id connected to this item.
     */
    public void addRecipeId(int recipeId) {
        if (!recipeIds.contains(recipeId)) {
            recipeIds.add(recipeId);
        }
    }

    /**
     * Remove Recipe Id from item's recipe ids.
     *
     * @param recipeId recipe id connected to this item.
     */
    public void removeRecipeId(int recipeId) {
        if (recipeIds.contains(recipeId)) {
            recipeIds.remove((Object) recipeId);
        }
    }

    /**
     * Returns true if both items have the same name.
     * This defines a weaker notion of equality between two items.
     */
    public boolean isSameItem(Item otherItem) {
        if (otherItem == this) {
            return true;
        }
        return otherItem != null
                && otherItem.getName().equals(getName());
    }

    /**
     * Returns true if both items have the same name and data fields.
     * This defines a stronger notion of equality between two items.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Item)) {
            return false;
        }

        Item otherItem = (Item) other;

        return otherItem.getName().equals(getName())
                && otherItem.getQuantity().equals((getQuantity()))
                && otherItem.getDescription().equals(getDescription())
                && otherItem.getLocationIds().equals(getLocationIds())
                && otherItem.getRecipeIds().equals(getRecipeIds())
                && otherItem.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, quantity, description, locationIds, recipeIds, tags);
    }

    @Override
    public String toString() {
        return name;
    }

    /**
     * Creates a DetailedItem with the same fields.
     */
    public DetailedItem detailedItem() {
        return new DetailedItem(id, name, quantity, description, Set.copyOf(locationIds),
                Set.copyOf(recipeIds), tags);
    }

    @Override
    public DisplayedInventoryType getType() {
        return DisplayedInventoryType.ITEMS;
    }
}
