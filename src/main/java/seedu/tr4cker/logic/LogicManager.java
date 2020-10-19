package seedu.tr4cker.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.tr4cker.commons.core.GuiSettings;
import seedu.tr4cker.commons.core.LogsCenter;
import seedu.tr4cker.logic.commands.Command;
import seedu.tr4cker.logic.commands.CommandResult;
import seedu.tr4cker.logic.commands.exceptions.CommandException;
import seedu.tr4cker.logic.parser.Tr4ckerParser;
import seedu.tr4cker.logic.parser.exceptions.ParseException;
import seedu.tr4cker.model.Model;
import seedu.tr4cker.model.ReadOnlyTr4cker;
import seedu.tr4cker.model.task.Task;
import seedu.tr4cker.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final Tr4ckerParser tr4ckerParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        tr4ckerParser = new Tr4ckerParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = tr4ckerParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveTr4cker(model.getTr4cker());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyTr4cker getTr4cker() {
        return model.getTr4cker();
    }

    @Override
    public ObservableList<Task> getFilteredTaskList() {
        return model.getFilteredTaskList();
    }

    @Override
    public ObservableList<Task> getPlannerFilteredTaskList() {
        return model.getPlannerFilteredTaskList();
    }

    @Override
    public Path getTr4ckerFilePath() {
        return model.getTr4ckerFilePath();
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
