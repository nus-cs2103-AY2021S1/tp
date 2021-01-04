package seedu.homerce.logic.commands.appointment;

import static java.util.Objects.requireNonNull;
import static seedu.homerce.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.homerce.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.homerce.logic.parser.CliSyntax.PREFIX_SERVICE_SERVICE_CODE;
import static seedu.homerce.logic.parser.CliSyntax.PREFIX_TIME_OF_DAY;

import java.time.LocalTime;

import seedu.homerce.logic.commands.Command;
import seedu.homerce.logic.commands.CommandResult;
import seedu.homerce.logic.commands.exceptions.CommandException;
import seedu.homerce.model.Model;
import seedu.homerce.model.appointment.Appointment;
import seedu.homerce.model.appointment.AppointmentTemp;
import seedu.homerce.model.client.Client;
import seedu.homerce.model.manager.HistoryManager;
import seedu.homerce.model.service.Service;
import seedu.homerce.ui.appointmentpanel.AppointmentListPanel;

/**
 * Adds an appointment to Homerce.
 */
public class AddAppointmentCommand extends Command {
    public static final String COMMAND_WORD = "addapt";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an appointment to the homerce. "
        + "Parameters: "
        + PREFIX_DATE + "DATE "
        + PREFIX_TIME_OF_DAY + "TIME "
        + PREFIX_SERVICE_SERVICE_CODE + "SERVICE_CODE "
        + PREFIX_PHONE + "PHONE" + "\n"
        + "Example: " + COMMAND_WORD + " "
        + PREFIX_DATE + "15-2-2021 "
        + PREFIX_TIME_OF_DAY + "1430 "
        + PREFIX_SERVICE_SERVICE_CODE + "SC001 "
        + PREFIX_PHONE + "91031282";
    public static final String MESSAGE_CLASHING_APPOINTMENT = "This appointment clashes with an existing appointment.";
    public static final String MESSAGE_INVALID_TIME_AND_DURATION =
        "The appointment cannot be conducted until or past midnight.";
    public static final String MESSAGE_INVALID_PHONE = "The phone number specified does not refer "
        + "to an existing client.";
    public static final String MESSAGE_INVALID_SERVICE_CODE = "The service code specified does"
        + " not exist in Homerce.";
    private static final String MESSAGE_ADD_APPOINTMENT_SUCCESS = "New appointment added: %1$s";
    private final AppointmentTemp toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Client}
     */
    public AddAppointmentCommand(AppointmentTemp appointment) {
        requireNonNull(appointment);
        toAdd = appointment;
    }

    @Override
    public CommandResult execute(Model model, HistoryManager historyManager) throws CommandException {
        requireNonNull(model);
        // Check if phone number and service code are present in the model.
        if (!model.checkClientWithPhone(toAdd.getPhone())) {
            throw new CommandException(MESSAGE_INVALID_PHONE);
        } else if (!model.hasService(toAdd.getServiceCode())) {
            throw new CommandException(MESSAGE_INVALID_SERVICE_CODE);
        }
        // Create client and service with the phone number and service code.
        Client clientToAdd = model.getClientByPhone(toAdd.getPhone());
        Service serviceToAdd = model.getServiceByServiceCode(toAdd.getServiceCode());
        Appointment resultToAdd = new Appointment(
            toAdd.getAppointmentDate(), toAdd.getAppointmentStartTime(),
            clientToAdd, serviceToAdd
        );
        // Check if appointment is already present.
        if (model.hasAppointment(resultToAdd)) {
            throw new CommandException(MESSAGE_CLASHING_APPOINTMENT);
        } else if (!isValidTimeAndDuration(resultToAdd)) {
            // Check if appointment runs past midnight.
            throw new CommandException(MESSAGE_INVALID_TIME_AND_DURATION);
        } else {
            model.addAppointment(resultToAdd);
        }
        model.updateFilteredAppointmentList(Model.PREDICATE_SHOW_ALL_APPOINTMENTS);
        model.refreshSchedule();
        return new CommandResult(
            String.format(MESSAGE_ADD_APPOINTMENT_SUCCESS, resultToAdd),
            AppointmentListPanel.TAB_NAME
        );
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof AddAppointmentCommand // instanceof handles nulls
            && toAdd.equals(((AddAppointmentCommand) other).toAdd));
    }

    private boolean isValidTimeAndDuration(Appointment appointmentToTest) {
        /// Check if timeOfDay + duration overflows past midnight.
        LocalTime startTime = appointmentToTest.getAppointmentStartTime().getLocalTime();
        LocalTime endTime = appointmentToTest.getAppointmentEndTime().getLocalTime();
        return endTime.compareTo(startTime) > 0; // End time must be after start time.
    }
}
