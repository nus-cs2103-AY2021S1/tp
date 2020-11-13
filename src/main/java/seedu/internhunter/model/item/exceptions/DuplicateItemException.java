package seedu.internhunter.model.item.exceptions;

import seedu.internhunter.model.item.Item;

/**
 * Signals that the operation will result in duplicate Items (Items are considered duplicates if they have the same
 * identity).
 */
public class DuplicateItemException extends RuntimeException {

    private static final String ITEM_DUPLICATE_EXCEPTION = "Operation would result in duplicate %s items";

    /**
     * Initializes the DuplicateItemException.
     *
     * @param item Item that has a duplicate.
     */
    public DuplicateItemException(Item item) {
        super(String.format(ITEM_DUPLICATE_EXCEPTION, item.getItemName()));
    }

}
