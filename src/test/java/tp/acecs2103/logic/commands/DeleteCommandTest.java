package tp.acecs2103.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tp.acecs2103.testutil.Assert.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import tp.acecs2103.logic.commands.exceptions.CommandException;
import tp.acecs2103.model.Model;
import tp.acecs2103.model.ModelManager;
import tp.acecs2103.model.UserPrefs;
import tp.acecs2103.model.task.CustomizedDeadline;
import tp.acecs2103.model.task.Description;
import tp.acecs2103.model.task.IP;
import tp.acecs2103.model.task.Index;
import tp.acecs2103.model.task.Remark;
import tp.acecs2103.model.task.Task;
import tp.acecs2103.model.task.WeekNumber;
import tp.acecs2103.testutil.TypicalTasks;

class DeleteCommandTest {
    @Test
    public void execute_validIndex_success() {
        Model model = new ModelManager(TypicalTasks.getTypicalTaskList(), new UserPrefs());
        ModelManager expectedModel = new ModelManager(TypicalTasks.getTypicalTaskList(), new UserPrefs());

        Task task = new IP(new Index("0203"), new WeekNumber("2"), new Description("Test Task One"),
                null, new CustomizedDeadline("2020-09-10", LocalDate.of(2020, 9, 10)),
                 new Remark("no remark"), true, false);

        AddCommand addCommand = null;
        try {
            addCommand = new AddCommand(task);
            addCommand.execute(model);
        } catch (CommandException e) {
            e.printStackTrace();
        }

        Index index = new Index("0203");
        DeleteCommand deleteCommand = new DeleteCommand(index);
        CommandResult commandResult = null;
        try {
            commandResult = deleteCommand.execute(model);
        } catch (CommandException e) {
            e.printStackTrace();
        }

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_TASK_SUCCESS, task);
        assertEquals(expectedMessage, commandResult.getFeedbackToUser());
        assertEquals(expectedModel, model);
    }

    @Test
    public void execute_invalidIndexNonExistent_fail() {
        Model model = new ModelManager(TypicalTasks.getTypicalTaskList(), new UserPrefs());

        Index index = new Index("0203");
        DeleteCommand deleteCommand = new DeleteCommand(index);

        assertThrows(CommandException.class, () -> deleteCommand.execute(model));
    }

    @Test
    public void execute_invalidIndexDefaultTask_fail() {
        Model model = new ModelManager(TypicalTasks.getTypicalTaskList(), new UserPrefs());

        Index index = new Index("0101");
        DeleteCommand deleteCommand = new DeleteCommand(index);

        assertThrows(CommandException.class, () -> deleteCommand.execute(model));
    }
    @Test
    public void equal_null_returnTrue() {
        Index index = new Index("0101");

        DeleteCommand deleteCommand = new DeleteCommand(index);
        DeleteCommand deleteCommand1 = new DeleteCommand(index);

        assertTrue(deleteCommand.equals(deleteCommand1));
    }

    @Test
    public void equal_null_returnFalse() {
        Index index = new Index("0101");
        Index index1 = new Index("0102");

        DeleteCommand deleteCommand = new DeleteCommand(index);
        DeleteCommand deleteCommand1 = new DeleteCommand(index1);

        assertFalse(deleteCommand.equals(deleteCommand1));
        assertFalse(deleteCommand.equals(null));
        assertFalse(deleteCommand.equals("0101"));
    }
}
