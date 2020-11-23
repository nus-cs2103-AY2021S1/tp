package seedu.tr4cker.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.tr4cker.logic.commands.CommandTestUtil.VALID_COMPLETION_STATUS_2;
import static seedu.tr4cker.logic.commands.CommandTestUtil.VALID_DEADLINE_2;
import static seedu.tr4cker.logic.commands.CommandTestUtil.VALID_DESCRIPTION_2;
import static seedu.tr4cker.logic.commands.CommandTestUtil.VALID_NAME_2;
import static seedu.tr4cker.logic.commands.CommandTestUtil.VALID_TAG_URGENT;
import static seedu.tr4cker.testutil.Assert.assertThrows;
import static seedu.tr4cker.testutil.TypicalTasks.MANUAL_TASK2;
import static seedu.tr4cker.testutil.TypicalTasks.TASK1;
import static seedu.tr4cker.testutil.TypicalTasks.TASK1_ADD;
import static seedu.tr4cker.testutil.TypicalTasks.TASK1_BEFORE_ADD;
import static seedu.tr4cker.testutil.TypicalTasks.TASK1_BEFORE_DELETE;
import static seedu.tr4cker.testutil.TypicalTasks.TASK1_DELETE;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.tr4cker.model.module.ModuleCode;
import seedu.tr4cker.model.tag.Tag;
import seedu.tr4cker.testutil.TaskBuilder;

public class TaskTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Task task = new TaskBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> task.getTags().remove(0));
    }

    @Test
    public void addTagsTest() {
        Tag tag1 = new Tag("tag1");
        Tag tag2 = new Tag("tag2");
        Set<Tag> tags = new HashSet<>();
        tags.add(tag1);
        tags.add(tag2);
        TASK1.addTags(tags);
        assertEquals(TASK1_ADD, TASK1);

        Tag tag3 = new Tag("tag1");
        Tag tag4 = new Tag("tag2");
        Set<Tag> duplicateTags = new HashSet<>();
        duplicateTags.add(tag3);
        duplicateTags.add(tag4);
        TASK1_ADD.addTags(duplicateTags);
        assertEquals(TASK1_BEFORE_ADD, TASK1_ADD);
    }

    @Test
    public void deleteTagsTest() {
        Tag tag1 = new Tag("tag1");
        Tag tag2 = new Tag("tag2");
        Set<Tag> tags = new HashSet<>();
        tags.add(tag1);
        tags.add(tag2);
        TASK1.deleteTags(tags);
        assertEquals(TASK1_DELETE, TASK1);

        Tag tag3 = new Tag("tag1");
        Tag tag4 = new Tag("tag2");
        Set<Tag> nonExistingTags = new HashSet<>();
        nonExistingTags.add(tag3);
        nonExistingTags.add(tag4);
        TASK1_DELETE.deleteTags(nonExistingTags);
        assertEquals(TASK1_BEFORE_DELETE, TASK1_DELETE);
    }

    @Test
    public void isSameTask() {
        // same object -> returns true
        assertTrue(TASK1.isSameTask(TASK1));

        // null -> returns false
        assertFalse(TASK1.isSameTask(null));

        // different deadline and descriptions -> returns false
        Task editedTask1 =
                new TaskBuilder(TASK1).withDeadline(VALID_DEADLINE_2).withTaskDescription(VALID_DESCRIPTION_2).build();
        assertFalse(TASK1.isSameTask(editedTask1));

        // different name -> returns false
        editedTask1 = new TaskBuilder(TASK1).withName(VALID_NAME_2).build();
        assertFalse(TASK1.isSameTask(editedTask1));

        // same name, same deadline, different attributes -> returns true
        editedTask1 = new TaskBuilder(TASK1).withCompletionStatus(VALID_COMPLETION_STATUS_2)
                .withTaskDescription(VALID_DESCRIPTION_2).withTags(VALID_TAG_URGENT).build();
        assertTrue(TASK1.isSameTask(editedTask1));

        // same name, different attributes -> returns false
        editedTask1 = new TaskBuilder(TASK1).withDeadline(VALID_DEADLINE_2).withTaskDescription(VALID_DESCRIPTION_2)
                .withTags(VALID_TAG_URGENT).build();
        assertFalse(TASK1.isSameTask(editedTask1));

        // same name, same deadline, different attributes -> returns true
        editedTask1 = new TaskBuilder(TASK1)
                .withTaskDescription(VALID_DESCRIPTION_2).withTags(VALID_TAG_URGENT).build();
        assertTrue(TASK1.isSameTask(editedTask1));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Task aliceCopy = new TaskBuilder(TASK1).build();
        assertTrue(TASK1.equals(aliceCopy));

        // same object -> returns true
        assertTrue(TASK1.equals(TASK1));

        // null -> returns false
        assertFalse(TASK1.equals(null));

        // different type -> returns false
        assertFalse(TASK1.equals(5));

        // different task -> returns false
        assertFalse(TASK1.equals(MANUAL_TASK2));

        // different name -> returns false
        Task editedTask1 = new TaskBuilder(TASK1).withName(VALID_NAME_2).build();
        assertFalse(TASK1.equals(editedTask1));

        // different deadline -> returns false
        editedTask1 = new TaskBuilder(TASK1).withDeadline(VALID_DEADLINE_2).build();
        assertFalse(TASK1.equals(editedTask1));

        // different completionStatus -> returns true
        editedTask1 = new TaskBuilder(TASK1).withCompletionStatus(VALID_COMPLETION_STATUS_2).build();
        assertTrue(TASK1.equals(editedTask1));

        // different tr4cker -> returns false
        editedTask1 = new TaskBuilder(TASK1).withTaskDescription(VALID_DESCRIPTION_2).build();
        assertFalse(TASK1.equals(editedTask1));

        // different tags -> returns false
        editedTask1 = new TaskBuilder(TASK1).withTags(VALID_TAG_URGENT).build();
        assertFalse(TASK1.equals(editedTask1));
    }

    @Test
    public void testHashcode() {
        Name name = new Name("name");
        Deadline deadline = new Deadline("02-12-2021 1800", false);
        CompletionStatus completionStatus = new CompletionStatus(0);
        TaskDescription taskDescription = new TaskDescription("description");
        ModuleCode code = new ModuleCode("codeName");
        Set<ModuleCode> moduleCode = new HashSet<>();
        moduleCode.add(code);
        Tag tag = new Tag("tag");
        Set<Tag> tags = new HashSet<>();
        tags.add(tag);
        Task task1 = new Task(name, deadline, completionStatus, taskDescription, moduleCode, tags);
        Task task2 = new Task(name, deadline, completionStatus, taskDescription, moduleCode, tags);
        assertEquals(task1.hashCode(), task2.hashCode());
    }

}
