package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_CREATED_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_LAB05;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRIORITY_HIGH;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRIORITY_NORMAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STATUS_COMPLETED;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STATUS_NOT_COMPLETED;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_CS2100;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.todolist.TypicalTasks.LAB_01;
import static seedu.address.testutil.todolist.TypicalTasks.LAB_02;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.model.tag.Tag;
import seedu.address.testutil.todolist.TaskBuilder;

public class TaskTest {

    @Test
    public void setName_nonNull_nameChanged() {
        Task task = new TaskBuilder().build();
        task = task.setName(new TaskName(VALID_NAME_LAB05));

        Task expectedTask = new TaskBuilder().withName(VALID_NAME_LAB05).build();

        assertEquals(task, expectedTask);
    }

    @Test
    public void setName_null_nullPointerException() {
        Task task = new TaskBuilder().build();
        assertThrows(NullPointerException.class, () -> task.setName(null));
    }

    @Test
    public void setTags_nonNull_tagsChanged() {
        Task task = new TaskBuilder().build();
        Set<Tag> tags = new HashSet<>();
        tags.add(new Tag(VALID_TAG_CS2100));
        task = task.setTags(tags);

        Task expectedTask = new TaskBuilder().withTags(VALID_TAG_CS2100).build();

        assertEquals(task, expectedTask);
    }

    @Test
    public void setPriority_nonNull_priorityChanged() {
        Task task = new TaskBuilder().build();
        task = task.setPriority(Priority.valueOf(VALID_PRIORITY_NORMAL));

        Task expectedTask = new TaskBuilder().withPriority(VALID_PRIORITY_NORMAL).build();

        assertEquals(task, expectedTask);
    }

    @Test
    public void setPriority_null_noException() {
        Task task = new TaskBuilder().build();
        assertDoesNotThrow(() -> task.setPriority(null));
    }

    @Test
    public void setDate() {
        Task task = new TaskBuilder().build();
        task = task.setDate(new Date(VALID_DATE1));

        Task expectedTask = new TaskBuilder().withDate(VALID_DATE1).build();

        assertEquals(task, expectedTask);
    }

    @Test
    public void setDate_null_noException() {
        Task task = new TaskBuilder().build();
        assertDoesNotThrow(() -> task.setDate(null));
    }

    @Test
    public void setStatus() {
        Task task = new TaskBuilder().build();
        task = task.setStatus(Status.valueOf(VALID_STATUS_COMPLETED));

        Task expectedTask = new TaskBuilder().withStatus(VALID_STATUS_COMPLETED).build();

        assertEquals(task, expectedTask);
    }

    @Test
    public void setStatus_null_nullPointerException() {
        Task task = new TaskBuilder().build();
        assertThrows(NullPointerException.class, () -> task.setStatus(null));
    }

    @Test
    public void setDateCreated() {
        Task task = new TaskBuilder().build();
        task = task.setDateCreated(LocalDate.parse(VALID_DATE_CREATED_1));

        Task expectedTask = new TaskBuilder().withDateCreated(VALID_DATE_CREATED_1).build();

        assertEquals(task, expectedTask);
    }

    @Test
    public void setDateCreated_null_nullPointerException() {
        Task task = new TaskBuilder().build();
        assertDoesNotThrow(() -> task.setDateCreated(null));
    }

    @Test
    public void getPriority_null_emptyOptional() {
        Task task = new TaskBuilder().withPriority(null).build();

        assertEquals(Optional.empty(), task.getPriority());
    }

