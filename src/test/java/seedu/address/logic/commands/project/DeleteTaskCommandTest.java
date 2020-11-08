package seedu.address.logic.commands.project;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PROJECT;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_TASK;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalProjects.getTypicalMainCatalogue;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.participation.Participation;
import seedu.address.model.project.Project;
import seedu.address.model.task.Task;
import seedu.address.testutil.ProjectBuilder;

class DeleteTaskCommandTest {
    private Model model = new ModelManager(getTypicalMainCatalogue(), new UserPrefs());
    private Project project = model.getFilteredProjectList()
            .get(INDEX_FIRST_PROJECT.getZeroBased()).addParticipation(ALICE);
    // now inside the first project, there is a participant Alice
    private Participation aliceParticipation = project.getParticipation(ALICE.getGitUserNameString());

    @Test
    void execute_invalidIndexUnfilteredTaskList_throwsCommandException() {
        model.enter(project);

        Index outOfBoundIndex = Index.fromOneBased(project.getFilteredSortedTaskList().size() + 1);
        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(outOfBoundIndex);

        assertCommandFailure(deleteTaskCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    void execute_validIndexUnfilteredTaskList_success() {
        model.enter(project);
        Task taskToDelete = project.getFilteredSortedTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(INDEX_FIRST_TASK);

        // Assign the taskToDelete to Alice so that
        // we can check whether DeleteTaskCommand will remove the task in the participation
        aliceParticipation.addTask(taskToDelete);
        taskToDelete.addAssignee(ALICE.getGitUserNameString());

        String expectedMessage = String.format(DeleteTaskCommand.MESSAGE_DELETE_TASK_SUCCESS, taskToDelete);

        ModelManager expectedModel = new ModelManager(model.getProjectCatalogue(), new UserPrefs());
        Project expectedProject = ProjectBuilder.makeCopy(project);
        expectedModel.setProject(project, expectedProject);
        expectedProject.deleteTask(taskToDelete);

        assertCommandSuccess(deleteTaskCommand, model, expectedMessage, expectedModel);
    }


    @Test
    public void execute_invalidIndexFilteredTaskList_throwsCommandException() {
        model.enter(project);

        project.updateTaskFilter(task -> true);
        Task taskToDelete = project.getFilteredSortedTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        // Update the task filter inside the project so that the taskToDelete is the only task shown
        project.updateTaskFilter(task -> task.getTaskName().contains(taskToDelete.getTaskName()));
        Index outOfBoundIndex = Index.fromOneBased(project.getFilteredSortedTaskList().size() + 1);
        // ensures that outOfBoundIndex is still in bounds of the whole task list
        assertTrue(outOfBoundIndex.getZeroBased() < project.getTasks().size());

        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(outOfBoundIndex);

        assertCommandFailure(deleteTaskCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredTaskList_success() {
        model.enter(project);

        project.updateTaskFilter(task -> true);
        Task taskToDelete = project.getFilteredSortedTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        // Update the task filter inside the project so that the taskToDelete is the only task shown
        project.updateTaskFilter(task -> task.getTaskName().contains(taskToDelete.getTaskName()));
        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(INDEX_FIRST_TASK);

        // Assign the taskToDelete to Alice so that
        // we can check whether DeleteTaskCommand will remove the task in the participation
        aliceParticipation.addTask(taskToDelete);
        taskToDelete.addAssignee(ALICE.getGitUserNameString());

        String expectedMessage = String.format(DeleteTaskCommand.MESSAGE_DELETE_TASK_SUCCESS, taskToDelete);

        Model expectedModel = new ModelManager(model.getProjectCatalogue(), new UserPrefs());
        Project expectedProject = ProjectBuilder.makeCopy(project);
        expectedModel.setProject(project, expectedProject);
        expectedProject.deleteTask(taskToDelete);

        assertCommandSuccess(deleteTaskCommand, model, expectedMessage, expectedModel);
    }

    /**
     * Deletes a task that is currently displayed.
     */
    @Test
    public void execute_deletingTaskToBeDisplayed_resetTaskDashboard() {
        Model newModel = new ModelManager(getTypicalMainCatalogue(), new UserPrefs());
        Project project = newModel.getFilteredProjectList().get(INDEX_FIRST_PROJECT.getZeroBased());

        project.updateTaskFilter(task -> true);
        Task taskToDelete = project.getFilteredSortedTaskList().get(INDEX_FIRST_TASK.getZeroBased());

        newModel.enter(project);
        newModel.enter(taskToDelete);

        try {
            new DeleteTaskCommand(INDEX_FIRST_TASK).execute(newModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of the command should not fail.", ce);
        }

        assertTrue(newModel.getTaskToBeDisplayedOnDashboard().isEmpty()
                && newModel.getProjectToBeDisplayedOnDashboard().get().getTaskOnView().isEmpty());
    }

    /**
     * Deletes a task that is currently not displayed.
     */
    @Test
    public void execute_deletingTaskNotToBeDisplayed_noChangesToTaskDashboard() {
        Model newModel = new ModelManager(getTypicalMainCatalogue(), new UserPrefs());
        Project project = newModel.getFilteredProjectList().get(INDEX_FIRST_PROJECT.getZeroBased());

        project.updateTaskFilter(task -> true);
        Task task = project.getFilteredSortedTaskList().get(INDEX_FIRST_TASK.getZeroBased());

        newModel.enter(project);
        newModel.enter(task);

        try {
            new DeleteTaskCommand(INDEX_SECOND_TASK).execute(newModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of the command should not fail.", ce);
        }

        assertTrue(newModel.getTaskToBeDisplayedOnDashboard().isPresent());
    }

    @Test
    public void equals() {
        DeleteTaskCommand deleteFirstCommand = new DeleteTaskCommand(INDEX_FIRST_TASK);
        DeleteTaskCommand deleteSecondCommand = new DeleteTaskCommand(INDEX_SECOND_TASK);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteTaskCommand deleteFirstCommandCopy = new DeleteTaskCommand(INDEX_FIRST_TASK);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different task -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }
}
