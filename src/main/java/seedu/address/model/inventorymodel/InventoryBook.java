package seedu.address.model.inventorymodel;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_REDO_LIMIT_REACHED;
import static seedu.address.commons.core.Messages.MESSAGE_UNDO_LIMIT_REACHED;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.logic.commands.exceptions.UndoRedoLimitReachedException;
import seedu.address.model.item.Item;
import seedu.address.model.item.UniqueItemList;

/**
 * Wraps all data at the inventory-book level
 * Duplicates are not allowed (by .isSameItem comparison)
 */
public class InventoryBook implements ReadOnlyInventoryBook {
    private static List<InventoryBook> inventoryBookStateList = new ArrayList<>();
    private static int inventoryBookStatePointer = -1;
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

    public InventoryBook() {}

    /**
     * Creates an InventoryBook using the Items in the {@code toBeCopied}
     */
    public InventoryBook(ReadOnlyInventoryBook toBeCopied) {
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
     * Resets the existing data of this {@code InventoryBook} with {@code newData}.
     */
    public void resetData(ReadOnlyInventoryBook newData) {
        requireNonNull(newData);
        setItems(newData.getItemList());
    }

    //// item-level operations

    /**
     * Returns true if a item with the same identity as {@code item} exists in the inventory book.
     */
    public boolean hasItem(Item item) {
        requireNonNull(item);
        return items.contains(item);
    }

    /**
     * Adds a item to the inventory book.
     * The item must not already exist in the inventory book.
     */
    public void addItem(Item p) {
        items.add(p);
    }

    /**
     * Combines quantity and tags of existing item with item provided
     * @param item item provided to combine with existing item
     * @return combined item
     */
    public Item addOnExistingItem(Item item) {
        return items.addOnExistingItem(item);
    }

    /**
     * Replaces the given item {@code target} in the list with {@code editedItem}.
     * {@code target} must exist in the inventory book.
     * The item identity of {@code editedItem} must not be the same as another existing item in the inventory book.
     */
    public void setItem(Item target, Item editedItem) {
        requireNonNull(editedItem);

        items.setItem(target, editedItem);
    }

    /**
     * Removes {@code key} from this {@code InventoryBook}.
     * {@code key} must exist in the inventory book.
     */
    public void removeItem(Item key) {
        items.remove(key);
    }

    //// undo-redo operations

    /**
     * Copies the current {@code InventoryBook} in the state list.
     */
    public void commit() {
        if (!inventoryBookStateList.isEmpty()) {
            inventoryBookStateList = inventoryBookStateList.subList(0, inventoryBookStatePointer + 1);
        }
        inventoryBookStateList.add(new InventoryBook(this));
        inventoryBookStatePointer++;
    }

    /**
     * Shifts the current {@code InventoryBook} to the previous one in the state list.
     * @throws UndoRedoLimitReachedException if there is nothing left to undo
     */
    public void undo() throws UndoRedoLimitReachedException {
        if (canUndo()) {
            inventoryBookStatePointer--;
            resetData(inventoryBookStateList.get(inventoryBookStatePointer));
        } else {
            throw new UndoRedoLimitReachedException(MESSAGE_UNDO_LIMIT_REACHED);
        }
    }

    /**
     * Shifts the current {@code InventoryBook} to the next one in the state list.
     * @throws UndoRedoLimitReachedException if there is nothing left to redo
     */
    public void redo() throws UndoRedoLimitReachedException {
        if (canRedo()) {
            inventoryBookStatePointer++;
            resetData(inventoryBookStateList.get(inventoryBookStatePointer));
        } else {
            throw new UndoRedoLimitReachedException(MESSAGE_REDO_LIMIT_REACHED);
        }
    }

    private boolean canUndo() {
        return inventoryBookStatePointer > 0;
    }

    private boolean canRedo() {
        return inventoryBookStatePointer < inventoryBookStateList.size() - 1;
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
                || (other instanceof InventoryBook // instanceof handles nulls
                && items.equals(((InventoryBook) other).items));
    }

    @Override
    public int hashCode() {
        return items.hashCode();
    }
}
