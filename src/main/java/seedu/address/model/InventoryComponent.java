package seedu.address.model;

import seedu.address.ui.DisplayedInventoryType;

/**
 * Encapsulates a possible Inventory entry, either an Item or a Recipe. Used for toggling displays.
 */
public abstract class InventoryComponent {
    public abstract DisplayedInventoryType getType();
}
