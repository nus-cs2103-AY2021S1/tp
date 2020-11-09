package seedu.address.model.room;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalRooms.ROOM_PATIENT_ALICE_NO_TASK;
import static seedu.address.testutil.TypicalRooms.ROOM_PATIENT_ALICE_TASK_REMIND_PATIENT;
import static seedu.address.testutil.TypicalTasks.REMIND_PATIENT;
import static seedu.address.testutil.TypicalTasks.RESTOCK_SUPPLY;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.task.Task;
import seedu.address.model.task.exceptions.TaskNotFoundException;
import seedu.address.testutil.RoomBuilder;

/**
 * Contains integration tests for the interactions between {@code Room} and {@code Task}.
 */
public class RoomIntegrationTest {

    private Room room;

    @BeforeEach
    public void setUp() {
        room = new RoomBuilder().build();
    }

    @Test
    public void addTask_nullTask_throwsNullPointerException() {
        Task nullTask = null;
        assertThrows(NullPointerException.class, () -> room.addTask(nullTask));
    }

    @Test
    public void addTask_validTask_success() {
        Room room = new RoomBuilder(ROOM_PATIENT_ALICE_NO_TASK).build();
        room.addTask(REMIND_PATIENT);
        assertEquals(ROOM_PATIENT_ALICE_TASK_REMIND_PATIENT, room);
    }

    @Test
    public void deleteTask_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> room.deleteTask(null));
    }

    @Test
    public void deleteTask_taskNotInTaskList_throwsTaskNotFoundException() {
        assertThrows(TaskNotFoundException.class, () -> room.deleteTask(REMIND_PATIENT));
    }

    @Test
    public void deleteTask_taskInTaskList_success() {
        Room room = new RoomBuilder(ROOM_PATIENT_ALICE_TASK_REMIND_PATIENT).build();
        room.deleteTask(REMIND_PATIENT);

        assertEquals(ROOM_PATIENT_ALICE_NO_TASK, room);
    }

    @Test
    public void setTask_nullEditedTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> room.setTask(REMIND_PATIENT, null));
    }

    @Test
    public void setTask_nullTarget_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> room.setTask(null, REMIND_PATIENT));
    }

    @Test
    public void setTask_taskNotInTaskList_throwsTaskNotFoundException() {
        assertThrows(TaskNotFoundException.class, () -> room.setTask(REMIND_PATIENT, RESTOCK_SUPPLY));
    }

    @Test
    public void setTask_taskInTaskList_success() {
        Room room = new RoomBuilder(ROOM_PATIENT_ALICE_TASK_REMIND_PATIENT).build();
        room.setTask(REMIND_PATIENT, RESTOCK_SUPPLY);

        Room expectedRoom = new RoomBuilder(ROOM_PATIENT_ALICE_NO_TASK).withTasks(RESTOCK_SUPPLY).build();

        assertEquals(expectedRoom, room);
    }

}
