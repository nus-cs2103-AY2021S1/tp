package tp.acecs2103.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tp.acecs2103.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import tp.acecs2103.commons.core.Messages;
import tp.acecs2103.model.Model;
import tp.acecs2103.model.ModelManager;
import tp.acecs2103.model.UserPrefs;
import tp.acecs2103.model.task.WeekNumber;
import tp.acecs2103.testutil.TypicalTasks;

class ListCommandTest {
    @Test
    public void execute_validWeekLower_success() {
        Model model = new ModelManager(TypicalTasks.getTypicalTaskList(), new UserPrefs());
        ModelManager expectedModel = new ModelManager(TypicalTasks.getTypicalTaskList(), new UserPrefs());

        WeekNumber weekNumber = new WeekNumber("1");
        ListCommand listCommand = new ListCommand(weekNumber);
        listCommand.execute(model);

        expectedModel.listTasks(weekNumber);

        String expectedMessage = ListCommand.MESSAGE_SUCCESS + weekNumber.value + "\n"
                + String.format(Messages.MESSAGE_TASKS_LISTED_OVERVIEW, model.getUiTaskList().size());

        assertCommandSuccess(listCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validWeekMiddle_success() {
        Model model = new ModelManager(TypicalTasks.getTypicalTaskList(), new UserPrefs());
        ModelManager expectedModel = new ModelManager(TypicalTasks.getTypicalTaskList(), new UserPrefs());

        WeekNumber weekNumber = new WeekNumber("7");
        ListCommand listCommand = new ListCommand(weekNumber);
        listCommand.execute(model);

        expectedModel.listTasks(weekNumber);

        String expectedMessage = ListCommand.MESSAGE_SUCCESS + weekNumber.value + "\n"
                + String.format(Messages.MESSAGE_TASKS_LISTED_OVERVIEW, model.getUiTaskList().size());

        assertCommandSuccess(listCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validWeekUpper_success() {
        Model model = new ModelManager(TypicalTasks.getTypicalTaskList(), new UserPrefs());
        ModelManager expectedModel = new ModelManager(TypicalTasks.getTypicalTaskList(), new UserPrefs());

        WeekNumber weekNumber = new WeekNumber("13");
        ListCommand listCommand = new ListCommand(weekNumber);
        listCommand.execute(model);

        expectedModel.listTasks(weekNumber);

        String expectedMessage = ListCommand.MESSAGE_SUCCESS + weekNumber.value + "\n"
                + String.format(Messages.MESSAGE_TASKS_LISTED_OVERVIEW, model.getUiTaskList().size());

        assertCommandSuccess(listCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equal_null_returnFalse() {
        WeekNumber weekNumber = new WeekNumber("13");
        WeekNumber weekNumber1 = new WeekNumber("11");
        ListCommand listCommand = new ListCommand(weekNumber);
        ListCommand listCommand1 = new ListCommand(weekNumber1);
        assertFalse(listCommand.equals(null));
        assertFalse(listCommand.equals("1"));
        assertFalse(listCommand1.equals(listCommand));
    }

    @Test
    public void equal_null_returnTrue() {
        WeekNumber weekNumber = new WeekNumber("13");
        ListCommand listCommand = new ListCommand(weekNumber);
        ListCommand listCommand1 = new ListCommand(weekNumber);
        assertTrue(listCommand.equals(listCommand1));
    }
}
