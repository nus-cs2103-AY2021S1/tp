package seedu.address.logic.commands.project;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PROJECT;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_TASK;
import static seedu.address.testutil.TypicalProjects.getTypicalMainCatalogue;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.project.Project;
import seedu.address.model.task.Task;
import seedu.address.model.util.SampleDataUtil;
import seedu.address.testutil.EditTaskDescriptorBuilder;
import seedu.address.testutil.TaskBuilder;
import seedu.address.testutil.TypicalTasks;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for EditCommand.
 */
public class EditTaskCommandTest {

    private Model model = new ModelManager(getTypicalMainCatalogue(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() throws ParseException {

        Task editedTask = new TaskBuilder().build();
        Project project = model.getFilteredProjectList().get(INDEX_FIRST_PROJECT.getZeroBased());
        model.enter(project);
        ModelManager expectedModel = new ModelManager(model.getProjectCatalogue(), new UserPrefs());

        EditTaskCommand.EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder(editedTask).build();
        EditTaskCommand editTaskCommand = new EditTaskCommand(ParserUtil.parseIndex("1"), descriptor);


        String expectedMessage = String.format(EditTaskCommand.MESSAGE_EDIT_TASK_SUCCESS, editedTask);

        Project projectCopy = new Project(project.getProjectName(), project.getDeadline(),
                project.getRepoUrl(), project.getProjectDescription(), project.getProjectTags(),
                new HashMap<>(), project.getTasks());
        projectCopy.deleteTask(projectCopy.getFilteredSortedTaskList().get(0));
        projectCopy.addTask(editedTask);
        expectedModel.setProject(project, projectCopy);
        expectedModel.enter(projectCopy);
        expectedModel.getFilteredProjectList().get(INDEX_FIRST_PROJECT.getZeroBased())
                .deleteTask(project.getFilteredSortedTaskList().get(0));
        expectedModel.getFilteredProjectList().get(INDEX_FIRST_PROJECT.getZeroBased())
                .addTask(editedTask);

        assertCommandSuccess(editTaskCommand, model, expectedMessage, expectedModel);

    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() throws ParseException {
        Task editedTask = new TaskBuilder(SampleDataUtil.generateTask(SampleDataUtil.getTask1()))
                .withTaskName("Changed name").withTaskDescription("Changed Description").build();
        Project project = model.getFilteredProjectList().get(INDEX_FIRST_PROJECT.getZeroBased());
        model.enter(project);
        ModelManager expectedModel = new ModelManager(model.getProjectCatalogue(), new UserPrefs());

        EditTaskCommand.EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder(editedTask).build();
        EditTaskCommand editTaskCommand = new EditTaskCommand(ParserUtil.parseIndex("1"), descriptor);


        String expectedMessage = String.format(EditTaskCommand.MESSAGE_EDIT_TASK_SUCCESS, editedTask);

        Project projectCopy = new Project(project.getProjectName(), project.getDeadline(),
                project.getRepoUrl(), project.getProjectDescription(), project.getProjectTags(),
                new HashMap<>(), project.getTasks());
        projectCopy.deleteTask(projectCopy.getFilteredSortedTaskList().get(0));
        projectCopy.addTask(editedTask);
        expectedModel.setProject(project, projectCopy);
        expectedModel.enter(projectCopy);
        expectedModel.getFilteredProjectList().get(INDEX_FIRST_PROJECT.getZeroBased())
                .deleteTask(project.getFilteredSortedTaskList().get(0));
        expectedModel.getFilteredProjectList().get(INDEX_FIRST_PROJECT.getZeroBased())
                .addTask(editedTask);

        assertCommandSuccess(editTaskCommand, model, expectedMessage, expectedModel);

    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() throws ParseException {

        Project project = model.getFilteredProjectList().get(INDEX_FIRST_PROJECT.getZeroBased());
        model.enter(project);
        ModelManager expectedModel = new ModelManager(model.getProjectCatalogue(), new UserPrefs());
        Task editedTask = project.getFilteredSortedTaskList().get(0);

        EditTaskCommand editTaskCommand = new EditTaskCommand(ParserUtil.parseIndex("1"),
                new EditTaskCommand.EditTaskDescriptor());

        Project projectCopy = new Project(project.getProjectName(), project.getDeadline(),
                project.getRepoUrl(), project.getProjectDescription(), project.getProjectTags(),
                new HashMap<>(), project.getTasks());
        expectedModel.setProject(project, projectCopy);
        expectedModel.enter(projectCopy);

        assertCommandFailure(editTaskCommand, model, EditTaskCommand.MESSAGE_NOT_EDITED);

    }

    @Test
    public void execute_duplicateTaskUnfilteredList_failure() throws ParseException {
        Project project = model.getFilteredProjectList().get(INDEX_FIRST_PROJECT.getZeroBased());
        model.enter(project);
        Task editedTask = project.getFilteredSortedTaskList().get(1);
        EditTaskCommand.EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder(editedTask).build();
        EditTaskCommand editTaskCommand = new EditTaskCommand(ParserUtil.parseIndex("1"), descriptor);

        assertCommandFailure(editTaskCommand, model, EditTaskCommand.MESSAGE_DUPLICATE_TASK);
    }

    @Test
    public void execute_duplicateTaskFilteredList_failure() throws ParseException {
        Project project = model.getFilteredProjectList().get(INDEX_FIRST_PROJECT.getZeroBased());
        model.enter(project);
        project.addTask(SampleDataUtil.generateTask(SampleDataUtil.getTask4()));
        project.addTask(SampleDataUtil.generateTask(SampleDataUtil.getTask6()));
        project.updateTaskFilter(x -> x.getTaskName().contains("Write"));
        Task editedTask = project.getFilteredSortedTaskList().get(0);
        EditTaskCommand.EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder(editedTask).build();
        EditTaskCommand editTaskCommand = new EditTaskCommand(ParserUtil.parseIndex("2"), descriptor);

        assertCommandFailure(editTaskCommand, model, EditTaskCommand.MESSAGE_DUPLICATE_TASK);
    }

    @Test
    public void execute_invalidTaskIndexUnfilteredList_failure() {
        Project project = model.getFilteredProjectList().get(INDEX_FIRST_PROJECT.getZeroBased());
        model.enter(project);
        Index outOfBoundIndex = Index.fromOneBased(project.getTasks().size() + 1);
        EditTaskCommand.EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder().withTaskName(
                TypicalTasks.TASK_A.getTaskName()).build();
        EditTaskCommand editTaskCommand = new EditTaskCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editTaskCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    /**
     * Edits a task is currently displayed.
     */
    @Test
    public void execute_editingTaskToBeDisplayed_resetTaskDashboard() {
        Model newModel = new ModelManager(getTypicalMainCatalogue(), new UserPrefs());
        Project project = newModel.getFilteredProjectList().get(INDEX_FIRST_PROJECT.getZeroBased());

        Task editedTask = new TaskBuilder(SampleDataUtil.generateTask(SampleDataUtil.getTask1()))
                .withTaskName("Changed name").withTaskDescription("Changed Description").build();
        EditTaskCommand.EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder(editedTask).build();

        project.updateTaskFilter(task -> true);
        Task taskToEdit = project.getFilteredSortedTaskList().get(INDEX_FIRST_TASK.getZeroBased());

        newModel.enter(project);
        newModel.enter(taskToEdit);

        try {
            new EditTaskCommand(INDEX_FIRST_TASK, descriptor).execute(newModel);
        } catch (CommandException e) {
            assertFalse(true);
        }

        assertTrue(newModel.getTaskToBeDisplayedOnDashboard().get().equals(editedTask)
                && newModel.getProjectToBeDisplayedOnDashboard().get().getTaskOnView().get().equals(editedTask));
    }

    /**
     * Edits a task is currently not displayed.
     */
    @Test
    public void execute_editingTaskNotToBeDisplayed_noChangesToTaskDashboard() {
        Model newModel = new ModelManager(getTypicalMainCatalogue(), new UserPrefs());
        Project project = newModel.getFilteredProjectList().get(INDEX_FIRST_PROJECT.getZeroBased());

        Task editedTask = new TaskBuilder(SampleDataUtil.generateTask(SampleDataUtil.getTask1()))
                .withTaskName("Changed name").withTaskDescription("Changed Description").build();
        EditTaskCommand.EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder(editedTask).build();

        project.updateTaskFilter(task -> true);
        Task task = project.getFilteredSortedTaskList().get(INDEX_FIRST_TASK.getZeroBased());

        newModel.enter(project);
        newModel.enter(task);

        try {
            new EditTaskCommand(INDEX_SECOND_TASK, descriptor).execute(newModel);
        } catch (CommandException e) {
            assertFalse(true);
        }

        assertFalse(newModel.getTaskToBeDisplayedOnDashboard().get().equals(editedTask)
                && newModel.getProjectToBeDisplayedOnDashboard().get().getTaskOnView().get().equals(editedTask));
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of main catalogue
     */
    @Test
    public void execute_invalidProjectIndexFilteredList_failure() {
        Project project = model.getFilteredProjectList().get(INDEX_FIRST_PROJECT.getZeroBased());
        model.enter(project);
        Index outOfBoundIndex = Index.fromOneBased(project.getFilteredSortedTaskList().size() + 1);
        EditTaskCommand.EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder().withTaskName(
                TypicalTasks.TASK_A.getTaskName()).build();
        EditTaskCommand editTaskCommand = new EditTaskCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editTaskCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

}
