package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showTaskAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_TASK;
import static seedu.address.testutil.TypicalTasks.getTypicalPlanus;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.Task;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for
 * {@code DeleteTaskCommand}.
 */
public class DeleteTaskCommandTest {

    private Model model = new ModelManager(getTypicalPlanus(), new UserPrefs());

    @Test
    public void execute_oneValidIndexUnfilteredList_success() {
        Index[] indexes = {INDEX_FIRST_TASK};
        Task[] taskToDelete = new Task[indexes.length];
        for (int i = 0; i < indexes.length; i++) {
            taskToDelete[i] = model.getFilteredTaskList().get(indexes[i].getZeroBased());
        }
        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(indexes);

        String expectedMessage = String.format(DeleteTaskCommand.buildMessage(taskToDelete));

        ModelManager expectedModel = new ModelManager(model.getPlanus(), new UserPrefs());
        expectedModel.deleteTask(taskToDelete);

        assertCommandSuccess(deleteTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_manyValidIndexUnfilteredList_success() {
        Index[] indexes = {INDEX_FIRST_TASK, INDEX_SECOND_TASK};
        Task[] taskToDelete = new Task[indexes.length];
        for (int i = 0; i < indexes.length; i++) {
            taskToDelete[i] = model.getFilteredTaskList().get(indexes[i].getZeroBased());
        }
        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(indexes);

        String expectedMessage = String.format(DeleteTaskCommand.buildMessage(taskToDelete));

        ModelManager expectedModel = new ModelManager(model.getPlanus(), new UserPrefs());
        expectedModel.deleteTask(taskToDelete);

        assertCommandSuccess(deleteTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_oneInvalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTaskList().size() + 1);
        Index[] indexes = {outOfBoundIndex};
        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(indexes);

        assertCommandFailure(deleteTaskCommand, model, Messages.MESSAGE_INVALID_TASKS_DISPLAYED_INDEX);
    }

    @Test
    public void execute_duplicatedIndex_throwsCommandException() {
        Index[] indexes = {INDEX_FIRST_TASK, INDEX_FIRST_TASK};

        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(indexes);

        assertCommandFailure(deleteTaskCommand, model, Messages.MESSAGE_DUPLICATE_TASK_INDEX);
    }

    @Test
    public void execute_manyInvalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex1 = Index.fromOneBased(model.getFilteredTaskList().size() + 1);
        Index outOfBoundIndex2 = Index.fromOneBased(model.getFilteredTaskList().size() + 2);
        Index[] indexes = {outOfBoundIndex1, outOfBoundIndex2};

        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(indexes);

        assertCommandFailure(deleteTaskCommand, model, Messages.MESSAGE_INVALID_TASKS_DISPLAYED_INDEX);
    }

    @Test
    public void execute_oneValidIndexFilteredList_success() {
        showTaskAtIndex(model, INDEX_FIRST_TASK);
        Index[] indexes = {INDEX_FIRST_TASK};
        Task[] taskToDelete = new Task[indexes.length];
        for (int i = 0; i < indexes.length; i++) {
            taskToDelete[i] = model.getFilteredTaskList().get(indexes[i].getZeroBased());
        }
        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(indexes);

        String expectedMessage = String.format(DeleteTaskCommand.buildMessage(taskToDelete));

        Model expectedModel = new ModelManager(model.getPlanus(), new UserPrefs());
        expectedModel.deleteTask(taskToDelete);
        showNoTask(expectedModel);

        assertCommandSuccess(deleteTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_oneInvalidIndexFilteredList_throwsCommandException() {
        showTaskAtIndex(model, INDEX_FIRST_TASK);

        Index outOfBoundIndex = INDEX_SECOND_TASK;
        Index[] indexes = {outOfBoundIndex};
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getPlanus().getTaskList().size());

        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(indexes);

        assertCommandFailure(deleteTaskCommand, model, Messages.MESSAGE_INVALID_TASKS_DISPLAYED_INDEX);
    }

    @Test
    public void execute_mixValidInvalidIndexList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTaskList().size() + 1);
        Index[] indexes = {INDEX_FIRST_TASK, outOfBoundIndex};

        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(indexes);

        assertCommandFailure(deleteTaskCommand, model, Messages.MESSAGE_INVALID_TASKS_DISPLAYED_INDEX);
    }

    @Test
    public void execute_mixValidInvalidIndexFilteredList_throwsCommandException() {
        showTaskAtIndex(model, INDEX_FIRST_TASK);
        Index outOfBoundIndex = INDEX_SECOND_TASK;
        Index[] indexes = {INDEX_FIRST_TASK, outOfBoundIndex};

        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(indexes);

        assertCommandFailure(deleteTaskCommand, model, Messages.MESSAGE_INVALID_TASKS_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        Index[] firstIndex = {INDEX_FIRST_TASK};
        Index[] secondIndex = {INDEX_SECOND_TASK};
        DeleteTaskCommand deleteFirstCommand = new DeleteTaskCommand(firstIndex);
        DeleteTaskCommand deleteSecondCommand = new DeleteTaskCommand(secondIndex);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteTaskCommand deleteFirstCommandCopy = new DeleteTaskCommand(firstIndex);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different task -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoTask(Model model) {
        model.updateFilteredTaskList(p -> false);

        assertTrue(model.getFilteredTaskList().isEmpty());
    }
}
