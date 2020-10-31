package seedu.tasklist.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.tasklist.commons.core.GuiSettings;
import seedu.tasklist.commons.core.LogsCenter;
import seedu.tasklist.logic.commands.Command;
import seedu.tasklist.logic.commands.CommandResult;
import seedu.tasklist.logic.commands.UndoCommand;
import seedu.tasklist.logic.commands.exceptions.CommandException;
import seedu.tasklist.logic.parser.ProductiveNusParser;
import seedu.tasklist.logic.parser.exceptions.ParseException;
import seedu.tasklist.model.Model;
import seedu.tasklist.model.ReadOnlyProductiveNus;
import seedu.tasklist.model.assignment.Assignment;
import seedu.tasklist.model.task.Task;
import seedu.tasklist.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final ProductiveNusParser productiveNusParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        productiveNusParser = new ProductiveNusParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = productiveNusParser.parseCommand(commandText);

        if (!(command instanceof UndoCommand)) {
            model.preUpdateModel();
        }

        try {
            commandResult = command.execute(model);
        } catch (CommandException commandException) {
            if (!(command instanceof UndoCommand)) {
                model.goToPreviousModel();
            }
            throw commandException;
        }

        try {
            storage.saveProductiveNus(model.getProductiveNus());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyProductiveNus getProductiveNus() {
        return model.getProductiveNus();
    }

    @Override
    public ObservableList<Assignment> getFilteredAssignmentList() {
        return model.getFilteredAssignmentList();
    }

    @Override
    public ObservableList<Task> getFilteredTaskList() {
        return model.getFilteredTaskList();
    }

    @Override
    public ObservableList<Assignment> getRemindedAssignmentList() {
        return model.getRemindedAssignmentsList();
    }

    @Override
    public Path getProductiveNusFilePath() {
        return model.getProductiveNusFilePath();
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
