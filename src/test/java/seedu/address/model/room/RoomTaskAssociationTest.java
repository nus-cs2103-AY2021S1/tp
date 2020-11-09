package seedu.address.model.room;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalRooms.ROOM_NO_PATIENT_TASK_RESTOCK_SUPPLY;
import static seedu.address.testutil.TypicalRooms.ROOM_PATIENT_ALICE_TASK_REMIND_PATIENT;
import static seedu.address.testutil.TypicalTasks.REMIND_PATIENT;
import static seedu.address.testutil.TypicalTasks.RESTOCK_SUPPLY;

import org.junit.jupiter.api.Test;

public class RoomTaskAssociationTest {

    private final RoomTaskAssociation roomTaskAssociation =
            new RoomTaskAssociation(ROOM_PATIENT_ALICE_TASK_REMIND_PATIENT, REMIND_PATIENT, 1);

    @Test
    public void constructor_nullRoom_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new RoomTaskAssociation(null, REMIND_PATIENT, 1));
    }

    @Test
    public void constructor_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new RoomTaskAssociation(ROOM_PATIENT_ALICE_TASK_REMIND_PATIENT, null, 1));
    }

    @Test
    public void constructor_taskIndexLesserThanOne_throwsAssertionError() {
        assertThrows(AssertionError.class, () -> new RoomTaskAssociation(ROOM_PATIENT_ALICE_TASK_REMIND_PATIENT,
                REMIND_PATIENT, 0));

        assertThrows(AssertionError.class, () -> new RoomTaskAssociation(ROOM_PATIENT_ALICE_TASK_REMIND_PATIENT,
                REMIND_PATIENT, -1));
    }

    @Test
    public void equals() {
        // same values -> returns true
        RoomTaskAssociation roomTaskAssociationCopy = new RoomTaskAssociation(
                ROOM_PATIENT_ALICE_TASK_REMIND_PATIENT, REMIND_PATIENT, 1);
        assertTrue(roomTaskAssociation.equals(roomTaskAssociationCopy));

        // same object -> returns true
        assertTrue(roomTaskAssociation.equals(roomTaskAssociation));

        // null -> returns false
        assertFalse(roomTaskAssociation.equals(null));

        // different type -> returns false
        assertFalse(roomTaskAssociation.equals(5));

        // different room -> returns false
        RoomTaskAssociation roomTaskAssociationDifferentRoom = new RoomTaskAssociation(
                ROOM_NO_PATIENT_TASK_RESTOCK_SUPPLY, REMIND_PATIENT, 1);
        assertFalse(roomTaskAssociation.equals(roomTaskAssociationDifferentRoom));

        // different task -> returns false
        RoomTaskAssociation roomTaskAssociationDifferentTask = new RoomTaskAssociation(
                ROOM_PATIENT_ALICE_TASK_REMIND_PATIENT, RESTOCK_SUPPLY, 1);
        assertFalse(roomTaskAssociation.equals(roomTaskAssociationDifferentTask));

        // different task index -> returns false
        RoomTaskAssociation roomTaskAssociationDifferentTaskIndex = new RoomTaskAssociation(
                ROOM_PATIENT_ALICE_TASK_REMIND_PATIENT, REMIND_PATIENT, 2);
        assertFalse(roomTaskAssociation.equals(roomTaskAssociationDifferentTaskIndex));
    }
}
