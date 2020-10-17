package seedu.address.logic.commands.AppointmentCommand;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PATIENTS;

import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.patient.Address;
import seedu.address.model.patient.Appointment;
import seedu.address.model.patient.Email;
import seedu.address.model.patient.Name;
import seedu.address.model.patient.Nric;
import seedu.address.model.patient.Patient;
import seedu.address.model.patient.Phone;
import seedu.address.model.tag.Tag;

public class AddApptCommand extends Command {

    public static final String COMMAND_WORD = "addAppt";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Add an appointment to the person identified "
            + "by the index number used in the displayed person list. \n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_APPOINTMENT + "APPOINTMENT TIME] \n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_APPOINTMENT + "28/09/2020 20:00 ";

    public static final String MESSAGE_ADD_APPT_SUCCESS = "Add Appointment Successfully!";

    private final Index index;
    private final Appointment appointment;

    /**
     * @param index of the person in the filtered person list to add appointment to
     * @param appointment the appointment to add to the patient
     */
    public AddApptCommand(Index index, Appointment appointment) {
        this.index = index;
        this.appointment = appointment;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Patient> lastShownList = model.getFilteredPatientList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
        }

        Patient patientToAddAppt = lastShownList.get(index.getZeroBased());
        Patient changedPatient = createChangedPerson(patientToAddAppt, appointment);

        assert !patientToAddAppt.isSamePatient(changedPatient);
        assert !model.hasPatient(changedPatient);

        model.setPatient(patientToAddAppt, changedPatient);
        model.updateFilteredPatientList(PREDICATE_SHOW_ALL_PATIENTS);
        return new CommandResult(String.format(MESSAGE_ADD_APPT_SUCCESS, changedPatient));
    }

    /**
     * Creates and returns a {@code Patient} with the details of {@code personToAddAppt}
     * edited with {@code editPersonDescriptor}.
     */
    private static Patient createChangedPerson(Patient patientToAddAppt, Appointment appointment) {
        assert patientToAddAppt != null;

        Name updatedName = patientToAddAppt.getName();
        Phone updatedPhone = patientToAddAppt.getPhone();
        Email updatedEmail = patientToAddAppt.getEmail();
        Nric updatedNric = patientToAddAppt.getNric();
        Address updatedAddress = patientToAddAppt.getAddress();
        Set<Tag> updatedTags = patientToAddAppt.getTags();
        Set<Appointment> appointments = patientToAddAppt.getAppointments();
        appointments.add(appointment);

        return new Patient(updatedName, updatedNric, updatedPhone, updatedEmail, updatedAddress,
                updatedTags, appointments);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddApptCommand)) {
            return false;
        }

        // state check
        AddApptCommand e = (AddApptCommand) other;
        return index.equals(e.index)
                && appointment.equals(e.appointment);
    }
}
