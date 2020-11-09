package seedu.address.model;

/**
 * Convenience class that stores item list, recipe list and location list.
 * Helps to maintain inventory state.
 */
public class Inventory {

    private final ItemList itemList;
    private final LocationList locationList;
    private final RecipeList recipeList;

    /**
     * Constructs an Inventory.
     *
     * @param itemList item list associated with inventory
     * @param recipeList recipe list associated with inventory
     * @param locationList location list associated with inventory
     */
    public Inventory(ItemList itemList, LocationList locationList, RecipeList recipeList) {
        this.itemList = itemList;
        this.locationList = locationList;
        this.recipeList = recipeList;
    }

    ItemList getItemList() {
        return this.itemList;
    }

    LocationList getLocationList() {
        return this.locationList;
    }

    RecipeList getRecipeList() {
        return this.recipeList;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (other instanceof Inventory) {
            Inventory otherInventory = (Inventory) other;
            return this.itemList.equals(otherInventory.itemList)
                    && this.locationList.equals(otherInventory.locationList)
                    && this.recipeList.equals(otherInventory.recipeList);
        }

        return false;
    }

}
