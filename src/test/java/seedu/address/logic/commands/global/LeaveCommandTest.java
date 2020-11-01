package seedu.address.logic.commands.global;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PROJECT;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.address.testutil.TypicalMainCatalogue.getTypicalMainCatalogue;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.project.Project;
import seedu.address.model.task.Task;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for
 * {@code StartProjectCommand}.
 */
public class LeaveCommandTest {

    private Model model = new ModelManager(getTypicalMainCatalogue(), new UserPrefs());

    @Test
    public void execute_onListLevel_doNothing() {
        LeaveCommand leaveCommand = new LeaveCommand();

        String expectedMessage = LeaveCommand.MESSAGE_LEAVE_SUCCESS;
        ModelManager expectedModel = new ModelManager(model.getProjectCatalogue(), new UserPrefs());
        assertCommandSuccess(leaveCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_onProjectPersonLevel_toListLevel() {
        Project projectToStart = model.getFilteredProjectList().get(INDEX_FIRST_PROJECT.getZeroBased());
        model.enter(projectToStart);
        LeaveCommand leaveCommand = new LeaveCommand();

        String expectedMessage = LeaveCommand.MESSAGE_LEAVE_SUCCESS;
        ModelManager expectedModel = new ModelManager(model.getProjectCatalogue(), new UserPrefs());
        assertCommandSuccess(leaveCommand, model, expectedMessage, expectedModel);

        Person personToStart = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        model.enter(personToStart);

        expectedMessage = LeaveCommand.MESSAGE_LEAVE_SUCCESS;
        expectedModel = new ModelManager(model.getProjectCatalogue(), new UserPrefs());
        assertCommandSuccess(leaveCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_onTaskLevel_toProjectLevel() {
        Project projectToStart = model.getFilteredProjectList().get(INDEX_FIRST_PROJECT.getZeroBased());
        model.enter(projectToStart);
        Task taskToStart = projectToStart.getFilteredSortedTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        model.enterTask(taskToStart);
        LeaveCommand leaveCommand = new LeaveCommand();

        String expectedMessage = LeaveCommand.MESSAGE_LEAVE_SUCCESS;
        ModelManager expectedModel = new ModelManager(model.getProjectCatalogue(), new UserPrefs());
        assertCommandSuccess(leaveCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        assertEquals(new LeaveCommand(), new LeaveCommand());
    }
}
