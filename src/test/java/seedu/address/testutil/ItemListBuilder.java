package seedu.address.testutil;

import seedu.address.model.ItemList;
import seedu.address.model.item.Item;

/**
 * A utility class to help with building ItemList objects.
 * Example usage: <br>
 *     {@code ItemList ab = new ItemListBuilder().withItem("Apple").build();}
 */
public class ItemListBuilder {

    private ItemList itemList;

    public ItemListBuilder() {
        itemList = new ItemList();
    }

    public ItemListBuilder(ItemList itemList) {
        this.itemList = itemList;
    }

    /**
     * Adds a new {@code Item} to the {@code ItemList} that we are building.
     */
    public ItemListBuilder withItem(Item item) {
        itemList.addItem(item);
        return this;
    }

    public ItemList build() {
        return itemList;
    }

}
