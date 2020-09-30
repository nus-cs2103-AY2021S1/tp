package chopchop.model;

import static java.util.Objects.requireNonNull;
import static chopchop.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import chopchop.model.ingredient.IngredientBook;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import chopchop.commons.core.GuiSettings;
import chopchop.commons.core.LogsCenter;
import chopchop.model.ingredient.Ingredient;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final IngredientBook ingredientBook;
    private final UserPrefs userPrefs;
    private final FilteredList<FoodEntry> filteredIngredients;

    /**
     * Initializes a ModelManager with the given ingredientBook and userPrefs.
     */
    public ModelManager(ReadOnlyFoodEntryBook ingredientBook, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(ingredientBook, userPrefs);

        logger.fine("Initializing with address book: " + ingredientBook + " and user prefs " + userPrefs);

        this.ingredientBook = new IngredientBook(ingredientBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredIngredients = new FilteredList<FoodEntry>(this.ingredientBook.getFoodEntryList());
    }

    public ModelManager() {
        this(new IngredientBook(), new UserPrefs());
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
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path ingredientBookFilePath) {
        requireNonNull(ingredientBookFilePath);
        userPrefs.setAddressBookFilePath(ingredientBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setAddressBook(ReadOnlyFoodEntryBook ingredientBook) {
        this.ingredientBook.resetData(ingredientBook);
    }

    @Override
    public ReadOnlyFoodEntryBook getIngredientBook() {
        return ingredientBook;
    }

    @Override
    public boolean hasIngredient(Ingredient person) {
        requireNonNull(person);
        return ingredientBook.hasIngredient(person);
    }

    @Override
    public void deleteIngredient(Ingredient target) {
        ingredientBook.removeIngredient(target);
    }

    @Override
    public void addIngredient(Ingredient person) {
        ingredientBook.addIngredient(person);
        updateFilteredIngredientList(PREDICATE_SHOW_ALL_INGREDIENTS);
    }

    @Override
    public void setIngredient(Ingredient target, Ingredient editedIngredient) {
        requireAllNonNull(target, editedIngredient);

        ingredientBook.setIngredient(target, editedIngredient);
    }

    //=========== Filtered Ingredient List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Ingredient} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<FoodEntry> getFilteredIngredientList() {
        return filteredIngredients;
    }

    @Override
    public void updateFilteredIngredientList(Predicate<FoodEntry> predicate) {
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
        return ingredientBook.equals(other.ingredientBook)
                && userPrefs.equals(other.userPrefs)
                && filteredIngredients.equals(other.filteredIngredients);
    }

}
