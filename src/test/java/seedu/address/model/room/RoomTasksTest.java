package seedu.address.model.room;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static seedu.address.testutil.TypicalTasks.REMIND_PATIENT;
import static seedu.address.testutil.TypicalTasks.RESTOCK_SUPPLY;
import static seedu.address.testutil.command.TaskCommandTestUtil.VALID_DESCRIPTION_REMIND_PATIENT;

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
        assertEquals(Collections.emptyList(), roomTasks.getFilteredList());
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
    public void setPredicate_nullPredicate_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> roomTasks.setPredicate(null));
    }

    @Test
    public void setPredicate_validPredicate_roomTasksFiltered() {
        roomTasks.addTask(REMIND_PATIENT);
        roomTasks.addTask(RESTOCK_SUPPLY);

        // invalid description -> empty filtered list
        roomTasks.setPredicate(task -> task.getDescription().value.equals("invalid description"));
        assertEquals(Collections.emptyList(), roomTasks.getFilteredList());

        // valid description for REMIND_PATIENT -> filtered list contains only REMIND_PATIENT
        roomTasks.setPredicate(task -> task.getDescription().value.equals(VALID_DESCRIPTION_REMIND_PATIENT));
        assertEquals(Collections.singletonList(REMIND_PATIENT), roomTasks.getFilteredList());
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
