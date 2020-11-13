package seedu.jarvis.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.jarvis.testutil.Assert.assertThrows;
import static seedu.jarvis.testutil.TypicalTasks.TEST_DEADLINE_ONE;
import static seedu.jarvis.testutil.TypicalTasks.TEST_EVENT_ONE;
import static seedu.jarvis.testutil.TypicalTasks.TEST_TODO_ONE;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.jarvis.model.task.exceptions.TaskNotFoundException;

class UniqueTasksListTest {

    private final UniqueTasksList taskList = new UniqueTasksList();
    private final List<Task> taskListTest = new ArrayList<>();

    @Test
    void add_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> taskList.add(null));
    }

    @Test
    void contains_nullMission_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> taskList.contains(null));
    }

    @Test
    public void contains_tasksNotInList_returnsFalse() {
        assertFalse(taskList.contains(TEST_TODO_ONE));
    }

    @Test
    public void contains_taskInList_returnsTrue() {
        taskList.add(TEST_EVENT_ONE);
        assertTrue(taskList.contains(TEST_EVENT_ONE));
        taskList.remove(TEST_EVENT_ONE);
        assertFalse(taskList.contains(TEST_EVENT_ONE));

        //Throws an exception if there is no such task found in the task list that is asked to be removed.
        assertThrows(TaskNotFoundException.class, () -> taskList.remove(TEST_EVENT_ONE));
    }

    @Test
    void setNullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> taskList.setTasks(null));
        taskListTest.add(TEST_DEADLINE_ONE);
        taskList.setTasks(taskListTest);
        assertFalse(taskList.contains(TEST_EVENT_ONE));
    }

    @Test
    void changeTaskListTo_asObservableList() {
        assertFalse(taskList.getClass().equals(taskList.asObservableList().getClass()));
    }

}
