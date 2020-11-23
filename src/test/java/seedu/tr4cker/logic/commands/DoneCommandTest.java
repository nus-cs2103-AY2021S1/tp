package seedu.tr4cker.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.tr4cker.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.tr4cker.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.tr4cker.logic.commands.CommandTestUtil.showTaskAtIndex;
import static seedu.tr4cker.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.tr4cker.testutil.TypicalIndexes.INDEX_SECOND_TASK;
import static seedu.tr4cker.testutil.TypicalTasks.getTypicalTr4cker;

import org.junit.jupiter.api.Test;

import seedu.tr4cker.commons.core.Messages;
import seedu.tr4cker.commons.core.index.Index;
import seedu.tr4cker.model.Model;
import seedu.tr4cker.model.ModelManager;
import seedu.tr4cker.model.UserPrefs;
import seedu.tr4cker.model.task.CompletionStatus;
import seedu.tr4cker.model.task.Task;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for
 * {@code DoneCommand}.
 */
public class DoneCommandTest {

    private final Model model = new ModelManager(getTypicalTr4cker(), new UserPrefs());

    @Test
    public void execute_percentageIncreasesUnfilteredList_success() {
        Task taskToComplete = model.getFilteredPendingTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        CompletionStatus percentage = new CompletionStatus(75);
        DoneCommand doneCommand = new DoneCommand(INDEX_FIRST_TASK, percentage);
        ModelManager expectedModel = new ModelManager(model.getTr4cker(), new UserPrefs());
        Task newTask = new Task(taskToComplete.getName(), taskToComplete.getDeadline(),
                new CompletionStatus(75), taskToComplete.getTaskDescription(),
                taskToComplete.getModuleCode(), taskToComplete.getTags());
        String expectedMessage = String.format(DoneCommand.MESSAGE_DONE_TASK_SUCCESS_INCREASE, newTask);
        expectedModel.setTask(model.getFilteredPendingTaskList().get(0), newTask);
        assertCommandSuccess(doneCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_percentageDecreasesUnfilteredList_success() {
        Task taskToComplete = model.getFilteredPendingTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        ModelManager expectedModel = new ModelManager(model.getTr4cker(), new UserPrefs());

        Task newTask = new Task(taskToComplete.getName(), taskToComplete.getDeadline(),
                new CompletionStatus(75), taskToComplete.getTaskDescription(),
                taskToComplete.getModuleCode(), taskToComplete.getTags());
        expectedModel.setTask(model.getFilteredPendingTaskList().get(0), newTask);

        model.setTask(model.getFilteredPendingTaskList().get(0), newTask);
        CompletionStatus percentage = new CompletionStatus(50);
        DoneCommand doneCommand = new DoneCommand(INDEX_FIRST_TASK, percentage);
        Task newTask2 = new Task(newTask.getName(), newTask.getDeadline(),
                new CompletionStatus(50), newTask.getTaskDescription(),
                taskToComplete.getModuleCode(), newTask.getTags());
        String expectedMessage = String.format(DoneCommand.MESSAGE_DONE_TASK_SUCCESS_DECREASE, newTask2);
        expectedModel.setTask(model.getFilteredPendingTaskList().get(0), newTask2);
        assertCommandSuccess(doneCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_percentageConstantUnfilteredList_throwsCommandException() {
        Task taskToComplete = model.getFilteredPendingTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        ModelManager expectedModel = new ModelManager(model.getTr4cker(), new UserPrefs());

        Task newTask = new Task(taskToComplete.getName(), taskToComplete.getDeadline(),
                new CompletionStatus(75), taskToComplete.getTaskDescription(),
                taskToComplete.getModuleCode(), taskToComplete.getTags());
        expectedModel.setTask(model.getFilteredPendingTaskList().get(0), newTask);

        model.setTask(model.getFilteredPendingTaskList().get(0), newTask);
        CompletionStatus percentage = new CompletionStatus(75);
        DoneCommand doneCommand = new DoneCommand(INDEX_FIRST_TASK, percentage);
        String expectedMessage = String.format(DoneCommand.MESSAGE_COMPLETION_SAME);
        assertCommandFailure(doneCommand, model, expectedMessage);
    }

    // Consider testing out of range (>100 or <0) and non mumber (xyz) but seems to throw IAE instead of CE

    @Test
    public void execute_validIndexFilteredList_success() {
        showTaskAtIndex(model, INDEX_FIRST_TASK);

        Task taskToComplete = model.getFilteredPendingTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        CompletionStatus percentage = new CompletionStatus(75);
        DoneCommand doneCommand = new DoneCommand(INDEX_FIRST_TASK, percentage);

        Task newTask = new Task(taskToComplete.getName(), taskToComplete.getDeadline(),
                new CompletionStatus(75), taskToComplete.getTaskDescription(),
                taskToComplete.getModuleCode(), taskToComplete.getTags());
        String expectedMessage = String.format(DoneCommand.MESSAGE_DONE_TASK_SUCCESS_INCREASE, newTask);

        Model expectedModel = new ModelManager(model.getTr4cker(), new UserPrefs());
        expectedModel.setTask(model.getFilteredPendingTaskList().get(0), newTask);
        assertCommandSuccess(doneCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showTaskAtIndex(model, INDEX_FIRST_TASK);

        Index outOfBoundIndex = Index.fromOneBased(model.getTr4cker().getTaskList().size() + 1);

        CompletionStatus percentage = new CompletionStatus(75);
        DoneCommand doneCommand = new DoneCommand(outOfBoundIndex, percentage);

        assertCommandFailure(doneCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        CompletionStatus firstCompletionStatus = new CompletionStatus(66);
        CompletionStatus secondCompletionStatus = new CompletionStatus(75);

        DoneCommand doneFirstCommand = new DoneCommand(INDEX_FIRST_TASK, firstCompletionStatus);
        DoneCommand doneSecondCommand = new DoneCommand(INDEX_SECOND_TASK, secondCompletionStatus);

        // same object -> returns true
        assertTrue(doneFirstCommand.equals(doneFirstCommand));

        // same values -> returns true
        DoneCommand doneFirstCommandCopy = new DoneCommand(INDEX_FIRST_TASK, firstCompletionStatus);
        assertTrue(doneFirstCommand.equals(doneFirstCommandCopy));

        // different types -> returns false
        assertFalse(doneFirstCommand.equals(1));

        // null -> returns false
        assertFalse(doneFirstCommand.equals(null));

        // different task -> returns false
        assertFalse(doneFirstCommand.equals(doneSecondCommand));
    }
}
