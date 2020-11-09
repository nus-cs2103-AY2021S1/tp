package seedu.address.logic.commands.appointmentcommands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.appointmentcommands.AppointmentCommandTestUtil.DESC_FOLLOWUP;
import static seedu.address.logic.commands.appointmentcommands.AppointmentCommandTestUtil.DESC_REVIEW;
import static seedu.address.logic.commands.appointmentcommands.AppointmentCommandTestUtil.VALID_DESCRIPTION_TWO;
import static seedu.address.logic.commands.appointmentcommands.AppointmentCommandTestUtil.VALID_END_TWO;
import static seedu.address.logic.commands.appointmentcommands.AppointmentCommandTestUtil.VALID_PATIENT_TWO;
import static seedu.address.logic.commands.appointmentcommands.AppointmentCommandTestUtil.VALID_START_TWO;
import static seedu.address.logic.commands.appointmentcommands.AppointmentCommandTestUtil.VALID_TAG_TWO;
import static seedu.address.logic.commands.appointmentcommands.AppointmentEditCommand.EditAppointmentDescriptor;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.EditAppointmentDescriptorBuilder;

public class EditAppointmentDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditAppointmentDescriptor descriptorWithSameValues =
                new EditAppointmentDescriptor(DESC_REVIEW);
        assertTrue(DESC_REVIEW.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_REVIEW.equals(DESC_REVIEW));

        // null -> returns false
        assertFalse(DESC_REVIEW.equals(null));

        // different types -> returns false
        assertFalse(DESC_REVIEW.equals(5));

        // different values -> returns false
        assertFalse(DESC_REVIEW.equals(DESC_FOLLOWUP));

        // different appointmentTime -> returns false
        EditAppointmentDescriptor editedReview =
                new EditAppointmentDescriptorBuilder(DESC_REVIEW)
                        .withAppointmentTime(VALID_START_TWO, VALID_END_TWO).build();
        assertFalse(DESC_REVIEW.equals(editedReview));

        // different description -> returns false
        editedReview = new EditAppointmentDescriptorBuilder(DESC_REVIEW)
                        .withDescription(VALID_DESCRIPTION_TWO).build();
        assertFalse(DESC_REVIEW.equals(editedReview));

        // different patientIndex -> returns false
        editedReview = new EditAppointmentDescriptorBuilder(DESC_REVIEW)
                .withPatient(VALID_PATIENT_TWO).build();
        assertFalse(DESC_REVIEW.equals(editedReview));

        // different tags -> returns false
        editedReview = new EditAppointmentDescriptorBuilder(DESC_REVIEW)
                .withDescription(VALID_TAG_TWO).build();
        assertFalse(DESC_REVIEW.equals(editedReview));
    }
}
