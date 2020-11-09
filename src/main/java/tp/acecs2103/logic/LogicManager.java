package tp.acecs2103.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import tp.acecs2103.commons.core.GuiSettings;
import tp.acecs2103.commons.core.LogsCenter;
import tp.acecs2103.logic.commands.Command;
import tp.acecs2103.logic.commands.CommandResult;
import tp.acecs2103.logic.commands.exceptions.CommandException;
import tp.acecs2103.logic.parser.TaskListParser;
import tp.acecs2103.logic.parser.exceptions.ParseException;
import tp.acecs2103.model.Model;
import tp.acecs2103.model.TaskList;
import tp.acecs2103.model.UiTaskList;
import tp.acecs2103.model.exceptions.InvalidTaskListOperationException;
import tp.acecs2103.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final TaskListParser taskListParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        taskListParser = new TaskListParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException,
            ParseException, InvalidTaskListOperationException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = taskListParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveTaskList(model.getTaskList());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public TaskList getTaskList() {
        return model.getTaskList();
    }

    @Override
    public UiTaskList getUiTaskList() {
        return model.getUiTaskList();
    }


    @Override
    public Path getTaskListFilePath() {
        return model.getTaskListFilePath();
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
