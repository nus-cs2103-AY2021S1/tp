package seedu.homerce.model.appointment.uniquelist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.homerce.testutil.Assert.assertThrows;
import static seedu.homerce.testutil.appointment.TypicalAppointments.OCT_25_2020_230PM_LASH_LIFT_BENSON;
import static seedu.homerce.testutil.appointment.TypicalAppointments.OCT_25_2020_2PM_HAIR_TREATMENT_ALICE;
import static seedu.homerce.testutil.appointment.TypicalAppointments.OCT_26_2020_11AM_MANICURE_CARL;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.homerce.model.appointment.Appointment;
import seedu.homerce.model.util.uniquelist.exceptions.DuplicateItemException;
import seedu.homerce.model.util.uniquelist.exceptions.ItemNotFoundException;

public class UniqueAppointmentListTest {


    @Test
    public void contains_null_throwsNullPointerException() {
        UniqueAppointmentList appointments = new UniqueAppointmentList();
        assertThrows(NullPointerException.class, () -> appointments.contains(null));
    }

    @Test
    public void contains_appointmentNotInList_returnsFalse() {
        UniqueAppointmentList appointments = new UniqueAppointmentList();
        assertFalse(appointments.contains(OCT_25_2020_2PM_HAIR_TREATMENT_ALICE));
    }

    @Test
    public void contains_appointmentInList_returnsTrue() {
        UniqueAppointmentList appointments = new UniqueAppointmentList();
        appointments.add(OCT_25_2020_2PM_HAIR_TREATMENT_ALICE);
        assertTrue(appointments.contains(OCT_25_2020_2PM_HAIR_TREATMENT_ALICE));
    }

    @Test
    public void contains_appointmentWithClashingDateInList_returnsTrue() {
        UniqueAppointmentList appointments = new UniqueAppointmentList();
        appointments.add(OCT_25_2020_2PM_HAIR_TREATMENT_ALICE);
        assertTrue(appointments.contains(OCT_25_2020_230PM_LASH_LIFT_BENSON));
    }

    @Test
    public void add_nullAppointment_throwsNullPointerException() {
        UniqueAppointmentList appointments = new UniqueAppointmentList();
        assertThrows(NullPointerException.class, () -> appointments.add(null));
    }

    @Test
    public void add_duplicateAppointment_throwsDuplicateAppointmentException() {
        UniqueAppointmentList appointments = new UniqueAppointmentList();
        appointments.add(OCT_26_2020_11AM_MANICURE_CARL);
        assertThrows(DuplicateItemException.class, () -> appointments.add(OCT_26_2020_11AM_MANICURE_CARL));
    }

    @Test
    public void setAppointment_nullTargetAppointment_throwsNullPointerException() {
        UniqueAppointmentList appointments = new UniqueAppointmentList();
        assertThrows(
            NullPointerException.class, () ->
                appointments.setItem(null, OCT_25_2020_2PM_HAIR_TREATMENT_ALICE)
        );
    }

    @Test
    public void setAppointment_nullEditedAppointment_throwsNullPointerException() {
        UniqueAppointmentList appointments = new UniqueAppointmentList();
        appointments.add(OCT_25_2020_2PM_HAIR_TREATMENT_ALICE);
        assertThrows(
            NullPointerException.class, () ->
                appointments.setItem(OCT_25_2020_2PM_HAIR_TREATMENT_ALICE, null)
        );
    }

    @Test
    public void setAppointment_targetAppointmentNotInList_throwsAppointmentNotFoundException() {
        UniqueAppointmentList appointments = new UniqueAppointmentList();
        assertThrows(
            ItemNotFoundException.class, () -> appointments.setItem(
                OCT_25_2020_2PM_HAIR_TREATMENT_ALICE,
                OCT_25_2020_2PM_HAIR_TREATMENT_ALICE
            )
        );
    }

    @Test
    public void setAppointment_editedAppointmentIsSameAppointment_success() {
        UniqueAppointmentList appointments = new UniqueAppointmentList();
        appointments.add(OCT_25_2020_2PM_HAIR_TREATMENT_ALICE);
        UniqueAppointmentList expectedUniqueAppointmentList = new UniqueAppointmentList();
        expectedUniqueAppointmentList.add(OCT_25_2020_2PM_HAIR_TREATMENT_ALICE);
        assertEquals(expectedUniqueAppointmentList, appointments);
    }

    @Test
    public void setAppointment_editedAppointmentHasSameIdentity_success() {
        UniqueAppointmentList appointments = new UniqueAppointmentList();
        appointments.add(OCT_25_2020_2PM_HAIR_TREATMENT_ALICE);
        Appointment editedAlice = OCT_25_2020_2PM_HAIR_TREATMENT_ALICE;
        appointments.setItem(OCT_25_2020_2PM_HAIR_TREATMENT_ALICE, editedAlice);
        UniqueAppointmentList expectedUniqueAppointmentList = new UniqueAppointmentList();
        expectedUniqueAppointmentList.add(editedAlice);
        assertEquals(expectedUniqueAppointmentList, appointments);
    }

