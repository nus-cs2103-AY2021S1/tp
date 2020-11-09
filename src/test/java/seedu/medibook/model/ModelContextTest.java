package seedu.medibook.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.medibook.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.medibook.model.patient.Ic;
import seedu.medibook.model.patient.Patient;
import seedu.medibook.testutil.PatientBuilder;

public class ModelContextTest {
    @Test
    public void testAccessedPatientMethods_success() {
        Context context = new ModelContext();
        Patient patient = new PatientBuilder().build();

        assertTrue(context.getPatientToAccess().isEmpty());

        context.accessPatient(patient);
        assertEquals(patient, context.getPatientToAccess().get());

        context.resetAccessedPatient();
        assertTrue(context.getPatientToAccess().isEmpty());
    }

    @Test
    public void accessPatient_nullPatient_throwsNullPointerException() {
        Context context = new ModelContext();
        assertThrows(NullPointerException.class, () -> context.accessPatient(null));
    }

    @Test
    public void testDeletedPatientMethods_success() {
        Context context = new ModelContext();
        Patient patient = new PatientBuilder().build();

        assertTrue(context.getDeletedPatient().isEmpty());

        context.setDeletedPatient(patient);
        assertEquals(patient, context.getDeletedPatient().get());

        context.resetDeletedPatient();
        assertTrue(context.getDeletedPatient().isEmpty());
    }

    @Test
    public void setDeletedPatient_nullPatient_throwsNullPointerException() {
        Context context = new ModelContext();
        assertThrows(NullPointerException.class, () -> context.setDeletedPatient(null));
    }

    @Test
    public void testEditedPatientMethods_success() {
        Context context = new ModelContext();
        Patient patient = new PatientBuilder().build();

        assertTrue(context.getEditedPatient().isEmpty());
        assertTrue(context.getEditedPatientPrevIc().isEmpty());

        Ic expectedIc = new Ic("T2193732R");
        context.setEditedPatient(patient, expectedIc);
        assertEquals(patient, context.getEditedPatient().get());
        assertEquals(expectedIc, context.getEditedPatientPrevIc().get());

        context.resetEditedPatient();
        assertTrue(context.getEditedPatient().isEmpty());
        assertTrue(context.getEditedPatientPrevIc().isEmpty());
    }

    @Test
    public void setEditedPatient_nullPatient_throwsNullPointerException() {
        Context context = new ModelContext();
        assertThrows(NullPointerException.class, () -> context.setEditedPatient(null, new Ic("S4554453A")));
    }

    @Test
    public void setEditedPatient_nullPrevIc_throwsNullPointerException() {
        Context context = new ModelContext();
        assertThrows(NullPointerException.class, () -> context.setEditedPatient(new PatientBuilder().build(), null));
    }

    @Test
    public void getAndSetShouldLoadMedicalNotes_success() {
        Context context = new ModelContext();

        context.setShouldLoadMedicalNotes(false);
        assertFalse(context.getShouldLoadMedicalNotes());

        context.setShouldLoadMedicalNotes(true);
        assertTrue(context.getShouldLoadMedicalNotes());
    }

    @Test
    public void getAndSetShouldDeleteAllMedicalNotes_success() {
        Context context = new ModelContext();

        context.setShouldDeleteAllMedicalNotes(true);
        assertTrue(context.getShouldDeleteAllMedicalNotes());

        context.setShouldDeleteAllMedicalNotes(false);
        assertFalse(context.getShouldDeleteAllMedicalNotes());
    }

    @Test
    public void equals_success() {
        Context context1 = new ModelContext();
        Context context2 = new ModelContext();

        assertEquals(context1, context2);

        context1.setDeletedPatient(new PatientBuilder().build());
        context1.setShouldDeleteAllMedicalNotes(true);
        assertNotEquals(context1, context2);

        context2.setDeletedPatient(new PatientBuilder().build());
        context2.setShouldDeleteAllMedicalNotes(true);
        assertEquals(context1, context2);

        context2.setEditedPatient(new PatientBuilder().build(), new Ic("S8324242F"));
        assertNotEquals(context1, context2);
    }
}
