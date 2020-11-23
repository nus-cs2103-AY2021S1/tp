package seedu.address.logic.commands.appointment;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PLACEHOLDER_CLIENT_INDEX;
import static seedu.address.logic.parser.CliSyntax.PLACEHOLDER_DATE_OF_APPT;
import static seedu.address.logic.parser.CliSyntax.PLACEHOLDER_HAIRDRESSER_INDEX;
import static seedu.address.logic.parser.CliSyntax.PLACEHOLDER_START_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLIENT_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE_OF_APPT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HAIRDRESSER_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_TIME;

import java.util.List;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentDate;
import seedu.address.model.appointment.AppointmentTime;
import seedu.address.model.appointment.FutureAppointment;
import seedu.address.model.appointment.exceptions.AppointmentNotInFutureException;
import seedu.address.model.person.client.Client;
import seedu.address.model.person.client.ClientId;
import seedu.address.model.person.hairdresser.Hairdresser;
import seedu.address.model.person.hairdresser.HairdresserId;

public class AddAppointmentCommand extends AddCommand {
    public static final String COMMAND_WORD = "add_appt";
    public static final String COMMAND_EXAMPLE = "Example: " + COMMAND_WORD + " "
        + PREFIX_CLIENT_ID + "1 "
        + PREFIX_HAIRDRESSER_ID + "1 "
        + PREFIX_DATE_OF_APPT + "2021-06-01 "
        + PREFIX_START_TIME + "09:00 ";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a new appointment to HairStyleX. "
        + "Parameters: "
        + PREFIX_CLIENT_ID + PLACEHOLDER_CLIENT_INDEX + " "
        + PREFIX_HAIRDRESSER_ID + PLACEHOLDER_HAIRDRESSER_INDEX + " "
        + PREFIX_DATE_OF_APPT + PLACEHOLDER_DATE_OF_APPT + " "
        + PREFIX_START_TIME + PLACEHOLDER_START_TIME + " " + "\n"
        + COMMAND_EXAMPLE;
    public static final String MESSAGE_SUCCESS = "New appointment added: %1$s";
    public static final String MESSAGE_DUPLICATE_APPOINTMENT = "This appointment already exists in HairStyleX";
    public static final String MESSAGE_CLIENT_NOT_FOUND =
        "Client with this ID is not found. Please enter a valid client ID.";
    public static final String MESSAGE_HAIRDRESSER_NOT_NOT_FOUND =
        "Hairdresser with this ID is not found. Please enter a valid hairdresser ID.";
    public static final String MESSAGE_CLASHING_APPOINTMENT =
        "This appointment you are attempting to add clashes with another appointment. "
            + "The other appointment's ID is %d.";

    private final ClientId clientId;
    private final HairdresserId hairdresserId;
    private final AppointmentDate date;
    private final AppointmentTime time;

    /**
     * Creates an AddAppointmentCommand to add the specified {@code appointment} created from {@code clientIndex},
     * {@code hairdresserIndex}, {@code date} and {@code time}.
     *
     * @param clientId      Client's Id
     * @param hairdresserId Hairdresser's Id
     * @param date          Date of appointment
     * @param time          TIme of appointment
     */
    public AddAppointmentCommand(ClientId clientId, HairdresserId hairdresserId, AppointmentDate date,
                                 AppointmentTime time) {
        this.clientId = clientId;
        this.hairdresserId = hairdresserId;
        this.date = date;
        this.time = time;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {

        requireNonNull(model);
        Client client = model.getClientById(clientId);
        Hairdresser hairdresser = model.getHairdresserById(hairdresserId);
        if (client == null) {
            throw new CommandException(MESSAGE_CLIENT_NOT_FOUND);
        }
        if (hairdresser == null) {
            throw new CommandException(MESSAGE_HAIRDRESSER_NOT_NOT_FOUND);
        }
        Appointment appointment;
        try {
            appointment = new FutureAppointment(client, hairdresser, date, time);
        } catch (AppointmentNotInFutureException e) {
            throw new CommandException(FutureAppointment.MESSAGE_CONSTRAINT_FUTURE);
        }

        if (model.hasAppointment(appointment)) {
            throw new CommandException(MESSAGE_DUPLICATE_APPOINTMENT);
        }

        // check if the new appointment clashes with any existing ones with the same client or haidresser
        List<Appointment> appointments = model.getAppointmentList();
        for (Appointment appt : appointments) {
            if (appointment.isClash(appt)) {
                throw new CommandException(String.format(MESSAGE_CLASHING_APPOINTMENT, appt.getId().getId()));
            }
        }
        //List<Appointment> sameHairdresser = appointments.stream()
        //    .filter(appt -> appt.getHairdresserId().equals(this.hairdresserId)).collect(Collectors.toList());
        //List<Appointment> sameClient = appointments.stream()
        //    .filter(appt -> appt.getClientId().equals(this.clientId)).collect(Collectors.toList());

        model.addAppointment(appointment);
        return new CommandResult(String.format(MESSAGE_SUCCESS, appointment));
    }
}
