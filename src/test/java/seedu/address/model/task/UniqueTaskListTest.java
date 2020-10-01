package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_WASH;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTasks.HOMEWORK;
import static seedu.address.testutil.TypicalTasks.WASH;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.task.exceptions.DuplicateTaskException;
import seedu.address.model.task.exceptions.TaskNotFoundException;
import seedu.address.testutil.TaskBuilder;

public class UniqueTaskListTest {

    private final UniqueTaskList uniqueTaskList = new UniqueTaskList();

    @Test
    public void contains_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTaskList.contains(null));
    }

    @Test
    public void contains_taskNotInList_returnsFalse() {
        assertFalse(uniqueTaskList.contains(HOMEWORK));
    }

    @Test
    public void contains_taskInList_returnsTrue() {
        uniqueTaskList.add(HOMEWORK);
        assertTrue(uniqueTaskList.contains(HOMEWORK));
    }

    @Test
    public void contains_taskWithSameIdentityFieldsInList_returnsTrue() {
        uniqueTaskList.add(HOMEWORK);
        Task editedAlice = new TaskBuilder(HOMEWORK).withAddress(VALID_ADDRESS_WASH).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(uniqueTaskList.contains(editedAlice));
    }

    @Test
    public void add_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTaskList.add(null));
    }

    @Test
    public void add_duplicateTask_throwsDuplicateTaskException() {
        uniqueTaskList.add(HOMEWORK);
        assertThrows(DuplicateTaskException.class, () -> uniqueTaskList.add(HOMEWORK));
    }

    @Test
    public void setTask_nullTargetTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTaskList.setTask(null, HOMEWORK));
    }

    @Test
    public void setTask_nullEditedTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTaskList.setTask(HOMEWORK, null));
    }

    @Test
    public void setTask_targetTaskNotInList_throwsTaskNotFoundException() {
        assertThrows(TaskNotFoundException.class, () -> uniqueTaskList.setTask(HOMEWORK, HOMEWORK));
    }

    @Test
    public void setTask_editedTaskIsSameTask_success() {
        uniqueTaskList.add(HOMEWORK);
        uniqueTaskList.setTask(HOMEWORK, HOMEWORK);
        UniqueTaskList expectedUniqueTaskList = new UniqueTaskList();
        expectedUniqueTaskList.add(HOMEWORK);
        assertEquals(expectedUniqueTaskList, uniqueTaskList);
    }

    @Test
    public void setTask_editedTaskHasSameIdentity_success() {
        uniqueTaskList.add(HOMEWORK);
        Task editedAlice = new TaskBuilder(HOMEWORK).withAddress(VALID_ADDRESS_WASH).withTags(VALID_TAG_HUSBAND)
                .build();
        uniqueTaskList.setTask(HOMEWORK, editedAlice);
        UniqueTaskList expectedUniqueTaskList = new UniqueTaskList();
        expectedUniqueTaskList.add(editedAlice);
        assertEquals(expectedUniqueTaskList, uniqueTaskList);
    }

    @Test
    public void setTask_editedTaskHasDifferentIdentity_success() {
        uniqueTaskList.add(HOMEWORK);
        uniqueTaskList.setTask(HOMEWORK, WASH);
        UniqueTaskList expectedUniqueTaskList = new UniqueTaskList();
        expectedUniqueTaskList.add(WASH);
        assertEquals(expectedUniqueTaskList, uniqueTaskList);
    }

    @Test
    public void setTask_editedTaskHasNonUniqueIdentity_throwsDuplicateTaskException() {
        uniqueTaskList.add(HOMEWORK);
        uniqueTaskList.add(WASH);
        assertThrows(DuplicateTaskException.class, () -> uniqueTaskList.setTask(HOMEWORK, WASH));
    }

    @Test
    public void remove_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTaskList.remove(null));
    }

    @Test
    public void remove_taskDoesNotExist_throwsTaskNotFoundException() {
        assertThrows(TaskNotFoundException.class, () -> uniqueTaskList.remove(HOMEWORK));
    }

    @Test
    public void remove_existingTask_removesTask() {
        uniqueTaskList.add(HOMEWORK);
        uniqueTaskList.remove(HOMEWORK);
        UniqueTaskList expectedUniqueTaskList = new UniqueTaskList();
        assertEquals(expectedUniqueTaskList, uniqueTaskList);
    }

    @Test
    public void setTask_nullUniqueTaskList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTaskList.setTasks((UniqueTaskList) null));
    }

    @Test
    public void setTask_uniqueTaskList_replacesOwnListWithProvidedUniqueTaskList() {
        uniqueTaskList.add(HOMEWORK);
        UniqueTaskList expectedUniqueTaskList = new UniqueTaskList();
        expectedUniqueTaskList.add(WASH);
        uniqueTaskList.setTasks(expectedUniqueTaskList);
        assertEquals(expectedUniqueTaskList, uniqueTaskList);
    }

    @Test
    public void setTask_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTaskList.setTasks((List<Task>) null));
    }

    @Test
    public void setTask_list_replacesOwnListWithProvidedList() {
        uniqueTaskList.add(HOMEWORK);
        List<Task> taskList = Collections.singletonList(WASH);
        uniqueTaskList.setTasks(taskList);
        UniqueTaskList expectedUniqueTaskList = new UniqueTaskList();
        expectedUniqueTaskList.add(WASH);
        assertEquals(expectedUniqueTaskList, uniqueTaskList);
    }

    @Test
    public void setTasks_listWithDuplicateTasks_throwsDuplicateTaskException() {
        List<Task> listWithDuplicateTasks = Arrays.asList(HOMEWORK, HOMEWORK);
        assertThrows(DuplicateTaskException.class, () -> uniqueTaskList.setTasks(listWithDuplicateTasks));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueTaskList.asUnmodifiableObservableList().remove(0));
    }
}
