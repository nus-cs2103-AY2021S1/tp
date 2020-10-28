package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.item.Item;
import seedu.address.model.item.UniqueItemList;

/**
 * Wraps all data at the item-list level
 * Duplicates are not allowed (by .isSameItem comparison)
 */
public class ItemList implements ReadOnlyItemList {

    private final UniqueItemList items;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        items = new UniqueItemList();
    }

    public ItemList() {}

    /**
     * Creates an ItemList using the Items in the {@code toBeCopied}.
     */
    public ItemList(ReadOnlyItemList toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the item list with {@code items}.
     * {@code items} must not contain duplicate items.
     */
    public void setItems(List<Item> items) {
        this.items.setItems(items);
    }

    /**
     * Resets the existing data of this {@code ItemList} with {@code newData}.
     */
    public void resetData(ReadOnlyItemList newData) {
        requireNonNull(newData);

        setItems(newData.getItemList());
    }

    //// item-level operations

    /**
     * Returns true if a item with the same identity as {@code item} exists in the item list.
     */
    public boolean hasItem(Item item) {
        requireNonNull(item);
        return items.contains(item);
    }

    /**
     * Adds a item to the item list.
     * The item must not already exist in the item list.
     */
    public void addItem(Item p) {
        items.add(p);
    }

    /**
     * Replaces the given item {@code target} in the list with {@code editedItem}.
     * {@code target} must exist in the item list.
     * The item identity of {@code editedItem} must not be the same as another existing item in the item list.
     */
    public void setItem(Item target, Item editedItem) {
        requireNonNull(editedItem);
        items.setItem(target, editedItem);
    }

    /**
     * Deletes {@code item} from this {@code ItemList}.
     * {@code item} must exist in the item list.
     */
    public void deleteItem(Item item) {
        items.remove(item);
    }

    /**
     * Removes {@code key} from this {@code ItemList}.
     * {@code key} must exist in the item list.
     */
    public void removeItem(Item key) {
        items.remove(key);
    }

    public int findItemIdByName(String itemName) {
        return items.findItemId(itemName);
    }

    public void addRecipeIdToItem(int itemId, int recipeId) {
        items.addRecipeIdToItem(itemId, recipeId);
    }

    //// util methods

    @Override
    public String toString() {
        return items.asUnmodifiableObservableList().size() + " items";
        // TODO: refine later
    }

    @Override
    public ObservableList<Item> getItemList() {
        return items.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ItemList // instanceof handles nulls
                && items.equals(((ItemList) other).items));
    }

    @Override
    public int hashCode() {
        return items.hashCode();
    }
}
