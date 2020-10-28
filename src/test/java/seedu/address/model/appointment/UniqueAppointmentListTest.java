package seedu.address.model.appointment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_TIME_SECOND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PATIENT_NAME_FIRST;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAppointments.APP1;
import static seedu.address.testutil.TypicalAppointments.APP2;
import static seedu.address.testutil.TypicalAppointments.CONFLICTING_APPOINTMENT1;
import static seedu.address.testutil.TypicalAppointments.CONFLICTING_APPOINTMENT2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.appointment.exceptions.AppointmentNotFoundException;
import seedu.address.model.appointment.exceptions.ConflictingAppointmentException;
import seedu.address.testutil.AppointmentBuilder;

public class UniqueAppointmentListTest {

    private final UniqueAppointmentList uniqueAppointmentList = new UniqueAppointmentList();

    @Test
    public void clashes_nullAppointment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAppointmentList.clashes(null));
    }

    @Test
    public void clashes_appointmentNotInList_returnsFalse() {
        assertFalse(uniqueAppointmentList.clashes(APP1));
    }

    @Test
    public void clashes_appointmentInList_returnsTrue() {
        uniqueAppointmentList.add(APP1);
        assertTrue(uniqueAppointmentList.clashes(APP1));
    }

    @Test
    public void clashes_appointmentClashes_returnsTrue() {
        uniqueAppointmentList.add(CONFLICTING_APPOINTMENT1);
        assertTrue(uniqueAppointmentList.clashes(CONFLICTING_APPOINTMENT2));
    }

    @Test
    public void add_nullAppointment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAppointmentList.add(null));
    }

    @Test
    public void add_conflictingAppointment_throwsConflictingAppointmentException() {
        uniqueAppointmentList.add(CONFLICTING_APPOINTMENT1);
        assertThrows(ConflictingAppointmentException.class, () -> uniqueAppointmentList.add(CONFLICTING_APPOINTMENT2));
    }

    @Test
    public void setAppointment_nullTargetAppointment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAppointmentList.setAppointment(null, APP1));
    }

    @Test
    public void setAppointment_nullEditedAppointment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAppointmentList.setAppointment(APP1, null));
    }

    @Test
    public void setAppointment_targetAppointmentNotInList_throwsAppointmentNotFoundException() {
        assertThrows(AppointmentNotFoundException.class, () -> uniqueAppointmentList.setAppointment(APP1, APP1));
    }

    @Test
    public void setAppointment_editedAppointmentIsSameAppointment_success() {
        uniqueAppointmentList.add(APP1);
        uniqueAppointmentList.setAppointment(APP1, APP1);
        UniqueAppointmentList expectedUniqueAppointmentList = new UniqueAppointmentList();
        expectedUniqueAppointmentList.add(APP1);
        assertEquals(expectedUniqueAppointmentList, uniqueAppointmentList);
    }

    @Test
    public void setAppointment_editedAppointmentHasSameName_success() {
        uniqueAppointmentList.add(APP1);
        Appointment editedAppointment = new AppointmentBuilder(APP1).withEndTime(VALID_END_TIME_SECOND).build();
        uniqueAppointmentList.setAppointment(APP1, editedAppointment);
        UniqueAppointmentList expectedUniqueAppointmentList = new UniqueAppointmentList();
        expectedUniqueAppointmentList.add(editedAppointment);
        UniqueAppointmentList notExpectedUniqueAppointmentList = new UniqueAppointmentList();
        notExpectedUniqueAppointmentList.add(APP1);

        assertNotEquals(uniqueAppointmentList, notExpectedUniqueAppointmentList);
        assertEquals(expectedUniqueAppointmentList, uniqueAppointmentList);
    }

    @Test
    public void setAppointment_editedAppointmentHasSameTimes_success() {
        uniqueAppointmentList.add(APP1);
        Appointment editedAppointment = new AppointmentBuilder(APP1).withPatientName(VALID_PATIENT_NAME_FIRST).build();
        uniqueAppointmentList.setAppointment(APP1, editedAppointment);
        UniqueAppointmentList expectedUniqueAppointmentList = new UniqueAppointmentList();
        expectedUniqueAppointmentList.add(editedAppointment);
        UniqueAppointmentList notExpectedUniqueAppointmentList = new UniqueAppointmentList();
        notExpectedUniqueAppointmentList.add(APP1);

        assertNotEquals(uniqueAppointmentList, notExpectedUniqueAppointmentList);
        assertEquals(expectedUniqueAppointmentList, uniqueAppointmentList);
    }

    @Test
    public void remove_nullAppointment_throwsAppointmentNotFoundException() {
        assertThrows(NullPointerException.class, () -> uniqueAppointmentList.remove(null));
    }

    @Test
    public void remove_appointmentDoesNotExist_throwsAppointmentNotFoundException() {
        assertThrows(AppointmentNotFoundException.class, () -> uniqueAppointmentList.remove(APP1));
    }

    @Test
    public void remove_existingAppointment_removesAppointment() {
        uniqueAppointmentList.add(APP1);
        uniqueAppointmentList.remove(APP1);
        UniqueAppointmentList expectedUniqueAppointmentList = new UniqueAppointmentList();
        assertEquals(uniqueAppointmentList, expectedUniqueAppointmentList);
    }

    @Test
    public void setAppointments_nullUniqueAppointmentList_throwsNullPointerException() {
        assertThrows(
                NullPointerException.class, () -> uniqueAppointmentList.setAppointments((UniqueAppointmentList) null));
    }

    @Test
    public void setAppointments_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAppointmentList.setAppointments((List<Appointment>) null));
    }

    @Test
    public void setAppointments_uniqueAppointmentList_replacesOwnListSuccess() {
        uniqueAppointmentList.add(APP1);
        UniqueAppointmentList expectedUniqueAppointmentList = new UniqueAppointmentList();
        expectedUniqueAppointmentList.add(APP2);
        uniqueAppointmentList.setAppointments(expectedUniqueAppointmentList);
        UniqueAppointmentList notExceptedUniqueAppointmentList = new UniqueAppointmentList();
        notExceptedUniqueAppointmentList.add(APP1);

        assertNotEquals(notExceptedUniqueAppointmentList, uniqueAppointmentList);
        assertEquals(expectedUniqueAppointmentList, uniqueAppointmentList);
    }

    @Test
    public void setAppointments_list_replacesOwnListSuccess() {
        uniqueAppointmentList.add(APP1);
        List<Appointment> appointmentList = Collections.singletonList(APP2);
        uniqueAppointmentList.setAppointments(appointmentList);
        UniqueAppointmentList expectedUniqueAppointmentList = new UniqueAppointmentList();
        expectedUniqueAppointmentList.add(APP2);
        UniqueAppointmentList notExpectedUniqueAppointmentList = new UniqueAppointmentList();
        notExpectedUniqueAppointmentList.add(APP1);

        assertNotEquals(notExpectedUniqueAppointmentList, uniqueAppointmentList);
        assertEquals(expectedUniqueAppointmentList, uniqueAppointmentList);
    }

    @Test
    public void setAppointments_listWithDuplicateAppointments_throwsConflictingAppointmentException() {
        List<Appointment> listWithDuplicateAppointments = Arrays.asList(APP1, APP1);
        assertThrows(ConflictingAppointmentException.class, () ->
                uniqueAppointmentList.setAppointments(listWithDuplicateAppointments));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () ->
                uniqueAppointmentList.asUnmodifiableObservableList().remove(0));
    }

}
