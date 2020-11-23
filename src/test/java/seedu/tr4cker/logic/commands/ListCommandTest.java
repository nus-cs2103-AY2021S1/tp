package seedu.tr4cker.logic.commands;

import static seedu.tr4cker.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.tr4cker.testutil.TypicalTasks.getTypicalTr4cker;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.tr4cker.model.Model;
import seedu.tr4cker.model.ModelManager;
import seedu.tr4cker.model.UserPrefs;
import seedu.tr4cker.model.task.Task;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalTr4cker(), new UserPrefs());
        expectedModel = new ModelManager(model.getTr4cker(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        List<Task> expectedTasksToList = expectedModel.getFilteredPendingTaskList();

        String expectedDisplayListNames = "";

        for (int i = 0; i < expectedTasksToList.size(); i++) {
            expectedDisplayListNames += (i + 1) + ". " + expectedTasksToList.get(i).getName().toString() + "\n";
        }
        String expectedMessage = ListCommand.MESSAGE_SUCCESS + expectedDisplayListNames;
        assertCommandSuccess(new ListCommand(), model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        model.updateFilteredPendingTaskList(x -> true);
        List<Task> expectedTasksToList = expectedModel.getFilteredPendingTaskList();

        String expectedDisplayListNames = "";

        for (int i = 0; i < expectedTasksToList.size(); i++) {
            expectedDisplayListNames += (i + 1) + ". " + expectedTasksToList.get(i).getName().toString() + "\n";
        }
        String expectedMessage = ListCommand.MESSAGE_SUCCESS + expectedDisplayListNames;
        assertCommandSuccess(new ListCommand(), model, expectedMessage, expectedModel);
    }
}
