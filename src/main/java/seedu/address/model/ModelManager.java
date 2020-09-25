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
import seedu.address.model.recipe.Ingredient;
import seedu.address.model.recipe.Recipe;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final WishfulShrinking addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Recipe> filteredRecipes;
    private final FilteredList<Ingredient> filteredIngredients;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyWishfulShrinking addressBook, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new WishfulShrinking(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredRecipes = new FilteredList<>(this.addressBook.getRecipeList());
        filteredIngredients = new FilteredList<>(this.addressBook.getIngredientList());
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
    public void setWishfulShrinkingFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setWishfulShrinkingFilePath(addressBookFilePath);
    }

    //=========== WishfulShrinking ================================================================================

    @Override
    public void setWishfulShrinking(ReadOnlyWishfulShrinking addressBook) {
        this.addressBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyWishfulShrinking getWishfulShrinking() {
        return addressBook;
    }

    @Override
    public boolean hasRecipe(Recipe recipe) {
        requireNonNull(recipe);
        return addressBook.hasRecipe(recipe);
    }

    @Override
    public void deleteRecipe(Recipe target) {
        addressBook.removeRecipe(target);
    }

    @Override
    public void addRecipe(Recipe recipe) {
        addressBook.addRecipe(recipe);
        updateFilteredRecipeList(PREDICATE_SHOW_ALL_RECIPES);
    }

    @Override
    public void setRecipe(Recipe target, Recipe editedRecipe) {
        requireAllNonNull(target, editedRecipe);

        addressBook.setRecipe(target, editedRecipe);
    }

    @Override
    public boolean hasIngredient(Ingredient ingredient) {
        requireNonNull(ingredient);
        return addressBook.hasIngredient(ingredient);
    }

    @Override
    public void deleteIngredient(Ingredient target) {
        addressBook.removeIngredient(target);
    }

    @Override
    public void addIngredient(Ingredient ingredient) {
        addressBook.addIngredient(ingredient);
        updateFilteredIngredientList(PREDICATE_SHOW_ALL_INGREDIENTS);
    }

    @Override
    public void setIngredient(Ingredient target, Ingredient editedIngredient) {
        requireAllNonNull(target, editedIngredient);

        addressBook.setIngredient(target, editedIngredient);
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
        return addressBook.equals(other.addressBook)
                && userPrefs.equals(other.userPrefs)
                && filteredRecipes.equals(other.filteredRecipes)
                && filteredIngredients.equals(other.filteredIngredients);
    }

}
