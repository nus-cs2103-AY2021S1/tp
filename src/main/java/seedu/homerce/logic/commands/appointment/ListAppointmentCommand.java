package seedu.homerce.logic.commands.appointment;

import static java.util.Objects.requireNonNull;
import static seedu.homerce.model.Model.PREDICATE_SHOW_ALL_APPOINTMENTS;

import seedu.homerce.logic.commands.Command;
import seedu.homerce.logic.commands.CommandResult;
import seedu.homerce.model.Model;
import seedu.homerce.model.manager.HistoryManager;
import seedu.homerce.ui.appointmentpanel.AppointmentListPanel;

public class ListAppointmentCommand extends Command {

    public static final String COMMAND_WORD = "listapt";
    public static final String MESSAGE_LIST_APPOINTMENT_SUCCESS = "Listed all appointments.";

    @Override
    public CommandResult execute(Model model, HistoryManager historyManager) {
        requireNonNull(model);
        model.updateFilteredAppointmentList(PREDICATE_SHOW_ALL_APPOINTMENTS);
        return new CommandResult(MESSAGE_LIST_APPOINTMENT_SUCCESS, AppointmentListPanel.TAB_NAME);
    }
}
