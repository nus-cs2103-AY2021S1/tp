package nustorage.logic;

import javafx.collections.ObservableList;
import nustorage.commons.core.GuiSettings;
import nustorage.logic.commands.CommandResult;
import nustorage.logic.commands.exceptions.CommandException;
import nustorage.logic.parser.exceptions.ParseException;
import nustorage.model.record.FinanceRecord;
import nustorage.model.record.InventoryRecord;

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

    ObservableList<InventoryRecord> getFilteredInventory();

    ObservableList<FinanceRecord> getFilteredFinanceList();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
