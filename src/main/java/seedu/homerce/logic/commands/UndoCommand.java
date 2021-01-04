package seedu.homerce.logic.commands;

import seedu.homerce.logic.commands.appointment.AddAppointmentCommand;
import seedu.homerce.logic.commands.appointment.ClearAppointmentCommand;
import seedu.homerce.logic.commands.appointment.DeleteAppointmentCommand;
import seedu.homerce.logic.commands.appointment.DoneAppointmentCommand;
import seedu.homerce.logic.commands.appointment.EditAppointmentCommand;
import seedu.homerce.logic.commands.appointment.UnDoneAppointmentCommand;
import seedu.homerce.logic.commands.client.AddClientCommand;
import seedu.homerce.logic.commands.client.ClearClientCommand;
import seedu.homerce.logic.commands.client.DeleteClientCommand;
import seedu.homerce.logic.commands.client.EditClientCommand;
import seedu.homerce.logic.commands.exceptions.CommandException;
import seedu.homerce.logic.commands.expense.AddExpenseCommand;
import seedu.homerce.logic.commands.expense.ClearExpenseCommand;
import seedu.homerce.logic.commands.expense.DeleteExpenseCommand;
import seedu.homerce.logic.commands.expense.EditExpenseCommand;
import seedu.homerce.logic.commands.expense.SortExpenseCommand;
import seedu.homerce.logic.commands.revenue.ClearRevenueCommand;
import seedu.homerce.logic.commands.revenue.SortRevenueCommand;
import seedu.homerce.logic.commands.service.AddServiceCommand;
import seedu.homerce.logic.commands.service.ClearServiceCommand;
import seedu.homerce.logic.commands.service.DeleteServiceCommand;
import seedu.homerce.logic.commands.service.EditServiceCommand;
import seedu.homerce.model.Model;
import seedu.homerce.model.manager.HistoryManager;
import seedu.homerce.model.undo.History;
import seedu.homerce.ui.appointmentpanel.AppointmentListPanel;
import seedu.homerce.ui.clientpanel.ClientListPanel;
import seedu.homerce.ui.expensepanel.ExpenseListPanel;
import seedu.homerce.ui.revenuepanel.RevenueListPanel;
import seedu.homerce.ui.schedulepanel.SchedulePanel;
import seedu.homerce.ui.servicepanel.ServiceListPanel;

/**
 * Undo the last command that updates the state of Homerce's storage.
 *
 * A command that does not affect the state of Homerce's storage will not be considered in the undo command.
 */
public class UndoCommand extends Command {
    public static final String COMMAND_WORD = "undo";
    public static final String MESSAGE_SUCCESS = "Undo successful!";

    @Override
    public CommandResult execute(Model model, HistoryManager historyManager) throws CommandException {
        History previousHistory = historyManager.getPreviousHistory();
        if (previousHistory == null) {
            throw new CommandException("Already at oldest change");
        }

        Model previousModel = previousHistory.getModel();
        Command previousCommand = previousHistory.getCommand();

        model.replaceModel(previousModel);
        return new CommandResult(MESSAGE_SUCCESS, getCommandTab(previousCommand));
    }

    /**
     * Gets the appropriate UI panel for Homerce to switch to when executing the undo command.
     *
     * @param previousCommand the command that resulted in a change in Homerce's state.
     * @return a String representation of the tab name of the panel to switch to.
     */
    private String getCommandTab(Command previousCommand) {
        if (isServiceCommand(previousCommand)) {
            return ServiceListPanel.TAB_NAME;

        } else if (isExpenseCommand(previousCommand)) {
            return ExpenseListPanel.TAB_NAME;

        } else if (isAppointmentCommand(previousCommand)) {
            return AppointmentListPanel.TAB_NAME;

        } else if (isClientCommand(previousCommand)) {
            return ClientListPanel.TAB_NAME;

        } else if (isRevenueCommand(previousCommand)) {
            return RevenueListPanel.TAB_NAME;

        } else { // Unknown command, default switch to schedule view
            return SchedulePanel.TAB_NAME;

        }
    }

    private boolean isServiceCommand(Command command) {
        // Don't include find and list as they don't change the state of Homerce
        return (command instanceof AddServiceCommand) || (command instanceof DeleteServiceCommand)
            || (command instanceof EditServiceCommand) || (command instanceof ClearServiceCommand);

    }

    private boolean isRevenueCommand(Command command) {
        return (command instanceof SortRevenueCommand) || (command instanceof ClearRevenueCommand);
    }

    private boolean isExpenseCommand(Command command) {
        return (command instanceof AddExpenseCommand) || (command instanceof DeleteExpenseCommand)
            || (command instanceof EditExpenseCommand) || (command instanceof ClearExpenseCommand)
            || (command instanceof SortExpenseCommand);

    }

    private boolean isAppointmentCommand(Command command) {
        return (command instanceof AddAppointmentCommand) || (command instanceof DeleteAppointmentCommand)
            || (command instanceof EditAppointmentCommand) || (command instanceof ClearAppointmentCommand)
            || (command instanceof DoneAppointmentCommand) || (command instanceof UnDoneAppointmentCommand);
    }

    private boolean isClientCommand(Command command) {
        return (command instanceof AddClientCommand) || (command instanceof DeleteClientCommand)
            || (command instanceof EditClientCommand) || (command instanceof ClearClientCommand);
    }
}
