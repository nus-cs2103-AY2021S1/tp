package seedu.homerce.logic.commands.appointment;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;

import seedu.homerce.logic.commands.Command;
import seedu.homerce.logic.commands.CommandResult;
import seedu.homerce.model.Model;
import seedu.homerce.model.manager.HistoryManager;
import seedu.homerce.ui.appointmentpanel.AppointmentListPanel;

/**
 * Clears the appointment list.
 */
public class ClearAppointmentCommand extends Command {
    public static final String COMMAND_WORD = "clearapt";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": clears all appointments in Homerce.";
    public static final String MESSAGE_CLEAR_APPOINTMENT_SUCCESS = "Appointment list has been cleared!";

    @Override
    public CommandResult execute(Model model, HistoryManager historyManager) {
        requireNonNull(model);
        model.setAppointment(new ArrayList<>());
        model.updateFilteredAppointmentList(Model.PREDICATE_SHOW_ALL_APPOINTMENTS);
        model.refreshSchedule();
        return new CommandResult(MESSAGE_CLEAR_APPOINTMENT_SUCCESS, AppointmentListPanel.TAB_NAME);
    }
}
