package seedu.address.model.item;

import seedu.address.model.tag.Tag;
import seedu.address.ui.View;

import java.util.Set;

public class DetailedItem extends Item{
    public DetailedItem(int id, String name, Quantity quantity, String description, Set<Integer> locationIds,
                Set<Integer> recipeIds, Set<Tag> tags, boolean isDeleted) {
        super(id, name, quantity, description, locationIds, recipeIds, tags, isDeleted);
    }

    @Override
    public View.InventoryType getType() {
        return View.InventoryType.DETAILED_ITEM;
    }
}
