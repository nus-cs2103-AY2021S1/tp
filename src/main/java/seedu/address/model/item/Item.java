package seedu.address.model.item;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents an Item in the Inventoryinator.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Item {

    //item:
    //int id
    //string name
    //int quantity
    //string description
    //list<integer> locationId
    //list<integer> recipe ids that create this item
    //boolean isDeleted

    private static int idCounter = 0;

    // Identity fields
    private final int id;
    private final String name;

    // Data fields
    private final int quantity;
    private final String description;
    private final List<Integer> locationId = new ArrayList<>();
    private final List<Integer> recipeId = new ArrayList<>();
    private final Set<Tag> tags = new HashSet<>();
    private final boolean isDeleted;

    /**
     * Every field must be present and not null.
     */
    public Item(int id, String name, int quantity, String description, Set<Integer> locationId,
                Set<Integer> recipeId, Set<Tag> tags, boolean isDeleted) {
        requireAllNonNull(id, name, quantity, description, locationId, recipeId, tags, isDeleted);
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.description = description;
        this.locationId.addAll(locationId);
        this.recipeId.addAll(recipeId);
        this.tags.addAll(tags);
        this.isDeleted = isDeleted;
        idCounter++;
    }

    public static int getIdCounter() {
        return idCounter;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getDescription() {
        return description;
    }

    public List<Integer> getLocationId() {
        return locationId;
    }

    public List<Integer> getRecipeId() {
        return recipeId;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    /**
     * Returns true if both persons of the same name have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSameItem(Item otherItem) {
        if (otherItem == this) {
            return true;
        }

        return otherItem != null
                && otherItem.getName().equals(getName());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
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
        return otherItem.getId() == getId()
                && otherItem.getName().equals(getName())
                && otherItem.getQuantity() == (getQuantity())
                && otherItem.getDescription().equals(getDescription())
                && otherItem.getLocationId().equals(getLocationId())
                && otherItem.getRecipeId().equals(getRecipeId())
                && otherItem.getTags().equals(getTags())
                && otherItem.isDeleted() == isDeleted();
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(id, name, quantity, description, locationId, recipeId, tags, isDeleted);
    }

    @Override
    public String toString() {
        /*
        final StringBuilder builder = new StringBuilder();
        builder.append("Id:")
                .append(getId())
                .append(" Name: ")
                .append(getName())
                .append(" Quantity: ")
                .append(getQuantity())
                .append(" Description: ")
                .append(getDescription())
                .append(" Location Ids: ");
        getLocationId().forEach(builder::append);
        builder.append(" Recipe Ids: ");
        getRecipeId().forEach(builder::append);
        builder.append(" Tags: ");
        getTags().forEach(builder::append);
        builder.append(" Is deleted: ")
                .append(isDeleted());
        return builder.toString();
         */
        return name;
    }

}
