package seedu.tasklist.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.tasklist.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.tasklist.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.tasklist.logic.commands.CommandTestUtil.showAssignmentAtIndex;
import static seedu.tasklist.model.Model.PREDICATE_SHOW_ALL_ASSIGNMENT;
import static seedu.tasklist.testutil.Assert.assertThrows;
import static seedu.tasklist.testutil.TypicalAssignments.getTypicalProductiveNus;
import static seedu.tasklist.testutil.TypicalIndexes.INDEX_FIRST_ASSIGNMENT;
import static seedu.tasklist.testutil.TypicalIndexes.INDEX_SECOND_ASSIGNMENT;
import static seedu.tasklist.testutil.TypicalIndexes.INDEX_THIRD_ASSIGNMENT;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.tasklist.commons.core.Messages;
import seedu.tasklist.commons.core.index.Index;
import seedu.tasklist.model.Model;
import seedu.tasklist.model.ModelManager;
import seedu.tasklist.model.UserPrefs;
import seedu.tasklist.model.assignment.Assignment;
import seedu.tasklist.testutil.AssignmentBuilder;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for
 * {@code DoneCommand}.
 */
public class DoneCommandTest {

    private Model model = new ModelManager(getTypicalProductiveNus(), new UserPrefs(), null);

    private List<Index> indexesToMarkDone = new ArrayList<>();
    private List<Assignment> assignmentsToMarkDone = new ArrayList<>();

    @Test
    public void constructor_nullAssignment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DoneCommand(null));
    }

    @Test
    public void execute_validIndexUnfilteredList_success() {
        indexesToMarkDone.add(INDEX_FIRST_ASSIGNMENT);
        DoneCommand doneCommand = new DoneCommand(indexesToMarkDone);

        Assignment assignmentToMarkDone = model.getFilteredAssignmentList().get(INDEX_FIRST_ASSIGNMENT.getZeroBased());
        assignmentsToMarkDone.add(assignmentToMarkDone);

        String expectedMessage = String.format(DoneCommand.MESSAGE_MARK_ASSIGNMENT_AS_DONE_SUCCESS,
                assignmentsToMarkDone);

        ModelManager expectedModel = new ModelManager(model.getProductiveNus(), new UserPrefs(), null);
        expectedModel.setAssignment(model.getFilteredAssignmentList().get(0), assignmentToMarkDone);

        assertCommandSuccess(doneCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredAssignmentList().size() + 1);
        indexesToMarkDone.add(outOfBoundIndex);
        DoneCommand doneCommand = new DoneCommand(indexesToMarkDone);

        assertCommandFailure(doneCommand, model, Messages.MESSAGE_INVALID_ASSIGNMENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showAssignmentAtIndex(model, INDEX_FIRST_ASSIGNMENT);
        indexesToMarkDone.add(INDEX_FIRST_ASSIGNMENT);

        Assignment assignmentToMarkDone = model.getFilteredAssignmentList().get(INDEX_FIRST_ASSIGNMENT.getZeroBased());
        DoneCommand doneCommand = new DoneCommand(indexesToMarkDone);
        assignmentsToMarkDone.add(assignmentToMarkDone);

        String expectedMessage = String.format(DoneCommand.MESSAGE_MARK_ASSIGNMENT_AS_DONE_SUCCESS,
                assignmentsToMarkDone);

        Model expectedModel = new ModelManager(model.getProductiveNus(), new UserPrefs(), null);
        expectedModel.setAssignment(model.getFilteredAssignmentList().get(0), assignmentToMarkDone);

        assertCommandSuccess(doneCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showAssignmentAtIndex(model, INDEX_FIRST_ASSIGNMENT);

        Index outOfBoundIndex = INDEX_SECOND_ASSIGNMENT;
        indexesToMarkDone.add(outOfBoundIndex);

        // ensures that outOfBoundIndex is still in bounds of ProductiveNus list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getProductiveNus().getAssignmentList().size());

        DoneCommand doneCommand = new DoneCommand(indexesToMarkDone);
        assertCommandFailure(doneCommand, model, Messages.MESSAGE_INVALID_ASSIGNMENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_alreadyMarkedDoneAssignmentUnfilteredList_failure() {
        indexesToMarkDone.add(INDEX_FIRST_ASSIGNMENT);

        // Set assignment in filtered list in ProductiveNus as done
        Assignment firstAssignment = model.getFilteredAssignmentList().get(INDEX_FIRST_ASSIGNMENT.getZeroBased());
        Assignment firstAssignmentMarkedDone = new AssignmentBuilder(firstAssignment).withDoneStatusSet().build();
        model.setAssignment(firstAssignment, firstAssignmentMarkedDone);

        DoneCommand doneCommand = new DoneCommand(indexesToMarkDone);

        assertCommandFailure(doneCommand, model, DoneCommand.MESSAGE_ALREADY_MARKED_ASSIGNMENT_AS_DONE);
    }

    @Test
    public void execute_alreadyMarkedDoneAssignmentFilteredList_failure() {
        showAssignmentAtIndex(model, INDEX_FIRST_ASSIGNMENT);
        indexesToMarkDone.add(INDEX_FIRST_ASSIGNMENT);

        // Set assignment in filtered list in ProductiveNus as done
        Assignment assignmentInList = model.getProductiveNus().getAssignmentList()
                .get(INDEX_FIRST_ASSIGNMENT.getZeroBased());
        Assignment assignmentInListMarkedDone = new AssignmentBuilder(assignmentInList).withDoneStatusSet().build();
        model.setAssignment(assignmentInList, assignmentInListMarkedDone);
        model.updateFilteredAssignmentList(PREDICATE_SHOW_ALL_ASSIGNMENT);

        DoneCommand doneCommand = new DoneCommand(indexesToMarkDone);

        assertCommandFailure(doneCommand, model, DoneCommand.MESSAGE_ALREADY_MARKED_ASSIGNMENT_AS_DONE);
    }

    @Test
    public void equals() {
        List<Index> firstCommandIndexes = new ArrayList<>();
        firstCommandIndexes.add(INDEX_FIRST_ASSIGNMENT);
        firstCommandIndexes.add(INDEX_THIRD_ASSIGNMENT);

        List<Index> secondCommandIndexes = new ArrayList<>();
        secondCommandIndexes.add(INDEX_SECOND_ASSIGNMENT);

        DoneCommand markFirstCommandDone = new DoneCommand(firstCommandIndexes);
        DoneCommand markSecondCommandDone = new DoneCommand(secondCommandIndexes);

        // same object -> returns true
        assertTrue(markFirstCommandDone.equals(markFirstCommandDone));
        assertTrue(markSecondCommandDone.equals(markSecondCommandDone));

        // same values -> returns true
        DoneCommand markFirstCommandDoneCopy = new DoneCommand(firstCommandIndexes);
        assertTrue(markFirstCommandDone.equals(markFirstCommandDoneCopy));

        DoneCommand markSecondCommandDoneCopy = new DoneCommand(secondCommandIndexes);
        assertTrue(markSecondCommandDone.equals(markSecondCommandDoneCopy));

        // different types -> returns false
        assertFalse(markFirstCommandDone.equals(1));
        assertFalse(markSecondCommandDone.equals(1));

        // null -> returns false
        assertFalse(markFirstCommandDone.equals(null));
        assertFalse(markSecondCommandDone.equals(null));

        // different assignment -> returns false
        assertFalse(markFirstCommandDone.equals(markSecondCommandDone));
    }
}
