package chopchop.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import chopchop.commons.core.GuiSettings;
import chopchop.model.recipe.Recipe;
import chopchop.model.ingredient.Ingredient;
import javafx.collections.ObservableList;
import chopchop.commons.core.GuiSettings;


/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Recipe> PREDICATE_SHOW_ALL_RECIPES = unused -> true;
    Predicate<FoodEntry> PREDICATE_SHOW_ALL_INGREDIENTS = unused -> true;

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
     * Returns the user prefs' recipe book file path.
     */
    Path getRecipeBookFilePath();

    /**
     * Sets the user prefs' recipe book file path.
     */
    void setRecipeBookFilePath(Path recipeBookFilePath);

    /**
     * Replaces recipe book data with the data in {@code recipeBook}.
     */
    void setRecipeBook(ReadOnlyRecipeBook recipeBook);

    /** Returns the RecipeBook */
    ReadOnlyRecipeBook getRecipeBook();

    /**
     * Returns true if a recipe with the same identity as {@code recipe} exists in the recipe book.
     */
    boolean hasRecipe(Recipe recipe);

    /**
     * Deletes the given recipe.
     * The recipe must exist in the recipe book.
     */
    void deleteRecipe(Recipe target);

    /**
     * Adds the given recipe.
     * {@code recipe} must not already exist in the recipe book.
     */
    void addRecipe(Recipe recipe);

    /**
     * Replaces the given recipe {@code target} with {@code editedRecipe}.
     * {@code target} must exist in the recipe book.
     * The recipe identity of {@code editedPerson} must not be the same as another existing recipe in the recipe book.
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
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyFoodEntryBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyFoodEntryBook getIngredientBook();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasIngredient(Ingredient person);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deleteIngredient(Ingredient target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addIngredient(Ingredient person);

    /**
     * Replaces the given person {@code target} with {@code editedIngredient}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedIngredient} must not be the same as another existing person in the
     * address book.
     */
    void setIngredient(Ingredient target, Ingredient editedIngredient);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<FoodEntry> getFilteredIngredientList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredIngredientList(Predicate<FoodEntry> predicate);

}
