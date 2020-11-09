package tp.acecs2103.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tp.acecs2103.logic.commands.CommandTestUtil.assertCommandSuccess;
import static tp.acecs2103.testutil.Assert.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import tp.acecs2103.logic.commands.exceptions.CommandException;
import tp.acecs2103.model.Model;
import tp.acecs2103.model.ModelManager;
import tp.acecs2103.model.UserPrefs;
import tp.acecs2103.model.exceptions.InvalidTaskOperationException;
import tp.acecs2103.model.task.CustomizedDeadline;
import tp.acecs2103.model.task.Index;
import tp.acecs2103.model.task.Task;
import tp.acecs2103.testutil.TypicalTasks;

public class DeadlineCommandTest {
    @Test
    public void execute_beforeOfficialOverDue_success() {
        Model model = new ModelManager(TypicalTasks.getTypicalTaskList(), new UserPrefs());
        ModelManager expectedModel = new ModelManager(TypicalTasks.getTypicalTaskList(), new UserPrefs());

        Index index = new Index("0101");
        CustomizedDeadline customizedDeadline = new CustomizedDeadline("2020-10-01",
                LocalDate.parse("2020-10-01"));
        DeadlineCommand deadlineCommand = new DeadlineCommand(index, customizedDeadline);

        try {
            deadlineCommand.execute(model);
        } catch (CommandException e) {
            e.printStackTrace();
        }

        Task taskToEdit = expectedModel.getTaskList().getTask(index);

        try {
            taskToEdit.setDeadline(customizedDeadline);
        } catch (InvalidTaskOperationException e) {
            e.printStackTrace();
        }

        String expectedMessage = String.format(DeadlineCommand.MESSAGE_SUCCESS, index.value) + customizedDeadline.value;
        assertCommandSuccess(deadlineCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_beforeOfficialNotOverDue_success() {
        Model model = new ModelManager(TypicalTasks.getTypicalTaskList(), new UserPrefs());
        ModelManager expectedModel = new ModelManager(TypicalTasks.getTypicalTaskList(), new UserPrefs());

        Index index = new Index("0401");
        CustomizedDeadline customizedDeadline = new CustomizedDeadline("2021-10-01",
                LocalDate.parse("2021-10-01"));
        DeadlineCommand deadlineCommand = new DeadlineCommand(index, customizedDeadline);

        try {
            deadlineCommand.execute(model);
        } catch (CommandException e) {
            e.printStackTrace();
        }

        Task taskToEdit = expectedModel.getTaskList().getTask(index);

        try {
            taskToEdit.setDeadline(customizedDeadline);
        } catch (InvalidTaskOperationException e) {
            e.printStackTrace();
        }

        String expectedMessage = String.format(DeadlineCommand.MESSAGE_SUCCESS, index.value) + customizedDeadline.value;
        assertCommandSuccess(deadlineCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_afterOfficialOverDue_success() {
        Model model = new ModelManager(TypicalTasks.getTypicalTaskList(), new UserPrefs());
        ModelManager expectedModel = new ModelManager(TypicalTasks.getTypicalTaskList(), new UserPrefs());

        Index index = new Index("0101");
        CustomizedDeadline customizedDeadline = new CustomizedDeadline("2021-10-01",
                LocalDate.parse("2021-10-01"));
        DeadlineCommand deadlineCommand = new DeadlineCommand(index, customizedDeadline);

        try {
            deadlineCommand.execute(model);
        } catch (CommandException e) {
            e.printStackTrace();
        }

        Task taskToEdit = expectedModel.getTaskList().getTask(index);

        try {
            taskToEdit.setDeadline(customizedDeadline);
        } catch (InvalidTaskOperationException e) {
            e.printStackTrace();
        }

        String expectedMessage = String.format(DeadlineCommand.MESSAGE_SUCCESS, index.value) + customizedDeadline.value;
        assertCommandSuccess(deadlineCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_afterOfficialNotOverDue_fail() {
        Model model = new ModelManager(TypicalTasks.getTypicalTaskList(), new UserPrefs());
        Index index = new Index("0401");
        CustomizedDeadline customizedDeadline = new CustomizedDeadline("2021-11-01",
                LocalDate.parse("2021-11-01"));
        DeadlineCommand deadlineCommand = new DeadlineCommand(index, customizedDeadline);

        assertThrows(CommandException.class, () -> deadlineCommand.execute(model));
    }

    @Test
    public void execute_nonExistantTask_fail() {
        Model model = new ModelManager(TypicalTasks.getTypicalTaskList(), new UserPrefs());
        Index index = new Index("0501");
        CustomizedDeadline customizedDeadline = new CustomizedDeadline("2020-11-01",
                LocalDate.parse("2020-11-01"));
        DeadlineCommand deadlineCommand = new DeadlineCommand(index, customizedDeadline);

        assertThrows(CommandException.class, () -> deadlineCommand.execute(model));
    }


    @Test
    public void equals_null_returnTrue() {
        Index index = new Index("0101");
        CustomizedDeadline customizedDeadline = new CustomizedDeadline("2020-11-01",
                LocalDate.parse("2020-11-01"));
        DeadlineCommand deadlineCommand = new DeadlineCommand(index, customizedDeadline);
        DeadlineCommand deadlineCommand1 = new DeadlineCommand(index, customizedDeadline);
        assertTrue(deadlineCommand.equals(deadlineCommand1));
    }

    @Test
    public void equals_null_returnFalse() {
        Index index = new Index("0101");
        Index index1 = new Index("0102");
        CustomizedDeadline customizedDeadline = new CustomizedDeadline("2020-11-01",
                LocalDate.parse("2020-11-01"));
        CustomizedDeadline customizedDeadline1 = new CustomizedDeadline("2020-11-02",
                LocalDate.parse("2020-11-02"));
        DeadlineCommand deadlineCommand = new DeadlineCommand(index, customizedDeadline);
        DeadlineCommand deadlineCommand1 = new DeadlineCommand(index1, customizedDeadline);
        DeadlineCommand deadlineCommand2 = new DeadlineCommand(index, customizedDeadline1);
        assertFalse(deadlineCommand.equals(deadlineCommand1));
        assertFalse(deadlineCommand.equals(deadlineCommand2));
        assertFalse(deadlineCommand.equals(null));
        assertFalse(deadlineCommand.equals("deadlineCommand1"));
    }

    @Test
    public void gets_null_returnTrue() {
        Index index = new Index("0101");
        CustomizedDeadline customizedDeadline = new CustomizedDeadline("2020-11-01",
                LocalDate.parse("2020-11-01"));
        DeadlineCommand deadlineCommand = new DeadlineCommand(index, customizedDeadline);
        assertTrue(deadlineCommand.getNewDeadline().equals(customizedDeadline));
        assertTrue(deadlineCommand.getTargetIndex().equals(index));
    }
}
