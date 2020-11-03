package seedu.stock.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.stock.commons.core.GuiSettings;
import seedu.stock.commons.core.LogsCenter;
import seedu.stock.logic.commands.Command;
import seedu.stock.logic.commands.CommandResult;
import seedu.stock.logic.commands.exceptions.CommandException;
import seedu.stock.logic.commands.exceptions.SerialNumberNotFoundException;
import seedu.stock.logic.commands.exceptions.SourceCompanyNotFoundException;
import seedu.stock.logic.parser.StockBookParser;
import seedu.stock.logic.parser.exceptions.ParseException;
import seedu.stock.model.Model;
import seedu.stock.model.ReadOnlyStockBook;
import seedu.stock.model.stock.Stock;
import seedu.stock.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final StockBookParser stockBookParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        stockBookParser = new StockBookParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException,
            SourceCompanyNotFoundException, SerialNumberNotFoundException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = stockBookParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveStockBook(model.getStockBook());
            storage.saveSerialNumberSetsBook(model.getSerialNumberSetsBook());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyStockBook getStockBook() {
        return model.getStockBook();
    }

    @Override
    public ObservableList<Stock> getFilteredStockList() {
        return model.getFilteredStockList();
    }

    @Override
    public Path getStockBookFilePath() {
        return model.getStockBookFilePath();
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
    public Model getModel() {
        return model;
    }
}
