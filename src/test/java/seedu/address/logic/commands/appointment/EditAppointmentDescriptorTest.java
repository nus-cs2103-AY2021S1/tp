package seedu.address.logic.commands.appointment;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_FIRST;
import static seedu.address.logic.commands.CommandTestUtil.DESC_SECOND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DURATION_SECOND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PATIENT_IC_SECOND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PATIENT_NAME_SECOND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_TIME_SECOND;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.appointment.EditAppointmentCommand.EditAppointmentDescriptor;
import seedu.address.testutil.EditAppointmentDescriptorBuilder;

public class EditAppointmentDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditAppointmentDescriptor descriptorWithSameValues = new EditAppointmentDescriptor(DESC_FIRST);
        assertTrue(DESC_FIRST.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_FIRST.equals(DESC_FIRST));

        // null -> returns false
        assertFalse(DESC_FIRST.equals(null));

        // different types -> returns false
        assertFalse(DESC_FIRST.equals(5));

        // different values -> returns false
        assertFalse(DESC_FIRST.equals(DESC_SECOND));

        // different patientName -> returns false
        EditAppointmentDescriptor editedFirst = new EditAppointmentDescriptorBuilder(DESC_FIRST)
                .withPatientName(VALID_PATIENT_NAME_SECOND).build();
        assertFalse(DESC_FIRST.equals(editedFirst));

        // different patientIc -> returns false
        editedFirst = new EditAppointmentDescriptorBuilder(DESC_FIRST).withPatientIc(VALID_PATIENT_IC_SECOND).build();
        assertFalse(DESC_FIRST.equals(editedFirst));

        // different startTime -> returns false
        editedFirst = new EditAppointmentDescriptorBuilder(DESC_FIRST).withStartTime(VALID_START_TIME_SECOND).build();
        assertFalse(DESC_FIRST.equals(editedFirst));

        // different duration -> returns false
        editedFirst = new EditAppointmentDescriptorBuilder(DESC_FIRST).withDuration(VALID_DURATION_SECOND).build();
        assertFalse(DESC_FIRST.equals(editedFirst));

    }
}
