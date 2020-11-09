package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.item.Item;
import seedu.address.model.item.ItemPrecursor;
import seedu.address.model.location.Location;
import seedu.address.model.recipe.Recipe;
import seedu.address.model.recipe.RecipePrecursor;

/**
 * The API of the Model component.
 */
public interface Model {
    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Item> PREDICATE_SHOW_ALL_ITEMS = unused -> true;
    Predicate<Location> PREDICATE_SHOW_ALL_LOCATIONS = unused -> true;
    Predicate<Recipe> PREDICATE_SHOW_ALL_RECIPES = unused -> true;

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
     * Returns the user prefs' item list file path.
     */
    Path getItemListFilePath();

    /**
     * Returns the user prefs' location list file path.
     */
    Path getLocationListFilePath();

    /**
     * Returns the user prefs' recipe list file path.
     */
    Path getRecipeListFilePath();

    /**
     * Sets the user prefs' item list file path.
     */
    void setItemListFilePath(Path itemListFilePath);

    /**
     * Sets the user prefs' location list file path.
     */
    void setLocationListFilePath(Path locationListFilePath);

    /**
     * Sets the user prefs' recipe list file path.
     */
    void setRecipeListFilePath(Path recipeListFilePath);

    /**
     * Replaces item list data with the data in {@code itemList}.
     */
    void setItemList(ReadOnlyItemList itemList);

    /**
     * Replaces recipe list data with the data in {@code recipeList}.
     */
    void setRecipeList(ReadOnlyRecipeList recipeList);

    /**
     * Returns the ItemList
     */
    ReadOnlyItemList getItemList();

    /**
     * Returns the LocationList
     */
    ReadOnlyLocationList getLocationList();

    /**
     * Returns the RecipeList
     */
    ReadOnlyRecipeList getRecipeList();

    /**
     * Returns true if an item with the same identity as {@code item} exists in the item list.
     */
    boolean hasItem(Item item);

    /**
     * Returns true if a location with the same identity as {@code location} exists in the location list.
     */
    boolean hasLocation(Location location);

    /**
     * Returns true if a recipe with the same identity as {@code recipe} exists in the recipe list.
     */
    boolean hasRecipe(Recipe recipe);

    /**
     * Deletes the given item.
     * The item must exist in the item list.
     */
    void deleteItem(Item target);

    /**
     * Deletes the given recipe.
     * The recipe must exist in the recipe list.
     */
    void deleteRecipe(Recipe target);

    /**
     * Adds the given item.
     * {@code item} must not already exist in the item list.
     */
    void addItem(Item item);

    /**
     * Adds the given location.
     * {@code location} must not already exist in the location list.
     */
    void addLocation(Location location);

    /**
     * Adds the given recipe.
     * {@code recipe} must not already exist in the recipe list.
     */
    void addRecipe(Recipe recipe);

    /**
     * Replaces the given item {@code target} with {@code editedItem}.
     * {@code target} must exist in the item list.
     * The item identity of {@code editedItem} must not be the same as another existing item in the item list.
     */
    void setItem(Item target, Item editedItem);

    /**
     * Replaces the given recipe {@code target} with {@code editedRecipe}.
     * {@code target} must exist in the recipe list.
     * The recipe identity of {@code editedRecipe} must not be the same as another existing recipe in the recipe list.
     */
    void setRecipe(Recipe target, Recipe editedRecipe);

    /**
     * Returns an unmodifiable view of the filtered item list
     */
    ObservableList<Item> getFilteredItemList();

    /**
     * Returns an unmodifiable view of the filtered location list
     */
    ObservableList<Location> getFilteredLocationList();

    /**
     * Returns an unmodifiable view of the filtered recipe list
     */
    ObservableList<Recipe> getFilteredRecipeList();

    /**
     * Updates the filter of the filtered item list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredItemList(Predicate<Item> predicate);

    /**
     * Updates the filter of the filtered location list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredLocationList(Predicate<Location> predicate);

    /**
     * Updates the filter of the filtered recipe list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredRecipeList(Predicate<Recipe> predicate);

    /**
     * Returns the ID of the given location to find.
     */
    int findLocationID(Location toFind);

    /**
     * Processes a RecipePrecursor into a Recipe.
     */
    Recipe processPrecursor(RecipePrecursor recipePrecursor);

    /**
     * Processes an ItemPrecursor into an Item.
     */
    Item processPrecursor(ItemPrecursor itemPrecursor);

    /**
     * Updates recipe product names.
     */
    void updateRecipeNames(String originalName, String updatedName);

    /**
     * Resets the filter to show all items in the item list.
     */
    void resetItemFilters();

    /**
     * Resets the filter to show all recipes in the recipe list.
     */
    void resetRecipeFilters();

    void setLocationList(LocationList locationList);

    /**
     * Commits the current state of the inventory to VersionedInventory.
     */
    void commitInventory();

    /**
     * Sets inventory to previous state.
     *
     * @return true on successful undo, returns false when no previous state exists
     */
    boolean undoInventory();

    /**
     * Sets inventory to next state.
     *
     * @return true on successful redo, returns false when no next state exists
     */
    boolean redoInventory();
}
