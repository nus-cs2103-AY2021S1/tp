package seedu.address.logic.commands.appointmentcommand;

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
 * Edits an appointment for a patient in Hospify.
 */
public class EditApptCommand extends Command {

    public static final String COMMAND_WORD = "editAppt";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits an appointment at the "
            + "specified time for the patient specified "
            + "by the index number used in the displayed person list. \n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_APPOINTMENT + "OLD APPOINTMENT TIME] "
            + "[" + PREFIX_APPOINTMENT + "NEW APPOINTMENT TIME] \n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_APPOINTMENT + "28/09/2020 20:00 "
            + PREFIX_APPOINTMENT + "30/09/2020 15:00";

    public static final String MESSAGE_EDIT_APPT_SUCCESS = "Edited appointment successfully!";
    public static final String MESSAGE_MISSING_APPOINTMENT =
            "This patient does not have any appointments with this timing";

    private final Index index;
    private final Appointment oldAppointment;
    private final Appointment newAppointment;

    /**
     * Initialize an EditApptCommand using Index and Appointment.
     *
     * @param index Index of the patient in the filtered person list to edit the appointment.
     * @param oldAppointment The appointment to be changed.
     * @param newAppointment The new appointment timing.
     */
    public EditApptCommand(Index index, Appointment oldAppointment, Appointment newAppointment) {
        this.index = index;
        this.oldAppointment = oldAppointment;
        this.newAppointment = newAppointment;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Patient> lastShownList = model.getFilteredPatientList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
        }

        Patient patientToEditAppt = lastShownList.get(index.getZeroBased());

        if (!patientToEditAppt.getAppointments().contains(oldAppointment)) {
            throw new CommandException(MESSAGE_MISSING_APPOINTMENT);
        }

        Set<Appointment> appointments = patientToEditAppt.getModifiableAppointments();
        appointments.remove(oldAppointment);
        appointments.add(newAppointment);
        Patient newPatient = createNewPatient(patientToEditAppt, appointments);

        assert !patientToEditAppt.equals(newPatient) : "New patient should be different from original";

        model.setPatient(patientToEditAppt, newPatient);
        model.updateFilteredPatientList(PREDICATE_SHOW_ALL_PATIENTS);
        return new CommandResult(String.format(MESSAGE_EDIT_APPT_SUCCESS, newPatient));
    }

    /**
     * Creates and returns a {@code Patient} with the details of {@code patientToEditAppt}
     * edited with {@code editPersonDescriptor}.
     */
    private static Patient createNewPatient(Patient patientToEditAppt, Set<Appointment> appointments) {
        assert patientToEditAppt != null : "Patient to edit Appointment should not be null!";

        Name updatedName = patientToEditAppt.getName();
        Phone updatedPhone = patientToEditAppt.getPhone();
        Email updatedEmail = patientToEditAppt.getEmail();
        Nric updatedNric = patientToEditAppt.getNric();
        Address updatedAddress = patientToEditAppt.getAddress();
        Set<Allergy> updatedAllergies = patientToEditAppt.getAllergies();
        MedicalRecord updatedMedicalRecord = patientToEditAppt.getMedicalRecord();

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
        if (!(other instanceof EditApptCommand)) {
            return false;
        }

        // state check
        EditApptCommand e = (EditApptCommand) other;
        return index.equals(e.index)
                && oldAppointment.equals(e.oldAppointment)
                && newAppointment.equals(e.newAppointment);
    }

}
