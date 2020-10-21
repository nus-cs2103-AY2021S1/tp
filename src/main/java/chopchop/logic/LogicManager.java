package chopchop.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import chopchop.commons.core.GuiSettings;
import chopchop.commons.core.LogsCenter;
import chopchop.logic.commands.CommandResult;
import chopchop.logic.commands.RedoCommand;
import chopchop.logic.commands.UndoCommand;
import chopchop.logic.commands.exceptions.CommandException;
import chopchop.logic.history.CommandHistory;
import chopchop.logic.history.History;
import chopchop.logic.history.HistoryManager;
import chopchop.logic.parser.CommandParser;
import chopchop.logic.parser.exceptions.ParseException;
import chopchop.model.Model;
import chopchop.model.ReadOnlyEntryBook;
import chopchop.model.ingredient.Ingredient;
import chopchop.model.recipe.Recipe;
import chopchop.storage.Storage;
import javafx.collections.ObservableList;

/**
 * The main CommandDispatcher governing the logic in the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final History history;
    private final CommandParser parser;

    /**
     * Constructs a {@code CommandDispatcher} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        this.history = new HistoryManager();
        this.parser = new CommandParser();
    }

    /**
     * Executes the specified command based on the commandText.
     *
     * @param commandText The command as entered by the user.
     */
    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        var res = this.parser.parse(commandText);
        if (res.isError()) {
            this.history.add(new CommandHistory(commandText));
            throw new ParseException(res.getError());
        }

        var cmd = res.getValue();
        var result = cmd.execute(this.model, this.history);

        if (!(cmd instanceof UndoCommand || cmd instanceof RedoCommand)) {
            this.history.add(new CommandHistory(commandText, cmd));
        }

        try {
            storage.saveIngredientBook(model.getIngredientBook());
            storage.saveRecipeBook(model.getRecipeBook());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return result;
    }

    @Override
    public ReadOnlyEntryBook<Recipe> getRecipeBook() {
        return model.getRecipeBook();
    }

    @Override
    public ObservableList<Recipe> getFilteredRecipeList() {
        return model.getFilteredRecipeList();
    }

    @Override
    public Path getRecipeBookFilePath() {
        return model.getRecipeBookFilePath();
    }

    @Override
    public ReadOnlyEntryBook<Ingredient> getIngredientBook() {
        return model.getIngredientBook();
    }

    @Override
    public ObservableList<Ingredient> getFilteredIngredientList() {
        return model.getFilteredIngredientList();
    }

    @Override
    public Path getIngredientBookFilePath() {
        return model.getIngredientBookFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }
}
