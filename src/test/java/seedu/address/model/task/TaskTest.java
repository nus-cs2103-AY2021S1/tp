package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalTasks.REMIND_PATIENT;
import static seedu.address.testutil.TypicalTasks.RESTOCK_SUPPLY;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TaskBuilder;

//@@author w-yeehong
public class TaskTest {

    @Test
    public void equals() {
        // same values -> returns true
        Task remindPatientCopy = new TaskBuilder(REMIND_PATIENT).build();
        assertTrue(REMIND_PATIENT.equals(remindPatientCopy));

        // same object -> returns true
        assertTrue(REMIND_PATIENT.equals(REMIND_PATIENT));

        // null -> returns false
        assertFalse(REMIND_PATIENT.equals(null));

        // different type -> returns false
        assertFalse(REMIND_PATIENT.equals(5));

        // different task -> returns false
        assertFalse(REMIND_PATIENT.equals(RESTOCK_SUPPLY));

        // different description -> returns false
        Task editedRemindPatient = new TaskBuilder(REMIND_PATIENT).withDescription("hi").build();
        assertFalse(REMIND_PATIENT.equals(editedRemindPatient));

        // different date-time due -> returns false
        editedRemindPatient = new TaskBuilder(REMIND_PATIENT).withDateTimeDue(Optional.of("20200101")).build();
        assertFalse(REMIND_PATIENT.equals(editedRemindPatient));
    }
}
