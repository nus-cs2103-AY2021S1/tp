package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.patient.MedicalRecord;
import seedu.address.model.patient.Nric;
import seedu.address.model.patient.Patient;
import seedu.address.model.patient.UniquePatientList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePatient comparison)
 */
public class HospifyBook implements ReadOnlyHospifyBook {

    private final UniquePatientList patients;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        patients = new UniquePatientList();
    }

    public HospifyBook() {}

    /**
     * Creates an Hospify using the Patients in the {@code toBeCopied}
     */
    public HospifyBook(ReadOnlyHospifyBook toBeCopied) {
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
     * Resets the existing data of this {@code HospifyBook} with {@code newData}.
     */
    public void resetData(ReadOnlyHospifyBook newData) {
        requireNonNull(newData);

        setPatients(newData.getPatientList());
    }

    //// patient-level operations

    /**
     * Returns true if a patient with the same identity as {@code patient} exists in Hospify.
     */
    public boolean hasPatient(Patient patient) {
        requireNonNull(patient);
        return patients.contains(patient);
    }

    /**
     * Returns true if a patient with the same NRIC as {@code nric} exists in Hospify.
     */
    public boolean hasPatientWithNric(Nric nric) {
        requireNonNull(nric);
        for (Patient patient : patients) {
            if (patient.getNric().equals(nric)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns true if a patient with the same Medical Record Url as {@code url} exists in Hospify.
     */
    public boolean hasPatientWithMrUrl(MedicalRecord url) {
        requireNonNull(url);
        for (Patient patient : patients) {
            if (patient.getMedicalRecord().equals(url)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds a patient to Hospify.
     * The patient must not already exist in Hospify.
     */
    public void addPatient(Patient p) {
        patients.add(p);
    }

    /**
     * Replaces the given patient {@code target} in the list with {@code editedPatient}.
     * {@code target} must exist in Hospify.
     * The patient identity of {@code editedPatient} must not be the same as another existing patient in
     * Hospify.
     */
    public void setPatient(Patient target, Patient editedPatient) {
        requireNonNull(editedPatient);

        patients.setPatient(target, editedPatient);
    }

    /**
     * Removes {@code key} from this {@code HospifyBook}.
     * {@code key} must exist in Hospify.
     */
    public void removePatient(Patient key) {
        patients.remove(key);
    }

    /**
     * Gives the number of records in Hospify.
     * @return number of patients.
     */
    public int count() {
        return patients.count();
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

    public void sort(Comparator<Patient> comparator) {
        patients.sort(comparator);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof HospifyBook // instanceof handles nulls
                && patients.equals(((HospifyBook) other).patients));
    }

    @Override
    public int hashCode() {
        return patients.hashCode();
    }
}
