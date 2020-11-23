package seedu.address.logic.commands.appointment;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.DeleteEntityCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentId;

/**
 * Deletes an appointment identified using it's displayed index from the HairStyleX.
 */
public class DeleteAppointmentCommand extends DeleteEntityCommand<AppointmentId> {
    public static final String COMMAND_WORD = "delete_appt";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the appointment identified by the appointment ID used in the displayed appointment list.\n"
            + "Parameters: APPOINTMENT_ID (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_APPOINTMENT_SUCCESS = "Deleted Appointment: %1$s";

    public DeleteAppointmentCommand(AppointmentId targetId) {
        super(targetId);
    }


    @Override
    public String deleteFromModel(Model model) throws CommandException {

        Appointment appointmentToDelete = model.getAppointmentById(targetId);
        if (appointmentToDelete == null) {
            throw new CommandException(Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_ID);
        }
        model.deleteAppointment(appointmentToDelete);

        return String.format(MESSAGE_DELETE_APPOINTMENT_SUCCESS, appointmentToDelete);
    }

}
