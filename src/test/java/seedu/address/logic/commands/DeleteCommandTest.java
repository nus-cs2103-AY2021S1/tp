package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showAssignmentAtIndex;
import static seedu.address.logic.commands.DeleteCommand.MESSAGE_DELETE_TASK_SUCCESS;
import static seedu.address.testutil.TypicalAssignments.getTypicalProductiveNus;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ASSIGNMENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_ASSIGNMENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_ASSIGNMENT;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.assignment.Assignment;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model model = new ModelManager(getTypicalProductiveNus(), new UserPrefs(), null);

    private List<Index> indexesToDelete = new ArrayList<>();
    private List<Assignment> assignmentsToDelete = new ArrayList<>();

    @Test
    public void execute_validIndexUnfilteredList_success() {
        indexesToDelete.add(INDEX_SECOND_ASSIGNMENT);

        Assignment assignmentToDelete = model.getFilteredAssignmentList()
                .get(INDEX_SECOND_ASSIGNMENT.getZeroBased());

        assignmentsToDelete.add(assignmentToDelete);

        DeleteCommand deleteCommand = new DeleteCommand(indexesToDelete);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_TASK_SUCCESS, assignmentsToDelete);

        ModelManager expectedModel = new ModelManager(model.getProductiveNus(), new UserPrefs(), null);
        expectedModel.deleteAssignment(assignmentToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showAssignmentAtIndex(model, INDEX_FIRST_ASSIGNMENT);

        indexesToDelete.add(INDEX_FIRST_ASSIGNMENT);

        Assignment firstAssignmentToDelete = model.getFilteredAssignmentList()
                .get(INDEX_FIRST_ASSIGNMENT.getZeroBased());

        assignmentsToDelete.add(firstAssignmentToDelete);

        DeleteCommand deleteCommand = new DeleteCommand(indexesToDelete);

        String expectedMessage = String.format(MESSAGE_DELETE_TASK_SUCCESS, assignmentsToDelete);
        Model expectedModel = new ModelManager(model.getProductiveNus(), new UserPrefs(), null);
        expectedModel.deleteAssignment(firstAssignmentToDelete);
        showNoAssignment(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validMultipleIndexesUnfilteredList_success() {
        indexesToDelete.add(INDEX_FIRST_ASSIGNMENT); // Add index 1
        indexesToDelete.add(INDEX_SECOND_ASSIGNMENT); // Add index 2

        Assignment firstAssignmentToDelete = model.getFilteredAssignmentList()
                .get(INDEX_FIRST_ASSIGNMENT.getZeroBased());

        Assignment secondAssignmentToDelete = model.getFilteredAssignmentList()
                .get(INDEX_SECOND_ASSIGNMENT.getZeroBased());

        assignmentsToDelete.add(secondAssignmentToDelete);
        assignmentsToDelete.add(firstAssignmentToDelete);

        DeleteCommand deleteCommand = new DeleteCommand(indexesToDelete);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_TASK_SUCCESS, assignmentsToDelete);

        ModelManager expectedModel = new ModelManager(model.getProductiveNus(), new UserPrefs(), null);

        expectedModel.deleteAssignment(secondAssignmentToDelete);
        expectedModel.deleteAssignment(firstAssignmentToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredAssignmentList().size() + 1);
        List<Index> assignmentIndexesToDelete = new ArrayList<>();
        assignmentIndexesToDelete.add(outOfBoundIndex);
        DeleteCommand deleteCommand = new DeleteCommand(assignmentIndexesToDelete);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_ASSIGNMENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showAssignmentAtIndex(model, INDEX_FIRST_ASSIGNMENT);

        Index outOfBoundIndex = INDEX_SECOND_ASSIGNMENT;
        indexesToDelete.add(outOfBoundIndex);
        // ensures that outOfBoundIndex is still in bounds of ProductiveNus list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getProductiveNus().getAssignmentList().size());

        DeleteCommand deleteCommand = new DeleteCommand(indexesToDelete);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_ASSIGNMENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_duplicatedIndexFilteredList_throwsCommandException() {
        showAssignmentAtIndex(model, INDEX_FIRST_ASSIGNMENT);

        Index duplicatedIndex = INDEX_FIRST_ASSIGNMENT;
        // adds duplicated indexes to list
        indexesToDelete.add(duplicatedIndex);
        indexesToDelete.add(duplicatedIndex);
        // ensures that outOfBoundIndex is still in bounds of ProductiveNus list
        assertTrue(duplicatedIndex.getZeroBased() < model.getProductiveNus().getAssignmentList().size());

        DeleteCommand deleteCommand = new DeleteCommand(indexesToDelete);

        assertCommandFailure(deleteCommand, model, String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, Messages
                .MESSAGE_DUPLICATE_INDEXES));
    }

    @Test
    public void equals() {
        List<Index> firstCommandIndexes = new ArrayList<>();
        firstCommandIndexes.add(INDEX_FIRST_ASSIGNMENT);
        firstCommandIndexes.add(INDEX_THIRD_ASSIGNMENT);

        List<Index> secondCommandIndexes = new ArrayList<>();
        secondCommandIndexes.add(INDEX_SECOND_ASSIGNMENT);

        DeleteCommand deleteFirstCommand = new DeleteCommand(firstCommandIndexes);
        DeleteCommand deleteSecondCommand = new DeleteCommand(secondCommandIndexes);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(firstCommandIndexes);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different assignment -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoAssignment(Model model) {
        model.updateFilteredAssignmentList(p -> false);

        assertTrue(model.getFilteredAssignmentList().isEmpty());
    }
}
