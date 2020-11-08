package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPatients.ALICE;
import static seedu.address.testutil.TypicalPatients.getTypicalPatientRecords;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.patient.Patient;
import seedu.address.model.patient.exceptions.DuplicatePatientException;
import seedu.address.testutil.PatientBuilder;

//@@author
public class PatientRecordsTest {

    private final PatientRecords patientRecords = new PatientRecords();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), patientRecords.getReadOnlyList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> patientRecords.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyCovigentApp_replacesData() {
        PatientRecords newData = getTypicalPatientRecords();
        patientRecords.resetData(newData);
        assertEquals(newData, patientRecords);
    }

    @Test
    public void resetData_withDuplicatePatients_throwsDuplicatePatientException() {
        // Two patients with the same identity fields
        Patient editedAlice = new PatientBuilder(ALICE).build();
        List<Patient> newPatients = Arrays.asList(ALICE, editedAlice);
        PatientRecordsStub newData = new PatientRecordsStub(newPatients);

        assertThrows(DuplicatePatientException.class, () -> patientRecords.resetData(newData));
    }

    @Test
    public void hasPatient_nullPatient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> patientRecords.hasPatient(null));
    }

    @Test
    public void hasPatient_patientNotInCovigentApp_returnsFalse() {
        assertFalse(patientRecords.hasPatient(ALICE));
    }

    @Test
    public void hasPatient_patientInCovigentApp_returnsTrue() {
        patientRecords.addPatient(ALICE);
        assertTrue(patientRecords.hasPatient(ALICE));
    }

    @Test
    public void hasPatient_patientWithSameIdentityFieldsInCovigentApp_returnsTrue() {
        patientRecords.addPatient(ALICE);
        Patient editedAlice = new PatientBuilder(ALICE).build();
        assertTrue(patientRecords.hasPatient(editedAlice));
    }

    @Test
    public void getPatientList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> patientRecords.getReadOnlyList().remove(0));
    }

    /**
     * A stub {@code ReadOnlyList<Patient>} whose patients list can violate interface constraints.
     */
    private static class PatientRecordsStub implements ReadOnlyList<Patient> {
        private final ObservableList<Patient> patients = FXCollections.observableArrayList();

        PatientRecordsStub(Collection<Patient> patients) {
            this.patients.setAll(patients);
        }

        @Override
        public ObservableList<Patient> getReadOnlyList() {
            return patients;
        }
    }
}
