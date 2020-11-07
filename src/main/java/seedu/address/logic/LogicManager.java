package seedu.address.logic;

import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.SupperStrikersParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.food.MenuItem;
import seedu.address.model.order.OrderItem;
import seedu.address.model.vendor.Vendor;
import seedu.address.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not load user data from file.";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final SupperStrikersParser supperStrikersParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        this.supperStrikersParser = new SupperStrikersParser();
    }


    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = supperStrikersParser.parseCommand(commandText);

        commandResult = command.execute(model, storage);

        return commandResult;
    }

    @Override
    public ObservableList<Vendor> getObservableVendorList() {
        return model.getVendorManager().getVendorList();
    }

    @Override
    public ObservableList<MenuItem> getFilteredMenuItemList() {
        return model.getFilteredMenuItemList();
    }

    @Override
    public ObservableList<OrderItem> getFilteredOrderItemList() {
        return model.getObservableOrderItemList();
    }

    @Override
    public boolean isSelected() {
        return model.getVendorIndex() != -1;
    }

    @Override
    public Path getVendorManagerFilePath() {
        return model.getVendorManagerFilePath();
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
