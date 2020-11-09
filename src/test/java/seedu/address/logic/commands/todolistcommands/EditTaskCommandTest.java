package seedu.address.logic.commands.todolistcommands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_LAB05;
import static seedu.address.logic.commands.CommandTestUtil.DESC_LAB07;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_LAB05;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_LAB07;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRIORITY_HIGH;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_CS2105;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showTaskAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_TASK;
import static seedu.address.testutil.todolist.TypicalTasks.getTypicalTodoList;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.ContactList;
import seedu.address.model.EventList;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ModuleList;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.Task;
import seedu.address.testutil.todolist.EditTaskDescriptorBuilder;
import seedu.address.testutil.todolist.TaskBuilder;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for
 * EditTaskCommand.
 */
public class EditTaskCommandTest {

    private Model model = new ModelManager(
            new ModuleList(),
            new ModuleList(),
            new ContactList(),
            getTypicalTodoList(),
            new EventList(),
            new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Task editedTask = new TaskBuilder().build();
        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder(editedTask).build();
        EditTaskCommand editTaskCommand = new EditTaskCommand(INDEX_FIRST_TASK, descriptor);

        String expectedMessage = String.format(EditTaskCommand.MESSAGE_EDIT_TASK_SUCCESS, editedTask);

        Model expectedModel = new ModelManager(
                new ModuleList(),
                new ModuleList(),
                new ContactList(),
                getTypicalTodoList(),
                new EventList(),
                new UserPrefs());
        expectedModel.setTask(model.getFilteredTodoList().get(0), editedTask);

        assertCommandSuccess(editTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastTask = Index.fromOneBased(model.getFilteredTodoList().size());
        Task lastTask = model.getFilteredTodoList().get(indexLastTask.getZeroBased());

        TaskBuilder taskInList = new TaskBuilder(lastTask);
        Task editedTask = taskInList
                .withName(VALID_NAME_LAB05)
                .withPriority(VALID_PRIORITY_HIGH)
                .withTags(VALID_TAG_CS2105).build();

        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder().withName(VALID_NAME_LAB05)
                .withPriority(VALID_PRIORITY_HIGH)
                .withTags(VALID_TAG_CS2105).build();
        EditTaskCommand editTaskCommand = new EditTaskCommand(indexLastTask, descriptor);

        String expectedMessage = String.format(EditTaskCommand.MESSAGE_EDIT_TASK_SUCCESS, editedTask);

        Model expectedModel = new ModelManager(
                new ModuleList(),
                new ModuleList(),
                new ContactList(),
                getTypicalTodoList(),
                new EventList(),
                new UserPrefs());
        expectedModel.setTask(lastTask, editedTask);

        assertCommandSuccess(editTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditTaskCommand editTaskCommand = new EditTaskCommand(INDEX_FIRST_TASK, new EditTaskDescriptor());
        Task editedTask = model.getFilteredTodoList().get(INDEX_FIRST_TASK.getZeroBased());

        String expectedMessage = String.format(EditTaskCommand.MESSAGE_EDIT_TASK_SUCCESS, editedTask);

        Model expectedModel = new ModelManager(
                new ModuleList(),
                new ModuleList(),
                new ContactList(),
                getTypicalTodoList(),
                new EventList(),
                new UserPrefs());
        assertCommandSuccess(editTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showTaskAtIndex(model, INDEX_FIRST_TASK);

        Task taskInFilteredList = model.getFilteredTodoList().get(INDEX_FIRST_TASK.getZeroBased());
        Task editedTask = new TaskBuilder(taskInFilteredList).withName(VALID_NAME_LAB05).build();
        EditTaskCommand editTaskCommand = new EditTaskCommand(INDEX_FIRST_TASK,
            new EditTaskDescriptorBuilder().withName(VALID_NAME_LAB05).build());

        String expectedMessage = String.format(EditTaskCommand.MESSAGE_EDIT_TASK_SUCCESS, editedTask);

        Model expectedModel = new ModelManager(
                new ModuleList(),
                new ModuleList(),
                new ContactList(),
                getTypicalTodoList(),
                new EventList(),
                new UserPrefs());
        expectedModel.setTask(model.getFilteredTodoList().get(0), editedTask);

        assertCommandSuccess(editTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateTaskUnfilteredList_failure() {
        Task firstTask = model.getFilteredTodoList().get(INDEX_FIRST_TASK.getZeroBased());
        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder(firstTask).build();
        EditTaskCommand editTaskCommand = new EditTaskCommand(INDEX_SECOND_TASK, descriptor);

        assertCommandFailure(editTaskCommand, model, EditTaskCommand.MESSAGE_DUPLICATE_TASK);
    }

    @Test
    public void execute_duplicateTaskFilteredList_failure() {
        showTaskAtIndex(model, INDEX_FIRST_TASK);

        // edit task in filtered list into a duplicate in address book
        Task taskInList = model.getTodoList().getTodoList().get(INDEX_SECOND_TASK.getZeroBased());
        EditTaskCommand editTaskCommand = new EditTaskCommand(INDEX_FIRST_TASK,
            new EditTaskDescriptorBuilder(taskInList).build());

        assertCommandFailure(editTaskCommand, model, EditTaskCommand.MESSAGE_DUPLICATE_TASK);
    }

    @Test
    public void execute_invalidTaskIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTodoList().size() + 1);
        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder().withName(VALID_NAME_LAB07).build();
        EditTaskCommand editTaskCommand = new EditTaskCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editTaskCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidTaskIndexFilteredList_failure() {
        showTaskAtIndex(model, INDEX_FIRST_TASK);
        Index outOfBoundIndex = INDEX_SECOND_TASK;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getTodoList().getTodoList().size());

        EditTaskCommand editTaskCommand = new EditTaskCommand(outOfBoundIndex,
            new EditTaskDescriptorBuilder().withName(VALID_NAME_LAB07).build());

        assertCommandFailure(editTaskCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    // ========================= Delete Operation Test ======================= //

    @Test
    public void execute_someFieldsSpecifiedAndDeletedUnfilteredList_success() {
        Index indexLastTask = Index.fromOneBased(model.getFilteredTodoList().size());
        Task lastTask = model.getFilteredTodoList().get(indexLastTask.getZeroBased());

        TaskBuilder taskInList = new TaskBuilder(lastTask);
        Task editedTask = taskInList
                .withName(VALID_NAME_LAB05)
                .withPriority(null)
                .withTags(VALID_TAG_CS2105).build();

        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder()
                .withName(VALID_NAME_LAB05)
                .withPriority(null)
                .withTags(VALID_TAG_CS2105)
                .withIsPriorityDeleted(true).build();
        EditTaskCommand editTaskCommand = new EditTaskCommand(indexLastTask, descriptor);

        String expectedMessage = String.format(EditTaskCommand.MESSAGE_EDIT_TASK_SUCCESS, editedTask);

        Model expectedModel = new ModelManager(
                new ModuleList(),
                new ModuleList(),
                new ContactList(),
                getTypicalTodoList(),
                new EventList(),
                new UserPrefs());
        expectedModel.setTask(lastTask, editedTask);

        assertCommandSuccess(editTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedButDeletedUnfilteredList_success() {
        Index indexLastTask = Index.fromOneBased(model.getFilteredTodoList().size());
        Task lastTask = model.getFilteredTodoList().get(indexLastTask.getZeroBased());

        TaskBuilder taskInList = new TaskBuilder(lastTask);
        Task editedTask = taskInList.withTags()
                .withPriority(null)
                .build();

        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder()
                .withName(null)
                .withTags()
                .withDate(null)
                .withPriority(null)
                .withIsPriorityDeleted(true)
                .build();
        EditTaskCommand editTaskCommand = new EditTaskCommand(indexLastTask, descriptor);

        String expectedMessage = String.format(EditTaskCommand.MESSAGE_EDIT_TASK_SUCCESS, editedTask);

        Model expectedModel = new ModelManager(
                new ModuleList(),
                new ModuleList(),
                new ContactList(),
                getTypicalTodoList(),
                new EventList(),
                new UserPrefs());
        expectedModel.setTask(lastTask, editedTask);

        assertCommandSuccess(editTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_withSomeFieldsDeletedFilteredList_success() {
        showTaskAtIndex(model, INDEX_FIRST_TASK);

        Task taskInFilteredList = model.getFilteredTodoList().get(INDEX_FIRST_TASK.getZeroBased());
        Task editedTask = new TaskBuilder(taskInFilteredList)
                .withName(VALID_NAME_LAB05)
                .withDate(null).build();

        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder()
                .withName(VALID_NAME_LAB05)
                .withIsDateDeleted(true)
                .build();
        EditTaskCommand editTaskCommand = new EditTaskCommand(INDEX_FIRST_TASK, descriptor);

        String expectedMessage = String.format(EditTaskCommand.MESSAGE_EDIT_TASK_SUCCESS, editedTask);

        Model expectedModel = new ModelManager(
                new ModuleList(),
                new ModuleList(),
                new ContactList(),
                getTypicalTodoList(),
                new EventList(),
                new UserPrefs());
        expectedModel.setTask(model.getFilteredTodoList().get(0), editedTask);

        assertCommandSuccess(editTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        final EditTaskCommand standardCommand = new EditTaskCommand(INDEX_FIRST_TASK, DESC_LAB05);

        // same values -> returns true
        EditTaskDescriptor copyDescriptor = new EditTaskDescriptor(DESC_LAB05);
        EditTaskCommand commandWithSameValues = new EditTaskCommand(INDEX_FIRST_TASK, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearTaskCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditTaskCommand(INDEX_SECOND_TASK, DESC_LAB05)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditTaskCommand(INDEX_FIRST_TASK, DESC_LAB07)));
    }

}
