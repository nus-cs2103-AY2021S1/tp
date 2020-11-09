package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalIndexes.*;
import static seedu.address.testutil.TypicalProblems.getTypicalReportLibrary;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.problem.Problem;
import seedu.address.ui.Mode;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for
 * {@code DeleteProblemCommand}.
 */
public class DeleteProblemCommandTest {

    private Model model = new ModelManager(getTypicalReportLibrary(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredReportList_success() {
        Problem problemToDelete = model.getFilteredProblemList().get(INDEX_FIRST_REPORT.getZeroBased());
        DeleteProblemCommand deleteProblemCommand = new DeleteProblemCommand(INDEX_FIRST_REPORT);

        String expectedMessage = String.format(DeleteProblemCommand.MESSAGE_DELETE_PROBLEM_SUCCESS, problemToDelete);

        ModelManager expectedModel = new ModelManager(model.getLibrary(), new UserPrefs());
        expectedModel.deleteProblem(problemToDelete);
        assertCommandSuccess(deleteProblemCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredReportList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredProblemList().size() + 1);
        DeleteProblemCommand deleteProblemCommand = new DeleteProblemCommand(outOfBoundIndex);
        assertCommandFailure(deleteProblemCommand, model, Messages.MESSAGE_INVALID_PROBLEM_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showReportAtIndex(model, INDEX_FIRST_REPORT);

        Problem problemToDelete = model.getFilteredProblemList().get(INDEX_FIRST_REPORT.getZeroBased());
        DeleteProblemCommand deleteProblemCommand = new DeleteProblemCommand(INDEX_FIRST_BOOK);

        String expectedMessage = String.format(DeleteProblemCommand.MESSAGE_DELETE_PROBLEM_SUCCESS, problemToDelete);

        Model expectedModel = new ModelManager(model.getLibrary(), new UserPrefs());
        expectedModel.deleteProblem(problemToDelete);
        showNoReport(expectedModel);

        assertCommandSuccess(deleteProblemCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showReportAtIndex(model, INDEX_FIRST_REPORT);

        Index outOfBoundIndex = INDEX_SECOND_REPORT;
        // ensures that outOfBoundIndex is still in bounds of library list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getLibrary().getProblemList().size());

        DeleteProblemCommand deleteProblemCommand = new DeleteProblemCommand(outOfBoundIndex);

        assertCommandFailure(deleteProblemCommand, model, Messages.MESSAGE_INVALID_PROBLEM_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteProblemCommand deleteFirstCommand = new DeleteProblemCommand(INDEX_FIRST_REPORT);
        DeleteProblemCommand deleteSecondCommand = new DeleteProblemCommand(INDEX_SECOND_REPORT);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteProblemCommand deleteFirstCommandCopy = new DeleteProblemCommand(INDEX_FIRST_REPORT);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different book -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoReport(Model model) {
        model.updateFilteredProblemList(p -> false, Mode.NORMAL);

        assertTrue(model.getFilteredProblemList().isEmpty());
    }
}
