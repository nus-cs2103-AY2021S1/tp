package seedu.pivot.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.pivot.commons.core.GuiSettings;
import seedu.pivot.logic.commands.CommandResult;
import seedu.pivot.logic.commands.exceptions.CommandException;
import seedu.pivot.logic.parser.exceptions.ParseException;
import seedu.pivot.model.ReadOnlyPivot;
import seedu.pivot.model.investigationcase.Case;

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
     * Returns PIVOT.
     *
     * @see seedu.pivot.model.Model#getPivot()
     */
    ReadOnlyPivot getPivot();

    /** Returns an unmodifiable view of the filtered list of cases */
    ObservableList<Case> getFilteredCaseList();

    /**
     * Returns the user prefs' PIVOT file path.
     */
    Path getPivotFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
