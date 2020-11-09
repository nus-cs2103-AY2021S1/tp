package seedu.medibook.model;

import javafx.collections.ObservableList;
import seedu.medibook.model.patient.Patient;

/**
 * Unmodifiable view of an medi book
 */
public interface ReadOnlyMediBook {

    /**
     * Returns an unmodifiable view of the patients list.
     * This list will not contain any duplicate patients.
     */
    ObservableList<Patient> getPatientList();

}
