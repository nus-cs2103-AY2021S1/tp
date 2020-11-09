package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showJobAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_JOB;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_JOB;
import static seedu.address.testutil.TypicalJobs.getTypicalJobAddressBook;
import static seedu.address.testutil.TypicalPersons.getTypicalPersonAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.information.Job;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for
 * {@code DeleteJobCommand}.
 */
public class DeleteJobCommandTest {

    private Model model = new ModelManager(getTypicalPersonAddressBook(), getTypicalJobAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Job jobToDelete = model.getFilteredJobList().get(INDEX_FIRST_JOB.getZeroBased());
        DeleteJobCommand deleteJobCommand = new DeleteJobCommand(INDEX_FIRST_JOB);

        String expectedMessage = String.format(DeleteJobCommand.MESSAGE_DELETE_JOB_SUCCESS, jobToDelete);

        ModelManager expectedModel = new ModelManager(getTypicalPersonAddressBook(), model.getJobAddressBook(),
                new UserPrefs());
        expectedModel.deleteJob(jobToDelete);

        assertCommandSuccess(deleteJobCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredJobList().size() + 1);
        DeleteJobCommand deleteJobCommand = new DeleteJobCommand(outOfBoundIndex);

        assertCommandFailure(deleteJobCommand, model, Messages.MESSAGE_INVALID_JOB_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showJobAtIndex(model, INDEX_FIRST_JOB);

        Job jobToDelete = model.getFilteredJobList().get(INDEX_FIRST_JOB.getZeroBased());
        DeleteJobCommand deleteJobCommand = new DeleteJobCommand(INDEX_FIRST_JOB);

        String expectedMessage = String.format(DeleteJobCommand.MESSAGE_DELETE_JOB_SUCCESS, jobToDelete);

        Model expectedModel = new ModelManager(model.getPersonAddressBook(), model.getJobAddressBook(),
                new UserPrefs());
        expectedModel.deleteJob(jobToDelete);
        showNoJob(expectedModel);

        assertCommandSuccess(deleteJobCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showJobAtIndex(model, INDEX_FIRST_JOB);

        Index outOfBoundIndex = INDEX_SECOND_JOB;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getJobAddressBook().getJobList().size());

        DeleteJobCommand deleteJobCommand = new DeleteJobCommand(outOfBoundIndex);

        assertCommandFailure(deleteJobCommand, model, Messages.MESSAGE_INVALID_JOB_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteJobCommand deleteFirstCommand = new DeleteJobCommand(INDEX_FIRST_JOB);
        DeleteJobCommand deleteSecondCommand = new DeleteJobCommand(INDEX_SECOND_JOB);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteJobCommand deleteFirstCommandCopy = new DeleteJobCommand(INDEX_FIRST_JOB);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no job.
     */
    private void showNoJob(Model model) {
        model.updateFilteredJobList(p -> false);

        assertTrue(model.getFilteredJobList().isEmpty());
    }
}

