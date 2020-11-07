package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_CREATED_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_LAB05;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRIORITY_NORMAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STATUS_COMPLETED;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_CS2100;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.todolist.TypicalTasks.LAB_01;
import static seedu.address.testutil.todolist.TypicalTasks.LAB_02;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.todolist.TaskBuilder;

public class TaskTest {

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
    public void equals() {
        // same values -> returns true
        Task aliceCopy = new TaskBuilder(LAB_01).build();
        assertTrue(LAB_01.equals(aliceCopy));

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
}
