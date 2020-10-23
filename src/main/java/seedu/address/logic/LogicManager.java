package seedu.address.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.InventoryParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.InventoryComponent;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyItemList;
import seedu.address.model.ReadOnlyLocationList;
import seedu.address.model.ReadOnlyRecipeList;
import seedu.address.model.item.Item;
import seedu.address.model.location.Location;
import seedu.address.model.recipe.Recipe;
import seedu.address.storage.Storage;
import seedu.address.ui.DisplayedInventoryType;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final InventoryParser inventoryParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        inventoryParser = new InventoryParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException, IOException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = inventoryParser.parseCommand(commandText);
        // clear filters to prepare for command execution
        model.resetItemFilters();
        model.resetRecipeFilters();

        commandResult = command.execute(model);

        try {
            // TODO: different saves
            storage.saveModel(model);
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    // Item

    @Override
    public ReadOnlyItemList getItemList() {
        return model.getItemList();
    }

    @Override
    public ObservableList<Item> getFilteredItemList() {
        return model.getFilteredItemList();
    }

    @Override
    public Path getItemListFilePath() {
        return model.getItemListFilePath();
    }

    // Location

    @Override
    public ReadOnlyLocationList getLocationList() {
        return model.getLocationList();
    }

    @Override
    public ObservableList<Location> getFilteredLocationList() {
        return model.getFilteredLocationList();
    }

    @Override
    public Path getLocationListFilePath() {
        return model.getLocationListFilePath();
    }

    // Recipe

    @Override
    public ReadOnlyRecipeList getRecipeList() {
        return model.getRecipeList();
    }

    @Override
    public ObservableList<Recipe> getFilteredRecipeList() {
        return model.getFilteredRecipeList();
    }

    @Override
    public Path getRecipeListFilePath() {
        return model.getRecipeListFilePath();
    }

    // GUI
    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }

    /**
     * Returns the relevant inventory list containing inventory of {@code inventoryType} to be displayed.
     */
    public ArrayList<InventoryComponent> getInventoryList(DisplayedInventoryType inventoryType) {
        ArrayList<InventoryComponent> inventoryList = new ArrayList<>();
        switch(inventoryType) {
        case ITEMS:
            inventoryList.addAll(model.getFilteredItemList());
            break;
        case RECIPES:
            model.resetItemFilters();
            // model.resetRecipeFilters();
            model.getFilteredRecipeList()
                    .forEach(recipe -> inventoryList.add(recipe.print(model.getFilteredItemList())));
            break;
        case DETAILED_ITEM:
            model.getFilteredItemList().forEach(item -> inventoryList.add(item.detailedItem()));
            break;
        case UNCHANGED:
            assert false;
            break;
        default:
            throw new IllegalStateException("This inventoryType is not valid" + inventoryType);
        }
        return inventoryList;
    }
}
