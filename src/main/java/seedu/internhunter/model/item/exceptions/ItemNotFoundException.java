package seedu.internhunter.model.item.exceptions;

import seedu.internhunter.model.item.Item;

/**
 * Signals that the operation is unable to find the specified item.
 */
public class ItemNotFoundException extends RuntimeException {

    private static final String ITEM_NOT_FOUND_EXCEPTION = "Not able to find this %s item";

    /**
     * Initializes the ItemNotFoundException.
     *
     * @param item Item that is not found UniqueItemList.
     */
    public ItemNotFoundException(Item item) {
        super(String.format(ITEM_NOT_FOUND_EXCEPTION, item.getItemName()));
    }

}
