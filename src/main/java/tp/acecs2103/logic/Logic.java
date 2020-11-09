package tp.acecs2103.logic;

import java.nio.file.Path;

import tp.acecs2103.commons.core.GuiSettings;
import tp.acecs2103.logic.commands.CommandResult;
import tp.acecs2103.logic.commands.exceptions.CommandException;
import tp.acecs2103.logic.parser.exceptions.ParseException;
import tp.acecs2103.model.Model;
import tp.acecs2103.model.TaskList;
import tp.acecs2103.model.UiTaskList;
import tp.acecs2103.model.exceptions.InvalidTaskListOperationException;


/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws
            CommandException, ParseException, InvalidTaskListOperationException;

    /**
     * Returns the TaskList.
     *
     * @see Model#getTaskList()
     */
    TaskList getTaskList();

    /**
    /* Returns the task list to be displayed in UI
    */
    UiTaskList getUiTaskList();

    /**
     * Returns the user prefs' task list file path.
     */
    Path getTaskListFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
