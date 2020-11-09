package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.item.Item;

/**
 * Unmodifiable view of an item list
 */
public interface ReadOnlyItemList {

    /**
     * Returns an unmodifiable view of the items list.
     * This list will not contain any duplicate items.
     */
    ObservableList<Item> getItemList();

}
