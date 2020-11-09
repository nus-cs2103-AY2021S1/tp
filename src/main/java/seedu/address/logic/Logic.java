package seedu.address.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ReadOnlyGradeBook;
import seedu.address.model.module.Module;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     *
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException   If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns the GradeBook.
     *
     * @see seedu.address.model.Model#getGradeBook()
     */
    ReadOnlyGradeBook getGradeBook();

    /**
     * Returns an unmodifiable view of the filtered list of modules
     */
    ObservableList<Module> getFilteredModuleList();

    /**
     * Returns the user prefs' grade book file path.
     */
    Path getGradeBookFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the cap calculated from the list of modules.
     *
     * @return a string representation of the cap to 2 significant figures.
     */
    String generateCap();

    /**
     * Returns the sem the user is currently editting.
     *
     * @return a string representation of the sem.
     */
    String generateSem();

    /**
     * Returns the filtered list of modules by semester.
     *
     * @return the filtered module list according to semester.
     */
    ObservableList<Module> filterModuleListBySem();

    ObservableList<Module> sortModuleListBySem();

    void resetFilteredList();

}
