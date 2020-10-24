//package seedu.address.logic.commands;
//
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
//import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
//import static seedu.address.logic.commands.CommandTestUtil.showAssignmentAtIndex;
//import static seedu.address.testutil.TypicalAssignments.getTypicalAddressBook;
//import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ASSIGNMENT;
//import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_ASSIGNMENT;
//
//import org.junit.jupiter.api.Test;
//
//import seedu.address.commons.core.Messages;
//import seedu.address.commons.core.index.Index;
//import seedu.address.model.Model;
//import seedu.address.model.ModelManager;
//import seedu.address.model.UserPrefs;
//import seedu.address.model.assignment.Assignment;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for
// * {@code DeleteCommand}.
// */
//public class DeleteCommandTest {
//
//    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), null);
//
//    @Test
//    public void execute_validIndexUnfilteredList_success() {
//        Assignment firstAssignmentToDelete = model.getFilteredAssignmentList()
//        .get(INDEX_FIRST_ASSIGNMENT.getZeroBased());
//
//        List<Index> assignmentIndexesToDelete = new ArrayList<>();
//        assignmentIndexesToDelete.add(INDEX_FIRST_ASSIGNMENT);
//        DeleteCommand deleteCommand = new DeleteCommand(assignmentIndexesToDelete);
//
//        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_TASK_SUCCESS, assignmentIndexesToDelete);
//
//        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(), null);
//        expectedModel.deleteAssignment(firstAssignmentToDelete);
//
//        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
//    }

//    @Test
//    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
//        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredAssignmentList().size() + 1);
//        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);
//
//        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_ASSIGNMENT_DISPLAYED_INDEX);
//    }

//    @Test
//    public void execute_validIndexFilteredList_success() {
//        showAssignmentAtIndex(model, INDEX_FIRST_ASSIGNMENT);
//
//        Assignment assignmentToDelete = model.getFilteredAssignmentList().get(INDEX_FIRST_ASSIGNMENT.getZeroBased());
//        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_ASSIGNMENT);
//
//        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_TASK_SUCCESS, assignmentToDelete);
//
//        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(), null);
//        expectedModel.deleteAssignment(assignmentToDelete);
//        showNoAssignment(expectedModel);
//
//        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
//    }

//    @Test
//    public void execute_invalidIndexFilteredList_throwsCommandException() {
//        showAssignmentAtIndex(model, INDEX_FIRST_ASSIGNMENT);
//
//        Index outOfBoundIndex = INDEX_SECOND_ASSIGNMENT;
//        // ensures that outOfBoundIndex is still in bounds of address book list
//        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getAssignmentList().size());
//
//        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);
//
//        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_ASSIGNMENT_DISPLAYED_INDEX);
//    }
//
//    @Test
//    public void equals() {
//        DeleteCommand deleteFirstCommand = new DeleteCommand(INDEX_FIRST_ASSIGNMENT);
//        DeleteCommand deleteSecondCommand = new DeleteCommand(INDEX_SECOND_ASSIGNMENT);
//
//        // same object -> returns true
//        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));
//
//        // same values -> returns true
//        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(INDEX_FIRST_ASSIGNMENT);
//        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));
//
//        // different types -> returns false
//        assertFalse(deleteFirstCommand.equals(1));
//
//        // null -> returns false
//        assertFalse(deleteFirstCommand.equals(null));
//
//        // different assignment -> returns false
//        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
//    }
//
//    /**
//     * Updates {@code model}'s filtered list to show no one.
//     */
//    private void showNoAssignment(Model model) {
//        model.updateFilteredAssignmentList(p -> false);
//
//        assertTrue(model.getFilteredAssignmentList().isEmpty());
//    }
//}
