package seedu.simplykitchen.model;

import javafx.beans.Observable;
import javafx.collections.ObservableList;
import seedu.simplykitchen.model.food.Food;

/**
 * Unmodifiable view of a Food inventory.
 */
public interface ReadOnlyFoodInventory extends Observable {

    /**
     * Returns an unmodifiable view of the food list.
     * This list will not contain any duplicate food items.
     */
    ObservableList<Food> getFoods();

}
