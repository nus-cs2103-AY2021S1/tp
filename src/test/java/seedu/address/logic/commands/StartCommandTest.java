package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showProjectAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PROJECT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PROJECT;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PROJECT;
import static seedu.address.testutil.TypicalProjects.getTypicalMainCatalogue;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.project.Project;
import seedu.address.model.project.Status;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for
 * {@code StartCommand}.
 */
public class StartCommandTest {

    private Model model = new ModelManager(getTypicalMainCatalogue(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Project projectToStart = model.getFilteredProjectList().get(INDEX_FIRST_PROJECT.getZeroBased());
        StartCommand startCommand = new StartCommand(INDEX_FIRST_PROJECT);

        String expectedMessage = String.format(StartCommand.MESSAGE_START_PROJECT_SUCCESS, projectToStart);

        ModelManager expectedModel = new ModelManager(model.getProjectCatalogue(), new UserPrefs());
        expectedModel.enter(projectToStart);

        assertCommandSuccess(startCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredProjectList().size() + 1);
        StartCommand startCommand = new StartCommand(outOfBoundIndex);

        assertCommandFailure(startCommand, model, Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showProjectAtIndex(model, INDEX_FIRST_PROJECT);

        Project projectToStart = model.getFilteredProjectList().get(INDEX_FIRST_PROJECT.getZeroBased());
        StartCommand startCommand = new StartCommand(INDEX_FIRST_PROJECT);

        String expectedMessage = String.format(StartCommand.MESSAGE_START_PROJECT_SUCCESS, projectToStart);

        Model expectedModel = new ModelManager(model.getProjectCatalogue(), new UserPrefs());
        expectedModel.enter(projectToStart);
        showProjectAtIndex(expectedModel, INDEX_FIRST_PROJECT);

        assertCommandSuccess(startCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showProjectAtIndex(model, INDEX_FIRST_PROJECT);

        Index outOfBoundIndex = INDEX_SECOND_PROJECT;
        // ensures that outOfBoundIndex is still in bounds of main catalogue list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getProjectCatalogue().getProjectList().size());

        StartCommand startCommand = new StartCommand(outOfBoundIndex);

        assertCommandFailure(startCommand, model, Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidScope_throwsCommandException() {
        model.enter(model.getFilteredProjectList().get(INDEX_FIRST_PROJECT.getOneBased()));
        StartCommand startCommand = new StartCommand(INDEX_FIRST_PROJECT);
        assertCommandFailure(startCommand, model, String.format(Messages.MESSAGE_INVALID_SCOPE_COMMAND,
                Status.CATALOGUE, Status.PROJECT));

        startCommand = new StartCommand(INDEX_THIRD_PROJECT);
        assertCommandFailure(startCommand, model, Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        StartCommand startFirstCommand = new StartCommand(INDEX_FIRST_PROJECT);
        StartCommand startSecondCommand = new StartCommand(INDEX_SECOND_PROJECT);

        // same object -> returns true
        assertTrue(startFirstCommand.equals(startFirstCommand));

        // same values -> returns true
        StartCommand startFirstCommandCopy = new StartCommand(INDEX_FIRST_PROJECT);
        assertTrue(startFirstCommand.equals(startFirstCommandCopy));

        // different types -> returns false
        assertFalse(startFirstCommand.equals(1));

        // null -> returns false
        assertFalse(startFirstCommand.equals(null));

        // different project -> returns false
        assertFalse(startFirstCommand.equals(startSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoProject(Model model) {
        model.updateFilteredProjectList(p -> false);

        assertTrue(model.getFilteredProjectList().isEmpty());
    }
}
