package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_PROJECT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRIORITY_PROJECT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_PROJECT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTasks.BAKE;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskManager;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.task.Task;
import seedu.address.model.task.ToDo;
import seedu.address.model.task.exceptions.DuplicateTaskException;
import seedu.address.testutil.ToDoBuilder;

public class TaskManagerTest {

    private final TaskManager taskManager = new TaskManager();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), taskManager.getTaskList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> taskManager.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyTaskManager_replacesData() {
        TaskManager newData = getTypicalTaskManager();
        taskManager.resetData(newData);
        assertEquals(newData, taskManager);
    }

    @Test
    public void resetData_withDuplicateTasks_throwsDuplicateTaskException() {
        // Two tasks with the same identity fields
        ToDo editedBake = new ToDoBuilder(BAKE).withPriority(VALID_PRIORITY_PROJECT).withTags(VALID_TAG_PROJECT)
                .build();
        List<Task> newTasks = Arrays.asList(BAKE, editedBake);
        TaskManagerStub newData = new TaskManagerStub(newTasks);

        assertThrows(DuplicateTaskException.class, () -> taskManager.resetData(newData));
    }

    @Test
    public void hasTask_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> taskManager.hasTask(null));
    }

    @Test
    public void hasTask_taskNotInTaskManager_returnsFalse() {
        assertFalse(taskManager.hasTask(BAKE));
    }

    @Test
    public void hasTask_taskInTaskManager_returnsTrue() {
        taskManager.addTask(BAKE);
        assertTrue(taskManager.hasTask(BAKE));
    }

    @Test
    public void getTaskList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> taskManager.getTaskList().remove(0));
    }

    @Test
    public void hasTask_taskWithSameIdentityFieldsInAddressBook_returnsTrue() {
        taskManager.addTask(BAKE);
        ToDo editedBake = new ToDoBuilder(BAKE).withPriority(VALID_PRIORITY_PROJECT).withTags(VALID_TAG_PROJECT)
                .build();
        assertTrue(taskManager.hasTask(editedBake));
    }

    /**
     * A stub ReadOnlyTaskManager whose tasks list can violate interface constraints.
     */
    private static class TaskManagerStub implements ReadOnlyTaskManager {
        private final ObservableList<Task> tasks = FXCollections.observableArrayList();

        TaskManagerStub(Collection<Task> tasks) {
            this.tasks.setAll(tasks);
        }

        @Override
        public ObservableList<Task> getTaskList() {
            return tasks;
        }
    }
}
