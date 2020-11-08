package seedu.address.logic.commands.global;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_A;
import static seedu.address.logic.commands.CommandTestUtil.DESC_B;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE_B;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PROJECT_NAME_B;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PROJECT_TAG_A;
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
import seedu.address.logic.commands.global.EditCommand.EditProjectDescriptor;
import seedu.address.model.MainCatalogue;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.project.Project;
import seedu.address.testutil.EditProjectDescriptorBuilder;
import seedu.address.testutil.ProjectBuilder;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private Model model = new ModelManager(getTypicalMainCatalogue(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Project editedProject = new ProjectBuilder().build();
        EditProjectDescriptor descriptor = new EditProjectDescriptorBuilder(editedProject).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PROJECT, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PROJECT_SUCCESS, editedProject);

        Model expectedModel = new ModelManager(new MainCatalogue(model.getProjectCatalogue()), new UserPrefs());
        expectedModel.setProject(model.getFilteredProjectList().get(0), editedProject);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastProject = Index.fromOneBased(model.getFilteredProjectList().size());
        Project lastProject = model.getFilteredProjectList().get(indexLastProject.getZeroBased());

        ProjectBuilder projectInList = new ProjectBuilder(lastProject);
        Project editedProject = projectInList.withProjectName(VALID_PROJECT_NAME_B).withDeadline(VALID_DEADLINE_B)
                .withTags(VALID_PROJECT_TAG_A).build();

        EditProjectDescriptor descriptor = new EditProjectDescriptorBuilder().withProjectName(VALID_PROJECT_NAME_B)
                .withDeadline(VALID_DEADLINE_B).withTags(VALID_PROJECT_TAG_A).build();
        EditCommand editCommand = new EditCommand(indexLastProject, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PROJECT_SUCCESS, editedProject);

        Model expectedModel = new ModelManager(new MainCatalogue(model.getProjectCatalogue()), new UserPrefs());
        expectedModel.setProject(lastProject, editedProject);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PROJECT, new EditProjectDescriptor());
        Project editedProject = model.getFilteredProjectList().get(INDEX_FIRST_PROJECT.getZeroBased());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PROJECT_SUCCESS, editedProject);

        Model expectedModel = new ModelManager(new MainCatalogue(model.getProjectCatalogue()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showProjectAtIndex(model, INDEX_FIRST_PROJECT);

        Project projectInFilteredList = model.getFilteredProjectList().get(INDEX_FIRST_PROJECT.getZeroBased());
        Project editedProject = new ProjectBuilder(projectInFilteredList).withProjectName(
            VALID_PROJECT_NAME_B).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PROJECT,
                new EditProjectDescriptorBuilder().withProjectName(VALID_PROJECT_NAME_B).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PROJECT_SUCCESS, editedProject);

        Model expectedModel = new ModelManager(new MainCatalogue(model.getProjectCatalogue()), new UserPrefs());
        expectedModel.setProject(model.getFilteredProjectList().get(0), editedProject);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateProjectUnfilteredList_failure() {
        Project firstProject = model.getFilteredProjectList().get(INDEX_FIRST_PROJECT.getZeroBased());
        EditProjectDescriptor descriptor = new EditProjectDescriptorBuilder(firstProject).build();
        EditCommand editCommand = new EditCommand(INDEX_SECOND_PROJECT, descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_PROJECT);
    }

    @Test
    public void execute_duplicateProjectFilteredList_failure() {
        showProjectAtIndex(model, INDEX_FIRST_PROJECT);

        // edit project in filtered list into a duplicate in main catalogue
        Project projectInList = model.getProjectCatalogue().getProjectList().get(INDEX_SECOND_PROJECT.getZeroBased());
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PROJECT,
                new EditProjectDescriptorBuilder(projectInList).build());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_PROJECT);
    }

    @Test
    public void execute_invalidProjectIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredProjectList().size() + 1);
        EditProjectDescriptor descriptor = new EditProjectDescriptorBuilder().withProjectName(
            VALID_PROJECT_NAME_B).build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of main catalogue
     */
    @Test
    public void execute_invalidProjectIndexFilteredList_failure() {
        showProjectAtIndex(model, INDEX_FIRST_PROJECT);
        Index outOfBoundIndex = INDEX_SECOND_PROJECT;
        // ensures that outOfBoundIndex is still in bounds of main catalogue list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getProjectCatalogue().getProjectList().size());

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
                new EditProjectDescriptorBuilder().withProjectName(VALID_PROJECT_NAME_B).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX);
    }

    /**
     * Edits project is currently display on dashboard.
     */
    @Test
    public void execute_editingProjectToBeDisplayed_changesToProjectToBeDisplayed() {
        Model newModel = new ModelManager(getTypicalMainCatalogue(), new UserPrefs());
        Index indexLastProject = Index.fromOneBased(newModel.getFilteredProjectList().size());
        Project lastProject = newModel.getFilteredProjectList().get(indexLastProject.getZeroBased());

        newModel.enter(lastProject);

        ProjectBuilder projectInList = new ProjectBuilder(lastProject);
        Project editedProject = projectInList.withProjectName(VALID_PROJECT_NAME_B).withDeadline(VALID_DEADLINE_B)
                .withTags(VALID_PROJECT_TAG_A).build();

        EditProjectDescriptor descriptor = new EditProjectDescriptorBuilder().withProjectName(VALID_PROJECT_NAME_B)
                .withDeadline(VALID_DEADLINE_B).withTags(VALID_PROJECT_TAG_A).build();

        try {
            new EditCommand(indexLastProject, descriptor).execute(newModel);
        } catch (CommandException e) {
            assertFalse(true);
        }

        assertTrue(newModel.getProjectToBeDisplayedOnDashboard().get().isSameProject(editedProject));
    }

    /**
     * Edits a project is currently not displayed.
     */
    @Test
    public void execute_editingProjectNotToBeDisplayed_noChangesToProjectToBeDisplayed() {
        Model newModel = new ModelManager(getTypicalMainCatalogue(), new UserPrefs());
        Index indexLastProject = Index.fromOneBased(newModel.getFilteredProjectList().size());
        Project lastProject = newModel.getFilteredProjectList().get(indexLastProject.getZeroBased());

        newModel.enter(lastProject);

        Project firstProject = newModel.getFilteredProjectList().get(INDEX_FIRST_PROJECT.getZeroBased());
        ProjectBuilder projectInList = new ProjectBuilder(firstProject);
        Project editedProject = projectInList.withProjectName(VALID_PROJECT_NAME_B).withDeadline(VALID_DEADLINE_B)
                .withTags(VALID_PROJECT_TAG_A).build();

        EditProjectDescriptor descriptor = new EditProjectDescriptorBuilder().withProjectName(VALID_PROJECT_NAME_B)
                .withDeadline(VALID_DEADLINE_B).withTags(VALID_PROJECT_TAG_A).build();

        try {
            new EditCommand(INDEX_FIRST_PROJECT, descriptor).execute(newModel);
        } catch (CommandException e) {
            assertFalse(true);
        }

        assertFalse(newModel.getProjectToBeDisplayedOnDashboard().get().isSameProject(editedProject));
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(INDEX_FIRST_PROJECT, DESC_A);

        // same values -> returns true
        EditProjectDescriptor copyDescriptor = new EditProjectDescriptor(DESC_A);
        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST_PROJECT, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND_PROJECT, DESC_A)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST_PROJECT, DESC_B)));
    }

}
