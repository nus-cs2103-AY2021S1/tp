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

    private final TodoList addressBook = new TodoList();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getTodoList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyTodoList_replacesData() {
        TodoList newData = getTypicalTodoList();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);
    }

    @Test
    public void resetData_withDuplicateTasks_throwsDuplicateTaskException() {
        // Two persons with the same name
        Task editedLab01 = new TaskBuilder(LAB_01).withStatus(VALID_STATUS_COMPLETED).build();
        List<Task> newTasks = Arrays.asList(LAB_01, editedLab01);
        TodoListStub newData = new TodoListStub(newTasks);

        assertThrows(DuplicateTaskException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasTask_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasTask(null));
    }

    @Test
    public void hasTask_personNotInTodoList_returnsFalse() {
        assertFalse(addressBook.hasTask(LAB_01));
    }

    @Test
    public void hasTask_personInTodoList_returnsTrue() {
        addressBook.addTask(LAB_01);
        assertTrue(addressBook.hasTask(LAB_01));
    }

    @Test
    public void hasTask_personWithSameNameInTodoList_returnsTrue() {
        addressBook.addTask(LAB_01);
        Task editedLab01 = new TaskBuilder(LAB_01).withStatus(VALID_STATUS_COMPLETED).build();
        assertTrue(addressBook.hasTask(editedLab01));
    }

    @Test
    public void getTodoList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getTodoList().remove(0));
    }

    /**
     * A stub ReadOnlyTodoList whose persons list can violate interface constraints.
     */
    private static class TodoListStub implements ReadOnlyTodoList {
        private final ObservableList<Task> persons = FXCollections.observableArrayList();

        TodoListStub(Collection<Task> persons) {
            this.persons.setAll(persons);
        }

        @Override
        public ObservableList<Task> getTodoList() {
            return persons;
        }
    }

}
