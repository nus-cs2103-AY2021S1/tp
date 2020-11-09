package tp.acecs2103.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import tp.acecs2103.logic.commands.exceptions.CommandException;
import tp.acecs2103.model.Model;
import tp.acecs2103.model.ModelManager;
import tp.acecs2103.model.UserPrefs;
import tp.acecs2103.model.task.Admin;
import tp.acecs2103.model.task.CustomizedDeadline;
import tp.acecs2103.model.task.Description;
import tp.acecs2103.model.task.IP;
import tp.acecs2103.model.task.Index;
import tp.acecs2103.model.task.OfficialDeadline;
import tp.acecs2103.model.task.Remark;
import tp.acecs2103.model.task.TP;
import tp.acecs2103.model.task.Task;
import tp.acecs2103.model.task.Topic;
import tp.acecs2103.model.task.WeekNumber;
import tp.acecs2103.testutil.TypicalTasks;

class AddCommandTest {

    @Test
    public void execute_newIpTask_success() {
        Model model = new ModelManager(TypicalTasks.getTypicalTaskList(), new UserPrefs());
        ModelManager expectedModel = new ModelManager(TypicalTasks.getTypicalTaskList(), new UserPrefs());

        Task task = new IP(new Index("0210"), new WeekNumber("2"), new Description("Test Task One"),
                new OfficialDeadline("2020-09-10", LocalDate.of(2020, 9, 10)),
                null, new Remark("no remark"), false, false);

        AddCommand addCommand = null;
        CommandResult commandResult = null;
        try {
            addCommand = new AddCommand(task);
            commandResult = addCommand.execute(model);
        } catch (CommandException e) {
            e.printStackTrace();
        }

        expectedModel.addTask(task);

        String expectedMessage = String.format(AddCommand.MESSAGE_SUCCESS, task);
        assertEquals(expectedMessage, commandResult.getFeedbackToUser());
        assertEquals(expectedModel, model);
    }

    @Test
    public void execute_newTpTask_success() {
        Model model = new ModelManager(TypicalTasks.getTypicalTaskList(), new UserPrefs());
        ModelManager expectedModel = new ModelManager(TypicalTasks.getTypicalTaskList(), new UserPrefs());

        Task task = new TP(new Index("0203"), new WeekNumber("2"), new Description("Test Task One"),
                new OfficialDeadline("2020-09-10", LocalDate.of(2020, 9, 10)),
                null, new Remark("no remark"), false, false);

        AddCommand addCommand = null;
        CommandResult commandResult = null;
        try {
            addCommand = new AddCommand(task);
            commandResult = addCommand.execute(model);
        } catch (CommandException e) {
            e.printStackTrace();
        }

        expectedModel.addTask(task);

        String expectedMessage = String.format(AddCommand.MESSAGE_SUCCESS, task);
        assertEquals(expectedMessage, commandResult.getFeedbackToUser());
        assertEquals(expectedModel, model);
    }

    @Test
    public void execute_newAdminTask_success() {
        Model model = new ModelManager(TypicalTasks.getTypicalTaskList(), new UserPrefs());
        ModelManager expectedModel = new ModelManager(TypicalTasks.getTypicalTaskList(), new UserPrefs());

        Task task = new Admin(new Index("0210"), new WeekNumber("2"), new Description("Test Task One"),
                new OfficialDeadline("2020-09-10", LocalDate.of(2020, 9, 10)),
                null, new Remark("no remark"), false, false);

        AddCommand addCommand = null;
        CommandResult commandResult = null;
        try {
            addCommand = new AddCommand(task);
            commandResult = addCommand.execute(model);
        } catch (CommandException e) {
            e.printStackTrace();
        }

        expectedModel.addTask(task);

        String expectedMessage = String.format(AddCommand.MESSAGE_SUCCESS, task);
        assertEquals(expectedMessage, commandResult.getFeedbackToUser());
        assertEquals(expectedModel, model);
    }

    @Test
    public void execute_newTopicTask_success() {
        Model model = new ModelManager(TypicalTasks.getTypicalTaskList(), new UserPrefs());
        ModelManager expectedModel = new ModelManager(TypicalTasks.getTypicalTaskList(), new UserPrefs());

        Task task = new Topic(new Index("0210"), new WeekNumber("2"), new Description("Test Task One"),
                new OfficialDeadline("2020-09-10", LocalDate.of(2020, 9, 10)),
                null, new Remark("no remark"), false, false);

        AddCommand addCommand = null;
        CommandResult commandResult = null;
        try {
            addCommand = new AddCommand(task);
            commandResult = addCommand.execute(model);
        } catch (CommandException e) {
            e.printStackTrace();
        }

        expectedModel.addTask(task);

        String expectedMessage = String.format(AddCommand.MESSAGE_SUCCESS, task);
        assertEquals(expectedMessage, commandResult.getFeedbackToUser());
        assertEquals(expectedModel, model);
    }

