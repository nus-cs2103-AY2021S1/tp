package seedu.address.model.item;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.model.tag.Tag;

public class ItemPrecursor {

    private final int id;
    private final String name;

    private final Quantity quantity;
    private final String description;
    private final List<String> locationNames = new ArrayList<>();
    private final Set<Tag> tags = new HashSet<>();

    public ItemPrecursor(int id, String name, Quantity quantity, String description,
                         Set<String> locationNames, Set<Tag> tags) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.description = description;
        this.locationNames.addAll(locationNames);
        this.tags.addAll(tags);
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

    public List<String> getLocationNames() {
        return locationNames;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public Item toItem(Set<Integer> locationIds, Set<Integer> recipeIds) {
        return new Item(id, name, quantity, description, locationIds, recipeIds, tags, false);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof ItemPrecursor)) {
            return false;
        }

        ItemPrecursor otherItem = (ItemPrecursor) other;
        return otherItem.getId() == getId()
                && otherItem.getName().equals(getName())
                && otherItem.getQuantity().equals(getQuantity())
                && otherItem.getDescription().equals(getDescription())
                && otherItem.getLocationNames().equals(getLocationNames())
                && otherItem.getTags().equals(getTags());
    }
}