    @Test
    public void getDate_null_emptyOptional() {
        Task task = new TaskBuilder().withDate(null).build();

        assertEquals(Optional.empty(), task.getDate());
    }

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Task task = new TaskBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> task.getTags().get().remove(0));
    }

    @Test
    public void isSameTask() {
        // same object -> returns true
        assertTrue(LAB_01.isSameTask(LAB_01));

        // null -> returns false
        assertFalse(LAB_01.isSameTask(null));

        // ===================== 1 field different ====================== //

        // different name -> returns false
        Task editedLab01 = new TaskBuilder(LAB_01).withName(VALID_NAME_LAB05).build();
        assertFalse(LAB_01.isSameTask(editedLab01));

        // different tags -> returns true
        editedLab01 = new TaskBuilder(LAB_01).withTags(VALID_TAG_CS2100).build();
        assertTrue(LAB_01.isSameTask(editedLab01));

        // different priority -> returns true
        editedLab01 = new TaskBuilder(LAB_01).withPriority(VALID_PRIORITY_NORMAL).build();
        assertTrue(LAB_01.isSameTask(editedLab01));

        // different date -> returns true
        editedLab01 = new TaskBuilder(LAB_01).withDate(VALID_DATE1).build();
        assertTrue(LAB_01.isSameTask(editedLab01));

        // different status -> returns true
        editedLab01 = new TaskBuilder(LAB_01).withStatus(VALID_STATUS_COMPLETED).build();
        assertTrue(LAB_01.isSameTask(editedLab01));

        // different date created -> returns true
        editedLab01 = new TaskBuilder(LAB_01).withDateCreated(VALID_DATE_CREATED_1).build();
        assertTrue(LAB_01.isSameTask(editedLab01));

        // ===================== 2 fields different ==================== //

        // different name and tags -> false
        editedLab01 = new TaskBuilder(LAB_01)
                .withName(VALID_NAME_LAB05)
                .withTags(VALID_TAG_CS2100).build();
        assertFalse(LAB_01.isSameTask(editedLab01));

        // different tags and priority -> true
        editedLab01 = new TaskBuilder(LAB_01)
            .withTags(VALID_TAG_CS2100)
            .withPriority(VALID_PRIORITY_NORMAL).build();
        assertTrue(LAB_01.isSameTask(editedLab01));

        // different priority, and date -> true
        editedLab01 = new TaskBuilder(LAB_01)
            .withPriority(VALID_PRIORITY_NORMAL)
            .withDate(VALID_DATE2).build();
        assertTrue(LAB_01.isSameTask(editedLab01));

        // ===================== 3 fields different ==================== //

        // different name, tags, and priority -> false
        editedLab01 = new TaskBuilder(LAB_01)
            .withName(VALID_NAME_LAB05)
            .withTags(VALID_TAG_CS2100)
            .withPriority(VALID_PRIORITY_NORMAL).build();
        assertFalse(LAB_01.isSameTask(editedLab01));

        // different tags, priority, and date -> true
        editedLab01 = new TaskBuilder(LAB_01)
            .withTags(VALID_TAG_CS2100)
            .withPriority(VALID_PRIORITY_NORMAL)
            .withDate(VALID_DATE2).build();
        assertTrue(LAB_01.isSameTask(editedLab01));

        // ==================== 4 fields different ===================== //

        // same name, different fields -> returns true
        editedLab01 = new TaskBuilder(LAB_01)
                .withTags(VALID_TAG_CS2100).withPriority(VALID_PRIORITY_NORMAL)
                .withDate(VALID_DATE1).withStatus(VALID_STATUS_COMPLETED)
                .withDateCreated(VALID_DATE_CREATED_1).build();
        assertTrue(LAB_01.isSameTask(editedLab01));

        // same status, different fields -> returns false
        editedLab01 = new TaskBuilder(LAB_01)
            .withName(VALID_NAME_LAB05)
            .withTags(VALID_TAG_CS2100).withPriority(VALID_PRIORITY_NORMAL)
            .withPriority(VALID_PRIORITY_NORMAL)
            .withDate(VALID_DATE1).withStatus(VALID_STATUS_COMPLETED)
            .withDateCreated(VALID_DATE_CREATED_1).build();
        assertFalse(LAB_01.isSameTask(editedLab01));
    }

    @Test
    public void hasSameDate() {
        Task task = new TaskBuilder().withDate(VALID_DATE1).build();

        // same date
        assertTrue(task.hasSameDate(new Date(VALID_DATE1)));

        // different date
        assertFalse(task.hasSameDate(new Date(VALID_DATE2)));
    }

    @Test
    public void hasSamePriority() {
        Task task = new TaskBuilder().withPriority(VALID_PRIORITY_HIGH).build();

        // same priority
        assertTrue(task.hasSamePriority(Priority.valueOf(VALID_PRIORITY_HIGH)));

        // different priority
        assertFalse(task.hasSamePriority(Priority.valueOf(VALID_PRIORITY_NORMAL)));
    }

    @Test
    public void hasSameStatus() {
        Task task = new TaskBuilder().withStatus(VALID_STATUS_NOT_COMPLETED).build();

        // same status
        assertTrue(task.hasSameStatus(Status.valueOf(VALID_STATUS_NOT_COMPLETED)));

        // different status
        assertFalse(task.hasSameStatus(Status.valueOf(VALID_STATUS_COMPLETED)));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Task taskCopy = new TaskBuilder(LAB_01).build();
        assertTrue(LAB_01.equals(taskCopy));

        // same object -> returns true
        assertTrue(LAB_01.equals(LAB_01));

        // null -> returns false
        assertFalse(LAB_01.equals(null));

        // different type -> returns false
        assertFalse(LAB_01.equals(5));

        // different task -> returns false
        assertFalse(LAB_01.equals(LAB_02));

        // different name -> returns false
        Task editedLab01 = new TaskBuilder(LAB_01).withName(VALID_NAME_LAB05).build();
        assertFalse(LAB_01.equals(editedLab01));

        // different tags -> returns false
        editedLab01 = new TaskBuilder(LAB_01).withTags(VALID_TAG_CS2100).build();
        assertFalse(LAB_01.equals(editedLab01));

        // different priority -> returns false
        editedLab01 = new TaskBuilder(LAB_01).withPriority(VALID_PRIORITY_NORMAL).build();
        assertFalse(LAB_01.equals(editedLab01));

        // different date -> returns false
        editedLab01 = new TaskBuilder(LAB_01).withDate(VALID_DATE2).build();
        assertFalse(LAB_01.equals(editedLab01));

        // different status -> returns false
        editedLab01 = new TaskBuilder(LAB_01).withStatus(VALID_STATUS_COMPLETED).build();
        assertFalse(LAB_01.equals(editedLab01));

        // different date created -> returns false
        editedLab01 = new TaskBuilder(LAB_01).withDateCreated(VALID_DATE_CREATED_1).build();
        assertFalse(LAB_01.equals(editedLab01));
    }

    @Test
    public void test_toString() {
        Task task = new TaskBuilder().build();
        assertEquals(task.toString(), task.toString());
    }

    // ========================== Getter UI test ============================== //
}
