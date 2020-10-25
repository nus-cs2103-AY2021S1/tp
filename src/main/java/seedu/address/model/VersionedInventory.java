package seedu.address.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Inventory with a state pointer to facilitate undo/redo commands.
 */
public class VersionedInventory {

    /** Saved states of inventory. */
    private final List<Inventory> inventoryStateList;

    /** Current state pointer. */
    private int currentStatePointer;

    /**
     * Constructs a state based on current inventory on initiation.
     */
    public VersionedInventory(Inventory inventory) {
        this.inventoryStateList = new ArrayList<>();
        // deep copy
        this.inventoryStateList.add(new Inventory(new ItemList(inventory.getItemList()),
                new LocationList(inventory.getLocationList()), new RecipeList(inventory.getRecipeList())));
        currentStatePointer = 0;
    }

    /**
     * Adds the current inventory's state to the saved states.
     * Used when a command alters the state of the inventory.
     *
     * @param model current model
     */
    public void commit(Model model) {
        Inventory state = new Inventory(new ItemList(model.getItemList()),
                new LocationList(model.getLocationList()), new RecipeList(model.getRecipeList()));

        if (inventoryStateList.size() > currentStatePointer + 1) {
            inventoryStateList.subList(currentStatePointer + 1, inventoryStateList.size()).clear();
        }

        inventoryStateList.add(state);

        currentStatePointer++;
    }

    /**
     * Sets the state to the previous state.
     *
     * @param model current model
     * @return true on successful undo, returns false when no previous state exists
     */
    public boolean undo(Model model) {
        if (currentStatePointer - 1 < 0) {
            return false;
        }

        Inventory prevState = inventoryStateList.get(currentStatePointer - 1);

        assert prevState != null : "Previous state is invalid!";

        model.setItemList(new ItemList(inventoryStateList.get(currentStatePointer - 1).getItemList()));
        model.setRecipeList(new RecipeList(inventoryStateList.get(currentStatePointer - 1).getRecipeList()));
        model.setLocationList(new LocationList(inventoryStateList.get(currentStatePointer - 1).getLocationList()));

        currentStatePointer--;

        return true;
    }

    /**
     * Sets the state to the next state.
     *
     * @param model current model
     * @return true on successful redo, returns false when no next state exists
     */
    public boolean redo(Model model) {
        if (currentStatePointer + 1 >= inventoryStateList.size()) {
            return false;
        }

        Inventory nextState = inventoryStateList.get(currentStatePointer + 1);

        assert nextState != null : "Next state is invalid!";

        model.setItemList(new ItemList(inventoryStateList.get(currentStatePointer + 1).getItemList()));
        model.setRecipeList(new RecipeList(inventoryStateList.get(currentStatePointer + 1).getRecipeList()));
        model.setLocationList(new LocationList(inventoryStateList.get(currentStatePointer + 1).getLocationList()));

        currentStatePointer++;

        return true;
    }

}
