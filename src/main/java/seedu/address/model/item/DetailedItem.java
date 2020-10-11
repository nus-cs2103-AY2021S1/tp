package seedu.address.model.item;

import java.util.Set;

import seedu.address.model.tag.Tag;
import seedu.address.ui.DisplayedInventoryType;

/**
 * Represents an Detailed Item which may be displayed.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class DetailedItem extends Item {
    public DetailedItem(int id, String name, Quantity quantity, String description, Set<Integer> locationIds,
                Set<Integer> recipeIds, Set<Tag> tags, boolean isDeleted) {
        super(id, name, quantity, description, locationIds, recipeIds, tags, isDeleted);
    }

    @Override
    public DisplayedInventoryType getType() {
        return DisplayedInventoryType.DETAILED_ITEM;
    }
}
