package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ALLERGY_PENICILLIN;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPatients.ALICE;
import static seedu.address.testutil.TypicalPatients.getTypicalCliniCal;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.patient.Patient;
import seedu.address.model.patient.exceptions.DuplicatePatientException;
import seedu.address.testutil.PatientBuilder;

public class CliniCalTest {

    private final CliniCal cliniCal = new CliniCal();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), cliniCal.getPatientList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> cliniCal.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyCliniCal_replacesData() {
        CliniCal newData = getTypicalCliniCal();
        cliniCal.resetData(newData);
        assertEquals(newData, cliniCal);
    }

    @Test
    public void resetData_withDuplicatePatients_throwsDuplicatePatientException() {
        // Two patients with the same ic number
        Patient editedAlice = new PatientBuilder(ALICE).withAddress(VALID_ADDRESS_BOB)
                .withAllergies(VALID_ALLERGY_PENICILLIN)
                .build();
        List<Patient> newPatients = Arrays.asList(ALICE, editedAlice);
        CliniCalStub newData = new CliniCalStub(newPatients);

        assertThrows(DuplicatePatientException.class, () -> cliniCal.resetData(newData));
    }

    @Test
    public void hasPatient_nullPatient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> cliniCal.hasPatient(null));
    }

    @Test
    public void hasPatient_patientNotInCliniCal_returnsFalse() {
        assertFalse(cliniCal.hasPatient(ALICE));
    }

    @Test
    public void hasPatient_patientInCliniCal_returnsTrue() {
        cliniCal.addPatient(ALICE);
        assertTrue(cliniCal.hasPatient(ALICE));
    }

    @Test
    public void hasPatient_patientWithSameIdentityFieldsInCliniCal_returnsTrue() {
        cliniCal.addPatient(ALICE);
        Patient editedAlice = new PatientBuilder(ALICE).withAddress(VALID_ADDRESS_BOB)
                .withAllergies(VALID_ALLERGY_PENICILLIN)
                .build();
        assertTrue(cliniCal.hasPatient(editedAlice));
    }

    @Test
    public void getPatientList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> cliniCal.getPatientList().remove(0));
    }

    /**
     * A stub ReadOnlyCliniCal whose patients list can violate interface constraints.
     */
    private static class CliniCalStub implements ReadOnlyCliniCal {
        private final ObservableList<Patient> patients = FXCollections.observableArrayList();
        private final ObservableList<Appointment> appointments = FXCollections.observableArrayList();

        CliniCalStub(Collection<Patient> patients) {
            this.patients.setAll(patients);
        }

        @Override
        public ObservableList<Patient> getPatientList() {
            return patients;
        }

        @Override
        public ObservableList<Appointment> getAppointmentList() {
            return appointments;
        }
    }

}
