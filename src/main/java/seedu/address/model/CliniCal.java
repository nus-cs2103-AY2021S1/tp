package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.UniqueAppointmentList;
import seedu.address.model.patient.Patient;
import seedu.address.model.patient.UniquePatientList;

/**
 * Wraps all data at the CliniCal application level
 * Duplicates are not allowed (by .isSamePatient comparison)
 */
public class CliniCal implements ReadOnlyCliniCal {

    private final UniquePatientList patients;
    private final UniqueAppointmentList appointments;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        patients = new UniquePatientList();
        appointments = new UniqueAppointmentList();
    }

    public CliniCal() {}

    /**
     * Creates a CliniCal using the Patients in the {@code toBeCopied}
     */
    public CliniCal(ReadOnlyCliniCal toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the patient list with {@code patients}.
     * {@code patients} must not contain duplicate patients.
     */
    public void setPatients(List<Patient> patients) {
        this.patients.setPatients(patients);
    }

    /**
     * Replaces the contents of the appointment list with {@code appointments}.
     * {@code appointments} must not contain any duplicate appointments.
     */
    public void setAppointments(List<Appointment> appointments) {
        this.appointments.setAppointments(appointments);
    }

    /**
     * Resets the existing data of this {@code CliniCal} with {@code newData}.
     */
    public void resetData(ReadOnlyCliniCal newData) {
        requireNonNull(newData);

        setPatients(newData.getPatientList());
        setAppointments(newData.getAppointmentList());
    }

    //// patient-level operations

    /**
     * Returns true if a patient with the same identity as {@code patient} exists in the patient database.
     */
    public boolean hasPatient(Patient patient) {
        requireNonNull(patient);
        return patients.contains(patient);
    }

    /**
     * Adds a patient to the patient database.
     * The patient must not already exist in the database.
     */
    public void addPatient(Patient p) {
        patients.add(p);
    }

    /**
     * Replaces the given patient {@code target} in the list with {@code editedPatient}.
     * {@code target} must exist in the patient database.
     * The patient identity of {@code editedPatient} must not be the same as another existing
     * patient in the database.
     */
    public void setPatient(Patient target, Patient editedPatient) {
        requireNonNull(editedPatient);

        patients.setPatient(target, editedPatient);
    }

    /**
     * Removes {@code key} from this {@code CliniCal}.
     * {@code key} must exist in the patient database.
     */
    public void removePatient(Patient key) {
        patients.remove(key);
    }

    //// appointment-level operations

    /**
     * Returns true if an appointment with the same identity as {@code appointment} exists in the appointment list.
     */
    public boolean hasAppointment(Appointment appointment) {
        requireNonNull(appointment);
        return appointments.clashes(appointment);
    }

    /**
     * Adds an appointment to the appointments.
     * The appointment must not already exist in the appointment list.
     */
    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }

    /**
     * Replaces the given appointment {@code target} in the list with {@code editedAppointment}.
     * {@code target} must exist in the appointment list.
     * The appointment identity of {@code editedAppointment} must not be the same as another existing
     * appointment in the appointment list.
     */
    public void setAppointment(Appointment target, Appointment editedAppointment) {
        requireNonNull(editedAppointment);

        appointments.setAppointment(target, editedAppointment);
    }

    /**
     * Removes {@code key} from this {@code CliniCal}.
     * {@code key} must exist in the appointments list.
     */
    public void removeAppointment(Appointment key) {
        appointments.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return patients.asUnmodifiableObservableList().size() + " patients";
        // TODO: refine later
    }

    @Override
    public ObservableList<Patient> getPatientList() {
        return patients.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Appointment> getAppointmentList() {
        return appointments.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CliniCal // instanceof handles nulls
                && patients.equals(((CliniCal) other).patients));
    }

    @Override
    public int hashCode() {
        return patients.hashCode();
    }
}
