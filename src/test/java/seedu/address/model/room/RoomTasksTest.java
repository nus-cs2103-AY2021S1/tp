package seedu.address.model.room;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static seedu.address.testutil.TypicalTasks.REMIND_PATIENT;
import static seedu.address.testutil.TypicalTasks.RESTOCK_SUPPLY;

import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.task.Task;

//@@author w-yeehong
public class RoomTasksTest {

    private final RoomTasks roomTasks = new RoomTasks();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), roomTasks.getReadOnlyList());
    }

    @Test
    public void getTaskWithTaskIndex_nullTaskIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> roomTasks.getTaskWithTaskIndex(null));
    }

    @Test
    public void getTaskWithTaskIndex_taskNotInList() {
        Index index = mock(Index.class);
        when(index.getZeroBased()).thenReturn(0);
        Optional<Task> optionalTask = roomTasks.getTaskWithTaskIndex(index);
        assertTrue(optionalTask.isEmpty());
    }

    @Test
    public void getTaskWithTaskIndex_taskInList_returnsTask() {
        Index index = mock(Index.class);
        when(index.getZeroBased()).thenReturn(0);

        roomTasks.addTask(REMIND_PATIENT);
        Optional<Task> optionalTask = roomTasks.getTaskWithTaskIndex(index);
        assertEquals(Optional.of(REMIND_PATIENT), optionalTask);
    }

    @Test
    public void toString_emptyTaskList_success() {
        String expectedString = "-";
        assertEquals(roomTasks.toString(), expectedString);
    }

    @Test
    public void toString_taskListWithTasks_success() {
        roomTasks.addTask(REMIND_PATIENT);
        roomTasks.addTask(RESTOCK_SUPPLY);

        String expectedString = "1. " + REMIND_PATIENT.toString();
        expectedString += "\n\n";
        expectedString += "2. " + RESTOCK_SUPPLY.toString();
        assertEquals(roomTasks.toString(), expectedString);
    }
}
