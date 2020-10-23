package seedu.address.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ReadOnlyCommonCents;
import seedu.address.model.account.Account;
import seedu.address.model.account.Name;
import seedu.address.model.account.entry.Expense;
import seedu.address.model.account.entry.Revenue;

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
     * Returns the CommonCents.
     *
     * @see seedu.address.model.Model#getCommonCents()
     */
    ReadOnlyCommonCents getCommonCents();

    /**
     * Returns the names of the active account
     */
    Name getActiveAccountName();

    /** Returns an unmodifiable view of the filtered list of accounts. */
    ObservableList<Account> getFilteredAccountList();

    /** Returns an unmodifiable view of the filtered list of expenses. */
    ObservableList<Expense> getFilteredExpenseList();

    /** Returns an unmodifiable view of the filtered list of revenues. */
    ObservableList<Revenue> getFilteredRevenueList();

    double getTotalRevenue();

    double getTotalExpense();

    /**
     * Returns the user prefs' Common Cents file path.
     */
    Path getCommonCentsFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
