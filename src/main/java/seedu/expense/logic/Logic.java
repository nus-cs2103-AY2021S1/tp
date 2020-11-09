package seedu.expense.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.expense.commons.core.GuiSettings;
import seedu.expense.logic.commands.CommandResult;
import seedu.expense.logic.commands.exceptions.CommandException;
import seedu.expense.logic.parser.exceptions.ParseException;
import seedu.expense.model.ReadOnlyExpenseBook;
import seedu.expense.model.Statistics;
import seedu.expense.model.expense.Expense;

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
     * Returns the ExpenseBook.
     *
     * @see seedu.expense.model.Model#getExpenseBook()
     */
    ReadOnlyExpenseBook getExpenseBook();

    /**
     * Returns the Statistics.
     *
     * @see seedu.expense.model.Model#getStatistics()
     */
    Statistics getStatistics();

    /**
     * Returns an unmodifiable view of the filtered list of expenses
     */
    ObservableList<Expense> getFilteredExpenseList();

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
