package tp.acecs2103.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tp.acecs2103.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import tp.acecs2103.logic.commands.exceptions.CommandException;
import tp.acecs2103.model.Model;
import tp.acecs2103.model.ModelManager;
import tp.acecs2103.model.UserPrefs;
import tp.acecs2103.model.task.Index;
import tp.acecs2103.model.task.Task;
import tp.acecs2103.testutil.TypicalTasks;



class UndoneCommandTest {
    @Test
    public void execute_validIndex_success() {
        Model model = new ModelManager(TypicalTasks.getTypicalTaskList(), new UserPrefs());

        Index index = new Index("0402");
        UndoneCommand undoneCommand = new UndoneCommand(index);
        Task task = model.getTaskList().getTask(index);
        task.markAsDone();

        CommandResult commandResult = null;
        try {
            commandResult = undoneCommand.execute(model);
        } catch (CommandException e) {
            e.printStackTrace();
        }

        String expectedMessage = String.format(UndoneCommand.MESSAGE_DONE_TASK_SUCCESS, task);
        assertEquals(expectedMessage, commandResult.getFeedbackToUser());
        assertTrue(!task.isDone());
    }

    @Test
    public void execute_invalidIndexNonExistent_fail() {
        Model model = new ModelManager(TypicalTasks.getTypicalTaskList(), new UserPrefs());

        Index index = new Index("0203");
        UndoneCommand doneCommand = new UndoneCommand(index);

        assertThrows(CommandException.class, () -> doneCommand.execute(model));
    }

    @Test
    public void execute_invalidIndexUndoneTask_fail() {
        Model model = new ModelManager(TypicalTasks.getTypicalTaskList(), new UserPrefs());

        Index index = new Index("0102");
        Task task = model.getTaskList().getTask(index);
        UndoneCommand undoneCommand = new UndoneCommand(index);

        assertThrows(CommandException.class, () -> undoneCommand.execute(model));
    }

    @Test
    public void equal_null_returnTrue() {
        Index index = new Index("0101");

        UndoneCommand doneCommand = new UndoneCommand(index);
        UndoneCommand doneCommand1 = new UndoneCommand(index);

        assertTrue(doneCommand.equals(doneCommand1));
    }

    @Test
    public void equal_null_returnFalse() {
        Index index = new Index("0101");
        Index index1 = new Index("0102");

        UndoneCommand doneCommand = new UndoneCommand(index);
        UndoneCommand doneCommand1 = new UndoneCommand(index1);

        assertFalse(doneCommand.equals(doneCommand1));
        assertFalse(doneCommand.equals(null));
        assertFalse(doneCommand.equals("0101"));
    }
}
