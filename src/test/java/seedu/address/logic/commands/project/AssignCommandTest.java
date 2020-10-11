package seedu.address.logic.commands.project;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PROJECT;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PROJECT;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalProjects.getTypicalMainCatalogue;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.catalogue.DeleteCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.project.Participation;
import seedu.address.model.project.Project;
import seedu.address.model.task.Task;

import java.util.HashMap;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for
 * {@code DeleteCommand}.
 */
public class AssignCommandTest {

    private Model model = new ModelManager(getTypicalMainCatalogue(), new UserPrefs());

    @Test
    public void execute_validIndexValidPerson_success() {
        Project project = model.getFilteredProjectList().get(INDEX_FIRST_PROJECT.getZeroBased());
        model.enter(project);
        project.addParticipation(ALICE);
        ModelManager expectedModel = new ModelManager(model.getProjectCatalogue(), new UserPrefs());

        Task taskToAssign = project.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        Participation assignee = project.getParticipation(ALICE.getPersonName().fullPersonName);
        AssignCommand assignCommand = new AssignCommand(INDEX_FIRST_TASK, ALICE.getPersonName().fullPersonName);


        String expectedMessage = String.format(AssignCommand.MESSAGE_ASSIGN_TASK_SUCCESS, taskToAssign, assignee);

        Project projectCopy = new Project(project.getProjectName(), project.getDeadline(), project.getRepoUrl(),
                project.getProjectDescription(), project.getProjectTags(), new HashMap<>(), project.getTasks());
        projectCopy.addParticipation(ALICE);
        // TODO: After refining Participation getters and setters this part can be done fully via getters
        expectedModel.setProject(project, projectCopy);
        expectedModel.enter(projectCopy);
        expectedModel.getFilteredProjectList().get(INDEX_FIRST_PROJECT.getZeroBased())
                .getParticipation(ALICE.getPersonName().fullPersonName).addTask(taskToAssign);

        assertCommandSuccess(assignCommand, model, expectedMessage, expectedModel);
    }

    // TODO: May add test cases for filtered/unfiltered list of tasks after filters are implemented

    @Test
    public void execute_invalidIndexValidPerson_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredProjectList().size() + 1);
        Project project = model.getFilteredProjectList().get(INDEX_FIRST_PROJECT.getZeroBased());
        model.enter(project);
        project.addParticipation(ALICE);
        AssignCommand assignCommand = new AssignCommand(outOfBoundIndex, ALICE.getPersonName().fullPersonName);

        assertCommandFailure(assignCommand, model, Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexInvalidPerson_throwsCommandException() {
        Project project = model.getFilteredProjectList().get(INDEX_FIRST_PROJECT.getZeroBased());
        model.enter(project);
        AssignCommand assignCommand = new AssignCommand(INDEX_FIRST_PROJECT, ALICE.getPersonName().fullPersonName);

        assertCommandFailure(assignCommand, model,
                String.format(Messages.MESSAGE_MEMBER_NOT_PRESENT, ALICE.getPersonName().fullPersonName));
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
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoProject(Model model) {
        model.updateFilteredProjectList(p -> false);

        assertTrue(model.getFilteredProjectList().isEmpty());
    }
}
