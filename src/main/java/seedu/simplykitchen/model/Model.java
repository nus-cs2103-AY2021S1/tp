package seedu.simplykitchen.model;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.simplykitchen.commons.core.GuiSettings;
import seedu.simplykitchen.model.food.Food;

/**
 * The API of the Model component.
 */
public interface Model {
    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Food> PREDICATE_SHOW_ALL_FOODS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' FoodInventory file path.
     */
    Path getFoodInventoryFilePath();

    /**
     * Sets the user prefs' FoodInventory file path.
     */
    void setFoodInventoryFilePath(Path foodInventoryFilePath);

    /**
     * Replaces the FoodInventory data with the data in {@code foodInventory}.
     */
    void setFoodInventory(ReadOnlyFoodInventory foodInventory);

    /**
     * Returns the FoodInventory.
     */
    ReadOnlyFoodInventory getFoodInventory();

    /**
     * Sets the sortingComparators information in the model.
     */
    void setSortingComparators(Comparator<Food>[] sortingComparators);

    /**
     * Returns true if a food item with the same identity as {@code food} exists in the FoodInventory.
     */
    boolean hasFood(Food food);

    /**
     * Deletes the given food item.
     * The food item must exist in the FoodInventory.
     */
    void deleteFood(Food target);

    /**
     * Adds the given food item.
     * {@code food} must not already exist in the FoodInventory.
     */
    void addFood(Food food);

    /**
     * Replaces the given food item {@code target} with {@code editedFood}.
     * {@code target} must exist in the FoodInventory.
     * The food item identity of {@code editedFood} must not be the same as another existing food item
     * in the FoodInventory.
     */
    void setFood(Food target, Food editedFood);

    /**
     * Sorts the FoodInventory data according to and in order of the input {@code comparators}.
     */
    void sortFoodInventory(Comparator<Food>... comparators);

    /**
     * Sorts the FoodInventory data according to the {@code sortingComparators} in Model.
     */
    void sortFoodInventoryBySortingComparators();

    /**
     * Returns an unmodifiable view of the filtered food list
     */
    ObservableList<Food> getFilteredFoodList();

    /**
     * Returns an unmodifiable view of the expiring food items in the filtered food list
     */
    ObservableList<Food> getFilteredExpiringFoodList();

    /**
     * Returns an unmodifiable view of the expired food items in the filtered food list
     */
    ObservableList<Food> getFilteredExpiredFoodList();

    /**
     * Updates the filter of the filtered food list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredFoodList(Predicate<Food> predicate);

    /**
     * Updates the Expiry Filtered List after any changes to the inventory.
     */
    void updateExpiringFilteredFoodList();

    /**
     * Returns true if the model has previous food inventory states to restore.
     */
    boolean canUndoFoodInventory();

    /**
     * Returns true if the model has undone FoodInventory states to restore.
     */
    boolean canRedoFoodInventory();

    /**
     * Restores the model's FoodInventory to its previous state.
     */
    void undoFoodInventory();

    /**
     * Restores the model's FoodInventory to its previously undone state.
     */
    void redoFoodInventory();

    /**
     * Saves the current FoodInventory state for undo/redo.
     */
    void commitFoodInventory();

    /**
     * Returns the predicate required to filter the Expiry food list.
     */
    Predicate<Food> getExpiringPredicate();

    /**
     * Returns the predicate required to filter the Expired food list.
     */
    Predicate<Food> getExpiredPredicate();

    /**
     * Sets the comparator of the sorted food list to sort by the given {@code comparator}.
     *
     * @throws NullPointerException if {@code comparator} is null.
     */
    void updateExpiringSortedFoodList();

    /**
     * Returns true if the data file format is invalid.
     */
    boolean isDataFileInvalid();
}
