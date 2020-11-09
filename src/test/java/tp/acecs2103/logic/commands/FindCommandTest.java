package tp.acecs2103.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tp.acecs2103.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import tp.acecs2103.commons.core.Messages;
import tp.acecs2103.model.Model;
import tp.acecs2103.model.ModelManager;
import tp.acecs2103.model.UserPrefs;
import tp.acecs2103.testutil.TypicalTasks;

class FindCommandTest {
    @Test
    public void execute_keywordBigOne_success() {
        Model model = new ModelManager(TypicalTasks.getTypicalTaskList(), new UserPrefs());
        ModelManager expectedModel = new ModelManager(TypicalTasks.getTypicalTaskList(), new UserPrefs());
        FindCommand findCommand = new FindCommand("One");
        findCommand.execute(model);
        expectedModel.findTasks("One");

        String expectedMessage = "Search for tasks with keyword: " + "One" + " in description / remark.\n"
                + String.format(Messages.MESSAGE_TASKS_LISTED_OVERVIEW, model.getUiTaskList().size());

        assertCommandSuccess(findCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_keywordSmallOne_success() {
        Model model = new ModelManager(TypicalTasks.getTypicalTaskList(), new UserPrefs());
        ModelManager expectedModel = new ModelManager(TypicalTasks.getTypicalTaskList(), new UserPrefs());
        FindCommand findCommand = new FindCommand("one");
        findCommand.execute(model);
        expectedModel.findTasks("one");

        String expectedMessage = "Search for tasks with keyword: " + "one" + " in description / remark.\n"
                + String.format(Messages.MESSAGE_TASKS_LISTED_OVERVIEW, model.getUiTaskList().size());

        assertCommandSuccess(findCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_keywordRemark_success() {
        Model model = new ModelManager(TypicalTasks.getTypicalTaskList(), new UserPrefs());
        ModelManager expectedModel = new ModelManager(TypicalTasks.getTypicalTaskList(), new UserPrefs());
        FindCommand findCommand = new FindCommand("Remark");
        findCommand.execute(model);
        expectedModel.findTasks("Remark");

        String expectedMessage = "Search for tasks with keyword: " + "Remark" + " in description / remark.\n"
                + String.format(Messages.MESSAGE_TASKS_LISTED_OVERVIEW, model.getUiTaskList().size());

        assertCommandSuccess(findCommand, model, expectedMessage, expectedModel);
    }

    public void equal_null_returnTrue() {
        String keyword = "keyword";
        FindCommand findCommand = new FindCommand(keyword);
        FindCommand findCommand1 = new FindCommand(keyword);

        assertTrue(findCommand.equals(findCommand1));
    }

    @Test
    public void equal_null_returnFalse() {
        String keyword = "keyword";
        String keyword1 = "keyword1";

        FindCommand findCommand = new FindCommand(keyword);
        FindCommand findCommand1 = new FindCommand(keyword1);

        assertFalse(findCommand.equals(findCommand1));
        assertFalse(findCommand.equals(null));
        assertFalse(findCommand.equals("keyword"));
    }

}
