package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STATUS_COMPLETED;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.todolist.TypicalTasks.LAB_01;
import static seedu.address.testutil.todolist.TypicalTasks.getTypicalTodoList;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.task.Task;
import seedu.address.model.task.exceptions.DuplicateTaskException;
import seedu.address.testutil.todolist.TaskBuilder;

public class TodoListTest {

    private final TodoList todoList = new TodoList();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), todoList.getTodoList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> todoList.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyTodoList_replacesData() {
        TodoList newData = getTypicalTodoList();
        todoList.resetData(newData);
        assertEquals(newData, todoList);
    }

    @Test
    public void resetData_withDuplicateTasks_throwsDuplicateTaskException() {
        // Two tasks with the same name
        Task editedLab01 = new TaskBuilder(LAB_01).withStatus(VALID_STATUS_COMPLETED).build();
        List<Task> newTasks = Arrays.asList(LAB_01, editedLab01);
        TodoListStub newData = new TodoListStub(newTasks);

        assertThrows(DuplicateTaskException.class, () -> todoList.resetData(newData));
    }

    @Test
    public void addTask() {
        assertThrows(NullPointerException.class, () -> todoList.addTask(null));
    }

    @Test
    public void setTask() {
        assertThrows(NullPointerException.class, () -> todoList.setTask(LAB_01, null));
        assertThrows(NullPointerException.class, () -> todoList.setTask(null, LAB_01));
        assertThrows(NullPointerException.class, () -> todoList.setTask(null, null));
    }

    @Test
    public void removeTask() {
        assertThrows(NullPointerException.class, () -> todoList.removeTask(null));
    }

    @Test
    public void hasTask_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> todoList.hasTask(null));
    }

    @Test
    public void hasTask_taskNotInTodoList_returnsFalse() {
        assertFalse(todoList.hasTask(LAB_01));
    }

    @Test
    public void hasTask_taskInTodoList_returnsTrue() {
        todoList.addTask(LAB_01);
        assertTrue(todoList.hasTask(LAB_01));
    }

    @Test
    public void hasTask_taskWithSameNameInTodoList_returnsTrue() {
        todoList.addTask(LAB_01);
        Task editedLab01 = new TaskBuilder(LAB_01).withStatus(VALID_STATUS_COMPLETED).build();
        assertTrue(todoList.hasTask(editedLab01));
    }

    @Test
    public void getTodoList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> todoList.getTodoList().remove(0));
    }

    /**
     * A stub ReadOnlyTodoList whose tasks list can violate interface constraints.
     */
    private static class TodoListStub implements ReadOnlyTodoList {
        private final ObservableList<Task> tasks = FXCollections.observableArrayList();

        TodoListStub(Collection<Task> tasks) {
            this.tasks.setAll(tasks);
        }

        @Override
        public ObservableList<Task> getTodoList() {
            return tasks;
        }
    }

}
