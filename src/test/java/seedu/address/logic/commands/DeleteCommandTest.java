package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showAssignmentAtIndex;
import static seedu.address.logic.commands.DeleteCommand.MESSAGE_DELETE_TASK_SUCCESS;
import static seedu.address.testutil.TypicalAssignments.getTypicalAddressBook;
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

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), null);

    @Test
    public void execute_validIndexUnfilteredList_success() {
        List<Index> indexesToDelete = new ArrayList<>();
        List<Assignment> assignmentsToDelete = new ArrayList<>();

        indexesToDelete.add(INDEX_SECOND_ASSIGNMENT);

        Assignment assignmentToDelete = model.getFilteredAssignmentList()
                .get(INDEX_SECOND_ASSIGNMENT.getZeroBased());
        assignmentsToDelete.add(assignmentToDelete);

        DeleteCommand deleteCommand = new DeleteCommand(indexesToDelete);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_TASK_SUCCESS, assignmentsToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(), null);
        expectedModel.deleteAssignment(assignmentToDelete);

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
    public void execute_validIndexFilteredList_success() {
        showAssignmentAtIndex(model, INDEX_FIRST_ASSIGNMENT);
        List<Index> indexesToDelete = new ArrayList<>();
        List<Assignment> deletedAssignments = new ArrayList<>();

        indexesToDelete.add(INDEX_FIRST_ASSIGNMENT);

        Assignment firstAssignmentToDelete = model.getFilteredAssignmentList()
                .get(INDEX_FIRST_ASSIGNMENT.getZeroBased());

        deletedAssignments.add(firstAssignmentToDelete);

        DeleteCommand deleteCommand = new DeleteCommand(indexesToDelete);

        String expectedMessage = String.format(MESSAGE_DELETE_TASK_SUCCESS, deletedAssignments);
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(), null);
        expectedModel.deleteAssignment(firstAssignmentToDelete);
        showNoAssignment(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showAssignmentAtIndex(model, INDEX_FIRST_ASSIGNMENT);
        List<Index> assignmentIndexesToDelete = new ArrayList<>();
        Index outOfBoundIndex = INDEX_SECOND_ASSIGNMENT;
        assignmentIndexesToDelete.add(outOfBoundIndex);
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getAssignmentList().size());

        DeleteCommand deleteCommand = new DeleteCommand(assignmentIndexesToDelete);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_ASSIGNMENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_duplicatedIndexFilteredList_throwsCommandException() {
        showAssignmentAtIndex(model, INDEX_FIRST_ASSIGNMENT);
        List<Index> assignmentIndexesToDelete = new ArrayList<>();
        Index duplicatedIndex = INDEX_FIRST_ASSIGNMENT;
        assignmentIndexesToDelete.add(duplicatedIndex);
        assignmentIndexesToDelete.add(duplicatedIndex);
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(duplicatedIndex.getZeroBased() < model.getAddressBook().getAssignmentList().size());

        DeleteCommand deleteCommand = new DeleteCommand(assignmentIndexesToDelete);

        assertCommandFailure(deleteCommand, model, String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand
                .MESSAGE_ASSIGNMENTS_DUPLICATED_INDEX));
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
