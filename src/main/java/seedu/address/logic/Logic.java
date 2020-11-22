package seedu.address.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyExpenseBook;
import seedu.address.model.expense.Expense;

/**
 * API of the Logic components
 */
public interface Logic {

    /** Returns an unmodifiable view of the filtered list of expenses */
    ObservableList<Expense> getFilteredExpenseList();

    /**
     * Executes the command and returns the result.
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns the ExpenseBook.
     *
     * @see Model#getExpenseBook()
     */
    ReadOnlyExpenseBook getExpenseBook();

    /**
     * Returns the user prefs' expense book file path.
     */
    Path getExpenseBookFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
