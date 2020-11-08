package seedu.address.logic.commands.appointmentcommand;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PATIENTS;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.allergy.Allergy;
import seedu.address.model.patient.Address;
import seedu.address.model.patient.Appointment;
import seedu.address.model.patient.Email;
import seedu.address.model.patient.MedicalRecord;
import seedu.address.model.patient.Name;
import seedu.address.model.patient.Nric;
import seedu.address.model.patient.Patient;
import seedu.address.model.patient.Phone;

/**
 * Deletes an appointment for a patient in Hospify.
 */
public class DeleteApptCommand extends Command {

    public static final String COMMAND_WORD = "deleteAppt";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes an appointment at the "
            + "specified time for the patient specified "
            + "by the NRIC of the patient. \n"
            + "Parameters: [NRIC] "
            + "[" + PREFIX_APPOINTMENT + "APPOINTMENT TIME] \n"
            + "Example: " + COMMAND_WORD + " S1234567A "
            + PREFIX_APPOINTMENT + "28/09/2022 20:00";

    public static final String MESSAGE_DELETE_APPT_SUCCESS = "Deleted appointment successfully!";
    public static final String MESSAGE_MISSING_APPOINTMENT =
            "This patient does not have any appointments with this timing";

    private final Nric nric;
    private final Appointment appointment;

    /**
     * Initialize a DeleteApptCommand using Nric and Appointment.
     *
     * @param nric Nric of the patient in the filtered person list to delete appointment from.
     * @param appointment The appointment to delete from the patient.
     */
    public DeleteApptCommand(Nric nric, Appointment appointment) {
        requireNonNull(nric);
        requireNonNull(appointment);
        this.nric = nric;
        this.appointment = appointment;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Patient> lastShownList = model.getFilteredPatientList();

        Patient patientToDeleteAppt;

        lastShownList = lastShownList.stream()
                .filter(patient -> patient.getNric().value.equals(nric.value.toUpperCase()))
                .collect(Collectors.toList());
        if (lastShownList.size() != 1) {
            throw new CommandException(Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_NRIC);
        }
        patientToDeleteAppt = lastShownList.get(0);

        if (!patientToDeleteAppt.getAppointments().contains(appointment)) {
            throw new CommandException(MESSAGE_MISSING_APPOINTMENT);
        }

        Set<Appointment> appointments = patientToDeleteAppt.getModifiableAppointments();
        appointments.remove(appointment);
        Patient newPatient = createNewPatient(patientToDeleteAppt, appointments);

        model.setPatient(patientToDeleteAppt, newPatient);
        model.updateFilteredPatientList(PREDICATE_SHOW_ALL_PATIENTS);
        return new CommandResult(String.format(MESSAGE_DELETE_APPT_SUCCESS, newPatient));
    }

    /**
     * Creates and returns a {@code Patient} with the details of {@code patientToDeleteAppt}
     * edited with {@code editPersonDescriptor}.
     */
    private static Patient createNewPatient(Patient patientToDeleteAppt, Set<Appointment> appointments) {
        assert patientToDeleteAppt != null : "Patient to delete Appointment should not be null!";

        Name updatedName = patientToDeleteAppt.getName();
        Phone updatedPhone = patientToDeleteAppt.getPhone();
        Email updatedEmail = patientToDeleteAppt.getEmail();
        Nric updatedNric = patientToDeleteAppt.getNric();
        Address updatedAddress = patientToDeleteAppt.getAddress();
        Set<Allergy> updatedAllergies = patientToDeleteAppt.getAllergies();
        MedicalRecord updatedMedicalRecord = patientToDeleteAppt.getMedicalRecord();

        return new Patient(updatedName, updatedNric, updatedPhone, updatedEmail, updatedAddress,
                updatedAllergies, appointments, updatedMedicalRecord);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (this == other) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteApptCommand)) {
            return false;
        }

        // state check
        DeleteApptCommand e = (DeleteApptCommand) other;
        return nric.equals(e.nric)
                && appointment.equals(e.appointment);
    }

}
