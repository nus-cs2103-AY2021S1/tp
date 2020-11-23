package seedu.tr4cker.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.tr4cker.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.tr4cker.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.tr4cker.logic.commands.CommandTestUtil.showTaskAtIndex;
import static seedu.tr4cker.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.tr4cker.testutil.TypicalIndexes.INDEX_SECOND_TASK;
import static seedu.tr4cker.testutil.TypicalTasks.getTypicalTr4cker;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.tr4cker.commons.core.Messages;
import seedu.tr4cker.commons.core.index.Index;
import seedu.tr4cker.model.Model;
import seedu.tr4cker.model.ModelManager;
import seedu.tr4cker.model.UserPrefs;
import seedu.tr4cker.model.tag.Tag;
import seedu.tr4cker.model.task.Task;

/**
 * Contains integration tests (interaction with the Model) for {@code TagCommand}.
 */
class TagCommandTest {

    private final Model model = new ModelManager(getTypicalTr4cker(), new UserPrefs());
    private final Tag tag1 = new Tag("homework");
    private final Tag tag2 = new Tag("assignment");
    private final Set<Tag> add = new HashSet<>();
    private final Set<Tag> delete = new HashSet<>();
    private final Set<Tag> duplicateTags = new HashSet<>();
    private final Set<Tag> nonExistingTags = new HashSet<>();

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Task taskToEdit = model.getFilteredTaskList().get(INDEX_SECOND_TASK.getZeroBased());
        TagCommand tagCommand = new TagCommand(INDEX_FIRST_TASK, add, delete);

        String expectedMessage = String.format(TagCommand.MESSAGE_SUCCESS, taskToEdit);

        ModelManager expectedModel = new ModelManager(model.getTr4cker(), new UserPrefs());
        taskToEdit.addTags(add);
        taskToEdit.deleteTags(delete);

        assertCommandSuccess(tagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTaskList().size() + 1);
        TagCommand tagCommand = new TagCommand(outOfBoundIndex, add, delete);

        assertCommandFailure(tagCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showTaskAtIndex(model, INDEX_FIRST_TASK);

        Task taskToEdit = model.getFilteredPendingTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        TagCommand tagCommand = new TagCommand(INDEX_FIRST_TASK, add, delete);

        String expectedMessage = String.format(TagCommand.MESSAGE_SUCCESS, taskToEdit);

        Model expectedModel = new ModelManager(model.getTr4cker(), new UserPrefs());
        taskToEdit.addTags(add);
        taskToEdit.deleteTags(delete);

        assertCommandSuccess(tagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showTaskAtIndex(model, INDEX_FIRST_TASK);

        Index outOfBoundIndex = Index.fromOneBased(model.getTr4cker().getTaskList().size() + 1);

        TagCommand tagCommand = new TagCommand(outOfBoundIndex, add, delete);

        assertCommandFailure(tagCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void execute_duplicateTags_success() {
        add.add(tag1);
        duplicateTags.add(tag1);

        showTaskAtIndex(model, INDEX_FIRST_TASK);

        Task taskToEdit = model.getFilteredPendingTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        TagCommand tagCommand = new TagCommand(INDEX_FIRST_TASK, add, delete);

        Task newTask = new Task(taskToEdit.getName(), taskToEdit.getDeadline(),
                taskToEdit.getCompletionStatus(), taskToEdit.getTaskDescription(),
                taskToEdit.getModuleCode(), add);

        String expectedMessage = String.format(TagCommand.MESSAGE_SUCCESS, newTask);
        expectedMessage += String.format(TagCommand.MESSAGE_SUCCESS_DUPLICATE_TAGS, duplicateTags);

        Model expectedModel = new ModelManager(model.getTr4cker(), new UserPrefs());
        expectedModel.setTask(model.getFilteredPendingTaskList().get(0), newTask);
        assertCommandSuccess(tagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_nonExistingTags_success() {
        delete.add(tag2);
        nonExistingTags.add(tag2);

        showTaskAtIndex(model, INDEX_FIRST_TASK);

        Task taskToEdit = model.getFilteredPendingTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        TagCommand tagCommand = new TagCommand(INDEX_FIRST_TASK, add, delete);

        Task newTask = new Task(taskToEdit.getName(), taskToEdit.getDeadline(),
                taskToEdit.getCompletionStatus(), taskToEdit.getTaskDescription(),
                taskToEdit.getModuleCode(), taskToEdit.getTags());
        newTask.deleteTags(delete);

        String expectedMessage = String.format(TagCommand.MESSAGE_SUCCESS, newTask);
        expectedMessage += String.format(TagCommand.MESSAGE_SUCCESS_NON_EXISTING_TAGS, nonExistingTags);

        Model expectedModel = new ModelManager(model.getTr4cker(), new UserPrefs());
        expectedModel.setTask(model.getFilteredPendingTaskList().get(0), newTask);
        assertCommandSuccess(tagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateAndNonExistingTags_success() {
        add.add(tag1);
        duplicateTags.add(tag1);
        delete.add(tag2);
        nonExistingTags.add(tag2);

        showTaskAtIndex(model, INDEX_FIRST_TASK);

        Task taskToEdit = model.getFilteredPendingTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        TagCommand tagCommand = new TagCommand(INDEX_FIRST_TASK, add, delete);

        Task newTask = new Task(taskToEdit.getName(), taskToEdit.getDeadline(),
                taskToEdit.getCompletionStatus(), taskToEdit.getTaskDescription(),
                taskToEdit.getModuleCode(), taskToEdit.getTags());

        String expectedMessage = String.format(TagCommand.MESSAGE_SUCCESS, newTask);
        expectedMessage += String.format(TagCommand.MESSAGE_SUCCESS_DUPLICATE_TAGS, duplicateTags);
        expectedMessage += String.format(TagCommand.MESSAGE_SUCCESS_NON_EXISTING_TAGS, nonExistingTags);

        Model expectedModel = new ModelManager(model.getTr4cker(), new UserPrefs());
        expectedModel.setTask(model.getFilteredPendingTaskList().get(0), newTask);
        assertCommandSuccess(tagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        Index index = Index.fromZeroBased(1);
        add.add(tag1);
        delete.add(tag2);
        TagCommand tagCommand1 = new TagCommand(index, add, delete);
        TagCommand tagCommand2 = new TagCommand(index, add, delete);
        TagCommand tagCommand3 = new TagCommand(index, delete, add);

        // same object -> returns true
        assertEquals(tagCommand1, tagCommand1);

        // same values -> returns true
        assertEquals(tagCommand2, tagCommand1);

        // different types -> returns false
        assertNotEquals(tagCommand1, 1);

        // null -> returns false
        assertNotEquals(tagCommand1, null);

        // different sets -> returns false
        assertNotEquals(tagCommand3, tagCommand1);
    }

}
