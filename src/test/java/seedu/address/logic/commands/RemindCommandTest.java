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
import seedu.address.testutil.AssignmentBuilder;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for
 * {@code RemindCommand}.
 */
public class RemindCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), null);

    private List<Index> indexesToRemind = new ArrayList<>();
    private List<Assignment> assignmentsToRemind = new ArrayList<>();

    @Test
    public void constructor_nullAssignment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new RemindCommand(null));
    }

    @Test
    public void execute_validIndexUnfilteredList_success() {
        indexesToRemind.add(INDEX_FIRST_ASSIGNMENT);

        Assignment assignmentToRemind = model.getFilteredAssignmentList().get(INDEX_FIRST_ASSIGNMENT.getZeroBased());
        RemindCommand remindCommand = new RemindCommand(indexesToRemind);
        assignmentsToRemind.add(assignmentToRemind);

        String expectedMessage = String.format(RemindCommand.MESSAGE_REMIND_ASSIGNMENT_SUCCESS, assignmentsToRemind);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(), null);
        expectedModel.setAssignment(model.getFilteredAssignmentList().get(0), assignmentToRemind);

        assertCommandSuccess(remindCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredAssignmentList().size() + 1);
        indexesToRemind.add(outOfBoundIndex);
        RemindCommand remindCommand = new RemindCommand(indexesToRemind);

        assertCommandFailure(remindCommand, model, Messages.MESSAGE_INVALID_ASSIGNMENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showAssignmentAtIndex(model, INDEX_FIRST_ASSIGNMENT);
        indexesToRemind.add(INDEX_FIRST_ASSIGNMENT);

        Assignment assignmentToRemind = model.getFilteredAssignmentList().get(INDEX_FIRST_ASSIGNMENT.getZeroBased());
        RemindCommand remindCommand = new RemindCommand(indexesToRemind);
        assignmentsToRemind.add(assignmentToRemind);

        String expectedMessage = String.format(RemindCommand.MESSAGE_REMIND_ASSIGNMENT_SUCCESS, assignmentsToRemind);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(), null);
        expectedModel.setAssignment(model.getFilteredAssignmentList().get(0), assignmentToRemind);

        assertCommandSuccess(remindCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showAssignmentAtIndex(model, INDEX_FIRST_ASSIGNMENT);

        Index outOfBoundIndex = INDEX_SECOND_ASSIGNMENT;
        indexesToRemind.add(outOfBoundIndex);

        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getAssignmentList().size());

        RemindCommand remindCommand = new RemindCommand(indexesToRemind);
        assertCommandFailure(remindCommand, model, Messages.MESSAGE_INVALID_ASSIGNMENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validMultipleIndexesUnfilteredList_success() {
        indexesToRemind.add(INDEX_FIRST_ASSIGNMENT);
        indexesToRemind.add(INDEX_SECOND_ASSIGNMENT); // Two indexes of assignments to remind

        Assignment firstAssignmentToRemind = model.getFilteredAssignmentList()
                .get(INDEX_FIRST_ASSIGNMENT.getZeroBased());

        Assignment secondAssignmentToRemind = model.getFilteredAssignmentList()
                .get(INDEX_SECOND_ASSIGNMENT.getZeroBased());

        assignmentsToRemind.add(secondAssignmentToRemind);
        assignmentsToRemind.add(firstAssignmentToRemind);

        RemindCommand remindCommand = new RemindCommand(indexesToRemind);

        String expectedMessage = String.format(RemindCommand.MESSAGE_REMIND_ASSIGNMENT_SUCCESS, assignmentsToRemind);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(), null);
        expectedModel.setAssignment(model.getFilteredAssignmentList().get(0), firstAssignmentToRemind);
        expectedModel.setAssignment(model.getFilteredAssignmentList().get(1), secondAssignmentToRemind);

        assertCommandSuccess(remindCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_alreadyRemindedAssignmentUnfilteredList_failure() {
        indexesToRemind.add(INDEX_FIRST_ASSIGNMENT);

        // Set reminders for assignment in filtered list in address book
        Assignment firstAssignment = model.getFilteredAssignmentList().get(INDEX_FIRST_ASSIGNMENT.getZeroBased());
        Assignment firstAssignmentReminded = new AssignmentBuilder(firstAssignment).withRemindersSet().build();
        model.setAssignment(firstAssignment, firstAssignmentReminded);

        RemindCommand remindCommand = new RemindCommand(indexesToRemind);

        assertCommandFailure(remindCommand, model, RemindCommand.MESSAGE_REMINDED_ASSIGNMENT);
    }

    @Test
    public void execute_alreadyRemindedAssignmentFilteredList_failure() {
        showAssignmentAtIndex(model, INDEX_FIRST_ASSIGNMENT);
        indexesToRemind.add(INDEX_FIRST_ASSIGNMENT);

        // Set reminders for assignment in filtered list in address book
        Assignment assignmentInList = model.getAddressBook().getAssignmentList()
                .get(INDEX_FIRST_ASSIGNMENT.getZeroBased());
        Assignment assignmentInListReminded = new AssignmentBuilder(assignmentInList).withRemindersSet().build();
        model.setAssignment(assignmentInList, assignmentInListReminded);
        model.updateFilteredAssignmentList(PREDICATE_SHOW_ALL_ASSIGNMENT);

        RemindCommand remindCommand = new RemindCommand(indexesToRemind);

        assertCommandFailure(remindCommand, model, RemindCommand.MESSAGE_REMINDED_ASSIGNMENT);
    }

    @Test
    public void execute_duplicatedIndexFilteredList_throwsCommandException() {
        showAssignmentAtIndex(model, INDEX_FIRST_ASSIGNMENT);

        Index duplicatedIndex = INDEX_FIRST_ASSIGNMENT;

        // adds duplicated indexes to list
        indexesToRemind.add(duplicatedIndex);
        indexesToRemind.add(duplicatedIndex);

        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(duplicatedIndex.getZeroBased() < model.getAddressBook().getAssignmentList().size());

        RemindCommand remindCommand = new RemindCommand(indexesToRemind);

        assertCommandFailure(remindCommand, model, String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, Messages
                .MESSAGE_DUPLICATE_INDEXES));
    }

    @Test
    public void equals() {
        List<Index> firstCommandIndexes = new ArrayList<>();
        firstCommandIndexes.add(INDEX_FIRST_ASSIGNMENT);
        firstCommandIndexes.add(INDEX_THIRD_ASSIGNMENT);

        List<Index> secondCommandIndexes = new ArrayList<>();
        secondCommandIndexes.add(INDEX_SECOND_ASSIGNMENT);

        RemindCommand remindFirstCommand = new RemindCommand(firstCommandIndexes);
        RemindCommand remindSecondCommand = new RemindCommand(secondCommandIndexes);

        // same object -> returns true
        assertTrue(remindFirstCommand.equals(remindFirstCommand));

        // same values -> returns true
        RemindCommand remindFirstCommandCopy = new RemindCommand(firstCommandIndexes);
        assertTrue(remindFirstCommand.equals(remindFirstCommandCopy));

        // different types -> returns false
        assertFalse(remindFirstCommand.equals(1));

        // null -> returns false
        assertFalse(remindFirstCommand.equals(null));

        // different assignment -> returns false
        assertFalse(remindFirstCommand.equals(remindSecondCommand));
    }
}
