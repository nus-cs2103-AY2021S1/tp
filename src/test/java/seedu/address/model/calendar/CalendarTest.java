package seedu.address.model.calendar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTasks.EVENT1;
import static seedu.address.testutil.TypicalTasks.EVENT2;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.task.Task;
import seedu.address.model.task.exceptions.DuplicateTaskException;
import seedu.address.model.task.exceptions.TaskNotFoundException;
import seedu.address.testutil.EventBuilder;


public class CalendarTest {

    private final Calendar calendarList = new Calendar();

    @Test
    public void contains_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> calendarList.contains(null));
    }

    @Test
    public void contains_taskNotInList_returnsFalse() {
        assertFalse(calendarList.contains(EVENT1));
    }

    @Test
    public void contains_taskInList_returnsTrue() {
        calendarList.add(EVENT1);
        assertTrue(calendarList.contains(EVENT1));
    }

    @Test
    public void contains_taskWithSameIdentityFieldsInList_returnsTrue() {
        calendarList.add(EVENT1);
        Task editedAlice = new EventBuilder(EVENT1).withTag(VALID_TAG)
                .build();
        assertTrue(calendarList.contains(editedAlice));
    }

    @Test
    public void add_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> calendarList.add(null));
    }

    @Test
    public void add_duplicateTask_throwsDuplicateTaskException() {
        calendarList.add(EVENT1);
        assertThrows(DuplicateTaskException.class, () -> calendarList.add(EVENT1));
    }

    @Test
    public void setTask_nullTargetTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> calendarList.setCalendarTask(null, EVENT1));
    }

    @Test
    public void setTask_nullEditedTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> calendarList.setCalendarTask(EVENT1, null));
    }

    @Test
    public void setTask_targetTaskNotInList_throwsTaskNotFoundException() {
        assertThrows(TaskNotFoundException.class, () -> calendarList.setCalendarTask(EVENT1, EVENT1));
    }

    @Test
    public void setTask_editedTaskIsSameTask_success() {
        calendarList.add(EVENT1);
        calendarList.setCalendarTask(EVENT1, EVENT1);
        Calendar expectedCalendarList = new Calendar();
        expectedCalendarList.add(EVENT1);
        assertEquals(expectedCalendarList, calendarList);
    }

    @Test
    public void setTask_editedTaskHasSameIdentity_success() {
        calendarList.add(EVENT1);
        Task editedAlice = new EventBuilder(EVENT1).withTag(VALID_TAG)
                .build();
        calendarList.setCalendarTask(EVENT1, editedAlice);
        Calendar expectedCalendarList = new Calendar();
        expectedCalendarList.add(editedAlice);
        assertEquals(expectedCalendarList, calendarList);
    }

    @Test
    public void setTask_editedTaskHasDifferentIdentity_success() {
        calendarList.add(EVENT1);
        calendarList.setCalendarTask(EVENT1, EVENT2);
        Calendar expectedCalendarList = new Calendar();
        expectedCalendarList.add(EVENT2);
        assertEquals(expectedCalendarList, calendarList);
    }

    @Test
    public void setTask_editedTaskHasNonUniqueIdentity_throwsDuplicateTaskException() {
        calendarList.add(EVENT1);
        calendarList.add(EVENT2);
        assertThrows(DuplicateTaskException.class, () -> calendarList.setCalendarTask(EVENT1, EVENT2));
    }

    @Test
    public void remove_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> calendarList.remove((Task) null));
    }

    @Test
    public void remove_nullTasks_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> calendarList.remove((Task[]) null));
    }

    @Test
    public void remove_taskDoesNotExist_throwsTaskNotFoundException() {
        assertThrows(TaskNotFoundException.class, () -> calendarList.remove(EVENT1));
    }

    @Test
    public void remove_existingTask_removesTask() {
        calendarList.add(EVENT1);
        calendarList.remove(EVENT1);
        Calendar expectedCalendarList = new Calendar();
        assertEquals(expectedCalendarList, calendarList);
    }

    @Test
    public void setTask_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> calendarList.setCalendarList((List<Task>) null));
    }

    @Test
    public void setTask_list_replacesOwnListWithProvidedList() {
        calendarList.add(EVENT1);
        List<Task> taskList = Collections.singletonList(EVENT2);
        calendarList.setCalendarList(taskList);
        Calendar expectedCalendarList = new Calendar();
        expectedCalendarList.add(EVENT2);
        assertEquals(expectedCalendarList, calendarList);
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> calendarList.asUnmodifiableObservableList().remove(0));
    }
}
