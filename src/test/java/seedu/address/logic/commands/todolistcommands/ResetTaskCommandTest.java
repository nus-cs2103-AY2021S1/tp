package seedu.address.logic.commands.todolistcommands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showTaskAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_TASK;
import static seedu.address.testutil.todolist.TypicalTasks.getTypicalTodoList;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.ContactList;
import seedu.address.model.EventList;
import seedu.address.model.ModelManager;
import seedu.address.model.ModuleList;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.Status;
import seedu.address.model.task.Task;

public class ResetTaskCommandTest {

    // first index of todo list is not completed, second index is completed
    private ModelManager model = new ModelManager(
        new ModuleList(),
        new ModuleList(),
        new ContactList(),
        getTypicalTodoList(),
        new EventList(),
        new UserPrefs());

    @Test
    public void execute_validIndexCompletedToNotCompletedUnfilteredList_resetSuccessful() throws Exception {
        Task taskToReset = model.getFilteredTodoList().get(INDEX_SECOND_TASK.getZeroBased());
        ResetTaskCommand resetTaskCommand = new ResetTaskCommand(INDEX_SECOND_TASK);

        ModelManager expectedModel = new ModelManager(
            new ModuleList(),
            new ModuleList(),
            new ContactList(),
            getTypicalTodoList(),
            new EventList(),
            new UserPrefs());
        Task resetdTask = taskToReset.setStatus(Status.NOT_COMPLETED);
        expectedModel.setTask(taskToReset, resetdTask);

        String expectedMessage = String.format(ResetTaskCommand.MESSAGE_RESET_TASK_SUCCESS, resetdTask);

        assertCommandSuccess(resetTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validIndexNotCompletedToNotCompletedUnfilteredList_resetSuccessful() throws Exception {
        Task taskToReset = model.getFilteredTodoList().get(INDEX_FIRST_TASK.getZeroBased());
        ResetTaskCommand resetTaskCommand = new ResetTaskCommand(INDEX_FIRST_TASK);

        ModelManager expectedModel = new ModelManager(
            new ModuleList(),
            new ModuleList(),
            new ContactList(),
            getTypicalTodoList(),
            new EventList(),
            new UserPrefs());
        Task resetdTask = taskToReset.setStatus(Status.NOT_COMPLETED);
        expectedModel.setTask(taskToReset, resetdTask);

        String expectedMessage = String.format(ResetTaskCommand.MESSAGE_RESET_TASK_SUCCESS, resetdTask);

        assertCommandSuccess(resetTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTodoList().size() + 1);
        ResetTaskCommand resetTaskCommand = new ResetTaskCommand(outOfBoundIndex);

        assertCommandFailure(resetTaskCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexCompletedToNotCompletedFilteredList_success() {
        showTaskAtIndex(model, INDEX_SECOND_TASK);

        Task taskToReset = model.getFilteredTodoList().get(INDEX_FIRST_TASK.getZeroBased());
        ResetTaskCommand resetTaskCommand = new ResetTaskCommand(INDEX_FIRST_TASK);

        ModelManager expectedModel = new ModelManager(
            new ModuleList(),
            new ModuleList(),
            new ContactList(),
            getTypicalTodoList(),
            new EventList(),
            new UserPrefs());
        Task resetdTask = taskToReset.setStatus(Status.NOT_COMPLETED);
        expectedModel.setTask(taskToReset, resetdTask);

        showTaskAtIndex(expectedModel, INDEX_SECOND_TASK);

        String expectedMessage = String.format(ResetTaskCommand.MESSAGE_RESET_TASK_SUCCESS, resetdTask);

        assertCommandSuccess(resetTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validIndexNotCompletedToNotCompletedFilteredList_success() {
        showTaskAtIndex(model, INDEX_FIRST_TASK);

        Task taskToReset = model.getFilteredTodoList().get(INDEX_FIRST_TASK.getZeroBased());
        ResetTaskCommand resetTaskCommand = new ResetTaskCommand(INDEX_FIRST_TASK);

        ModelManager expectedModel = new ModelManager(
            new ModuleList(),
            new ModuleList(),
            new ContactList(),
            getTypicalTodoList(),
            new EventList(),
            new UserPrefs());
        Task resetdTask = taskToReset.setStatus(Status.NOT_COMPLETED);
        expectedModel.setTask(taskToReset, resetdTask);

        showTaskAtIndex(expectedModel, INDEX_SECOND_TASK);

        String expectedMessage = String.format(ResetTaskCommand.MESSAGE_RESET_TASK_SUCCESS, resetdTask);

        assertCommandSuccess(resetTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showTaskAtIndex(model, INDEX_FIRST_TASK);

        Index outOfBoundIndex = INDEX_SECOND_TASK;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getTodoList().getTodoList().size());

        ResetTaskCommand resetTaskCommand = new ResetTaskCommand(outOfBoundIndex);

        assertCommandFailure(resetTaskCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        ResetTaskCommand resetSecondCommand = new ResetTaskCommand(INDEX_SECOND_TASK);
        ResetTaskCommand resetThirdCommand = new ResetTaskCommand(INDEX_THIRD_TASK);

        // same object -> returns true
        assertTrue(resetSecondCommand.equals(resetSecondCommand));

        // same values -> returns true
        ResetTaskCommand resetSecondCommandCopy = new ResetTaskCommand(INDEX_SECOND_TASK);
        assertTrue(resetSecondCommand.equals(resetSecondCommandCopy));

        // different types -> returns false
        assertFalse(resetSecondCommand.equals(1));

        // null -> returns false
        assertFalse(resetSecondCommand.equals(null));

        // different task -> returns false
        assertFalse(resetSecondCommand.equals(resetThirdCommand));
    }
}
