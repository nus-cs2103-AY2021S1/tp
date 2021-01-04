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

public class DoneAppointmentCommand extends Command {
    public static final String COMMAND_WORD = "done";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks the appointment identified by the index number used in the displayed appointment list as done.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";
    public static final String MESSAGE_DONE_APPOINTMENT_SUCCESS = "Marked Appointment as done: %1$s";
    public static final String MESSAGE_ADD_REVENUE_SUCCESS = "Added this %1$s";

    private final Index targetIndex;

    public DoneAppointmentCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model, HistoryManager historyManager) throws CommandException {
        requireNonNull(model);
        List<Appointment> lastShownList = model.getFilteredAppointmentList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);
        }

        Appointment appointmentToMarkDone = lastShownList.get(targetIndex.getZeroBased());
        if (appointmentToMarkDone.getStatus().isDone()) {
            throw new CommandException(Messages.MESSAGE_APPOINTMENT_ALREADY_DONE);
        }
        appointmentToMarkDone.markDone();
        Revenue revenueToAdd = new Revenue(
                appointmentToMarkDone.getService(),
                appointmentToMarkDone.getAppointmentDate()
        );
        model.addRevenue(revenueToAdd);
        model.setAppointment(appointmentToMarkDone, appointmentToMarkDone);
        model.refreshSchedule();
        CommandResult r = new CommandResult(
            String.format(MESSAGE_DONE_APPOINTMENT_SUCCESS, appointmentToMarkDone)
                + "\n" + String.format(MESSAGE_ADD_REVENUE_SUCCESS, revenueToAdd),
            AppointmentListPanel.TAB_NAME
        );
        return r;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DoneAppointmentCommand // instanceof handles nulls
                && targetIndex.equals(((DoneAppointmentCommand) other).targetIndex)); // state check
    }
}
