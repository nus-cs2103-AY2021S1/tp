package chopchop.model;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import chopchop.commons.core.GuiSettings;
import chopchop.commons.util.Pair;
import chopchop.model.ingredient.Ingredient;
import chopchop.model.ingredient.IngredientReference;
import chopchop.model.recipe.Recipe;
import chopchop.model.usage.IngredientUsage;
import chopchop.model.usage.RecipeUsage;
import javafx.collections.ObservableList;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Entry> PREDICATE_SHOW_ALL_ENTRIES = unused -> true;

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
    void setRecipeBook(ReadOnlyEntryBook<Recipe> recipeBook);

    /** Returns the ReadOnlyRecipeBook */
    ReadOnlyEntryBook<Recipe> getRecipeBook();

    /**
     * Returns true if a recipe with the same identity as {@code recipe} exists in the recipe book.
     */
    boolean hasRecipe(Recipe recipe);

    /**
     * Finds a recipe by name.
     */
    Optional<Recipe> findRecipeWithName(String name);

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
     * The recipe identity of {@code editedRecipe} must not be the same as another existing recipe in the recipe book.
     */
    void setRecipe(Recipe target, Recipe editedRecipe);

    /** Returns an unmodifiable view of the filtered recipe list */
    ObservableList<Recipe> getFilteredRecipeList();

    /**
     * Updates the filter of the filtered recipe list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredRecipeList(Predicate<? super Recipe> predicate);

    /**
     * Returns the user prefs' ingredient book file path.
     */
    Path getIngredientBookFilePath();

    /**
     * Sets the user prefs' ingredient book file path.
     */
    void setIngredientBookFilePath(Path ingredientBookFilePath);

    /**
     * Replaces ingredient book data with the data in {@code ingredientBook}.
     */
    void setIngredientBook(ReadOnlyEntryBook<Ingredient> ingredientBook);

    /** Returns the ReadOnlyIngredientBook */
    ReadOnlyEntryBook<Ingredient> getIngredientBook();

    /**
     * Returns true if an ingredient with the same identity as {@code ingredient} exists in the ingredient book.
     */
    boolean hasIngredient(Ingredient ingredient);

    /**
     * Deletes the given ingredient.
     * The ingredient must exist in the ingredient book.
     */
    void deleteIngredient(Ingredient ingredient);

    /**
     * Finds an ingredient by name.
     */
    Optional<Ingredient> findIngredientWithName(String name);

    /**
     * Adds the given ingredient.
     * {@code ingredient} must not already exist in the ingredient book.
     */
    void addIngredient(Ingredient ingredient);

    /**
     * Replaces the given ingredient {@code target} with {@code editedIngredient}.
     * {@code target} must exist in the ingredient book.
     * The ingredient identity of {@code editedIngredient} must not be the same as another existing ingredient in the
     * ingredient book.
     */
    void setIngredient(Ingredient target, Ingredient editedIngredient);

    /** Returns an unmodifiable view of the filtered ingredient list */
    ObservableList<Ingredient> getFilteredIngredientList();

    /**
     * Updates the filter of the filtered ingredient list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredIngredientList(Predicate<? super Ingredient> predicate);

    /**
     * Starts a bulk edit operation for ingredients. Every call to {@code startEditingIngredients} *MUST*
     * be paired with a corresponding call to {@code finishEditingIngredients}. These pairs can be nested.
     */
    void startEditingIngredients();

    /**
     * Finishes a bulk edit operation for ingredients. Every call to {@code finishEditingIngredients} *MUST*
     * be paired with a corresponding call to {@code startEditingIngredients}. These pairs can be nested.
     */
    void finishEditingIngredients();

    /** Returns the UsageList of recipe */
    UsageList<RecipeUsage> getRecipeUsageList();

    /** Return the List sorted by most made recipe. */
    List<Pair<String, String>> getMostMadeRecipeList();

    /** Returns the UsageList of ingredient */
    UsageList<IngredientUsage> getIngredientUsageList();

    /** Returns the 'actual' {@code ObservableList<>} backing the RecipeUsageList */
    ObservableList<RecipeUsage> getObservableRecipeUsages();

    /** Returns the 'actual' {@code ObservableList<>} backing the IngredientUsageList */
    ObservableList<IngredientUsage> getObservableIngredientUsages();

    void addRecipeUsage(Recipe recipe);

    void removeRecipeUsage(Recipe recipe);

    void addIngredientUsage(IngredientReference ingredient);

    void removeIngredientUsage(IngredientReference ingredient);

    /** Sets the RecipeUsageList */
    void setRecipeUsageList(UsageList<RecipeUsage> rl);

    /** Sets the IngredientUsageList */
    void setIngredientUsageList(UsageList<IngredientUsage> rl);

    List<Pair<String, String>> getRecipesMadeBetween(LocalDateTime after, LocalDateTime before);

    List<Pair<String, String>> getIngredientsUsedBetween(LocalDateTime after, LocalDateTime before);

    List<Pair<String, String>> getRecentlyUsedRecipes(int n);

    List<Pair<String, String>> getRecentlyUsedIngredients(int n);
}
