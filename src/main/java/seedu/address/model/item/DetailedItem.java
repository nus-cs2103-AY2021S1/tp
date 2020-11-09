package seedu.address.model.item;

import java.util.Set;

import seedu.address.model.tag.Tag;
import seedu.address.ui.DisplayedInventoryType;

/**
 * Represents a Detailed Item which may be displayed.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class DetailedItem extends Item {

    /**
     * Constructs a detailed item based on the given fields.
     */
    public DetailedItem(int id, String name, Quantity quantity, String description, Set<Integer> locationIds,
                Set<Integer> recipeIds, Set<Tag> tags) {
        super(id, name, quantity, description, locationIds, recipeIds, tags);
    }

    @Override
    public DisplayedInventoryType getType() {
        return DisplayedInventoryType.DETAILED_ITEM;
    }
}
