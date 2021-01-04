package seedu.homerce.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.homerce.commons.core.GuiSettings;
import seedu.homerce.logic.commands.CommandResult;
import seedu.homerce.logic.commands.exceptions.CommandException;
import seedu.homerce.logic.parser.exceptions.ParseException;
import seedu.homerce.model.appointment.Appointment;
import seedu.homerce.model.client.Client;
import seedu.homerce.model.expense.Expense;
import seedu.homerce.model.manager.ReadOnlyAppointmentManager;
import seedu.homerce.model.manager.ReadOnlyClientManager;
import seedu.homerce.model.revenue.Revenue;
import seedu.homerce.model.service.Service;

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
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns the ClientManager.
     *
     * @see seedu.homerce.model.Model#getClientManager()
     */
    ReadOnlyClientManager getClientManager();

    /**
     * Returns an unmodifiable view of the filtered list of clients
     */
    ObservableList<Client> getFilteredClientList();

    /**
     * Returns an unmodifiable view of the filtered list of expenses
     */
    ObservableList<Expense> getFilteredExpenseList();

    /**
     * Returns an unmodifiable view of the filtered list of services
     */
    ObservableList<Service> getFilteredServiceList();

    /** Returns an unmodifiable view of the filtered list of appointments */
    ObservableList<Appointment> getFilteredAppointmentList();

    /** Returns an unmodifiable view of the filtered list of appointments for a particular week */
    ObservableList<Appointment> getFilteredSchedule();
    /**
     * Returns an unmodifiable view of the filtered list of revenues
     */
    ObservableList<Revenue> getFilteredRevenueList();

    /**
     * Returns the user prefs' homerce file path.
     */
    Path getClientManagerFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    ReadOnlyAppointmentManager getAppointmentManager();
}
