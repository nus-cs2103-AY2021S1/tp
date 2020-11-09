package seedu.zookeep.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.zookeep.commons.core.GuiSettings;
import seedu.zookeep.commons.core.LogsCenter;
import seedu.zookeep.logic.commands.Command;
import seedu.zookeep.logic.commands.CommandResult;
import seedu.zookeep.logic.commands.exceptions.CommandException;
import seedu.zookeep.logic.parser.ZooKeepBookParser;
import seedu.zookeep.logic.parser.exceptions.ParseException;
import seedu.zookeep.model.HistoryStack;
import seedu.zookeep.model.Model;
import seedu.zookeep.model.ReadOnlyZooKeepBook;
import seedu.zookeep.model.ZooKeepBook;
import seedu.zookeep.model.animal.Animal;
import seedu.zookeep.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final ZooKeepBookParser zooKeepBookParser;
    private final HistoryStack historyStack;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        zooKeepBookParser = new ZooKeepBookParser();
        historyStack = HistoryStack.getHistoryStack();
        historyStack.addToHistory(new ZooKeepBook(model.getZooKeepBook()));
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        ReadOnlyZooKeepBook currentRedo = new ZooKeepBook();
        if (historyStack.getRedoSize() > 0) {
            currentRedo = historyStack.viewRecentRedo();
        }

        CommandResult commandResult;
        Command command = zooKeepBookParser.parseCommand(commandText);
        commandResult = command.execute(model);

        ReadOnlyZooKeepBook book = model.getZooKeepBook();
        historyStack.addToHistory(new ZooKeepBook(book));

        logger.info("--------------- ADDED CHANGE TO HISTORY STACK");
        logger.info(historyStack.toString());

        if (!commandText.equals("undo") && (historyStack.getRedoSize() > 0)) {
            historyStack.checkEdit(currentRedo, book); // clear redo stack if a new edit has been made
        }

        try {
            storage.saveZooKeepBook(book);
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyZooKeepBook getZooKeepBook() {
        return model.getZooKeepBook();
    }

    @Override
    public ObservableList<Animal> getFilteredAnimalList() {
        return model.getFilteredAnimalList();
    }

    @Override
    public Path getZooKeepBookFilePath() {
        return model.getZooKeepBookFilePath();
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
