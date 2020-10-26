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
 * {@code RemindCommand}.
 */
public class RemindCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), null);

    @Test
    public void constructor_nullAssignment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new RemindCommand(null));
    }

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Assignment assignmentToRemind = model.getFilteredAssignmentList().get(INDEX_FIRST_ASSIGNMENT.getZeroBased());
        RemindCommand remindCommand = new RemindCommand(INDEX_FIRST_ASSIGNMENT);

        String expectedMessage = String.format(RemindCommand.MESSAGE_REMIND_ASSIGNMENT_SUCCESS, assignmentToRemind);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(), null);
        expectedModel.setAssignment(model.getFilteredAssignmentList().get(0), assignmentToRemind);

        assertCommandSuccess(remindCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredAssignmentList().size() + 1);
        RemindCommand remindCommand = new RemindCommand(outOfBoundIndex);

        assertCommandFailure(remindCommand, model, Messages.MESSAGE_INVALID_ASSIGNMENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showAssignmentAtIndex(model, INDEX_FIRST_ASSIGNMENT);

        Assignment assignmentToRemind = model.getFilteredAssignmentList().get(INDEX_FIRST_ASSIGNMENT.getZeroBased());
        RemindCommand remindCommand = new RemindCommand(INDEX_FIRST_ASSIGNMENT);

        String expectedMessage = String.format(RemindCommand.MESSAGE_REMIND_ASSIGNMENT_SUCCESS, assignmentToRemind);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(), null);
        expectedModel.setAssignment(model.getFilteredAssignmentList().get(0), assignmentToRemind);

        assertCommandSuccess(remindCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showAssignmentAtIndex(model, INDEX_FIRST_ASSIGNMENT);

        Index outOfBoundIndex = INDEX_SECOND_ASSIGNMENT;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getAssignmentList().size());

        RemindCommand remindCommand = new RemindCommand(outOfBoundIndex);

        assertCommandFailure(remindCommand, model, Messages.MESSAGE_INVALID_ASSIGNMENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_alreadyRemindedAssignmentUnfilteredList_failure() {
        // Set reminders for assignment in filtered list in address book
        Assignment firstAssignment = model.getFilteredAssignmentList().get(INDEX_FIRST_ASSIGNMENT.getZeroBased());
        Assignment firstAssignmentReminded = new AssignmentBuilder(firstAssignment).withRemindersSet().build();
        model.setAssignment(firstAssignment, firstAssignmentReminded);

        RemindCommand remindCommand = new RemindCommand(INDEX_FIRST_ASSIGNMENT);

        assertCommandFailure(remindCommand, model, RemindCommand.MESSAGE_REMINDED_ASSIGNMENT);
    }

    @Test
    public void execute_alreadyRemindedAssignmentFilteredList_failure() {
        showAssignmentAtIndex(model, INDEX_FIRST_ASSIGNMENT);

        // Set reminders for assignment in filtered list in address book
        Assignment assignmentInList = model.getAddressBook().getAssignmentList()
                .get(INDEX_FIRST_ASSIGNMENT.getZeroBased());
        Assignment assignmentInListReminded = new AssignmentBuilder(assignmentInList).withRemindersSet().build();
        model.setAssignment(assignmentInList, assignmentInListReminded);
        model.updateFilteredAssignmentList(PREDICATE_SHOW_ALL_ASSIGNMENT);

        RemindCommand remindCommand = new RemindCommand(INDEX_FIRST_ASSIGNMENT);

        assertCommandFailure(remindCommand, model, RemindCommand.MESSAGE_REMINDED_ASSIGNMENT);
    }

    @Test
    public void equals() {
        RemindCommand remindFirstCommand = new RemindCommand(INDEX_FIRST_ASSIGNMENT);
        RemindCommand remindSecondCommand = new RemindCommand(INDEX_SECOND_ASSIGNMENT);

        // same object -> returns true
        assertTrue(remindFirstCommand.equals(remindFirstCommand));

        // same values -> returns true
        RemindCommand remindFirstCommandCopy = new RemindCommand(INDEX_FIRST_ASSIGNMENT);
        assertTrue(remindFirstCommand.equals(remindFirstCommandCopy));

        // different types -> returns false
        assertFalse(remindFirstCommand.equals(1));

        // null -> returns false
        assertFalse(remindFirstCommand.equals(null));

        // different assignment -> returns false
        assertFalse(remindFirstCommand.equals(remindSecondCommand));
    }
}
