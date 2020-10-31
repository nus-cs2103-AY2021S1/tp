package seedu.tasklist.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.tasklist.commons.core.GuiSettings;
import seedu.tasklist.logic.commands.CommandResult;
import seedu.tasklist.logic.commands.exceptions.CommandException;
import seedu.tasklist.logic.parser.exceptions.ParseException;
import seedu.tasklist.model.ReadOnlyProductiveNus;
import seedu.tasklist.model.assignment.Assignment;
import seedu.tasklist.model.task.Task;

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
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns ProductiveNus.
     *
     * @see seedu.tasklist.model.Model#getProductiveNus()
     */
    ReadOnlyProductiveNus getProductiveNus();

    /** Returns an unmodifiable view of the filtered list of assignments */
    ObservableList<Assignment> getFilteredAssignmentList();

    /** Returns an unmodifiable view of the filtered list of assignments */
    ObservableList<Task> getFilteredTaskList();

    /** Returns an unmodifiable view of the filtered list of reminder assignments */
    ObservableList<Assignment> getRemindedAssignmentList();

    /**
     * Returns the user prefs' ProductiveNus file path.
     */
    Path getProductiveNusFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
