package seedu.medibook.model;

import java.util.Optional;

import seedu.medibook.model.doctor.Doctor;
import seedu.medibook.model.patient.Ic;
import seedu.medibook.model.patient.Patient;

public interface Context {
    /**
     * Accesses the given patient.
     * The patient must exist in the medi book.
     */
    void accessPatient(Patient patient);

    /**
     * Returns an {@code Optional} of the current accessed {@code patient}.
     */
    Optional<Patient> getPatientToAccess();

    /**
     * Resets the accessed patient.
     */
    void resetAccessedPatient();

    /**
     * Sets the {@code patient} that has just been deleted.
     */
    void setDeletedPatient(Patient patient);

    /**
     * Returns the {@code patient} that has just been deleted.
     */
    Optional<Patient> getDeletedPatient();

    /**
     * Resets the {@code patient} that has just been deleted.
     */
    void resetDeletedPatient();

    /**
     * Sets the {@code patient} that has just been edited.
     */
    void setEditedPatient(Patient patient, Ic prevIc);

    /**
     * Returns the {@code patient} that has just been edited.
     */
    Optional<Patient> getEditedPatient();

    /**
     * Returns the previous Ic of an edited patient assuming the Ic was edited.
     */
    Optional<Ic> getEditedPatientPrevIc();

    /**
     * Resets the {@code patient} that has just been edited.
     */
    void resetEditedPatient();

    /**
     * Returns a boolean on whether the medical notes of a {@code patient} should be loaded.
     */
    boolean getShouldLoadMedicalNotes();

    /**
     * Sets the boolean on whether the medical notes of a {@code patient} should be loaded.
     */
    void setShouldLoadMedicalNotes(boolean b);

    /**
     * Returns a boolean on whether all medical notes should be cleared.
     */
    boolean getShouldDeleteAllMedicalNotes();

    /**
     * Sets the boolean on whether all medical notes should be cleared.
     */
    void setShouldDeleteAllMedicalNotes(boolean b);

    /**
     * Sets the current user using MediBook.
     */
    void setActiveUser(Optional<Doctor> doctor);

    /**
     * Returns the doctor currently using the system.
     */
    Optional<Doctor> getActiveUser();
}
