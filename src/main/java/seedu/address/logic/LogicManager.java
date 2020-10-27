package seedu.address.logic;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.WishfulShrinkingParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyWishfulShrinking;
import seedu.address.model.consumption.Consumption;
import seedu.address.model.recipe.Ingredient;
import seedu.address.model.recipe.Recipe;
import seedu.address.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final WishfulShrinkingParser wishfulShrinkingParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        wishfulShrinkingParser = new WishfulShrinkingParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException,
            ParseException, IOException, URISyntaxException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = wishfulShrinkingParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveWishfulShrinking(model.getWishfulShrinking());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyWishfulShrinking getWishfulShrinking() {
        return model.getWishfulShrinking();
    }

    @Override
    public ObservableList<Recipe> getFilteredRecipeList() {
        return model.getFilteredRecipeList();
    }

    @Override
    public ObservableList<Ingredient> getFilteredIngredientList() {
        return model.getFilteredIngredientList();
    }

    @Override
    public ObservableList<Consumption> getFilteredConsumptionList() {
        return model.getFilteredConsumptionList();
    }

    @Override
    public Path getWishfulShrinkingFilePath() {
        return model.getWishfulShrinkingFilePath();
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
