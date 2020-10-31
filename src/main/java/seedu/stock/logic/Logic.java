package seedu.stock.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.stock.commons.core.GuiSettings;
import seedu.stock.logic.commands.CommandResult;
import seedu.stock.logic.commands.exceptions.CommandException;
import seedu.stock.logic.commands.exceptions.SerialNumberNotFoundException;
import seedu.stock.logic.commands.exceptions.SourceCompanyNotFoundException;
import seedu.stock.logic.parser.exceptions.ParseException;
import seedu.stock.model.ReadOnlyStockBook;
import seedu.stock.model.stock.Stock;

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
    CommandResult execute(String commandText) throws CommandException, ParseException,
            SourceCompanyNotFoundException, SerialNumberNotFoundException;

    /**
     * Returns the StockBook.
     *
     * @see seedu.stock.model.Model#getStockBook()
     */
    ReadOnlyStockBook getStockBook();

    /** Returns an unmodifiable view of the filtered list of stocks */
    ObservableList<Stock> getFilteredStockList();

    /**
     * Returns the user prefs' stock book file path.
     */
    Path getStockBookFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
