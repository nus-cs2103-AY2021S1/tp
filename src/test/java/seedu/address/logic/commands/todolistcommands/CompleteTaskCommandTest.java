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

public class CompleteTaskCommandTest {

    // first index of todo list is completed, second index is not completed
    private ModelManager model = new ModelManager(
            new ModuleList(),
            new ModuleList(),
            new ContactList(),
            getTypicalTodoList(),
            new EventList(),
            new UserPrefs());

    @Test
    public void execute_validIndexNotCompletedToCompletedUnfilteredList_completeSuccessful() throws Exception {
        Task taskToComplete = model.getFilteredTodoList().get(INDEX_FIRST_TASK.getZeroBased());
        CompleteTaskCommand completeTaskCommand = new CompleteTaskCommand(INDEX_FIRST_TASK);

        ModelManager expectedModel = new ModelManager(
            new ModuleList(),
            new ModuleList(),
            new ContactList(),
            getTypicalTodoList(),
            new EventList(),
            new UserPrefs());
        Task completedTask = taskToComplete.setStatus(Status.COMPLETED);
        expectedModel.setTask(taskToComplete, completedTask);

        String expectedMessage = String.format(CompleteTaskCommand.MESSAGE_COMPLETE_TASK_SUCCESS, completedTask);

        assertCommandSuccess(completeTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validIndexCompletedToCompletedUnfilteredList_completeSuccessful() throws Exception {
        Task taskToComplete = model.getFilteredTodoList().get(INDEX_FIRST_TASK.getZeroBased());
        CompleteTaskCommand completeTaskCommand = new CompleteTaskCommand(INDEX_FIRST_TASK);

        ModelManager expectedModel = new ModelManager(
                new ModuleList(),
                new ModuleList(),
                new ContactList(),
                getTypicalTodoList(),
                new EventList(),
                new UserPrefs());
        Task completedTask = taskToComplete.setStatus(Status.COMPLETED);
        expectedModel.setTask(taskToComplete, completedTask);

        String expectedMessage = String.format(CompleteTaskCommand.MESSAGE_COMPLETE_TASK_SUCCESS, completedTask);

        assertCommandSuccess(completeTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTodoList().size() + 1);
        CompleteTaskCommand completeTaskCommand = new CompleteTaskCommand(outOfBoundIndex);

        assertCommandFailure(completeTaskCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexNotCompletedToCompletedFilteredList_success() {
        showTaskAtIndex(model, INDEX_SECOND_TASK);

        Task taskToComplete = model.getFilteredTodoList().get(INDEX_FIRST_TASK.getZeroBased());
        CompleteTaskCommand completeTaskCommand = new CompleteTaskCommand(INDEX_FIRST_TASK);

        ModelManager expectedModel = new ModelManager(
            new ModuleList(),
            new ModuleList(),
            new ContactList(),
            getTypicalTodoList(),
            new EventList(),
            new UserPrefs());
        Task completedTask = taskToComplete.setStatus(Status.COMPLETED);
        expectedModel.setTask(taskToComplete, completedTask);

        showTaskAtIndex(expectedModel, INDEX_SECOND_TASK);

        String expectedMessage = String.format(CompleteTaskCommand.MESSAGE_COMPLETE_TASK_SUCCESS, completedTask);

        assertCommandSuccess(completeTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validIndexCompletedToCompletedFilteredList_success() {
        showTaskAtIndex(model, INDEX_SECOND_TASK);

        Task taskToComplete = model.getFilteredTodoList().get(INDEX_FIRST_TASK.getZeroBased());
        CompleteTaskCommand completeTaskCommand = new CompleteTaskCommand(INDEX_FIRST_TASK);

        ModelManager expectedModel = new ModelManager(
            new ModuleList(),
            new ModuleList(),
            new ContactList(),
            getTypicalTodoList(),
            new EventList(),
            new UserPrefs());
        Task completedTask = taskToComplete.setStatus(Status.COMPLETED);
        expectedModel.setTask(taskToComplete, completedTask);

        showTaskAtIndex(expectedModel, INDEX_SECOND_TASK);

        String expectedMessage = String.format(CompleteTaskCommand.MESSAGE_COMPLETE_TASK_SUCCESS, completedTask);

        assertCommandSuccess(completeTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showTaskAtIndex(model, INDEX_FIRST_TASK);

        Index outOfBoundIndex = INDEX_SECOND_TASK;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getTodoList().getTodoList().size());

        CompleteTaskCommand completeTaskCommand = new CompleteTaskCommand(outOfBoundIndex);

        assertCommandFailure(completeTaskCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        CompleteTaskCommand completeSecondCommand = new CompleteTaskCommand(INDEX_SECOND_TASK);
        CompleteTaskCommand completeThirdCommand = new CompleteTaskCommand(INDEX_THIRD_TASK);

        // same object -> returns true
        assertTrue(completeSecondCommand.equals(completeSecondCommand));

        // same values -> returns true
        CompleteTaskCommand completeSecondCommandCopy = new CompleteTaskCommand(INDEX_SECOND_TASK);
        assertTrue(completeSecondCommand.equals(completeSecondCommandCopy));

        // different types -> returns false
        assertFalse(completeSecondCommand.equals(1));

        // null -> returns false
        assertFalse(completeSecondCommand.equals(null));

        // different task -> returns false
        assertFalse(completeSecondCommand.equals(completeThirdCommand));
    }
}
