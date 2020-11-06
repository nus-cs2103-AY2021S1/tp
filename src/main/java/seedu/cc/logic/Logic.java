package seedu.cc.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.cc.commons.core.GuiSettings;
import seedu.cc.logic.commands.CommandResult;
import seedu.cc.logic.commands.exceptions.CommandException;
import seedu.cc.logic.parser.exceptions.ParseException;
import seedu.cc.model.ReadOnlyCommonCents;
import seedu.cc.model.account.Account;
import seedu.cc.model.account.Name;
import seedu.cc.model.account.entry.Expense;
import seedu.cc.model.account.entry.Revenue;

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
     * @see seedu.cc.model.Model#getCommonCents()
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
