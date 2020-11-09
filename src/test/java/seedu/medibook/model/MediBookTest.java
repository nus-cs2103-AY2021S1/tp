package seedu.medibook.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.medibook.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.medibook.logic.commands.CommandTestUtil.VALID_HEIGHT_BOB;
import static seedu.medibook.logic.commands.CommandTestUtil.VALID_TREATMENT_PHYSIOTHERAPY;
import static seedu.medibook.testutil.Assert.assertThrows;
import static seedu.medibook.testutil.TypicalPatients.ALICE;
import static seedu.medibook.testutil.TypicalPatients.getTypicalMediBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.medibook.model.patient.Patient;
import seedu.medibook.model.patient.exceptions.DuplicatePatientException;
import seedu.medibook.testutil.PatientBuilder;

public class MediBookTest {

    private final MediBook mediBook = new MediBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), mediBook.getPatientList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> mediBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyMediBook_replacesData() {
        MediBook newData = getTypicalMediBook();
        mediBook.resetData(newData);
        assertEquals(newData, mediBook);
    }

    @Test
    public void resetData_withDuplicatePatients_throwsDuplicatePatientException() {
        // Two patients with the same identity fields
        Patient editedAlice = new PatientBuilder(ALICE).withAddress(VALID_ADDRESS_BOB)
                .withTreatments(VALID_TREATMENT_PHYSIOTHERAPY).withHeight(VALID_HEIGHT_BOB).build();
        List<Patient> newPatients = Arrays.asList(ALICE, editedAlice);
        MediBookStub newData = new MediBookStub(newPatients);

        assertThrows(DuplicatePatientException.class, () -> mediBook.resetData(newData));
    }

    @Test
    public void hasPatient_nullPatient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> mediBook.hasPatient(null));
    }

    @Test
    public void hasPatient_patientNotInMediBook_returnsFalse() {
        assertFalse(mediBook.hasPatient(ALICE));
    }

    @Test
    public void hasPatient_patientInMediBook_returnsTrue() {
        mediBook.addPatient(ALICE);
        assertTrue(mediBook.hasPatient(ALICE));
    }

    @Test
    public void hasPatient_patientWithSameIdentityFieldsInMediBook_returnsTrue() {
        mediBook.addPatient(ALICE);
        Patient editedAlice = new PatientBuilder(ALICE).withAddress(VALID_ADDRESS_BOB)
                .withTreatments(VALID_TREATMENT_PHYSIOTHERAPY).build();
        assertTrue(mediBook.hasPatient(editedAlice));
    }

    @Test
    public void getPatientList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> mediBook.getPatientList().remove(0));
    }

    /**
     * A stub ReadOnlyMediBook whose patients list can violate interface constraints.
     */
    private static class MediBookStub implements ReadOnlyMediBook {
        private final ObservableList<Patient> patients = FXCollections.observableArrayList();

        MediBookStub(Collection<Patient> patients) {
            this.patients.setAll(patients);
        }

        @Override
        public ObservableList<Patient> getPatientList() {
            return patients;
        }
    }

}
