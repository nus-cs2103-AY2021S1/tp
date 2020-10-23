package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.patient.Patient;

/**
 * Unmodifiable view of Hospify
 */
public interface ReadOnlyHospifyBook {

    /**
     * Returns an unmodifiable view of the patient list.
     * This list will not contain any duplicate patients.
     */
    ObservableList<Patient> getPatientList();

}
