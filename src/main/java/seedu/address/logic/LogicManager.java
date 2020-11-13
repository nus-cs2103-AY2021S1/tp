package seedu.address.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.UndoCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.ProductiveNusParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyProductiveNus;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.task.Task;
import seedu.address.storage.Storage;

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
