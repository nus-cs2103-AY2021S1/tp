package seedu.medibook.model;

import java.util.Optional;

import seedu.medibook.model.doctor.Doctor;
import seedu.medibook.model.patient.Ic;
import seedu.medibook.model.patient.Patient;

/**
 * Wraps the data from the most recently executed command.
 */
public class ModelContext implements Context {
    private Optional<Patient> accessedPatient = Optional.empty();
    private boolean shouldLoadMedicalNotes = true;
    private Optional<Patient> deletedPatient = Optional.empty();
    private Optional<Patient> editedPatient = Optional.empty();
    private Optional<Ic> editedPatientPrevIc = Optional.empty();
    private boolean shouldDeleteAllMedicalNotes = false;
    private Optional<Doctor> currentDoctor = Optional.empty();

    @Override
    public void accessPatient(Patient patient) {
        this.accessedPatient = Optional.of(patient);
    }

    @Override
    public Optional<Patient> getPatientToAccess() {
        return this.accessedPatient;
    }

    @Override
    public void resetAccessedPatient() {
        this.accessedPatient = Optional.empty();
    }

    @Override
    public void setDeletedPatient(Patient patient) {
        this.deletedPatient = Optional.of(patient);
    }

    @Override
    public Optional<Patient> getDeletedPatient() {
        return this.deletedPatient;
    }

    @Override
    public void resetDeletedPatient() {
        this.deletedPatient = Optional.empty();
    }

    @Override
    public void setEditedPatient(Patient patient, Ic prevIc) {
        this.editedPatient = Optional.of(patient);
        this.editedPatientPrevIc = Optional.of(prevIc);
    }

    @Override
    public Optional<Patient> getEditedPatient() {
        return this.editedPatient;
    }

    @Override
    public Optional<Ic> getEditedPatientPrevIc() {
        return this.editedPatientPrevIc;
    }

    @Override
    public void resetEditedPatient() {
        this.editedPatient = Optional.empty();
        this.editedPatientPrevIc = Optional.empty();
    }

    @Override
    public boolean getShouldLoadMedicalNotes() {
        return this.shouldLoadMedicalNotes;
    }

    @Override
    public void setShouldLoadMedicalNotes(boolean b) {
        this.shouldLoadMedicalNotes = b;
    }

    @Override
    public boolean getShouldDeleteAllMedicalNotes() {
        return this.shouldDeleteAllMedicalNotes;
    }

    @Override
    public void setShouldDeleteAllMedicalNotes(boolean b) {
        this.shouldDeleteAllMedicalNotes = b;
    }

    @Override
    public void setActiveUser(Optional<Doctor> doctor) {
        this.currentDoctor = doctor;
    }

    @Override
    public Optional<Doctor> getActiveUser() {
        return this.currentDoctor;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof ModelContext)) {
            return false;
        }

        ModelContext otherContext = (ModelContext) other;
        return otherContext.shouldDeleteAllMedicalNotes == shouldDeleteAllMedicalNotes
                && otherContext.shouldLoadMedicalNotes == shouldLoadMedicalNotes
                && otherContext.accessedPatient.equals(accessedPatient)
                && otherContext.deletedPatient.equals(deletedPatient)
                && otherContext.editedPatient.equals(editedPatient)
                && otherContext.editedPatientPrevIc.equals(editedPatientPrevIc);
    }
}
