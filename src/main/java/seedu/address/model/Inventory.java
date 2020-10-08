package seedu.address.model;

import seedu.address.ui.View;

/**
 * Encapsulates a possible Inventory entry, either an Item or a Recipe.
 */
public abstract class Inventory {
    public abstract View.InventoryType getType();
}
