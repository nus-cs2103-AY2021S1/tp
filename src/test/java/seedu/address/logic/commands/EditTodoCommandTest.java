package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_TASK1;
import static seedu.address.logic.commands.CommandTestUtil.DESC_TASK2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_TIME_TUTOR_MEETING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_IMPORTANT;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIFTH_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_TASK;
import static seedu.address.testutil.TypicalTodos.getTypicalTodosTaskList;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.edit.EditTodoCommand;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.TaskList;
import seedu.address.model.UserPrefs;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Todo;
import seedu.address.testutil.TodoBuilder;

public class EditTodoCommandTest {

    private Model model = new ModelManager(new AddressBook(), new UserPrefs(), getTypicalTodosTaskList());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Todo editedTodo = new TodoBuilder().build();
        EditTodoCommand.EditTodoDescriptor descriptor = new EditTodoCommand.EditTodoDescriptor();
        descriptor.setDescription("homework");
        descriptor.setDate("14-12-2020");
        descriptor.setTime("2359");
        EditTodoCommand editTodoCommand = new EditTodoCommand(INDEX_FIRST_TASK, descriptor);

        String expectedMessage = String.format(EditTodoCommand.MESSAGE_EDIT_TODO_SUCCESS, editedTodo);

        Model expectedModel = new ModelManager(new AddressBook(),
                new UserPrefs(), new TaskList(model.getTaskList()));
        expectedModel.setTask(model.getFilteredTaskList().get(0), editedTodo);

        assertCommandSuccess(editTodoCommand, model, expectedMessage, "TASK", expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastTodo = Index.fromOneBased(model.getFilteredTaskList().size());
        Todo lastTodo = (Todo) model.getFilteredTaskList().get(indexLastTodo.getZeroBased());

        TodoBuilder todoInList = new TodoBuilder(lastTodo);
        Todo editedTodo = todoInList.withDescription(VALID_DESCRIPTION).withDateTime(VALID_DATE_TIME_TUTOR_MEETING)
                .withTags(VALID_TAG_IMPORTANT).build();

        EditTodoCommand.EditTodoDescriptor descriptor = new EditTodoCommand.EditTodoDescriptor();
        descriptor.setDescription(VALID_DESCRIPTION);
        descriptor.setDate("10-12-2017");
        descriptor.setTime("1445");
        descriptor.setTags(Stream.of(VALID_TAG_IMPORTANT).map(Tag::new).collect(Collectors.toSet()));

        EditTodoCommand editTodoCommand = new EditTodoCommand(indexLastTodo, descriptor);

        String expectedMessage = String.format(EditTodoCommand.MESSAGE_EDIT_TODO_SUCCESS, editedTodo);

        Model expectedModel = new ModelManager(new AddressBook(),
                new UserPrefs(), new TaskList(model.getTaskList()));
        expectedModel.setTask(lastTodo, editedTodo);

        assertCommandSuccess(editTodoCommand, model, expectedMessage, "TASK", expectedModel);
    }

    @Test
    public void execute_duplicateTodoUnfilteredList_failure() {
        Todo firstTodo = (Todo) model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        EditTodoCommand.EditTodoDescriptor descriptor = new EditTodoCommand.EditTodoDescriptor();
        descriptor.setDescription(firstTodo.getDescription());
        descriptor.setTags(firstTodo.getTags());
        descriptor.setDate(firstTodo.getInputDate().split(" ", 2)[0]);
        descriptor.setTime(firstTodo.getInputDate().split(" ", 2)[1]);

        EditTodoCommand editTodoCommand = new EditTodoCommand(INDEX_FIFTH_TASK, descriptor);

        assertCommandFailure(editTodoCommand, model, EditTodoCommand.MESSAGE_DUPLICATE_TODO);
    }

    @Test
    public void equals() {
        final EditTodoCommand standardCommand = new EditTodoCommand(INDEX_FIRST_TASK, DESC_TASK1);

        // same values -> returns true
        EditTodoCommand.EditTodoDescriptor copyDescriptor = new EditTodoCommand.EditTodoDescriptor(DESC_TASK1);
        EditTodoCommand commandWithSameValues = new EditTodoCommand(INDEX_FIRST_TASK, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditTodoCommand(INDEX_SECOND_TASK, DESC_TASK1)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditTodoCommand(INDEX_FIRST_TASK, DESC_TASK2)));
    }
}
