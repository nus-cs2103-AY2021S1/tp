package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.todolist.TypicalTasks.LAB_01;
import static seedu.address.testutil.todolist.TypicalTasks.LAB_02;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.task.exceptions.DuplicateTaskException;
import seedu.address.model.task.exceptions.TaskNotFoundException;
import seedu.address.testutil.todolist.TaskBuilder;

public class UniqueTodoListTest {

    private final UniqueTodoList uniqueTodoList = new UniqueTodoList();

    @Test
    public void contains_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTodoList.contains(null));
    }

    @Test
    public void contains_taskNotInList_returnsFalse() {
        assertFalse(uniqueTodoList.contains(LAB_01));
    }

    @Test
    public void contains_taskInList_returnsTrue() {
        uniqueTodoList.add(LAB_01);
        assertTrue(uniqueTodoList.contains(LAB_01));
    }

    @Test
    public void contains_taskWithSameNameFieldsInList_returnsTrue() {
        uniqueTodoList.add(LAB_01);
        Task editedLab01 = new TaskBuilder().withName("Finish Lab01").build();
        assertTrue(uniqueTodoList.contains(editedLab01));
    }

    @Test
    public void add_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTodoList.add(null));
    }

    @Test
    public void add_duplicateTask_throwsDuplicateTaskException() {
        uniqueTodoList.add(LAB_01);
        assertThrows(DuplicateTaskException.class, () -> uniqueTodoList.add(LAB_01));
    }

    @Test
    public void setTask_nullTargetTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTodoList.setTask(null, LAB_01));
    }

    @Test
    public void setTask_nullEditedTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTodoList.setTask(LAB_01, null));
    }

    @Test
    public void setTask_targetTaskNotInList_throwsTaskNotFoundException() {
        assertThrows(TaskNotFoundException.class, () -> uniqueTodoList.setTask(LAB_01, LAB_01));
    }

    @Test
    public void setTask_editedTaskIsSameTask_success() {
        uniqueTodoList.add(LAB_01);
        uniqueTodoList.setTask(LAB_01, LAB_01);
        UniqueTodoList expectedUniqueTodoList = new UniqueTodoList();
        expectedUniqueTodoList.add(LAB_01);
        assertEquals(expectedUniqueTodoList, uniqueTodoList);
    }

    @Test
    public void setTask_editedTaskHasSameName_success() {
        uniqueTodoList.add(LAB_01);
        Task editedLab01 = new TaskBuilder().withName("Finish Lab01").build();
        uniqueTodoList.setTask(LAB_01, editedLab01);
        UniqueTodoList expectedUniqueTodoList = new UniqueTodoList();
        expectedUniqueTodoList.add(editedLab01);
        assertEquals(expectedUniqueTodoList, uniqueTodoList);
    }

    @Test
    public void setTask_editedTaskHasDifferentIdentity_success() {
        uniqueTodoList.add(LAB_01);
        uniqueTodoList.setTask(LAB_01, LAB_02);
        UniqueTodoList expectedUniqueTodoList = new UniqueTodoList();
        expectedUniqueTodoList.add(LAB_02);
        assertEquals(expectedUniqueTodoList, uniqueTodoList);
    }

    @Test
    public void setTask_editedTaskHasNonUniqueIdentity_throwsDuplicateTaskException() {
        uniqueTodoList.add(LAB_01);
        uniqueTodoList.add(LAB_02);
        assertThrows(DuplicateTaskException.class, () -> uniqueTodoList.setTask(LAB_01, LAB_02));
    }

    @Test
    public void remove_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTodoList.remove(null));
    }

    @Test
    public void remove_todoDoesNotExist_throwsTaskNotFoundException() {
        assertThrows(TaskNotFoundException.class, () -> uniqueTodoList.remove(LAB_01));
    }

    @Test
    public void remove_existingTask_removesTask() {
        uniqueTodoList.add(LAB_01);
        uniqueTodoList.remove(LAB_01);
        UniqueTodoList expectedUniqueTodoList = new UniqueTodoList();
        assertEquals(expectedUniqueTodoList, uniqueTodoList);
    }

    @Test
    public void setTasks_nullUniqueTodoList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTodoList.setTasks((UniqueTodoList) null));
    }

    @Test
    public void setTasks_uniqueTodoList_replacesOwnListWithProvidedUniqueTodoList() {
        uniqueTodoList.add(LAB_01);
        UniqueTodoList expectedUniqueTodoList = new UniqueTodoList();
        expectedUniqueTodoList.add(LAB_02);
        uniqueTodoList.setTasks(expectedUniqueTodoList);
        assertEquals(expectedUniqueTodoList, uniqueTodoList);
    }

    @Test
    public void setTasks_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTodoList.setTasks((List<Task>) null));
    }

    @Test
    public void setTasks_list_replacesOwnListWithProvidedList() {
        uniqueTodoList.add(LAB_01);
        List<Task> todoList = Collections.singletonList(LAB_02);
        uniqueTodoList.setTasks(todoList);
        UniqueTodoList expectedUniqueTodoList = new UniqueTodoList();
        expectedUniqueTodoList.add(LAB_02);
        assertEquals(expectedUniqueTodoList, uniqueTodoList);
    }

    @Test
    public void setTasks_listWithDuplicateTasks_throwsDuplicateTaskException() {
        List<Task> listWithDuplicateTasks = Arrays.asList(LAB_01, LAB_01);
        assertThrows(DuplicateTaskException.class, () -> uniqueTodoList.setTasks(listWithDuplicateTasks));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueTodoList.asUnmodifiableObservableList().remove(0));
    }
}
