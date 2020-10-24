package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showAssignmentAtIndex;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ASSIGNMENT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAssignments.getTypicalAddressBook;
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

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for
 * {@code DoneCommand}.
 */
public class DoneCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), null);

    @Test
    public void constructor_nullAssignment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DoneCommand(null));
    }

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Assignment assignmentToMarkDone = model.getFilteredAssignmentList().get(INDEX_FIRST_ASSIGNMENT.getZeroBased());
        DoneCommand doneCommand = new DoneCommand(INDEX_FIRST_ASSIGNMENT);

        String expectedMessage = String.format(DoneCommand.MESSAGE_MARK_ASSIGNMENT_AS_DONE_SUCCESS,
                assignmentToMarkDone);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(), null);
        expectedModel.setAssignment(model.getFilteredAssignmentList().get(0), assignmentToMarkDone);

        assertCommandSuccess(doneCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredAssignmentList().size() + 1);
        DoneCommand doneCommand = new DoneCommand(outOfBoundIndex);

        assertCommandFailure(doneCommand, model, Messages.MESSAGE_INVALID_ASSIGNMENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showAssignmentAtIndex(model, INDEX_FIRST_ASSIGNMENT);

        Assignment assignmentToMarkDone = model.getFilteredAssignmentList().get(INDEX_FIRST_ASSIGNMENT.getZeroBased());
        DoneCommand doneCommand = new DoneCommand(INDEX_FIRST_ASSIGNMENT);

        String expectedMessage = String.format(DoneCommand.MESSAGE_MARK_ASSIGNMENT_AS_DONE_SUCCESS,
                assignmentToMarkDone);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(), null);
        expectedModel.setAssignment(model.getFilteredAssignmentList().get(0), assignmentToMarkDone);

        assertCommandSuccess(doneCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showAssignmentAtIndex(model, INDEX_FIRST_ASSIGNMENT);

        Index outOfBoundIndex = INDEX_SECOND_ASSIGNMENT;

        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getAssignmentList().size());

        DoneCommand doneCommand = new DoneCommand(outOfBoundIndex);

        assertCommandFailure(doneCommand, model, Messages.MESSAGE_INVALID_ASSIGNMENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_alreadyMarkedDoneAssignmentUnfilteredList_failure() {

        // TODO: Find out what exactly is unfilteredList
        // Set assignment in filtered list in address book as done
        Assignment firstAssignment = model.getFilteredAssignmentList().get(INDEX_FIRST_ASSIGNMENT.getZeroBased());
        Assignment firstAssignmentMarkedDone = new AssignmentBuilder(firstAssignment).withDoneStatusSet().build();
        model.setAssignment(firstAssignment, firstAssignmentMarkedDone);

        DoneCommand doneCommand = new DoneCommand(INDEX_FIRST_ASSIGNMENT);

        assertCommandFailure(doneCommand, model, DoneCommand.MESSAGE_ALREADY_MARKED_ASSIGNMENT_AS_DONE);
    }

    @Test
    public void execute_alreadyMarkedDoneAssignmentFilteredList_failure() {
        showAssignmentAtIndex(model, INDEX_FIRST_ASSIGNMENT);

        // Set assignment in filtered list in address book as done
        Assignment assignmentInList = model.getAddressBook().getAssignmentList()
                .get(INDEX_FIRST_ASSIGNMENT.getZeroBased());
        Assignment assignmentInListMarkedDone = new AssignmentBuilder(assignmentInList).withDoneStatusSet().build();
        model.setAssignment(assignmentInList, assignmentInListMarkedDone);
        model.updateFilteredAssignmentList(PREDICATE_SHOW_ALL_ASSIGNMENT);

        DoneCommand doneCommand = new DoneCommand(INDEX_FIRST_ASSIGNMENT);

        assertCommandFailure(doneCommand, model, DoneCommand.MESSAGE_ALREADY_MARKED_ASSIGNMENT_AS_DONE);
    }

    @Test
    public void equals() {
        DoneCommand markFirstDoneCommand = new DoneCommand(INDEX_FIRST_ASSIGNMENT);
        DoneCommand markSecondDoneCommand = new DoneCommand(INDEX_SECOND_ASSIGNMENT);

        // same object -> returns true
        assertTrue(markFirstDoneCommand.equals(markFirstDoneCommand));

        // same values -> returns true
        DoneCommand remindFirstCommandCopy = new DoneCommand(INDEX_FIRST_ASSIGNMENT);
        assertTrue(markFirstDoneCommand.equals(remindFirstCommandCopy));

        // different types -> returns false
        assertFalse(markFirstDoneCommand.equals(1));

        // null -> returns false
        assertFalse(markFirstDoneCommand.equals(null));

        // different assignment -> returns false
        assertFalse(markFirstDoneCommand.equals(markSecondDoneCommand));
    }
}
