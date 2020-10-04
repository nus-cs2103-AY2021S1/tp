package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PROJECT;
import static seedu.address.testutil.TypicalProjects.getTypicalMainCatalogue;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.project.Project;
import seedu.address.model.project.Status;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for
 * {@code StartCommand}.
 */
public class LeaveCommandTest {

    private Model model = new ModelManager(getTypicalMainCatalogue(), new UserPrefs());

    @Test
    public void execute_inProjectScope_success() {
        Project projectToStart = model.getFilteredProjectList().get(INDEX_FIRST_PROJECT.getZeroBased());
        model.enter(projectToStart);
        LeaveCommand leaveCommand = new LeaveCommand();

        String expectedMessage = LeaveCommand.MESSAGE_LEAVE_SUCCESS;

        ModelManager expectedModel = new ModelManager(model.getProjectCatalogue(), new UserPrefs());

        assertCommandSuccess(leaveCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_inCatalogueScope_throwsCommandException() {
        LeaveCommand leaveCommand = new LeaveCommand();
        assertCommandFailure(leaveCommand, model, String.format(Messages.MESSAGE_INVALID_SCOPE_COMMAND,
                Status.PROJECT, Status.CATALOGUE));
    }

    @Test
    public void equals() {
        assertEquals(new LeaveCommand(), new LeaveCommand());
    }
}
