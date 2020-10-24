package seedu.fma.logic;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.fma.commons.core.GuiSettings;
import seedu.fma.logic.commands.CommandResult;
import seedu.fma.logic.commands.exceptions.CommandException;
import seedu.fma.logic.parser.exceptions.ParseException;
import seedu.fma.model.ReadOnlyLogBook;
import seedu.fma.model.log.Log;

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
     * Returns the LogBook.
     *
     * @see seedu.fma.model.Model#getLogBook()
     */
    ReadOnlyLogBook getLogBook();

    /** Returns an unmodifiable view of the filtered list of logs */
    ObservableList<Log> getFilteredLogList();

    /**
     * Returns the user prefs' log book file path.
     */
    Path getLogBookFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Get command message usages
     */
    HashMap<String, String> getCommandMessageUsage();

}