    @Test
    public void setAppointment_editedAppointmentHasDifferentIdentity_success() {
        UniqueAppointmentList appointments = new UniqueAppointmentList();
        appointments.add(OCT_25_2020_2PM_HAIR_TREATMENT_ALICE);
        appointments.setItem(OCT_25_2020_2PM_HAIR_TREATMENT_ALICE, OCT_26_2020_11AM_MANICURE_CARL);
        UniqueAppointmentList expectedUniqueAppointmentList = new UniqueAppointmentList();
        expectedUniqueAppointmentList.add(OCT_26_2020_11AM_MANICURE_CARL);
        assertEquals(expectedUniqueAppointmentList, appointments);
    }

    @Test
    public void setAppointment_editedAppointmentHasNonUniqueIdentity_throwsDuplicateAppointmentException() {
        UniqueAppointmentList appointments = new UniqueAppointmentList();
        appointments.add(OCT_25_2020_2PM_HAIR_TREATMENT_ALICE);
        appointments.add(OCT_26_2020_11AM_MANICURE_CARL);
        assertThrows(
            DuplicateItemException.class, () ->
                appointments.setItem(
                    OCT_25_2020_2PM_HAIR_TREATMENT_ALICE,
                    OCT_26_2020_11AM_MANICURE_CARL)
        );
    }

    @Test
    public void remove_nullAppointment_throwsNullPointerException() {
        UniqueAppointmentList appointments = new UniqueAppointmentList();
        assertThrows(NullPointerException.class, () -> appointments.remove(null));
    }

    @Test
    public void remove_appointmentDoesNotExist_throwsAppointmentNotFoundException() {
        UniqueAppointmentList appointments = new UniqueAppointmentList();
        assertThrows(
            ItemNotFoundException.class, () ->
                appointments.remove(OCT_25_2020_2PM_HAIR_TREATMENT_ALICE)
        );
    }

    @Test
    public void remove_existingAppointment_removesAppointment() {
        UniqueAppointmentList appointments = new UniqueAppointmentList();
        appointments.add(OCT_25_2020_2PM_HAIR_TREATMENT_ALICE);
        appointments.remove(OCT_25_2020_2PM_HAIR_TREATMENT_ALICE);
        UniqueAppointmentList expectedUniqueAppointmentList = new UniqueAppointmentList();
        assertEquals(expectedUniqueAppointmentList, appointments);
    }

    @Test
    public void setAppointments_nullUniqueAppointmentList_throwsNullPointerException() {
        UniqueAppointmentList appointments = new UniqueAppointmentList();
        assertThrows(NullPointerException.class, () -> appointments.setItems((UniqueAppointmentList) null));
    }

    @Test
    public void setAppointments_uniqueAppointmentList_replacesOwnListWithProvidedUniqueAppointmentList() {
        UniqueAppointmentList appointments = new UniqueAppointmentList();
        appointments.add(OCT_25_2020_2PM_HAIR_TREATMENT_ALICE);
        UniqueAppointmentList expectedUniqueAppointmentList = new UniqueAppointmentList();
        expectedUniqueAppointmentList.add(OCT_26_2020_11AM_MANICURE_CARL);
        appointments.setItems(expectedUniqueAppointmentList);
        assertEquals(expectedUniqueAppointmentList, appointments);
    }

    @Test
    public void setAppointments_nullList_throwsNullPointerException() {
        UniqueAppointmentList appointments = new UniqueAppointmentList();
        assertThrows(NullPointerException.class, () -> appointments.setItems((List<Appointment>) null));
    }

    @Test
    public void setAppointments_list_replacesOwnListWithProvidedList() {
        UniqueAppointmentList appointments = new UniqueAppointmentList();
        appointments.add(OCT_25_2020_2PM_HAIR_TREATMENT_ALICE);
        List<Appointment> appointmentList = Collections.singletonList(OCT_26_2020_11AM_MANICURE_CARL);
        appointments.setItems(appointmentList);
        UniqueAppointmentList expectedUniqueAppointmentList = new UniqueAppointmentList();
        expectedUniqueAppointmentList.add(OCT_26_2020_11AM_MANICURE_CARL);
        assertEquals(expectedUniqueAppointmentList, appointments);
    }

    @Test
    public void setAppointments_listWithDuplicateAppointments_throwsDuplicateAppointmentException() {
        UniqueAppointmentList appointments = new UniqueAppointmentList();
        List<Appointment> listWithDuplicateAppointments =
            Arrays.asList(OCT_25_2020_2PM_HAIR_TREATMENT_ALICE, OCT_25_2020_2PM_HAIR_TREATMENT_ALICE);
        assertThrows(DuplicateItemException.class, () -> appointments
            .setItems(listWithDuplicateAppointments));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        UniqueAppointmentList appointments = new UniqueAppointmentList();
        assertThrows(UnsupportedOperationException.class, ()
            -> appointments.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void testHashCode() {
        UniqueAppointmentList firstList = new UniqueAppointmentList();
        UniqueAppointmentList secondList = new UniqueAppointmentList();
        firstList.add(OCT_25_2020_2PM_HAIR_TREATMENT_ALICE);
        secondList.add(OCT_25_2020_2PM_HAIR_TREATMENT_ALICE);

        assertEquals(firstList.hashCode(), secondList.hashCode());

        secondList.add(OCT_26_2020_11AM_MANICURE_CARL);
        assertNotEquals(firstList.hashCode(), secondList.hashCode());
    }
}
