package seedu.address.logic.commands.appointment;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPT_STATUS;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentId;
import seedu.address.model.appointment.AppointmentStatus;

public class EditAppointmentCommand extends Command {
    public static final String COMMAND_WORD = "edit_appt";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Change the status of an existing appointment. "
            + "The status that can be set are: "
            + AppointmentStatus.ACTIVE.name() + ", "
            + AppointmentStatus.COMPLETED.name() + ", "
            + AppointmentStatus.MISSED.name() + "\n"
            + "Parameters: APPOINTMENT_ID (must be a positive integer) " + "\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_APPT_STATUS + "COMPLETED";

    public static final String MESSAGE_MARK_APPT_SUCCESS = "Changed status of Appointment: %1$s";
    public static final String MESSAGE_STATUS_MISSING = "The new appointment status must be provided.";
    public static final String MESSAGE_DUPLICATE_APPT = "This appointment already exists in HairStyleX.";
    public static final String MESSAGE_STATUS_UNCHANGED = "The new status is the same as the existing status.";

    private final AppointmentId appointmentId;
    private final ChangedAppointmentDescriptor changedAppointmentDescriptor;

    /**
     * @param appointmentId of the appointment in the filtered appointment list
     * @param changedAppointmentDescriptor details of the changed status
     */
    public EditAppointmentCommand(AppointmentId appointmentId,
                                  ChangedAppointmentDescriptor changedAppointmentDescriptor) {
        requireNonNull(appointmentId);
        requireNonNull(changedAppointmentDescriptor);

        this.appointmentId = appointmentId;
        this.changedAppointmentDescriptor = new ChangedAppointmentDescriptor(changedAppointmentDescriptor);
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Appointment appointmentToChange = model.getAppointmentById(appointmentId);
        if (appointmentId == null) {
            throw new CommandException(Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_ID);
        }
        Appointment changedAppointment = createChangedAppointment(appointmentToChange, changedAppointmentDescriptor);

        if (!appointmentToChange.isSameAppointment(changedAppointment) && model.hasAppointment(changedAppointment)) {
            throw new CommandException(MESSAGE_DUPLICATE_APPT);
        }

        if (appointmentToChange.getAppointmentStatus().equals(changedAppointment.getAppointmentStatus())) {
            throw new CommandException(MESSAGE_STATUS_UNCHANGED);
        }

        if ((changedAppointment.getAppointmentStatus().equals(AppointmentStatus.COMPLETED)
                || changedAppointment.getAppointmentStatus().equals(AppointmentStatus.MISSED))
                && !appointmentToChange.isPast()) {
            throw new CommandException(AppointmentStatus.MESSAGE_CONSTRAINT_PAST);
        }

        model.setAppointment(appointmentToChange, changedAppointment);

        return new CommandResult(String.format(MESSAGE_MARK_APPT_SUCCESS, changedAppointment));
    }

    /**
     * Creates and returns a {@code Appointment} with the details of {@code appointmentToChange}
     * changed with {@code changedAppointmentDescriptor}.
     */
    private static Appointment createChangedAppointment(Appointment appointmentToChange,
                                                        ChangedAppointmentDescriptor changedAppointmentDescriptor) {
        assert appointmentToChange != null;

        AppointmentStatus newStatus = changedAppointmentDescriptor.getStatus();

        return new Appointment(
                appointmentToChange.getId(),
                appointmentToChange.getClient(),
                appointmentToChange.getHairdresser(),
                appointmentToChange.getDate(),
                appointmentToChange.getTime(),
                newStatus);
    }

    /**
     * Stores new status of the appointment.
     */
    public static class ChangedAppointmentDescriptor {
        private AppointmentStatus status;

        public ChangedAppointmentDescriptor() {
        }

        /**
         * Copy constructor. For defensive purposes, ensures only a copy is used.
         */
        public ChangedAppointmentDescriptor(ChangedAppointmentDescriptor toCopy) {
            setStatus(toCopy.status);
        }

        /**
         * Returns true if status is changed.
         */
        public boolean isStatusChanged() {
            return status != null;
        }

        public void setStatus(AppointmentStatus status) {
            this.status = status;
        }

        public AppointmentStatus getStatus() {
            return status;
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditAppointmentCommand.ChangedAppointmentDescriptor)) {
                return false;
            }

            // state check
            ChangedAppointmentDescriptor e = (ChangedAppointmentDescriptor) other;
            return getStatus().equals(e.getStatus());
        }
    }
}
