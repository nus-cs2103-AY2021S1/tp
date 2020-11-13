package seedu.jarvis.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.jarvis.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.jarvis.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.jarvis.testutil.TypicalStudents.getTypicalAddressBook;
import static seedu.jarvis.testutil.TypicalTasks.TEST_EVENT_TWO;
import static seedu.jarvis.testutil.TypicalTasks.TEST_TODO_TWO;

import org.junit.jupiter.api.Test;

import seedu.jarvis.commons.core.Messages;
import seedu.jarvis.logic.commands.delete.DeleteTaskCommand;
import seedu.jarvis.logic.commands.exceptions.CommandException;
import seedu.jarvis.model.Model;
import seedu.jarvis.model.ModelManager;
import seedu.jarvis.model.task.Todo;
import seedu.jarvis.testutil.TypicalManagers;

public class DeleteTaskCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), TypicalManagers.getUserPrefs(),
            TypicalManagers.getUserLogin());

    @Test
    //There is no need to test for filtered and unfiltered lists as deleting task is based on unique Id assigned to each
    //task upon creation.
    public void execute_validIndexList_success() {
        Todo toDeleteTask = TEST_TODO_TWO;
        model.addTodo(toDeleteTask);
        String toDeleteId = toDeleteTask.getTaskId();
        DeleteTaskCommand deleteCommand = new DeleteTaskCommand(toDeleteId);

        String expectedMessage =
                String.format(DeleteTaskCommand.MESSAGE_SUCCESS, toDeleteTask);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), TypicalManagers.getUserPrefs(),
                TypicalManagers.getUserLogin());
        expectedModel.deleteTask(toDeleteTask);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexList_throwsCommandException() {
        model.addEvent(TEST_EVENT_TWO);
        String wrongId = "ABC123";
        DeleteTaskCommand deleteCommand = new DeleteTaskCommand(wrongId);
        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_ID);
    }

    @Test
    public void execute_validIndexTaskList_commandTargetFeatureAccurate() throws CommandException {
        Todo toDeleteTask = TEST_TODO_TWO;
        model.addTodo(toDeleteTask);
        String toDeleteId = toDeleteTask.getTaskId();
        DeleteTaskCommand deleteCommand = new DeleteTaskCommand(toDeleteId);
        CommandResult commandResult = deleteCommand.execute(model);
        CommandTargetFeature commandTargetFeature = commandResult.getCommandTargetFeature();

        assertEquals(CommandTargetFeature.Tasks, commandTargetFeature);
    }
}
