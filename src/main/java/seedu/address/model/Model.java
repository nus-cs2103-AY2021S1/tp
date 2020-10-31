package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.consumption.Consumption;
import seedu.address.model.ingredient.Ingredient;
import seedu.address.model.recipe.Recipe;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Recipe> PREDICATE_SHOW_ALL_RECIPES = unused -> true;
    Predicate<Ingredient> PREDICATE_SHOW_ALL_INGREDIENTS = unused -> true;
    Predicate<Consumption> PREDICATE_SHOW_ALL_CONSUMPTION = unused -> true;

    //=========== UserPref ================================================================================

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
     * Returns the user prefs' Wishful Shrinking file path.
     */
    Path getWishfulShrinkingFilePath();

    /**
     * Sets the user prefs' Wishful Shrinking file path.
     */
    void setWishfulShrinkingFilePath(Path wishfulShrinkingFilePath);

    //=========== WishfulShrinking ================================================================================

    /**
     * Replaces Wishful Shrinking data with the data in {@code wishfulShrinking}.
     */
    void setWishfulShrinking(ReadOnlyWishfulShrinking wishfulShrinking);

    /** Returns the WishfulShrinking */
    ReadOnlyWishfulShrinking getWishfulShrinking();

    //=========== Recipe ================================================================================

    /**
     * Returns true if a recipe with the same identity as {@code recipe} exists in the recipe collection.
     */
    boolean hasRecipe(Recipe recipe);

    /**
     * Deletes the given recipe.
     * The recipe must exist in the Wishful Shrinking.
     */
    void deleteRecipe(Recipe target);

    /**
     * Adds the given recipe.
     * {@code recipe} must not already exist in the recipe collection.
     */
    void addRecipe(Recipe recipe);

    /**
     * Replaces the given recipe {@code target} with {@code editedRecipe}.
     * {@code target} must exist in the recipe collection.
     * The recipe identity of {@code editedRecipe} must not be the same as
     * another existing recipe in the recipe collection.
     */
    void setRecipe(Recipe target, Recipe editedRecipe);

    /** Returns an unmodifiable view of the filtered recipe list */
    ObservableList<Recipe> getFilteredRecipeList();

    /**
     * Updates the filter of the filtered recipe list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredRecipeList(Predicate<Recipe> predicate);

    /**
     * Clears all the recipe.
     */
    void clearRecipe();

    //=========== Consumption ================================================================================

    /**
     * Eat the given recipe.
     * The recipe must exist in the recipe Collection.
     */
    void addConsumption(Consumption target);

    /**
     * Deletes the given consumption.
     * The ingredient must exist in the consumption list.
     */
    void deleteConsumption(Consumption target);

    /** Returns an unmodifiable view of the filtered consumption list */
    ObservableList<Consumption> getFilteredConsumptionList();

    /**
     * Updates the filter of the filtered consumption list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredConsumptionList(Predicate<Consumption> predicate);

    /**
     * Clears all the Consumption.
     */
    void clearConsumption();

    //=========== Ingredient ================================================================================

    /**
     * Returns true if an ingredient with the same identity as {@code ingredient} exists in the fridge.
     */
    boolean hasIngredient(Ingredient ingredient);

    /**
     * Deletes the given ingredient.
     * The ingredient must exist in the fridge.
     */
    void deleteIngredient(Ingredient target);

    /**
     * Adds the given ingredient.
     * {@code ingredient} must not already exist in the fridge.
     */
    void addIngredient(Ingredient ingredient);

    /**
     * Replaces the given ingredient {@code target} with {@code editedIngredient}.
     * {@code target} must exist in the fridge.
     * The ingredient identity of {@code editedIngredient} must not be the same as another existing ingredient
     * in the fridge.
     */
    void setIngredient(Ingredient target, Ingredient editedIngredient);

    /** Returns an unmodifiable view of the filtered ingredient list */
    ObservableList<Ingredient> getFilteredIngredientList();

    /**
     * Updates the filter of the filtered ingredient list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredIngredientList(Predicate<Ingredient> predicate);

    /**
     * Clears all the Ingredient.
     */
    void clearIngredient();

}
