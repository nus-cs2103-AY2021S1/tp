package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.consumption.Consumption;
import seedu.address.model.ingredient.Ingredient;
import seedu.address.model.recipe.Recipe;

/**
 * Represents the in-memory model of the Wishful Shrinking data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final WishfulShrinking wishfulShrinking;
    private final UserPrefs userPrefs;
    private final FilteredList<Recipe> filteredRecipes;
    private final FilteredList<Ingredient> filteredIngredients;
    private final FilteredList<Consumption> filteredConsumption;

    /**
     * Initializes a ModelManager with the given wishfulShrinking and userPrefs.
     */
    public ModelManager(ReadOnlyWishfulShrinking wishfulShrinking, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(wishfulShrinking, userPrefs);

        logger.fine("Initializing with Wishful Shrinking: " + wishfulShrinking + " and user prefs " + userPrefs);

        this.wishfulShrinking = new WishfulShrinking(wishfulShrinking);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredRecipes = new FilteredList<>(this.wishfulShrinking.getRecipeList());
        filteredIngredients = new FilteredList<>(this.wishfulShrinking.getIngredientList());
        filteredConsumption = new FilteredList<>(this.wishfulShrinking.getConsumptionList());
    }

    public ModelManager() {
        this(new WishfulShrinking(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getWishfulShrinkingFilePath() {
        return userPrefs.getWishfulShrinkingFilePath();
    }

    @Override
    public void setWishfulShrinkingFilePath(Path wishfulShrinkingFilePath) {
        requireNonNull(wishfulShrinkingFilePath);
        userPrefs.setWishfulShrinkingFilePath(wishfulShrinkingFilePath);
    }

    //=========== WishfulShrinking ================================================================================

    @Override
    public void setWishfulShrinking(ReadOnlyWishfulShrinking wishfulShrinking) {
        this.wishfulShrinking.resetData(wishfulShrinking);
    }

    @Override
    public ReadOnlyWishfulShrinking getWishfulShrinking() {
        return wishfulShrinking;
    }

    //=========== Recipe ================================================================================

    @Override
    public boolean hasRecipe(Recipe recipe) {
        requireNonNull(recipe);
        return wishfulShrinking.hasRecipe(recipe);
    }

    @Override
    public void deleteRecipe(Recipe target) {
        wishfulShrinking.removeRecipe(target);
    }

    @Override
    public void addRecipe(Recipe recipe) {
        wishfulShrinking.addRecipe(recipe);
        updateFilteredRecipeList(PREDICATE_SHOW_ALL_RECIPES);
    }

    @Override
    public void setRecipe(Recipe target, Recipe editedRecipe) {
        requireAllNonNull(target, editedRecipe);

        wishfulShrinking.setRecipe(target, editedRecipe);
    }

    @Override
    public void clearRecipe() {
        wishfulShrinking.clearRecipe();
    }

    //=========== Consumption ================================================================================

    @Override
    public void addConsumption(Consumption target) {
        wishfulShrinking.addConsumption(target);
    }

    @Override
    public void deleteConsumption(Consumption target) {
        wishfulShrinking.removeConsumption(target);
    }

    @Override
    public void clearConsumption() {
        wishfulShrinking.clearConsumption();
    }

    //=========== Ingredient ================================================================================

    @Override
    public boolean hasIngredient(Ingredient ingredient) {
        requireNonNull(ingredient);
        return wishfulShrinking.hasIngredient(ingredient);
    }

    @Override
    public void deleteIngredient(Ingredient target) {
        wishfulShrinking.removeIngredient(target);
    }

    @Override
    public void addIngredient(Ingredient ingredient) {
        wishfulShrinking.addIngredient(ingredient);
        updateFilteredIngredientList(PREDICATE_SHOW_ALL_INGREDIENTS);
    }

    @Override
    public void setIngredient(Ingredient target, Ingredient editedIngredient) {
        requireAllNonNull(target, editedIngredient);

        wishfulShrinking.setIngredient(target, editedIngredient);
    }

    @Override
    public void clearIngredient() {
        wishfulShrinking.clearIngredient();
    }

    //=========== Filtered Recipe List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Recipe} backed by the internal list of
     * {@code versionedWishfulShrinking}
     */
    @Override
    public ObservableList<Recipe> getFilteredRecipeList() {
        return filteredRecipes;
    }

    @Override
    public void updateFilteredRecipeList(Predicate<Recipe> predicate) {
        requireNonNull(predicate);
        filteredRecipes.setPredicate(predicate);
    }

    //=========== Filtered Consumption List Accessors =============================================================
    @Override
    public ObservableList<Consumption> getFilteredConsumptionList() {
        return filteredConsumption;
    }

    @Override
    public void updateFilteredConsumptionList(Predicate<Consumption> predicate) {
        requireNonNull(predicate);
        filteredConsumption.setPredicate(predicate);
    }

    //=========== Filtered Ingredients List Accessors =============================================================
    /**
     * Returns an unmodifiable view of the list of {@code Ingredient} backed by the internal list of
     * {@code versionedWishfulShrinking}
     */
    @Override
    public ObservableList<Ingredient> getFilteredIngredientList() {
        return filteredIngredients;
    }

    @Override
    public void updateFilteredIngredientList(Predicate<Ingredient> predicate) {
        requireNonNull(predicate);
        filteredIngredients.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;

        return wishfulShrinking.equals(other.wishfulShrinking)
                && userPrefs.equals(other.userPrefs)
                && filteredRecipes.equals(other.filteredRecipes)
                && filteredIngredients.equals(other.filteredIngredients)
                && filteredConsumption.equals(other.filteredConsumption);
    }

}
