package tp.acecs2103.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static tp.acecs2103.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import tp.acecs2103.commons.core.Messages;
import tp.acecs2103.commons.util.AppUtil;
import tp.acecs2103.model.Model;
import tp.acecs2103.model.ModelManager;
import tp.acecs2103.model.UserPrefs;
import tp.acecs2103.model.task.WeekNumber;
import tp.acecs2103.testutil.TypicalTasks;

class HomeCommandTest {
    @Test
    public void execute_null_success() {
        Model model = new ModelManager(TypicalTasks.getTypicalTaskList(), new UserPrefs());
        ModelManager expectedModel = new ModelManager(TypicalTasks.getTypicalTaskList(), new UserPrefs());

        WeekNumber currentWeek = AppUtil.getCurrentWeekNumber();
        expectedModel.listTasks(currentWeek);

        HomeCommand homeCommand = new HomeCommand();
        homeCommand.execute(model);

        String expectedMessage = String.format(Messages.MESSAGE_WEEKLY_TASKS_LISTED, currentWeek.value)
                + "\n -->"
                + String.format(Messages.MESSAGE_TASKS_LISTED_OVERVIEW, model.getUiTaskList().size());

        assertCommandSuccess(homeCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals_null_returnFalse() {
        HomeCommand homeCommand = new HomeCommand();
        assertFalse(homeCommand.equals(null));
        assertFalse(homeCommand.equals("home"));
    }
}
