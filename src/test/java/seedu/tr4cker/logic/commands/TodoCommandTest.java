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
import seedu.tr4cker.model.daily.Todo;
import seedu.tr4cker.model.task.Task;

/**
 *  Contains integration tests (interaction with the Model) and unit tests for
 *  * {@code TodoCommand}.
 */
public class TodoCommandTest {

    private final Model model = new ModelManager(getTypicalTr4cker(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Task taskToAdd = model.getFilteredPendingTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        Todo todoToAdd = new Todo(taskToAdd.getName(), taskToAdd.getDeadline());
        TodoCommand todoCommand = new TodoCommand(INDEX_FIRST_TASK);

        String expectedMessage = String.format(TodoCommand.MESSAGE_ADD_TODO_TASK_SUCCESS, todoToAdd);

        ModelManager expectedModel = new ModelManager(model.getTr4cker(), new UserPrefs());
        expectedModel.addTodo(todoToAdd);

        assertCommandSuccess(todoCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPendingTaskList().size() + 1);
        TodoCommand todoCommand = new TodoCommand(outOfBoundIndex);

        assertCommandFailure(todoCommand, model, Messages.MESSAGE_INVALID_TODO_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showTaskAtIndex(model, INDEX_FIRST_TASK);

        Task taskToAdd = model.getFilteredPendingTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        Todo todoToAdd = new Todo(taskToAdd.getName(), taskToAdd.getDeadline());
        TodoCommand todoCommand = new TodoCommand(INDEX_FIRST_TASK);

        String expectedMessage = String.format(TodoCommand.MESSAGE_ADD_TODO_TASK_SUCCESS, todoToAdd);

        ModelManager expectedModel = new ModelManager(model.getTr4cker(), new UserPrefs());
        expectedModel.addTodo(todoToAdd);

        assertCommandSuccess(todoCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showTaskAtIndex(model, INDEX_FIRST_TASK);

        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPendingTaskList().size() + 1);
        TodoCommand todoCommand = new TodoCommand(outOfBoundIndex);

        assertCommandFailure(todoCommand, model, Messages.MESSAGE_INVALID_TODO_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        TodoCommand todoFirstCommand = new TodoCommand(INDEX_FIRST_TASK);
        TodoCommand todoSecondCommand = new TodoCommand(INDEX_SECOND_TASK);

        // same object -> returns true
        assertTrue(todoFirstCommand.equals(todoFirstCommand));

        // same values -> returns true
        TodoCommand todoFirstCommandCopy = new TodoCommand(INDEX_FIRST_TASK);
        assertTrue(todoFirstCommand.equals(todoFirstCommandCopy));

        // different types -> returns false
        assertFalse(todoFirstCommand.equals(1));

        // null -> returns false
        assertFalse(todoFirstCommand.equals(null));

        // different task -> returns false
        assertFalse(todoFirstCommand.equals(todoSecondCommand));
    }
}
