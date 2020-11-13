package seedu.address.logic.commands.global;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showProjectAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PROJECT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PROJECT;
import static seedu.address.testutil.TypicalProjects.getTypicalMainCatalogue;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.project.Project;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model model = new ModelManager(getTypicalMainCatalogue(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Project projectToDelete = model.getFilteredProjectList().get(INDEX_FIRST_PROJECT.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_PROJECT);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PROJECT_SUCCESS, projectToDelete);

        ModelManager expectedModel = new ModelManager(model.getProjectCatalogue(), new UserPrefs());
        expectedModel.deleteProject(projectToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredProjectList().size() + 1);
        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showProjectAtIndex(model, INDEX_FIRST_PROJECT);

        Project projectToDelete = model.getFilteredProjectList().get(INDEX_FIRST_PROJECT.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_PROJECT);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PROJECT_SUCCESS, projectToDelete);

        Model expectedModel = new ModelManager(model.getProjectCatalogue(), new UserPrefs());
        expectedModel.deleteProject(projectToDelete);
        showNoProject(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showProjectAtIndex(model, INDEX_FIRST_PROJECT);

        Index outOfBoundIndex = INDEX_SECOND_PROJECT;
        // ensures that outOfBoundIndex is still in bounds of main catalogue list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getProjectCatalogue().getProjectList().size());

        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteCommand deleteFirstCommand = new DeleteCommand(INDEX_FIRST_PROJECT);
        DeleteCommand deleteSecondCommand = new DeleteCommand(INDEX_SECOND_PROJECT);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(INDEX_FIRST_PROJECT);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different project -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }


    /**
     * Deletes a project is currently displayed.
     */
    @Test
    public void execute_deletingProjectToBeDisplayed_resetProjectDashboard() {
        Model newModel = new ModelManager(getTypicalMainCatalogue(), new UserPrefs());
        Index indexLastProject = Index.fromOneBased(newModel.getFilteredProjectList().size());
        Project lastProject = newModel.getFilteredProjectList().get(indexLastProject.getZeroBased());

        newModel.enter(lastProject);

        try {
            new DeleteCommand(indexLastProject).execute(newModel);
        } catch (CommandException e) {
            assertFalse(true);
        }

        assertTrue(newModel.getProjectToBeDisplayedOnDashboard().isEmpty());
    }

    /**
     * Deletes a project is currently not displayed.
     */
    @Test
    public void execute_deletingProjectNotToBeDisplayed_noChangesToProjectDashboard() {
        Model newModel = new ModelManager(getTypicalMainCatalogue(), new UserPrefs());
        Index indexLastProject = Index.fromOneBased(newModel.getFilteredProjectList().size());
        Project lastProject = newModel.getFilteredProjectList().get(indexLastProject.getZeroBased());

        newModel.enter(lastProject);

        try {
            new DeleteCommand(INDEX_FIRST_PROJECT).execute(newModel);
        } catch (CommandException e) {
            assertFalse(true);
        }

        assertTrue(newModel.getProjectToBeDisplayedOnDashboard().isPresent());
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoProject(Model model) {
        model.updateFilteredProjectList(p -> false);

        assertTrue(model.getFilteredProjectList().isEmpty());
    }
}
