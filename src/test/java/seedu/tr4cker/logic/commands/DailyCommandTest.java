package seedu.tr4cker.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.tr4cker.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.tr4cker.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.tr4cker.logic.commands.DailyCommand.MESSAGE_SWITCH_TAB_SUCCESS;
import static seedu.tr4cker.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.tr4cker.testutil.TypicalIndexes.INDEX_SECOND_TASK;
import static seedu.tr4cker.testutil.TypicalTasks.getTypicalTr4cker;

import org.junit.jupiter.api.Test;

import seedu.tr4cker.commons.core.Messages;
import seedu.tr4cker.commons.core.index.Index;
import seedu.tr4cker.logic.commands.exceptions.CommandException;
import seedu.tr4cker.model.Model;
import seedu.tr4cker.model.ModelManager;
import seedu.tr4cker.model.UserPrefs;
import seedu.tr4cker.model.daily.Todo;

/**
 * Contains integration tests (interaction with the Model) for {@code DailyCommand}.
 */
public class DailyCommandTest {

    private final Model model = new ModelManager(getTypicalTr4cker(), new UserPrefs());

    @Test
    public void execute_switchDailyTab_success() throws CommandException {
        CommandResult commandResult = new DailyCommand().execute(model);
        CommandResult expectedCommandResult =
                CommandResult.createDailyTabSwitchCommandResult(MESSAGE_SWITCH_TAB_SUCCESS);

        assertEquals(commandResult, expectedCommandResult);
    }

    @Test
    public void execute_validIndex_success() {
        Todo todoToDelete = model.getFilteredTodoList().get(INDEX_FIRST_TASK.getZeroBased());
        DailyCommand dailyCommand = new DailyCommand(INDEX_FIRST_TASK);

        String expectedMessage = String.format(DailyCommand.MESSAGE_TODO_DELETE_SUCCESS, todoToDelete);

        ModelManager expectedModel = new ModelManager(model.getTr4cker(), new UserPrefs());
        expectedModel.deleteTodo(todoToDelete);

        assertCommandSuccess(dailyCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTodoList().size() + 1);
        DailyCommand dailyCommand = new DailyCommand(outOfBoundIndex);

        assertCommandFailure(dailyCommand, model, Messages.MESSAGE_INVALID_TODO_DISPLAYED_INDEX);
    }

    @Test
    public void testEquals() {
        DailyCommand dailyCommand1 = new DailyCommand();
        DailyCommand dailyCommand2 = new DailyCommand();

        DailyCommand dailyCommand3 = new DailyCommand(INDEX_FIRST_TASK);
        DailyCommand dailyCommand4 = new DailyCommand(INDEX_SECOND_TASK);

        // same object -> returns true
        assertTrue(dailyCommand1.equals(dailyCommand1));

        //same value -> returns true
        assertTrue(dailyCommand1.equals(dailyCommand2));
        assertTrue(dailyCommand3.equals(new DailyCommand(INDEX_FIRST_TASK)));

        // different types -> returns false
        assertFalse(dailyCommand1.equals(1));
        assertFalse(dailyCommand2.equals("daily"));

        // null -> returns false
        assertFalse(dailyCommand1.equals(null));
        assertFalse(dailyCommand3.equals(null));

        // different task -> returns false
        assertFalse(dailyCommand3.equals(dailyCommand4));
    }
}
