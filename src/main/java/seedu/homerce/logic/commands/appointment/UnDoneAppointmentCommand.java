package seedu.homerce.logic.commands.appointment;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.homerce.commons.core.Messages;
import seedu.homerce.commons.core.index.Index;
import seedu.homerce.logic.commands.Command;
import seedu.homerce.logic.commands.CommandResult;
import seedu.homerce.logic.commands.exceptions.CommandException;
import seedu.homerce.model.Model;
import seedu.homerce.model.appointment.Appointment;
import seedu.homerce.model.manager.HistoryManager;
import seedu.homerce.model.revenue.Revenue;
import seedu.homerce.ui.appointmentpanel.AppointmentListPanel;

public class UnDoneAppointmentCommand extends Command {
    public static final String COMMAND_WORD = "undone";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Marks the appointment identified by the index number used in the displayed appointment list as undone.\n"
        + "Parameters: INDEX (must be a positive integer)\n"
        + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_UNDONE_APPOINTMENT_SUCCESS = "Marked Appointment as undone: %1$s";
    public static final String MESSAGE_DELETE_REVENUE_SUCCESS = "Deleted this %1$s";
    public static final String MESSAGE_FAILED_TO_DELETE_REVENUE =
        "Failed to delete the revenue attached to this appointment. Revenue tracker might have been cleared "
        + "or storage files corrupted. ";
    private final Index targetIndex;

    public UnDoneAppointmentCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model, HistoryManager historyManager) throws CommandException {
        requireNonNull(model);
        List<Appointment> lastShownList = model.getFilteredAppointmentList();
        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);
        }
        Appointment appointmentToMarkUnDone = lastShownList.get(targetIndex.getZeroBased());
        if (!appointmentToMarkUnDone.getStatus().isDone()) {
            throw new CommandException(Messages.MESSAGE_APPOINTMENT_ALREADY_UNDONE);
        }
        appointmentToMarkUnDone.markUnDone();
        Revenue revenueToRemove = new Revenue(
            appointmentToMarkUnDone.getService(),
            appointmentToMarkUnDone.getAppointmentDate()
        );
        String deletionOfRevenueResult;
        if (model.getRevenueTracker().getRevenueList().contains(revenueToRemove)) {
            model.deleteRevenue(revenueToRemove);
            deletionOfRevenueResult = String.format(MESSAGE_DELETE_REVENUE_SUCCESS, revenueToRemove);
        } else {
            deletionOfRevenueResult = MESSAGE_FAILED_TO_DELETE_REVENUE;
        }
        model.refreshSchedule();
        model.setAppointment(appointmentToMarkUnDone, appointmentToMarkUnDone);
        return new CommandResult(
            String.format(MESSAGE_UNDONE_APPOINTMENT_SUCCESS, appointmentToMarkUnDone)
            + "\n" + deletionOfRevenueResult,
            AppointmentListPanel.TAB_NAME
        );
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof UnDoneAppointmentCommand // instanceof handles nulls
            && targetIndex.equals(((UnDoneAppointmentCommand) other).targetIndex)); // state check
    }
}
