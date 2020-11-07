package seedu.simplykitchen.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.simplykitchen.commons.core.GuiSettings;
import seedu.simplykitchen.commons.core.LogsCenter;
import seedu.simplykitchen.logic.commands.Command;
import seedu.simplykitchen.logic.commands.CommandResult;
import seedu.simplykitchen.logic.commands.exceptions.CommandException;
import seedu.simplykitchen.logic.parser.FoodInventoryParser;
import seedu.simplykitchen.logic.parser.exceptions.ParseException;
import seedu.simplykitchen.model.Model;
import seedu.simplykitchen.model.ReadOnlyFoodInventory;
import seedu.simplykitchen.model.food.Food;
import seedu.simplykitchen.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final FoodInventoryParser foodInventoryParser;
    private boolean foodInventoryModified;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        foodInventoryParser = new FoodInventoryParser();

        // Set foodInventoryModified to true whenever the models' food inventory is modified.
        model.getFoodInventory().addListener(observable -> foodInventoryModified = true);
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");
        foodInventoryModified = false;

        CommandResult commandResult;
        Command command = foodInventoryParser.parseCommand(commandText);
        commandResult = command.execute(model);

        if (foodInventoryModified) {
            try {
                storage.saveFoodInventory(model.getFoodInventory());
            } catch (IOException ioe) {
                throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
            }
        }

        return commandResult;
    }

    @Override
    public ReadOnlyFoodInventory getFoodInventory() {
        return model.getFoodInventory();
    }

    @Override
    public ObservableList<Food> getFilteredFoodList() {
        return model.getFilteredFoodList();
    }

    @Override
    public ObservableList<Food> getFilteredExpiringFoodList() {
        return model.getFilteredExpiringFoodList();
    }

    @Override
    public ObservableList<Food> getFilteredExpiredFoodList() {
        return model.getFilteredExpiredFoodList();
    }

    @Override
    public Path getFoodInventoryFilePath() {
        return model.getFoodInventoryFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }

    @Override
    public boolean isDataFileInvalid() {
        return model.isDataFileInvalid();
    }
}