    @Test
    public void execute_inconsistentIndex_fail() {
        Model model = new ModelManager(TypicalTasks.getTypicalTaskList(), new UserPrefs());
        ModelManager expectedModel = new ModelManager(TypicalTasks.getTypicalTaskList(), new UserPrefs());

        Task task = new IP(new Index("0203"), new WeekNumber("1"), new Description("Test Task One"),
                new OfficialDeadline("2020-09-10", LocalDate.of(2020, 9, 10)),
                null, new Remark("no remark"), false, false);

        assertThrows(CommandException.class, () -> new AddCommand(task).execute(model));
    }

    @Test
    public void execute_existingTask_fail() {
        Model model = new ModelManager(TypicalTasks.getTypicalTaskList(), new UserPrefs());
        ModelManager expectedModel = new ModelManager(TypicalTasks.getTypicalTaskList(), new UserPrefs());

        Task task = new IP(new Index("0101"), new WeekNumber("1"), new Description("Test Task One"),
                new OfficialDeadline("2020-09-10", LocalDate.of(2020, 9, 10)),
                null, new Remark("no remark"), false, false);

        assertThrows(CommandException.class, () -> new AddCommand(task).execute(model));
    }

    @Test
    public void execute_missingDeadline_fail() {
        Model model = new ModelManager(TypicalTasks.getTypicalTaskList(), new UserPrefs());
        ModelManager expectedModel = new ModelManager(TypicalTasks.getTypicalTaskList(), new UserPrefs());

        Task task = new IP(new Index("0203"), new WeekNumber("2"), new Description("Test Task One"),
                new OfficialDeadline("2020-09-10", LocalDate.of(2020, 9, 10)),
                null, new Remark("no remark"), true, false);
        Task task2 = new IP(new Index("0203"), new WeekNumber("2"), new Description("Test Task One"),
                null, new CustomizedDeadline("2020-09-10",
                LocalDate.of(2020, 9, 10)), new Remark("no remark"),
                false, false);

        assertThrows(CommandException.class, () -> new AddCommand(task).execute(model));
        assertThrows(CommandException.class, () -> new AddCommand(task2).execute(model));
    }

    @Test
    public void execute_extraDeadline_fail() {
        Model model = new ModelManager(TypicalTasks.getTypicalTaskList(), new UserPrefs());
        ModelManager expectedModel = new ModelManager(TypicalTasks.getTypicalTaskList(), new UserPrefs());

        Task task = new IP(new Index("0203"), new WeekNumber("2"), new Description("Test Task One"),
                new OfficialDeadline("2020-09-10", LocalDate.of(2020, 9, 10)),
                new CustomizedDeadline("2020-09-10",
                        LocalDate.of(2020, 9, 10)), new Remark("no remark"),
                true, false);

        assertThrows(CommandException.class, () -> new AddCommand(task).execute(model));
    }

    @Test
    public void equals_null_returnTrue() {
        Task task = new Admin(new Index("0203"), new WeekNumber("2"), new Description("Test Task One"),
                new OfficialDeadline("2020-09-10", LocalDate.of(2020, 9, 10)),
                null, new Remark("no remark"), false, false);
        AddCommand addCommand = null;
        AddCommand addCommand1 = null;
        try {
            addCommand = new AddCommand(task);
            addCommand1 = new AddCommand(task);
        } catch (CommandException e) {
            e.printStackTrace();
        }

        assertTrue(addCommand.equals(addCommand1));
    }

    @Test
    public void equals_null_returnFalse() {
        Task task = new IP(new Index("0203"), new WeekNumber("2"), new Description("Test Task One"),
                new OfficialDeadline("2020-09-10", LocalDate.of(2020, 9, 10)),
                null, new Remark("no remark"), false, false);
        Task task1 = new TP(new Index("0204"), new WeekNumber("2"), new Description("Test Task One"),
                new OfficialDeadline("2020-09-10", LocalDate.of(2020, 9, 10)),
                null, new Remark("no remark"), false, false);
        Task task2 = new Admin(new Index("0205"), new WeekNumber("2"), new Description("Test Task"),
                new OfficialDeadline("2020-09-10", LocalDate.of(2020, 9, 10)),
                null, new Remark("no remark"), false, false);
        AddCommand addCommand = null;
        AddCommand addCommand1 = null;
        AddCommand addCommand2 = null;
        try {
            addCommand = new AddCommand(task);
            addCommand1 = new AddCommand(task1);
            addCommand2 = new AddCommand(task2);
        } catch (CommandException e) {
            e.printStackTrace();
        }
        assertFalse(addCommand.equals(addCommand1));
        assertFalse(addCommand.equals(addCommand2));
    }

    @Test
    public void get_nullTask() {
        Task task = new IP(new Index("0203"), new WeekNumber("2"), new Description("Test Task One"),
                new OfficialDeadline("2020-09-10", LocalDate.of(2020, 9, 10)),
                null, new Remark("no remark"), false, false);
        AddCommand addCommand = null;
        try {
            addCommand = new AddCommand(task);
        } catch (CommandException e) {
            e.printStackTrace();

            assertTrue(task.equals(addCommand.getTaskToAdd()));
        }
    }
}
