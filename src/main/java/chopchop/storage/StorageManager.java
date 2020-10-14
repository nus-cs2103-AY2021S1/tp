package chopchop.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import chopchop.commons.core.LogsCenter;
import chopchop.commons.exceptions.DataConversionException;
import chopchop.model.ReadOnlyEntryBook;
import chopchop.model.ReadOnlyUserPrefs;
import chopchop.model.UserPrefs;
import chopchop.model.ingredient.Ingredient;
import chopchop.model.recipe.Recipe;

/**
 * Manages storage of IngredientBook and RecipeBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private final IngredientBookStorage ingredientBookStorage;
    private final RecipeBookStorage recipeBookStorage;
    private final UserPrefsStorage userPrefsStorage;


    /**
     * Creates a {@code StorageManager} with the given {@code RecipeBookStorage}, {@code IngredientBookStorage} and
     * {@code UserPrefStorage}.
     */
    public StorageManager(RecipeBookStorage recipeBookStorage, IngredientBookStorage ingredientBookStorage,
                          UserPrefsStorage userPrefsStorage) {
        super();
        this.recipeBookStorage = recipeBookStorage;
        this.ingredientBookStorage = ingredientBookStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }

    @Override
    public Path getIngredientBookFilePath() {
        return ingredientBookStorage.getIngredientBookFilePath();
    }

    @Override
    public Optional<ReadOnlyEntryBook<Recipe>> readRecipeBook()
        throws DataConversionException, IOException {
        return readRecipeBook(recipeBookStorage.getRecipeBookFilePath());
    }

    /**
     * Read and parse json file into recipe book.
     *
     * @param filePath relative path where the json file is saved.
     * @see #getRecipeBookFilePath()
     */
    @Override
    public Optional<ReadOnlyEntryBook<Recipe>> readRecipeBook(Path filePath)
        throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return recipeBookStorage.readRecipeBook(filePath);
    }

    @Override
    public Path getRecipeBookFilePath() {
        return recipeBookStorage.getRecipeBookFilePath();
    }

    @Override
    public Optional<ReadOnlyEntryBook<Ingredient>> readIngredientBook()
        throws DataConversionException, IOException {
        return readIngredientBook(ingredientBookStorage.getIngredientBookFilePath());
    }

    /**
     * Read and parse json file into ingredient book.
     *
     * @param filePath relative path where the json file is saved.
     * @see #getIngredientBookFilePath()
     */
    @Override
    public Optional<ReadOnlyEntryBook<Ingredient>> readIngredientBook(Path filePath)
        throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return ingredientBookStorage.readIngredientBook(filePath);
    }

    @Override
    public void saveIngredientBook(ReadOnlyEntryBook<Ingredient> ingredientBook) throws IOException {
        saveIngredientBook(ingredientBook, ingredientBookStorage.getIngredientBookFilePath());
    }

    /**
     * Write to ingredient book data file.
     *
     * @param ingredientBook the ingredient book to be written from.
     * @param filePath the path where the data file is saved.
     * @see #saveIngredientBook(ReadOnlyEntryBook)
     */
    @Override
    public void saveIngredientBook(ReadOnlyEntryBook<Ingredient> ingredientBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        ingredientBookStorage.saveIngredientBook(ingredientBook, filePath);
    }

    @Override
    public void saveRecipeBook(ReadOnlyEntryBook<Recipe> recipeBook) throws IOException {
        saveRecipeBook(recipeBook, recipeBookStorage.getRecipeBookFilePath());
    }

    /**
     * Write to recipe book data file.
     *
     * @param recipeBook the ingredient book to be written from.
     * @param filePath the path where the data file is saved.
     * @see #saveIngredientBook(ReadOnlyEntryBook)
     */
    @Override
    public void saveRecipeBook(ReadOnlyEntryBook<Recipe> recipeBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        recipeBookStorage.saveRecipeBook(recipeBook, filePath);
    }

}
