package nustorage.logic;

import java.io.IOException;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import nustorage.commons.core.GuiSettings;
import nustorage.commons.core.LogsCenter;
import nustorage.logic.commands.Command;
import nustorage.logic.commands.CommandResult;
import nustorage.logic.commands.exceptions.CommandException;
import nustorage.logic.parser.NuStorageParser;
import nustorage.logic.parser.exceptions.ParseException;
import nustorage.model.Model;
import nustorage.model.record.FinanceRecord;
import nustorage.model.record.InventoryRecord;
import nustorage.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final NuStorageParser nuStorageParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        nuStorageParser = new NuStorageParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = nuStorageParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            // storage.saveAddressBook(model.getAddressBook());
            storage.saveFinanceAccount(model.getFinanceAccount());
            storage.saveInventory(model.getInventory());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    public ObservableList<InventoryRecord> getFilteredInventory() {
        return model.getFilteredInventory();
    }

    @Override
    public ObservableList<FinanceRecord> getFilteredFinanceList() {
        return model.getFilteredFinanceList();
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
