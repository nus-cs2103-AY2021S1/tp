package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showTaskAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_DURATION;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_DURATION;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_DURATION;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_TASK;
import static seedu.address.testutil.TypicalTasks.getTypicalPlanus;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.deadline.Deadline;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for
 * {@code DeleteCommand}.
 */
public class DoneCommandTest {

    private Model model = new ModelManager(getTypicalPlanus(), new UserPrefs());

    @Test
    public void execute_oneValidIndexUnfilteredList_success() {
        Index[] indexes = {INDEX_FIRST_TASK};
        int[] durations = {INDEX_FIRST_DURATION};
        Deadline[] tasksToDone = new Deadline[indexes.length];
        for (int i = 0; i < indexes.length; i++) {
            tasksToDone[i] = (Deadline) model.getFilteredTaskList().get(indexes[i].getZeroBased());
        }
        DoneCommand doneCommand = new DoneCommand(indexes, durations);

        String expectedMessage = String.format(DoneCommand.buildMessage(tasksToDone));

        ModelManager expectedModel = new ModelManager(model.getPlanus(), new UserPrefs());
        expectedModel.markAsDone(tasksToDone, durations);

        assertCommandSuccess(doneCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_manyValidIndexUnfilteredList_success() {
        Index[] indexes = {INDEX_FIRST_TASK, INDEX_THIRD_TASK};
        int[] durations = {INDEX_FIRST_DURATION, INDEX_THIRD_DURATION};
        Deadline[] tasksToDone = new Deadline[indexes.length];
        for (int i = 0; i < indexes.length; i++) {
            tasksToDone[i] = (Deadline) model.getFilteredTaskList().get(indexes[i].getZeroBased());
        }
        DoneCommand doneCommand = new DoneCommand(indexes, durations);

        String expectedMessage = String.format(DoneCommand.buildMessage(tasksToDone));

        ModelManager expectedModel = new ModelManager(model.getPlanus(), new UserPrefs());
        expectedModel.markAsDone(tasksToDone, durations);

        assertCommandSuccess(doneCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_oneInvalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTaskList().size() + 1);
        Index[] indexes = {outOfBoundIndex};
        int[] durations = {INDEX_FIRST_DURATION};

        DoneCommand doneCommand = new DoneCommand(indexes, durations);

        assertCommandFailure(doneCommand, model, Messages.MESSAGE_INVALID_TASKS_DISPLAYED_INDEX);
    }

    @Test
    public void execute_duplicatedIndex_throwsCommandException() {
        Index[] indexes = {INDEX_FIRST_TASK, INDEX_FIRST_TASK};
        int[] durations = {INDEX_FIRST_DURATION, INDEX_SECOND_DURATION};

        DoneCommand doneCommand = new DoneCommand(indexes, durations);

        assertCommandFailure(doneCommand, model, Messages.MESSAGE_DUPLICATE_TASK_INDEX);
    }

    @Test
    public void execute_incorrectStatusTask_throwsCommandException() {
        Index[] indexes = {INDEX_SECOND_TASK};
        int[] durations = {INDEX_SECOND_DURATION};

        DoneCommand doneCommand = new DoneCommand(indexes, durations);

        assertCommandFailure(doneCommand, model, Messages.MESSAGE_INCORRECT_TASK_STATUS);
    }

    @Test
    public void execute_manyInvalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex1 = Index.fromOneBased(model.getFilteredTaskList().size() + 1);
        Index outOfBoundIndex2 = Index.fromOneBased(model.getFilteredTaskList().size() + 2);
        Index[] indexes = {outOfBoundIndex1, outOfBoundIndex2};
        int[] durations = {INDEX_FIRST_DURATION, INDEX_SECOND_DURATION};

        DoneCommand doneCommand = new DoneCommand(indexes, durations);

        assertCommandFailure(doneCommand, model, Messages.MESSAGE_INVALID_TASKS_DISPLAYED_INDEX);
    }

    @Test
    public void execute_oneValidIndexFilteredList_success() {
        showTaskAtIndex(model, INDEX_FIRST_TASK);
        Index[] indexes = {INDEX_FIRST_TASK};
        int[] durations = {INDEX_FIRST_DURATION};
        Deadline[] tasksToDone = new Deadline[indexes.length];
        for (int i = 0; i < indexes.length; i++) {
            tasksToDone[i] = (Deadline) model.getFilteredTaskList().get(indexes[i].getZeroBased());
        }
        DoneCommand doneCommand = new DoneCommand(indexes, durations);

        String expectedMessage = String.format(DoneCommand.buildMessage(tasksToDone));

        Model expectedModel = new ModelManager(model.getPlanus(), new UserPrefs());
        showTaskAtIndex(expectedModel, INDEX_FIRST_TASK);
        expectedModel.markAsDone(tasksToDone, durations);

        assertCommandSuccess(doneCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_oneInvalidIndexFilteredList_throwsCommandException() {
        showTaskAtIndex(model, INDEX_FIRST_TASK);

        Index outOfBoundIndex = INDEX_SECOND_TASK;
        Index[] indexes = {outOfBoundIndex};
        int[] durations = {INDEX_SECOND_DURATION};
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getPlanus().getTaskList().size());

        DoneCommand doneCommand = new DoneCommand(indexes, durations);

        assertCommandFailure(doneCommand, model, Messages.MESSAGE_INVALID_TASKS_DISPLAYED_INDEX);
    }

    @Test
    public void execute_mixValidInvalidIndexList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTaskList().size() + 1);
        Index[] indexes = {INDEX_FIRST_TASK, outOfBoundIndex};
        int[] durations = {INDEX_FIRST_DURATION, INDEX_SECOND_DURATION};

        DoneCommand doneCommand = new DoneCommand(indexes, durations);

        assertCommandFailure(doneCommand, model, Messages.MESSAGE_INVALID_TASKS_DISPLAYED_INDEX);
    }

    @Test
    public void execute_mixValidInvalidIndexFilteredList_throwsCommandException() {
        showTaskAtIndex(model, INDEX_FIRST_TASK);
        Index outOfBoundIndex = INDEX_SECOND_TASK;
        Index[] indexes = {INDEX_FIRST_TASK, outOfBoundIndex};
        int[] durations = {INDEX_FIRST_DURATION, INDEX_SECOND_DURATION};

        DoneCommand doneCommand = new DoneCommand(indexes, durations);

        assertCommandFailure(doneCommand, model, Messages.MESSAGE_INVALID_TASKS_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        Index[] firstIndex = {INDEX_FIRST_TASK};
        Index[] secondIndex = {INDEX_SECOND_TASK};
        int[] firstDurations = {INDEX_FIRST_DURATION};
        int[] secondDurations = {INDEX_SECOND_DURATION};
        DoneCommand doneFirstCommand = new DoneCommand(firstIndex, firstDurations);
        DoneCommand doneSecondCommand = new DoneCommand(secondIndex, secondDurations);

        // same object -> returns true
        assertTrue(doneFirstCommand.equals(doneFirstCommand));

        // same values -> returns true
        DoneCommand doneFirstCommandCopy = new DoneCommand(firstIndex, firstDurations);
        assertTrue(doneFirstCommand.equals(doneFirstCommandCopy));

        // different types -> returns false
        assertFalse(doneFirstCommand.equals(1));

        // null -> returns false
        assertFalse(doneFirstCommand.equals(null));

        // different task -> returns false
        assertFalse(doneFirstCommand.equals(doneSecondCommand));
    }

}
