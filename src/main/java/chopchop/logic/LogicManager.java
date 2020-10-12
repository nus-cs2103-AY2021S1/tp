package chopchop.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;
import chopchop.commons.core.GuiSettings;
import chopchop.model.ingredient.Ingredient;
import chopchop.model.ingredient.ReadOnlyIngredientBook;
import chopchop.model.recipe.ReadOnlyRecipeBook;
import chopchop.model.recipe.Recipe;
import javafx.collections.ObservableList;
import chopchop.commons.core.LogsCenter;
import chopchop.logic.commands.CommandResult;
import chopchop.logic.commands.exceptions.CommandException;
import chopchop.model.Model;
import chopchop.storage.Storage;
import seedu.address.logic.parser.exceptions.ParseException;

public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(chopchop.logic.LogicManager.class);

    private final Model model;
    private final Storage storage;

    /**
     * Constructs {@code LogicManager}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        try {
            storage.saveIngredientBook(model.getIngredientBook());
            storage.saveRecipeBook(model.getRecipeBook());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return new CommandResult("This returns nothing");
    }

    /**
     * Returns the RecipeBook.
     */
    @Override
    public ReadOnlyRecipeBook getRecipeBook() {
        return null;
    }

    /**
     * Returns the IngredientBook.
     */
    @Override
    public ReadOnlyIngredientBook getIngredientBook() {
        return null;
    }

    /**
     * Returns an unmodifiable view of the filtered list of recipes.
     */
    @Override
    public ObservableList<Recipe> getFilteredRecipeList() {
        return null;
    }

    /**
     * Returns an unmodifiable view of the filtered ingredient list.
     */
    @Override
    public ObservableList<Ingredient> getFilteredIngredientList() {
        return null;
    }

    /**
     * Returns the user prefs' recipe book file path.
     */
    @Override
    public Path getRecipeBookFilePath() {
        return null;
    }

    /**
     * Returns the user prefs' ingredient book file path.
     */
    @Override
    public Path getIngredientBookFilePath() {
        return null;
    }

    /**
     * Returns the user prefs' GUI settings.
     */
    @Override
    public GuiSettings getGuiSettings() {
        return null;
    }

    /**
     * Set the user prefs' GUI settings.
     *
     * @param guiSettings
     */
    @Override
    public void setGuiSettings(GuiSettings guiSettings) {

    }
}
