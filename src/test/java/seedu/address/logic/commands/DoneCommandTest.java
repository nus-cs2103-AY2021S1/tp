package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalEvents.PARTY;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalTodos.CHORES;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.TaskList;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.Event;
import seedu.address.model.task.Todo;
import seedu.address.testutil.EventBuilder;
import seedu.address.testutil.TodoBuilder;

public class DoneCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new TaskList());

    @Test
    public void execute_doneTodo_success() {
        Todo todo = new TodoBuilder().build();
        model.addTodo(todo);
        DoneCommand doneCommand = new DoneCommand(INDEX_FIRST_PERSON);

        ModelManager expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new TaskList());
        todo.markAsDone();
        expectedModel.addTodo(todo);

        String expectedMessage = String.format(DoneCommand.MESSAGE_MARK_TASK_AS_DONE_SUCCESS, todo);

        assertCommandSuccess(doneCommand, model, expectedMessage, "TASK", expectedModel);
    }

    @Test
    public void execute_doneEvent_success() {
        Event event = new EventBuilder().build();
        model.addEvent(event);
        DoneCommand doneCommand = new DoneCommand(INDEX_FIRST_PERSON);

        ModelManager expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new TaskList());
        event.markAsDone();
        expectedModel.addEvent(event);

        String expectedMessage = String.format(DoneCommand.MESSAGE_MARK_TASK_AS_DONE_SUCCESS, event);

        assertCommandSuccess(doneCommand, model, expectedMessage, "TASK", expectedModel);
    }

    @Test
    public void execute_doneRecurringTodo_success() throws CommandException {
        Todo todoRecurring = new TodoBuilder(CHORES).build();
        Todo todoRecurred = new TodoBuilder(CHORES).withDateTime("08-01-2020 1800").build();

        model.addTodo(todoRecurring);
        DoneCommand doneCommand = new DoneCommand(INDEX_FIRST_PERSON);

        ModelManager expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new TaskList());
        expectedModel.addTodo(todoRecurring);
        todoRecurring.markAsDone();
        expectedModel.addTodo(todoRecurred);

        String expectedMessage = String.format(DoneCommand.MESSAGE_MARK_TASK_AS_DONE_SUCCESS, todoRecurring);

        assertCommandSuccess(doneCommand, model, expectedMessage, "TASK", expectedModel);

    }

    @Test
    public void execute_doneRecurringEvent_success() throws CommandException {
        Event eventRecurring = new EventBuilder(PARTY).build();
        Event eventRecurred = new EventBuilder(PARTY).withStartDateTime("01-01-2021 1800").withEndDateTime("02-01"
                + "-2021 0600").build();

        model.addEvent(eventRecurring);
        DoneCommand doneCommand = new DoneCommand(INDEX_FIRST_PERSON);

        ModelManager expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new TaskList());
        expectedModel.addEvent(eventRecurring);
        expectedModel.markAsDone(eventRecurring);
        expectedModel.addEvent(eventRecurred);

        String expectedMessage = String.format(DoneCommand.MESSAGE_MARK_TASK_AS_DONE_SUCCESS, eventRecurring);

        assertCommandSuccess(doneCommand, model, expectedMessage, "TASK", expectedModel);
    }

    @Test
    public void execute_invalidIndexTaskList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTaskList().size() + 1);
        DoneCommand doneCommand = new DoneCommand(outOfBoundIndex);

        assertCommandFailure(doneCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DoneCommand doneFirstCommand = new DoneCommand(INDEX_FIRST_PERSON);
        DoneCommand doneSecondCommand = new DoneCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(doneFirstCommand.equals(doneFirstCommand));

        // same values -> returns true
        DoneCommand doneFirstCommandCopy = new DoneCommand(INDEX_FIRST_PERSON);
        assertTrue(doneFirstCommand.equals(doneFirstCommandCopy));

        // different types -> returns false
        assertFalse(doneFirstCommand.equals(1));

        // null -> returns false
        assertFalse(doneFirstCommand.equals(null));

        // different task -> returns false
        assertFalse(doneFirstCommand.equals(doneSecondCommand));
    }
}
