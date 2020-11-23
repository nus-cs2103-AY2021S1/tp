package seedu.tr4cker.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.tr4cker.logic.commands.CommandTestUtil.DESC_1;
import static seedu.tr4cker.logic.commands.CommandTestUtil.DESC_2;
import static seedu.tr4cker.logic.commands.CommandTestUtil.DESC_3;
import static seedu.tr4cker.logic.commands.CommandTestUtil.DESC_4;
import static seedu.tr4cker.logic.commands.CommandTestUtil.VALID_DEADLINE_2;
import static seedu.tr4cker.logic.commands.CommandTestUtil.VALID_NAME_2;
import static seedu.tr4cker.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.tr4cker.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.tr4cker.logic.commands.CommandTestUtil.showTaskAtIndex;
import static seedu.tr4cker.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.tr4cker.testutil.TypicalIndexes.INDEX_SECOND_TASK;
import static seedu.tr4cker.testutil.TypicalIndexes.INDEX_THIRD_TASK;
import static seedu.tr4cker.testutil.TypicalTasks.getTypicalTr4cker;

import org.junit.jupiter.api.Test;

import seedu.tr4cker.commons.core.Messages;
import seedu.tr4cker.commons.core.index.Index;
import seedu.tr4cker.logic.commands.EditCommand.EditTaskDescriptor;
import seedu.tr4cker.logic.commands.EditCommand.EditTodoDescriptor;
import seedu.tr4cker.model.Model;
import seedu.tr4cker.model.ModelManager;
import seedu.tr4cker.model.Tr4cker;
import seedu.tr4cker.model.UserPrefs;
import seedu.tr4cker.model.daily.Todo;
import seedu.tr4cker.model.task.Task;
import seedu.tr4cker.testutil.EditTaskDescriptorBuilder;
import seedu.tr4cker.testutil.EditTodoDescriptorBuilder;
import seedu.tr4cker.testutil.TaskBuilder;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private final Model model = new ModelManager(getTypicalTr4cker(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredPendingList_success() {
        Task editedTask = new TaskBuilder().build();
        Todo editedTodo = new Todo(editedTask.getName(), editedTask.getDeadline());
        EditTaskDescriptor descriptor1 = new EditTaskDescriptorBuilder(editedTask).build();
        EditTodoDescriptor descriptor2 = new EditTodoDescriptorBuilder(editedTodo).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_TASK, descriptor1, descriptor2);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_TASK_SUCCESS, editedTask);

        Model expectedModel = new ModelManager(new Tr4cker(model.getTr4cker()), new UserPrefs());
        expectedModel.setTask(model.getFilteredPendingTaskList().get(0), editedTask);

        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        expectedCommandResult.setHomeTab();
        assertCommandSuccess(editCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredPendingList_success() {
        Index indexLastTask = Index.fromOneBased(model.getFilteredPendingTaskList().size());
        Task lastTask = model.getFilteredPendingTaskList().get(indexLastTask.getZeroBased());
        TaskBuilder taskInList = new TaskBuilder(lastTask);
        Task editedTask = taskInList.withName(VALID_NAME_2).withDeadline(VALID_DEADLINE_2).build();
        Todo editedTodo = new Todo(editedTask.getName(), editedTask.getDeadline());

        EditTaskDescriptor descriptor1 = new EditTaskDescriptorBuilder().withName(VALID_NAME_2)
                .withDeadline(VALID_DEADLINE_2).build();
        EditTodoDescriptor descriptor2 = new EditTodoDescriptorBuilder().withName(VALID_NAME_2)
                .withDeadline(VALID_DEADLINE_2).build();
        EditCommand editCommand = new EditCommand(indexLastTask, descriptor1, descriptor2);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_TASK_SUCCESS, editedTask);

        Model expectedModel = new ModelManager(new Tr4cker(model.getTr4cker()), new UserPrefs());
        expectedModel.setTask(lastTask, editedTask);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand = new EditCommand(INDEX_FIRST_TASK, new EditTaskDescriptor(), new EditTodoDescriptor());
        Task editedTask = model.getFilteredPendingTaskList().get(INDEX_FIRST_TASK.getZeroBased());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_UNCHANGED);
    }

    @Test
    public void execute_filteredPendingList_success() {
        showTaskAtIndex(model, INDEX_FIRST_TASK);

        Task taskInFilteredList = model.getFilteredPendingTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        Task editedTask = new TaskBuilder(taskInFilteredList).withName(VALID_NAME_2).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_TASK,
                new EditTaskDescriptorBuilder().withName(VALID_NAME_2).build(),
                new EditTodoDescriptorBuilder().withName(VALID_NAME_2).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_TASK_SUCCESS, editedTask);

        Model expectedModel = new ModelManager(new Tr4cker(model.getTr4cker()), new UserPrefs());
        expectedModel.setTask(model.getFilteredPendingTaskList().get(0), editedTask);

        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        expectedCommandResult.setHomeTab();
        assertCommandSuccess(editCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_duplicateTaskUnfilteredPendingList_failure() {
        Task firstTask = model.getFilteredPendingTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        Todo firstTodo = new Todo(firstTask.getName(), firstTask.getDeadline());
        EditTaskDescriptor descriptor1 = new EditTaskDescriptorBuilder(firstTask).build();
        EditTodoDescriptor descriptor2 = new EditTodoDescriptorBuilder(firstTodo).build();
        EditCommand editCommand = new EditCommand(INDEX_SECOND_TASK, descriptor1, descriptor2);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_TASK);
    }

    @Test
    public void execute_duplicateTaskFilteredPendingList_failure() {
        showTaskAtIndex(model, INDEX_SECOND_TASK);

        // edit task in filtered list into a duplicate in Tr4cker
        Task taskInList = model.getTr4cker().getTaskList().get(INDEX_THIRD_TASK.getZeroBased());
        Todo todoInList = new Todo(taskInList.getName(), taskInList.getDeadline());
        EditCommand editCommand = new EditCommand(INDEX_FIRST_TASK,
                new EditTaskDescriptorBuilder(taskInList).build(),
                new EditTodoDescriptorBuilder(todoInList).build());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_TASK);
    }

    @Test
    public void execute_invalidTaskIndexUnfilteredPendingList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPendingTaskList().size() + 1);
        EditTaskDescriptor descriptor1 = new EditTaskDescriptorBuilder().withName(VALID_NAME_2).build();
        EditTodoDescriptor descriptor2 = new EditTodoDescriptorBuilder().withName(VALID_NAME_2).build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor1, descriptor2);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void execute_deleteModuleForTaskWithoutModule_failure() {
        EditTaskDescriptor descriptor1 = new EditTaskDescriptorBuilder().withName(VALID_NAME_2).build();
        EditTodoDescriptor descriptor2 = new EditTodoDescriptorBuilder().withName(VALID_NAME_2).build();
        descriptor1.setModuleDeleted();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_TASK, descriptor1, descriptor2);
        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_MODULE_ALREADY_DELETED);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of Tr4cker
     */
    @Test
    public void execute_invalidTaskIndexFilteredPendingList_failure() {
        showTaskAtIndex(model, INDEX_FIRST_TASK);
        Index outOfBoundIndex = Index.fromOneBased(model.getTr4cker().getTaskList().size() + 1);

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
                new EditTaskDescriptorBuilder().withName(VALID_NAME_2).build(),
                new EditTodoDescriptorBuilder().withName(VALID_NAME_2).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(INDEX_FIRST_TASK, DESC_1, DESC_3);

        // same values -> returns true
        EditTaskDescriptor copyDescriptor1 = new EditTaskDescriptor(DESC_1);
        EditTodoDescriptor copyDescriptor2 = new EditTodoDescriptor(DESC_3);
        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST_TASK, copyDescriptor1, copyDescriptor2);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ResetCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND_TASK, DESC_1, DESC_3)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST_TASK, DESC_2, DESC_4)));
    }

}
