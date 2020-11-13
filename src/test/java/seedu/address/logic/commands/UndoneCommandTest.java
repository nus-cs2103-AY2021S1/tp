package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showAssignmentAtIndex;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ASSIGNMENT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAssignments.getTypicalProductiveNus;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ASSIGNMENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_ASSIGNMENT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.assignment.Assignment;
import seedu.address.testutil.AssignmentBuilder;

public class UndoneCommandTest {

    private Model model = new ModelManager(getTypicalProductiveNus(), new UserPrefs(), null);

    @Test
    public void constructor_nullAssignment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new UndoneCommand(null));
    }

    @Test
    public void execute_validIndexUnfilteredList_success() {
        markFirstAssignmentAsDone();
        Assignment assignmentToMarkUndone = model.getFilteredAssignmentList()
                .get(INDEX_FIRST_ASSIGNMENT.getZeroBased());
        UndoneCommand undoneCommand = new UndoneCommand(INDEX_FIRST_ASSIGNMENT);

        String expectedMessage = String.format(
                UndoneCommand.MESSAGE_MARK_ASSIGNMENT_AS_UNDONE_SUCCESS, assignmentToMarkUndone);

        ModelManager expectedModel = new ModelManager(model.getProductiveNus(), new UserPrefs(),
                model.getPreviousModel());
        expectedModel.setAssignment(model.getFilteredAssignmentList().get(0), assignmentToMarkUndone);

        assertCommandSuccess(undoneCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredAssignmentList().size() + 1);
        UndoneCommand undoneCommand = new UndoneCommand(outOfBoundIndex);

        assertCommandFailure(undoneCommand, model, Messages.MESSAGE_INVALID_ASSIGNMENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showAssignmentAtIndex(model, INDEX_FIRST_ASSIGNMENT);

        markFirstAssignmentAsDone();
        model.updateFilteredAssignmentList(PREDICATE_SHOW_ALL_ASSIGNMENT);

        Assignment assignmentToMarkAsUndone = model.getFilteredAssignmentList()
                .get(INDEX_FIRST_ASSIGNMENT.getZeroBased());
        UndoneCommand undoneCommand = new UndoneCommand(INDEX_FIRST_ASSIGNMENT);

        String expectedMessage = String.format(
                UndoneCommand.MESSAGE_MARK_ASSIGNMENT_AS_UNDONE_SUCCESS, assignmentToMarkAsUndone);

        Model expectedModel = new ModelManager(model.getProductiveNus(), new UserPrefs(), model.getPreviousModel());
        expectedModel.setAssignment(model.getFilteredAssignmentList().get(0), assignmentToMarkAsUndone);

        assertCommandSuccess(undoneCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showAssignmentAtIndex(model, INDEX_FIRST_ASSIGNMENT);

        Index outOfBoundIndex = INDEX_SECOND_ASSIGNMENT;
        // ensures that outOfBoundIndex is still in bounds of ProductiveNus list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getProductiveNus().getAssignmentList().size());

        UndoneCommand undoneCommand = new UndoneCommand(outOfBoundIndex);

        assertCommandFailure(undoneCommand, model, Messages.MESSAGE_INVALID_ASSIGNMENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_alreadyMarkedUndoneAssignmentUnfilteredList_failure() {
        UndoneCommand undoneCommand = new UndoneCommand(INDEX_FIRST_ASSIGNMENT);

        assertCommandFailure(undoneCommand, model, UndoneCommand.MESSAGE_ALREADY_UNDONE_ASSIGNMENT);
    }

    @Test
    public void execute_alreadyMarkedUndoneAssignmentFilteredList_failure() {
        showAssignmentAtIndex(model, INDEX_FIRST_ASSIGNMENT);

        UndoneCommand undoneCommand = new UndoneCommand(INDEX_FIRST_ASSIGNMENT);

        assertCommandFailure(undoneCommand, model, UndoneCommand.MESSAGE_ALREADY_UNDONE_ASSIGNMENT);
    }

    @Test
    public void equals() {
        markFirstAssignmentAsDone();
        markSecondAssignmentAsDone();

        UndoneCommand markFirstAsUndoneCommand = new UndoneCommand(INDEX_FIRST_ASSIGNMENT);
        UndoneCommand markSecondAsUndoneCommand = new UndoneCommand(INDEX_SECOND_ASSIGNMENT);

        // same object -> returns true
        assertTrue(markFirstAsUndoneCommand.equals(markFirstAsUndoneCommand));

        // same values -> returns true
        UndoneCommand undoneFirstCommandCopy = new UndoneCommand(INDEX_FIRST_ASSIGNMENT);
        assertTrue(markFirstAsUndoneCommand.equals(undoneFirstCommandCopy));

        // different types -> returns false
        assertFalse(markFirstAsUndoneCommand.equals(1));

        // null -> returns false
        assertFalse(markFirstAsUndoneCommand.equals(null));

        // different assignment -> returns false
        assertFalse(markFirstAsUndoneCommand.equals(markSecondAsUndoneCommand));
    }

    // Mark first assignment in filtered list as done
    private void markFirstAssignmentAsDone() {
        // Set done status for assignment in filtered list in ProductiveNus
        Assignment assignmentInList = model.getProductiveNus().getAssignmentList()
                .get(INDEX_FIRST_ASSIGNMENT.getZeroBased());
        Assignment assignmentInListMarkAsDone = new AssignmentBuilder(assignmentInList).withDoneStatusSet().build();
        model.setAssignment(assignmentInList, assignmentInListMarkAsDone);
    }

    // Mark second assignment in filtered list as done
    private void markSecondAssignmentAsDone() {
        // Set done status for assignment in filtered list in ProductiveNus
        Assignment assignmentInList = model.getProductiveNus().getAssignmentList()
                .get(INDEX_SECOND_ASSIGNMENT.getZeroBased());
        Assignment assignmentInListMarkAsDone = new AssignmentBuilder(assignmentInList).withDoneStatusSet().build();
        model.setAssignment(assignmentInList, assignmentInListMarkAsDone);
    }
}
